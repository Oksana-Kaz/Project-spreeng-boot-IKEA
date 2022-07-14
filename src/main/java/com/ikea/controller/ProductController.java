package com.ikea.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.ikea.model.Product;
import com.ikea.model.View;
import com.ikea.service.ProductService;

@RestController
@RequestMapping()
public class ProductController {

	@Autowired
	ProductService ps;

//	Get all Products
	@GetMapping("/")
	public List<Product> getAllProducts() {
		return ps.getAllProducts();
	}

//	Get Product by id
	@GetMapping("/id/{id}")
	public Optional<Product> getAllProductById(@PathVariable Integer id) {
		return ps.getProductById(id);
	}

//  Get Product by Category
	@GetMapping("/category/{category}")
	@JsonView(value = View.viewProduct.viewProductByCategory.class)
	public List<Product> findAllProductByCategory(@PathVariable String category) {
		return ps.findAllProductByCategory(category);
	}

//	Create Product  -- POST
	@PostMapping("/")
	public void saveProduct(@RequestBody Product product) {
		ps.saveProduct(product);
	}

//	Get product by code
	@GetMapping("/code/{code}")
	@JsonView(value = { View.viewProduct.viewProductAvailable.class })
	public List<Product> findByCode(@PathVariable String code) {
		return ps.findByCode(code);
	}

//	Retrieve product cost with tax  
	@GetMapping("/cost/{category}")
	public List<String> costProduct(@PathVariable String category) {
		return ps.retrieveCostProductByCategoryWithTax(category);
	}

//	Get product Highest rating with discount
	@GetMapping("/cost/rating/{rating}")
	public List<String> discountProduct(@PathVariable Integer rating) {
		return ps.retrieveProductByRatingWithDiscount();

	}

//	Update Product
	@PutMapping("/id/{id}")
	public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable Integer id) {
		try {
			Product existProduct = ps.getId(id);

			if (existProduct != null) {
				existProduct.setId(id);
				existProduct.setName(product.getName());
				existProduct.setCategory(product.getCategory());
				existProduct.setCode(product.getCode());
				existProduct.setPrice(product.getPrice());
				existProduct.setQuantity(product.getQuantity());
				existProduct.setInStock(false);
				existProduct.setRating(product.getRating());
				ps.saveProduct(existProduct);
			}

			return new ResponseEntity<>(HttpStatus.OK);

		} catch (NoSuchElementException e) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

//	Delete
	@DeleteMapping("/")
	public void delete() {
		ps.delete();
	}

}