package com.watsoo.borrow_service.entity;

import java.util.Date;

import com.watsoo.borrow_service.dto.BorrowDto;
import com.watsoo.borrow_service.enums.BorrowStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "borrowings")
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Column(name = "borrow_date")
    private Date borrowDate;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "return_date")
    private Date returnDate;

    @Enumerated(EnumType.STRING)
    private BorrowStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public BorrowStatus getStatus() {
		return status;
	}

	public void setStatus(BorrowStatus status) {
		this.status = status;
	}

	public Borrowing(Long id, User user, Long bookId, Date borrowDate, Date dueDate, Date returnDate,
			BorrowStatus status) {
		super();
		this.id = id;
		this.user = user;
		this.bookId = bookId;
		this.borrowDate = borrowDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
		this.status = status;
	}

	public Borrowing() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	public BorrowDto convertToDto() {
	    return new BorrowDto(
	        this.id != null ? this.id : null,
	        this.user != null ? this.user.getId() : null,
	        this.bookId != null ? this.bookId : null,
	        this.borrowDate != null ? this.borrowDate : null,
	        this.dueDate != null ? this.dueDate : null,
	        this.returnDate != null ? this.returnDate : null,
	        this.status != null ? this.status : null
	    );
	}


    
}
    