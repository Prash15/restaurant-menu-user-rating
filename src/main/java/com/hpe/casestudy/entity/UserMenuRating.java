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
@Table(name = "user_menu_rating")
public class UserMenuRating {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
    @ManyToOne
    @JoinColumn(name = "user_id")
	private User user;
	
    @ManyToOne
    @JoinColumn(name = "menu_id")
	private Menu menu;
	
	@Column(name = "menu_rating")
	private Integer menuRating;
	
	public UserMenuRating() {
		
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

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Integer getMenuRating() {
		return menuRating;
	}

	public void setMenuRating(Integer menuRating) {
		this.menuRating = menuRating;
	}
}
