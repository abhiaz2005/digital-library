package com.watsoo.catalog_service.entity;

import com.watsoo.catalog_service.dto.CategoryDto;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

	public Category() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public CategoryDto convertToDto() {
		return new CategoryDto(this.id != null ? this.id : null, this.name != null ? this.name : null);
	}
}
