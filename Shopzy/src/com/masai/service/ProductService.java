package com.masai.service;

import java.util.Map;

import com.masai.entities.Product;
import com.masai.exceptions.ProductException;

public interface ProductService {
	
	public String addProduct(Product pro, Map<Integer, Product> products);

	public void viewAllProducts(Map<Integer, Product> products) throws ProductException;

	public void deleteProduct(int id, Map<Integer, Product> products) throws ProductException;

	public String updateProduct(int id, Product prod, Map<Integer, Product> products) throws ProductException;
}
