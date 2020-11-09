package com.hpe.casestudy.entity;

import java.math.BigDecimal;
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
@Table(name = "restaurant")
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "latitude", precision=6, scale=4)
	private BigDecimal latitude;
	
	@Column(name = "longitude", precision=6, scale=4)
	private BigDecimal longitude;
	
	@Column(name = "contact_number")
	private String contactNumber;
	
	@Column(name = "overall_rating")
	private Integer overallRating;
	
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	private Set<OperationTiming> operationTimings;
	
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	private Set<Menu> menu;
	
	public Restaurant() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Integer getOverallRating() {
		return overallRating;
	}

	public void setOverallRating(Integer overallRating) {
		this.overallRating = overallRating;
	}

	public Set<OperationTiming> getOperationTimings() {
		return operationTimings;
	}

	public void setOperationTimings(Set<OperationTiming> operationTimings) {
		this.operationTimings = operationTimings;
	}


	public Set<Menu> getMenu() {
		return menu;
	}

	public void setMenu(Set<Menu> menu) {
		this.menu = menu;
	}


	
	
	
}
