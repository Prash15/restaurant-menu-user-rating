package com.hpe.casestudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hpe.casestudy.entity.UserRestaurantRating;

@Repository
public interface UserRestaurantRatingRepository extends JpaRepository<UserRestaurantRating, Long>{

	@Query(value = "SELECT AVG(u.overall_rating) from user_restaurant_rating u WHERE restaurant_id = ?1", nativeQuery = true)
	int getAverageUserRestaurantRating(Long restaurantId);

}
