package com.watsoo.catalog_service.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.watsoo.catalog_service.dto.BookDto;
import com.watsoo.catalog_service.dto.Pagination;
import com.watsoo.catalog_service.dto.Response;
import com.watsoo.catalog_service.dto.SearchDto;
import com.watsoo.catalog_service.dto.UserDto;
import com.watsoo.catalog_service.entity.Book;
import com.watsoo.catalog_service.entity.Category;
import com.watsoo.catalog_service.enums.Role;
import com.watsoo.catalog_service.feignClient.BorrowFeignClient;
import com.watsoo.catalog_service.repository.BookRepository;
import com.watsoo.catalog_service.repository.CategoryRepository;
import com.watsoo.catalog_service.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private BorrowFeignClient borrowFeignClient ;

	@Override
	public Response<?> createBook(BookDto bookDto,Long userId) {
		try {
			if(userId == null) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "please provide admin user id.", null);
			}
			Response<UserDto> userResponse = borrowFeignClient.getUserById(userId);
			if(userResponse.getResponseCode() != HttpStatus.OK.value()) {
				return userResponse ;
			}
			if(!userResponse.getData().getRole().equals(Role.ADMIN)) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "You have no access to this api.", null);
			}
			Optional<Category> categoryOptional = categoryRepository.findById(bookDto.getCategory().getId());
			if (categoryOptional.isEmpty()) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Invalid Category ID.", null);
			}
			Category category = categoryOptional.get();

			if (bookDto.getId() == null) {
				Optional<Book> existingBook = bookRepository.bookExistsByTitleAuthorAndCategory(
						bookDto.getTitle().trim().toLowerCase(), bookDto.getAuthor().trim().toLowerCase(),
						bookDto.getCategory().getId());
				if (existingBook.isPresent()) {
					return new Response<>(HttpStatus.BAD_REQUEST.value(), "Book already exists.", null);
				}
				Book book = bookDto.convertToEntity(category);
				book.setCreatedAt(new Date());
				bookRepository.save(book);
				return new Response<>(HttpStatus.OK.value(), "Book registration successful.", null);
			} else {
				Optional<Book> exitingBook = bookRepository.findById(bookDto.getId());

				if (exitingBook == null || exitingBook.isEmpty()) {
					return new Response<>(HttpStatus.BAD_REQUEST.value(), "Provide Valid Book Id.", null);
				}
				Book book = exitingBook.get();

				if (bookDto.getTitle() != null && !bookDto.getTitle().isBlank()) {
					book.setTitle(bookDto.getTitle().trim());
				}

				if (bookDto.getAuthor() != null && !bookDto.getAuthor().isBlank()) {
					book.setAuthor(bookDto.getAuthor().trim());
				}

				if (bookDto.getTotalCopies() != null) {
					book.setTotalCopies(bookDto.getTotalCopies());
				}

				if (bookDto.getCategory() != null || bookDto.getCategory().getId() != null) {
					book.setCategory(category);
				}
				book.setUpdatedAt(new Date());

				bookRepository.save(book);
				return new Response<>(HttpStatus.OK.value(), "Book Update successful.", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Something went wrong", null);
		}
	}

	@Override
	public Response<?> getAllBooksPaginate(SearchDto searchDto) {
		try {
			if(searchDto.getPageSize() != null ) {
				if(searchDto.getPageSize() <= 0 ) {
					return new Response<>(HttpStatus.OK.value(), "Page-size must not be 0", null);
				}
			}
			Pageable pageable = PageRequest.of(searchDto.getPageNo(), searchDto.getPageSize(),
					Sort.by("id").descending());

			Page<Book> bookpage = bookRepository.findAll(searchDto, pageable);
			List<BookDto> userDtos = bookpage.getContent().stream().map(Book::convertEntityToDto)
					.collect(Collectors.toList());
			Pagination<List<?>> pagination = new Pagination<>();
			pagination.setData(userDtos);
			pagination.setTotalElements(bookpage.getTotalElements());
			pagination.setTotalPages(bookpage.getTotalPages());
			pagination.setNumberOfElements(bookpage.getNumberOfElements());
			Response<Object> response = new Response<>();
			response.setPaginationData(pagination);
			response.setResponseCode(HttpStatus.OK.value());

			return new Response<>(HttpStatus.OK.value(), "Data fetch succesfully", response);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Something went wrong", null);
		}
	}

	@Override
	public Response<?> getBookById(Long bookId) {
		try {
			if (bookId == null) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Provide Book Id", null);
			}
			Optional<Book> bookOptional = bookRepository.findById(bookId);
			if (!bookOptional.isPresent()) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Book not found", null);
			}
			BookDto bookDto = bookOptional.get().convertEntityToDto();
			return new Response<>(HttpStatus.OK.value(), "OK", bookDto);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Something went wrong", null);
		}
	}

}
