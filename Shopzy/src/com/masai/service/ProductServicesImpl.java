package com.masai.service;

import java.util.Map;

import com.masai.entities.Product;
import com.masai.exceptions.ProductException;

public class ProductServicesImpl implements ProductService{

	@Override
	public String addProduct(Product pro, Map<Integer, Product> products) {
		// TODO Auto-generated method stub
		products.put(pro.getId(), pro);

		return "Product added successfully";
	}

	@Override
	public void viewAllProducts(Map<Integer, Product> products) throws ProductException {
		// TODO Auto-generated method stub
		if (products != null && products.size() > 0) {
			for (Map.Entry<Integer, Product> me : products.entrySet()) {
				System.out.println(me.getValue());
			}

		} else {
			throw new ProductException("Product List is empty");
		}
	}

	@Override
	public void deleteProduct(int id, Map<Integer, Product> products) throws ProductException {
		// TODO Auto-generated method stub
		if (products != null && products.size() > 0) {

			if (products.containsKey(id)) {
				products.remove(id);
				System.out.println("product deleted successfully");

			} else {
				throw new ProductException("Product not found");
			}

		} else {
			throw new ProductException("Product list is empty");
		}

		
	}

	@Override
	public String updateProduct(int id, Product prod, Map<Integer, Product> products) throws ProductException {
		// TODO Auto-generated method stub
		if (products != null && products.size() > 0) {

			if (products.containsKey(id)) {
				products.put(id, prod);
				return "Product has successfully updated";
			} else {
				throw new ProductException("Product not found");
			}

		} else {
			throw new ProductException("Product list is empty");
		}

		
	}
	
		
}
