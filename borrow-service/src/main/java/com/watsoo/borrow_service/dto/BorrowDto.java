package com.watsoo.borrow_service.dto;

import java.util.Date;

import com.watsoo.borrow_service.enums.BorrowStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class BorrowDto {
	private Long id ;
	private Long userId;
	private Long bookId;
	private Date borrowDate;
	private Date dueDate;
	private Date returnDate;
	private String status;
	private BorrowStatus borrowStatus;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Date getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BorrowStatus getBorrowStatus() {
		return borrowStatus;
	}

	public void setBorrowStatus(BorrowStatus borrowStatus) {
		this.borrowStatus = borrowStatus;
	}

	public BorrowDto(Long userId, Long bookId, Date borrowDate, Date dueDate, Date returnDate, String status) {
		super();
		this.userId = userId;
		this.bookId = bookId;
		this.borrowDate = borrowDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
		this.status = status;
	}

	

	

	public BorrowDto(Long id, Long userId, Long bookId, Date borrowDate, Date dueDate, Date returnDate,
			BorrowStatus borrowStatus) {
		super();
		this.id = id;
		this.userId = userId;
		this.bookId = bookId;
		this.borrowDate = borrowDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
		this.borrowStatus = borrowStatus;
	}

	public BorrowDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
