package com.watsoo.catalog_service.dto;

import com.watsoo.catalog_service.entity.Book;
import com.watsoo.catalog_service.entity.Category;

public class BookDto {
	private Long id;
	private String title;
	private String author;
	 private CategoryDto category;
	 private Long totalCopies;

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

	

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public Long getTotalCopies() {
		return totalCopies;
	}

	public void setTotalCopies(Long totalCopies) {
		this.totalCopies = totalCopies;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public BookDto(Long id, String title, String author, CategoryDto category, Long totalCopies) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.category = category;
		this.totalCopies = totalCopies;
	}

	public BookDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book convertToEntity(Category category2) {
		return new Book(id!=null?id:null ,
						title!=null?title:null, 
						author!=null?author.trim().toLowerCase():null, 
						category2!=null?category2:null,
						totalCopies!=null?totalCopies:null, null, null);
	}

//	Book book = new Book();
//	book.setAuthor(bookDto.getAuthor().trim().toLowerCase());
//	book.setCategory(category);
//	book.setTitle(bookDto.getTitle());
//	book.setTotalCopies(bookDto.getTotalCopies());
//	book.setCreatedAt(new Date());
}
