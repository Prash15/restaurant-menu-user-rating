package com.hpe.casestudy.entity;


import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "menu_name")
	private String menuName;
	
	@Column(name = "price", scale=2)
	private BigDecimal price;
	
	@Column(name = "menu_rating")
	private Integer menuRating;
	
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;
    
	@OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
	private Set<UserMenuRating> userMenuRating;
	
	public Menu() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getMenuRating() {
		return menuRating;
	}

	public void setMenuRating(Integer menuRating) {
		this.menuRating = menuRating;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Set<UserMenuRating> getUserMenuRating() {
		return userMenuRating;
	}

	public void setUserMenuRating(Set<UserMenuRating> userMenuRating) {
		this.userMenuRating = userMenuRating;
	}
	
}
