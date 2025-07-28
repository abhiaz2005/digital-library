package com.watsoo.catalog_service.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.watsoo.catalog_service.dto.CategoryDto;
import com.watsoo.catalog_service.dto.SearchDto;
import com.watsoo.catalog_service.entity.Book;
import com.watsoo.catalog_service.entity.Category;

import jakarta.persistence.criteria.Predicate;

public interface BookRepository extends JpaRepository<Book, Long> {
	@Query(value = "SELECT * FROM books where LOWER(title) = LOWER(?1) AND lower(author) = LOWER(?2) AND category_id = ?3", nativeQuery = true)
	Optional<Book> bookExistsByTitleAuthorAndCategory(String title, String author, Long categoryId);

	Page<Book> findAll(Specification<Book> spec, Pageable pageable);

	default Page<Book> findAll(SearchDto searchDTO, Pageable pageable) {
		return findAll(search(searchDTO), pageable);
	}

	static Specification<Book> search(SearchDto searchDTO) {
	    return (root, cq, cb) -> {
	        Predicate predicate = cb.conjunction();

	        if (searchDTO.getSearchField() != null && !searchDTO.getSearchField().trim().isEmpty()) {
	            String value = "%" + searchDTO.getSearchField().toLowerCase() + "%";

	            Predicate titlePredicate = cb.like(cb.lower(root.get("title")), value);
	            Predicate authorPredicate = cb.like(cb.lower(root.get("author")), value);
	            Predicate categoryPredicate = cb.like(cb.lower(root.get("category").get("name")), value);

	            predicate = cb.or(titlePredicate, authorPredicate, categoryPredicate);
	        }

	        cq.orderBy(cb.desc(root.get("id")));
	        return predicate;
	    };
	}

}
