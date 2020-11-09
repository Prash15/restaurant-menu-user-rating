package com.hpe.casestudy.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hpe.casestudy.entity.Menu;
import com.hpe.casestudy.entity.OperationTiming;
import com.hpe.casestudy.entity.Restaurant;
import com.hpe.casestudy.entity.User;
import com.hpe.casestudy.exception.ResourceNotFoundException;
import com.hpe.casestudy.repository.MenuRepository;
import com.hpe.casestudy.repository.OperationTimingRepository;
import com.hpe.casestudy.repository.RestaurantRepository;

@Service
public class RestaurantService {

	// add new restaurant
	// search menu rating
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private OperationTimingRepository operationTimingRepository;
	
	@Autowired
	private MenuRepository menuRepository;

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
	public Restaurant createRestaurantDetails(Restaurant restaurant) {
		restaurant.setOverallRating(new Integer(0));
		Restaurant addedRestaurant =restaurantRepository.save(restaurant);
		Set<OperationTiming> operationTimingsSet = restaurant.getOperationTimings();
		Set<Menu> menuSet = restaurant.getMenu();
		for (OperationTiming operationTiming : operationTimingsSet) {
			operationTiming.setRestaurant(addedRestaurant);
			operationTimingRepository.save(operationTiming);
		}
		for (Menu menu : menuSet){
			menu.setRestaurant(addedRestaurant);
			menuRepository.save(menu);
		}
		return addedRestaurant;
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
}
