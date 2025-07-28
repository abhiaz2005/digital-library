package com.watsoo.borrow_service.dto;

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

}
