package com.watsoo.catalog_service.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.watsoo.catalog_service.dto.CategoryDto;
import com.watsoo.catalog_service.dto.Response;
import com.watsoo.catalog_service.dto.UserDto;
import com.watsoo.catalog_service.entity.Category;
import com.watsoo.catalog_service.enums.Role;
import com.watsoo.catalog_service.feignClient.BorrowFeignClient;
import com.watsoo.catalog_service.repository.CategoryRepository;
import com.watsoo.catalog_service.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private BorrowFeignClient borrowFeignClient ;

	@Override
	public Response<?> createCategory(CategoryDto categoryDto,Long userId) {
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
		
		if (categoryDto == null || categoryDto.getName() == null || categoryDto.getName().trim().isEmpty()) {
			return new Response<>(HttpStatus.BAD_REQUEST.value(), "Category name is required", null);
		}

		if (categoryDto.getId() == null) {
			Optional<Category> existing = categoryRepository.findByNameIgnoreCase(categoryDto.getName().trim());
			if (existing.isPresent()) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Category already exists", null);
			}

			Category newCategory = new Category();
			newCategory.setName(categoryDto.getName());
			categoryRepository.save(newCategory);

			return new Response<>(HttpStatus.OK.value(), "Category created successfully", null);

		} else {
			Optional<Category> existing = categoryRepository.findById(categoryDto.getId());
			if (existing.isEmpty()) {
				return new Response<>(HttpStatus.BAD_REQUEST.value(), "Invalid Category ID", null);
			}
			Category category = existing.get();
			category.setName(categoryDto.getName());
			categoryRepository.save(category);
			return new Response<>(HttpStatus.OK.value(), "Category updated successfully", null);
		}
	}

}
