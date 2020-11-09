package com.hpe.casestudy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_restaurant_rating")
public class UserRestaurantRating {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
    @ManyToOne
    @JoinColumn(name = "user_id")
	private User user;
	
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;
	
	@Column(name = "overall_rating")
	private Integer overallRating;
	
	public UserRestaurantRating() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Integer getOverallRating() {
		return overallRating;
	}

	public void setOverallRating(Integer overallRating) {
		this.overallRating = overallRating;
	}
	
}
