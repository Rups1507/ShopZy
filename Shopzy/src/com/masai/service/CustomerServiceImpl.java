package com.masai.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.masai.entities.Customer;
import com.masai.entities.Product;
import com.masai.entities.Transaction;
import com.masai.exceptions.DuplicateDataException;
import com.masai.exceptions.InvalidDetailsException;
import com.masai.exceptions.ProductException;

public class CustomerServiceImpl implements CustomerService{

	@Override
	public boolean login(String email, String password, Map<String, Customer> customers)
			throws InvalidDetailsException {
		// TODO Auto-generated method stub
			if (customers.containsKey(email) ) {
					
					if(customers.get(email).getPassword().equals(password)) {
						return true;
					}
					else {
						throw new InvalidDetailsException("Invalid Credentials");
					}
					
				} else {
					throw new InvalidDetailsException("you have not sign up yet, please signup");
				}
	}

	@Override
	public void signUp(Customer cus, Map<String, Customer> customers) throws DuplicateDataException {
		// TODO Auto-generated method stub
		if (customers.containsKey(cus.getEmail())) {
			throw new DuplicateDataException("Customer already exists , please login");
		} else {

			customers.put(cus.getEmail(), cus);

		}
	}

	@Override
	public boolean buyProduct(int id, int qty, String email, Map<Integer, Product> products,
			Map<String, Customer> customers, List<Transaction> transactions)
			throws InvalidDetailsException, ProductException {
		// TODO Auto-generated method stub
		
			if (products.size() == 0)
				throw new ProductException("Product list is empty");

			if (products.containsKey(id)) {

				Product prod = products.get(id);

				if (prod.getQty() >= qty) {

					Customer cus = customers.get(email);

					double buyingPrice = qty * prod.getPrice();

					if (cus.getWalletBalance() >= buyingPrice) {
						cus.setWalletBalance(cus.getWalletBalance() - buyingPrice);

						prod.setQty(prod.getQty() - qty);

						products.put(id, prod);

						Transaction tr = new Transaction(cus.getUsername(), email, id,prod.getName(), qty, prod.getPrice(),
								prod.getPrice() * qty, LocalDate.now());

						transactions.add(tr);

					} else {
						throw new InvalidDetailsException("wallet balance is not sufficient");
					}

				} else {
					throw new InvalidDetailsException("product quantity is not suffiecient");
				}

			} else {
				throw new InvalidDetailsException("product not available with id: " + id);
			}
		return false;
	}

	@Override
	public boolean addMoneyToWallet(double amount, String email, Map<String, Customer> customers) {
		// TODO Auto-generated method stub
		Customer cus = customers.get(email);

		cus.setWalletBalance(cus.getWalletBalance() + amount);

		customers.put(email, cus);
		return false;
	}

	@Override
	public double viewWalletBalance(String email, Map<String, Customer> customers) {
		// TODO Auto-generated method stub
		Customer cus = customers.get(email);

		return cus.getWalletBalance();
	}

	@Override
	public Customer viewCustomerDetails(String email, Map<String, Customer> customers) {
		// TODO Auto-generated method stub
		if (customers.containsKey(email)) {

			return customers.get(email);

		}

		return null;
	}

	@Override
	public List<Customer> viewAllCustomers(Map<String, Customer> customers) throws ProductException {
		// TODO Auto-generated method stub
		List<Customer> list = null;

		if (customers != null && customers.size() > 0) {
			Collection<Customer> coll = customers.values();
			list = new ArrayList<>(coll);
		} else {
			throw new ProductException("Customer list is empty");
		}

		return list;
	}
		
	
}
