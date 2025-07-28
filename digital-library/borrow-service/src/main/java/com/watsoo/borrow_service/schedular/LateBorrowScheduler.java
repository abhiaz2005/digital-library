package com.watsoo.borrow_service.schedular;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.watsoo.borrow_service.entity.Borrowing;
import com.watsoo.borrow_service.entity.User;
import com.watsoo.borrow_service.enums.BorrowStatus;
import com.watsoo.borrow_service.repository.BorrowingRepository;
import com.watsoo.borrow_service.service.EmailService;

@Component
public class LateBorrowScheduler {
	@Autowired
	private BorrowingRepository borrowingRepository;

	@Autowired
	private EmailService emailService;


	@Scheduled(cron = "0 0 0 * * *")
	public void schedulaLateBorrow() {
		
		Date now = new Date();

		List<Borrowing> lateBorrows = borrowingRepository.findByDueDateBeforeAndStatus(now, BorrowStatus.BORROWED);

		lateBorrows.forEach(borrow -> borrow.setStatus(BorrowStatus.LATE));
		borrowingRepository.saveAll(lateBorrows);

		Set<User> usersToNotify = lateBorrows.stream().map(Borrowing::getUser).collect(Collectors.toSet());

		usersToNotify.forEach(user -> {
			String email = user.getEmail();
			String name = user.getName();

			emailService.sendEmail(email, "Late Book Return Notice", "Dear " + name
					+ ",\n\nYour borrowed book(s) are overdue. Please return them as soon as possible.\n\nThank you!");
		});
	}

}
