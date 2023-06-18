package com.masai.entities;

import java.io.Serializable;

public class Vendor extends User implements Serializable{

	public Vendor(String username, String password, String address, String email) {
		super(username, password, address, email);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Vendor [getUsername()=" + getUsername() +  ", getAddress()="+ getAddress() + ", getEmail()=" + getEmail() + "]";
	}
		
	
	
}
