package com.hpe.casestudy.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<UserMenuRating> userMenuRatings;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<UserRestaurantRating> userRestaurantRatings;
	
	public User() {
		
	}
	
	public User(long id, String firstName, String lastName, String email, Set<UserMenuRating> userMenuRatings,
			Set<UserRestaurantRating> userRestaurantRatings) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userMenuRatings = userMenuRatings;
		this.userRestaurantRatings = userRestaurantRatings;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Set<UserMenuRating> getUserMenuRatings() {
		return userMenuRatings;
	}

	public void setUserMenuRatings(Set<UserMenuRating> userMenuRatings) {
		this.userMenuRatings = userMenuRatings;
	}

	public Set<UserRestaurantRating> getUserRestaurantRatings() {
		return userRestaurantRatings;
	}

	public void setUserRestaurantRatings(Set<UserRestaurantRating> userRestaurantRatings) {
		this.userRestaurantRatings = userRestaurantRatings;
	}
	
	
}
