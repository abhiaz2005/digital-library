package com.watsoo.catalog_service.dto;

public class SearchDto {

	private Integer pageNo = 0;
	private Integer pageSize = 10;


	private String searchField;
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public SearchDto(Integer pageNo, Integer pageSize, String searchField) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}
	public SearchDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getSearchField() {
		return searchField;
	}
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	
	
}
