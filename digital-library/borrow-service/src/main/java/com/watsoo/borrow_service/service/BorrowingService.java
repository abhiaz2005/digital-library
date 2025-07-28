package com.watsoo.borrow_service.service;

import com.watsoo.borrow_service.dto.BorrowDto;
import com.watsoo.borrow_service.dto.Response;

public interface BorrowingService {

	Response<?>borrowBook(BorrowDto borrowDto);

	Response<?> returnBook(Long borrowId);

	Response<?> getAllBorrows(Long userId);
}
