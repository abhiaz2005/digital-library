package com.watsoo.catalog_service.entity;

import java.util.Date;

import com.watsoo.catalog_service.dto.BookDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "author")
	private String author;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@Column(name = "total_copies")
	private Long totalCopies;

	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;

	public BookDto convertEntityToDto() {
		return new BookDto(this.id != null ? this.id : null, this.title != null ? this.title : null,
				this.author != null ? this.author : null, this.category != null ? this.category.convertToDto() : null,
				this.totalCopies != null ? this.totalCopies : null);
	}

	public Book(Long id, String title, String author, Category category, Long totalCopies, Date createdAt,
			Date updatedAt) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.category = category;
		this.totalCopies = totalCopies;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getTotalCopies() {
		return totalCopies;
	}

	public void setTotalCopies(Long totalCopies) {
		this.totalCopies = totalCopies;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
