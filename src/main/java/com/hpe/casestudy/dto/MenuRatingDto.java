package com.hpe.casestudy.dto;


public class MenuRatingDto {
	
	private Long userId;
	private Long menuId;
	private Integer menuRating;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	public Integer getMenuRating() {
		return menuRating;
	}
	public void setMenuRating(Integer menuRating) {
		this.menuRating = menuRating;
	}
	
	
	
}
