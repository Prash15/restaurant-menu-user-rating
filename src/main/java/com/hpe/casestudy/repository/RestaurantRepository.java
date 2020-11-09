package com.hpe.casestudy.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.hpe.casestudy.entity.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

	@Query(value = "SELECT * from restaurant order by overall_rating desc", 
			nativeQuery = true)
	List<Restaurant> getRestaurantByRating();
  
	@Query(value = "SELECT * from restaurant INNER JOIN menu where menu_name like'?1%'", 
			nativeQuery = true)
	List<Restaurant> getRestaurantBasedOnMenuName(String searchValue);
	
	List<Restaurant> findByNameStartsWith(String searchValue);

	List<Restaurant> findByAddressStartsWith(String searchValue);

	@Procedure("FIND_RESTAURANT_NEARBY")
	List<Restaurant> getRestaurantByGeoLocation(String searchValue);
	// try to get a search value of current geo loactions latitude,longtitude
	/*CREATE PROCEDURE FIND_RESTAURANT_NEARBY(IN latitude DECIMAL, IN longitude DECIMAL)
	BEGIN 
	DECLARE @GEO1 GEOGRAPHY, @LAT VARCHAR(10), @LONG VARCHAR(10);

	SET @LAT=latitude;
	SET @LONG=longitude;

	SET @geo1= geography::Point(@LAT, @LONG, 4326);

	SELECT name,address,LEFT(CONVERT(VARCHAR,(@geo1.STDistance(geography::Point(ISNULL(LAT,0),
	ISNULL(LONG,0), 4326))/1000)),5)+' Km' as DISTANCE FROM restaurant
	END;*/

	@Query(value = "SELECT * from restaurant INNER JOIN operation_timing where open_hours like'?1%'", 
			nativeQuery = true)
	List<Restaurant> getRestaurantByOperatingHours(String searchValue);

	

}
