package com.hpe.casestudy.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hpe.casestudy.dto.RestaurantByRatingDto;
import com.hpe.casestudy.entity.Restaurant;
import com.hpe.casestudy.entity.User;
import com.hpe.casestudy.exception.ResourceNotFoundException;
import com.hpe.casestudy.repository.RestaurantRepository;
import com.hpe.casestudy.service.RestaurantService;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {

	// add new restaurant
	// search menu rating
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private RestaurantService restaurantService;

	// get all restaurants
	@GetMapping
	public List<Restaurant> getAllRestaurants() {
		return this.restaurantRepository.findAll();
	}

	// get restaurant by id
	@GetMapping("/{id}")
	public Restaurant getRestaurantById(@PathVariable (value = "id") long restaurantId) {
		return this.restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + restaurantId));
	}

	// create restaurant
	@PostMapping(path="/register", consumes = "application/json")
	public String createRestaurant(@RequestBody Restaurant restaurant) {		
		Restaurant newRestaurant =restaurantService.createRestaurantDetails(restaurant);		
		return "Restaurant Registered Successfully";
	}
	
	// update restaurant
	@PutMapping("/{id}")
	public Restaurant updateRestaurant(@RequestBody User user, @PathVariable ("id") long restaurantId) {
		Restaurant existingRestaurant = this.restaurantRepository.findById(restaurantId)
			.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + restaurantId));
		/*existingRestaurant.setFirstName(user.getFirstName());
		existingRestaurant.setLastName(user.getLastName());
		existingRestaurant.setEmail(user.getEmail());*/
		 return this.restaurantRepository.save(existingRestaurant);
	}
	
	// delete restaurant by id
	@DeleteMapping("/{id}")
	public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable ("id") long restaurantId){
		 Restaurant existingRestaurant = this.restaurantRepository.findById(restaurantId)
					.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + restaurantId));
		 this.restaurantRepository.delete(existingRestaurant);
		 return ResponseEntity.ok().build();
	}
	
	@GetMapping("/bySearchType")
	@ResponseBody
	public List<RestaurantByRatingDto> getRestaurantBySearchType
	(@RequestParam String seacrchType,@RequestParam String searchValue) {
		List<Restaurant> restaurantList= new ArrayList<Restaurant>();
		
		if (seacrchType.equals("Menu Name")){
			restaurantList=this.restaurantRepository.getRestaurantBasedOnMenuName(searchValue);
		} else if (seacrchType.equals("Restaurant Name")) {
			restaurantList=this.restaurantRepository.findByNameStartsWith(searchValue);
		} else if (seacrchType.equals("Restaurant Address")){
			restaurantList=this.restaurantRepository.findByAddressStartsWith(searchValue);
		} else if (seacrchType.equals("Geo Location")) {
			//restaurantList=this.restaurantRepository.getRestaurantByGeoLocation(searchValue);
		} else if (seacrchType.equals("Operating Timings")){
			//restaurantList=this.restaurantRepository.getRestaurantByOperatingHours(searchValue);
		}		
		List<RestaurantByRatingDto> result = restaurantList.stream().map(temp -> {
			RestaurantByRatingDto restaurantByRatingDto = new RestaurantByRatingDto();			
			restaurantByRatingDto.setRestaurantName(temp.getName());
			restaurantByRatingDto.setAddress(temp.getAddress());
			restaurantByRatingDto.setOverallRating(temp.getOverallRating());
            return restaurantByRatingDto;
        }).collect(Collectors.toList());
		return result;
	}
	
	@GetMapping("/byRating")
	public List<RestaurantByRatingDto> getRestaurantByRating() {
		List<Restaurant> restaurantList=this.restaurantRepository.getRestaurantByRating();		
		List<RestaurantByRatingDto> result = restaurantList.stream().map(temp -> {
			RestaurantByRatingDto restaurantByRatingDto = new RestaurantByRatingDto();			
			restaurantByRatingDto.setRestaurantName(temp.getName());
			restaurantByRatingDto.setAddress(temp.getAddress());
			restaurantByRatingDto.setOverallRating(temp.getOverallRating());
            return restaurantByRatingDto;
        }).collect(Collectors.toList());
		return result;
	}
}
