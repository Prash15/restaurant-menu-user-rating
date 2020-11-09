package com.hpe.casestudy.entity;


import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "operation_timing")
public class OperationTiming {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "day")
	private String day;
	
	@Column(name = "open_hours")
	private Time openHours;
	
	@Column(name = "close_hours")
	private Time closeHours;
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;
	
	public OperationTiming() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Time getOpenHours() {
		return openHours;
	}

	public void setOpenHours(Time openHours) {
		this.openHours = openHours;
	}

	public Time getCloseHours() {
		return closeHours;
	}

	public void setCloseHours(Time closeHours) {
		this.closeHours = closeHours;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	
}
