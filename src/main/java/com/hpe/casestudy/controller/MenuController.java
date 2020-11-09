package com.hpe.casestudy.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hpe.casestudy.dto.MenuByRatingDto;
import com.hpe.casestudy.dto.MenuDto;
import com.hpe.casestudy.entity.Menu;
import com.hpe.casestudy.entity.Restaurant;
import com.hpe.casestudy.entity.UserMenuRating;
import com.hpe.casestudy.exception.ResourceNotFoundException;
import com.hpe.casestudy.repository.MenuRepository;
import com.hpe.casestudy.repository.RestaurantRepository;

@RestController
@RequestMapping("/api/v1/menu")
public class MenuController {

	// add new menu
	// search menu rating
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;

	// get all menu
	@GetMapping
	public List<Menu> getAllMenu() {
		return this.menuRepository.findAll();
	}

	// get menu by id
	@GetMapping("/{id}")
	public Menu getMenuById(@PathVariable (value = "id") long menuId) {
		return this.menuRepository.findById(menuId)
				.orElseThrow(() -> new ResourceNotFoundException("Menu not found with id :" + menuId));
	}

	// create menu
	@PostMapping(path="/publish", consumes = "application/json")
	public String createMenu(@RequestBody MenuDto menuDto) {
		Restaurant publishingRestaurant = restaurantRepository.getOne(menuDto.getRestaurantId());
		Menu menu = new Menu();
		menu.setMenuName(menuDto.getMenuName());
		menu.setPrice(menuDto.getPrice());
		menu.setMenuRating(new Integer(0));
		menu.setRestaurant(publishingRestaurant);
		this.menuRepository.save(menu);
		return "Menu Added Successfully";
	}
	
	// update user
	@PutMapping("/{id}")
	public Menu updateMenu(@RequestBody Menu menu, @PathVariable ("id") long menuId) {
		Menu existingMenu = this.menuRepository.findById(menuId)
			.orElseThrow(() -> new ResourceNotFoundException("Menu not found with id :" + menuId));
		/*existingMenu.setFirstName(menu.getFirstName());
		existingMenu.setLastName(menu.getLastName());
		existingMenu.setEmail(menu.getEmail());*/
		return this.menuRepository.save(existingMenu);
	}
	
	// delete user by id
	@DeleteMapping("/{id}")
	public ResponseEntity<Menu> deleteMenu(@PathVariable ("id") long menuId){
		 Menu existingMenu = this.menuRepository.findById(menuId)
					.orElseThrow(() -> new ResourceNotFoundException("Menu not found with id :" + menuId));
		 this.menuRepository.delete(existingMenu);
		 return ResponseEntity.ok().build();
	}
	
	@GetMapping("/byRating")
	public List<MenuByRatingDto> getMenuByRating() {
		List<Menu> menuList=this.menuRepository.getMenuByRating();		
		List<MenuByRatingDto> result = menuList.stream().map(temp -> {
			MenuByRatingDto menuByRatingDto = new MenuByRatingDto();
			menuByRatingDto.setMenuName(temp.getMenuName());
			menuByRatingDto.setPrice(temp.getPrice());
			menuByRatingDto.setMenu_rating(temp.getMenuRating());
            return menuByRatingDto;
        }).collect(Collectors.toList());
		return result;
	}
}
