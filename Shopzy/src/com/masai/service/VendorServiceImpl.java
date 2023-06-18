package com.masai.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


import com.masai.entities.Vendor;
import com.masai.exceptions.DuplicateDataException;
import com.masai.exceptions.InvalidDetailsException;
import com.masai.exceptions.ProductException;

public class VendorServiceImpl implements VendorService{

	@Override
	public boolean login(String email, String password, Map<String, Vendor> vendor) throws InvalidDetailsException {
		// TODO Auto-generated method stub
		if (vendor.containsKey(email) ) {
			
			if(vendor.get(email).getPassword().equals(password)) {
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
	public void signUp(Vendor ven, Map<String, Vendor> vendor) throws DuplicateDataException {
		// TODO Auto-generated method stub
		if (vendor.containsKey(ven.getEmail())) {
			throw new DuplicateDataException("Customer already exists , please login");
		} else {

			vendor.put(ven.getEmail(), ven);

		}
	}

	@Override
	public Vendor viewVendorDetails(String email, Map<String, Vendor> vendor) {
		// TODO Auto-generated method stub
		if (vendor.containsKey(email)) {

			return vendor.get(email);

		}

		
		return null;
	}

	@Override
	public List<Vendor> viewAllVendors(Map<String, Vendor> vendor) throws ProductException {
		// TODO Auto-generated method stub
		List<Vendor> list = null;

		if (vendor != null && vendor.size() > 0) {
			Collection<Vendor> coll = vendor.values();
			list = new ArrayList<>(coll);
		} else {
			throw new ProductException("Customer list is empty");
		}

		return list;
	}
		
		
}
