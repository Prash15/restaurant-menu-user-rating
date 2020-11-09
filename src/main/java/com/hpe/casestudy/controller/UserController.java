package com.hpe.casestudy.controller;

import java.util.List;

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

import com.hpe.casestudy.dto.MenuRatingDto;
import com.hpe.casestudy.dto.RestaurantRatingDto;
import com.hpe.casestudy.entity.Menu;
import com.hpe.casestudy.entity.Restaurant;
import com.hpe.casestudy.entity.User;
import com.hpe.casestudy.entity.UserMenuRating;
import com.hpe.casestudy.entity.UserRestaurantRating;
import com.hpe.casestudy.exception.ResourceNotFoundException;
import com.hpe.casestudy.repository.MenuRepository;
import com.hpe.casestudy.repository.RestaurantRepository;
import com.hpe.casestudy.repository.UserMenuRatingRepository;
import com.hpe.casestudy.repository.UserRepository;
import com.hpe.casestudy.repository.UserRestaurantRatingRepository;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	// add rating
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private UserMenuRatingRepository userMenuRatingRepository;
	
	@Autowired
	private UserRestaurantRatingRepository userRestaurantRatingRepository;

	// get all users
	@GetMapping
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}

	// get user by id
	@GetMapping("/{id}")
	public User getUserById(@PathVariable (value = "id") long userId) {
		return this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
	}

	// create user
	@PostMapping
	public User createUser(@RequestBody User user) {
		return this.userRepository.save(user);
	}
	
	// update user
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user, @PathVariable ("id") long userId) {
		 User existingUser = this.userRepository.findById(userId)
			.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
		 existingUser.setFirstName(user.getFirstName());
		 existingUser.setLastName(user.getLastName());
		 existingUser.setEmail(user.getEmail());
		 return this.userRepository.save(existingUser);
	}
	
	// delete user by id
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable ("id") long userId){
		 User existingUser = this.userRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
		 this.userRepository.delete(existingUser);
		 return ResponseEntity.ok().build();
	}
	
	
	// create user
	@PostMapping(path="/menuRating", consumes = "application/json")
	public String createUserMenuRating(@RequestBody MenuRatingDto menuRatingDto) {
		UserMenuRating userMenuRating = new UserMenuRating();
		Menu selectedMenu = menuRepository.getOne(menuRatingDto.getMenuId());
		userMenuRating.setMenu(selectedMenu);
		userMenuRating.setUser(userRepository.getOne(menuRatingDto.getUserId()));
		userMenuRating.setMenuRating(menuRatingDto.getMenuRating());
		this.userMenuRatingRepository.save(userMenuRating);
		
		int avgMenuRating=userMenuRatingRepository.getAverageUserMenuRating(menuRatingDto.getMenuId());
		selectedMenu.setMenuRating(avgMenuRating);
		this.menuRepository.save(selectedMenu);
		return "User Rating for Menu Added Successfully";
	}
	
	// create user
	@PostMapping(path="/restaurantRating", consumes = "application/json")
	public String createUserRestaurantRating(@RequestBody RestaurantRatingDto restaurantRatingDto) {
		UserRestaurantRating userRestaurantRating = new UserRestaurantRating();		
		Restaurant selectedRestaurant = restaurantRepository.getOne(restaurantRatingDto.getRestaurantId());		
		userRestaurantRating.setRestaurant(selectedRestaurant);
		userRestaurantRating.setUser(userRepository.getOne(restaurantRatingDto.getUserId()));
		userRestaurantRating.setOverallRating(restaurantRatingDto.getOverallRating());
		this.userRestaurantRatingRepository.save(userRestaurantRating);
		
		int avgRestaurantRating=userRestaurantRatingRepository.
				getAverageUserRestaurantRating(restaurantRatingDto.getRestaurantId());
		selectedRestaurant.setOverallRating(avgRestaurantRating);
		this.restaurantRepository.save(selectedRestaurant);
		return "User Rating for Restaurant Added Successfully";
	}
}
