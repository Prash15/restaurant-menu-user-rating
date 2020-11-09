package com.hpe.casestudy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.hpe.casestudy.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long>{

	@Query(value = "SELECT * from menu order by menu_rating desc", nativeQuery = true)
	List<Menu> getMenuByRating();

}
