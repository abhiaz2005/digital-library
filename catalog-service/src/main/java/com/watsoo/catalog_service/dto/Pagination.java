package com.watsoo.catalog_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Pagination<T> {

	private int totalPages;
	private int numberOfElements;
	private long totalElements;
	private T data;

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}


	 public Pagination(int totalPages, int numberOfElements, long totalElements, T data) {
	        this.totalPages = totalPages;
	        this.numberOfElements = numberOfElements;
	        this.totalElements = totalElements;
	        this.data = data;
	    }

	public Pagination() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	 
}
