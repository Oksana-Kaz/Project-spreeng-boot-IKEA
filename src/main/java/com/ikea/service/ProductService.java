package com.ikea.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikea.model.Product;
import com.ikea.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

	private static final String costTemplate = "Price your %s  particular name  %s is %.2f $. Item costs with tax %.2f $ is %.2f$ . ";
	private static final String dicsountTemplate = "Only one week we give discount  on all product  %s . Your price with discount %.2f $ is %.2f $";

	private final double discount = 0.1;
	private final double tax = 0.13;

	@Autowired
	ProductRepository pr;

	// Get all products
	public List<Product> getAllProducts() {
		return pr.findAll();
	}

	// Get product for Id
	public Optional<Product> getProductById(Integer id) {
		return pr.findById(id);

	}

//	Get product by category
	public List<Product> findAllProductByCategory(String category) {
		return pr.findByCategory(category);

	}

//	Create product
	public void saveProduct(Product product) {
		pr.save(product);
	}

//	Get product by code
	public List<Product> findByCode(String code) {
		return pr.fndByCode(code);
	}

//	Get cost of product with tax by category

	private String computeTaxAndFormat(Product product) {
		double tax = product.getPrice() * this.tax;
		return String.format(costTemplate, product.getCategory(), product.getName(), product.getPrice(), tax,
				product.getPrice() + tax);
	}

	public List<String> retrieveCostProductByCategoryWithTax(String category) {

		return pr.findByCategory(category).stream().filter(product -> product.getCategory().equalsIgnoreCase(category))
				.map(this::computeTaxAndFormat).collect(Collectors.toList());
	}

//	Get product Highest rating with discount

	public List<String> retrieveProductByRatingWithDiscount() {

		return pr.getMaxRating().stream().filter(product -> product.getRating() == 5).map(this::computeDiscAndFormat)
				.collect(Collectors.toList());
	}

	private String computeDiscAndFormat(Product product) {
		double discount = product.getPrice() * this.discount;
		return String.format(dicsountTemplate, product.getName(), discount, product.getPrice() - discount);
	}

//	Get product
	public Product getId(Integer id) {
		return pr.findById(id).get();
	}

//	Update Product
	public void updateProduct(Product product) {
		pr.save(product);

	}

//	delete
	public void delete() {
		pr.deleteByRating();
	}

}
