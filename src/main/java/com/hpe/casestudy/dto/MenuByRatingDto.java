package com.hpe.casestudy.dto;


import java.math.BigDecimal;


public class MenuByRatingDto {
	
	private String menuName;
	private BigDecimal price;
	private Integer menu_rating;
	
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
	public Integer getMenu_rating() {
		return menu_rating;
	}
	public void setMenu_rating(Integer menu_rating) {
		this.menu_rating = menu_rating;
	}
	
}
