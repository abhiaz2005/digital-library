package com.watsoo.borrow_service.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.watsoo.borrow_service.entity.Borrowing;
import com.watsoo.borrow_service.enums.BorrowStatus;
@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {

	int countByBookIdAndStatus(Long id, BorrowStatus borrowed);

	Optional<Borrowing> findByUserIdAndBookIdAndStatus(Long userId, Long bookId, BorrowStatus borrowed);

	boolean existsByUserIdAndStatus(Long userId, BorrowStatus borrowed);

	List<Borrowing> findByDueDateBeforeAndStatus(Date now, BorrowStatus borrowed);

	Optional<Borrowing> findByIdAndStatus(Long borrowId, BorrowStatus borrowed);
	
	@Query(value = "select * from borrowings where status in (?1)", nativeQuery = true)
	List<Borrowing> findByStatusIn(List<String> statuses);


}
