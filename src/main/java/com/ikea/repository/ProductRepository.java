package com.ikea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ikea.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	List<Product> findByname(String name);

	@Query("select p from Product p where category LIKE %?1%")
	List<Product> findByCategory(String category);

	@Query("select p from Product p where code LIKE %?1%  and inStock is true and quantity > 0 ")
	List<Product> fndByCode(String code);

	@Query("select p from Product p where rating = 5")
	List<Product> getMaxRating();

	@Modifying
	@Query("delete from Product p where rating = 1")
	void deleteByRating();
}
	
