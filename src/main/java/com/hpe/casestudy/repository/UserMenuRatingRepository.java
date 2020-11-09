package com.hpe.casestudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hpe.casestudy.entity.UserMenuRating;

@Repository
public interface UserMenuRatingRepository extends JpaRepository<UserMenuRating, Long>{

    @Query(value = "SELECT AVG(u.menu_rating) from user_menu_rating u WHERE menu_id = ?1", nativeQuery = true)
    int getAverageUserMenuRating(Long menu_id);
}
