package com.masai.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.masai.entities.Customer;
import com.masai.entities.Product;
import com.masai.entities.Transaction;
import com.masai.entities.Vendor;

public class FileExists {
		
	public static Map<Integer, Product> productFile() {

		Map<Integer, Product> pFile = null;

		File f = new File("Product.ser");
		boolean flag = false;
		try {
			if (!f.exists()) {
				f.createNewFile();
				flag = true;
			}

			if (flag) {

				pFile = new LinkedHashMap<>();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
				oos.writeObject(pFile);
				return pFile;

			} else {

				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				pFile = (Map<Integer, Product>) ois.readObject();

				return pFile;

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return pFile;
	}
	
	public static Map<String, Customer> customerFile() {

		Map<String, Customer> cFile = null;

		File f = new File("Customer.ser");
		boolean flag = false;
		try {
			if (!f.exists()) {
				f.createNewFile();
				flag = true;
			}

			if (flag) {
				
				cFile = new LinkedHashMap<>();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
				oos.writeObject(cFile);
				return cFile;

			} else {
				
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				cFile = (Map<String, Customer>) ois.readObject();

				return cFile;

			}

		} catch (Exception e) {
			// TODO: handle exception

			System.out.println(e.getMessage());
		}
		return cFile;

	}
	
	public static List<Transaction> transactionFile() {

		List<Transaction> tFile = new ArrayList<>();

		File f = new File("Transactions.ser");
		boolean flag = false;
		try {
			if (!f.exists()) {
				f.createNewFile();
				flag = true;
			}

			if (flag) {
				tFile =  new ArrayList<>();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
				oos.writeObject(tFile);

				return tFile;

			} else {

				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				tFile = (List<Transaction>) ois.readObject();
				return tFile;

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		return tFile;

	}
	
	
	public static Map<String, Vendor> vendorFile(){
		 Map<String, Vendor> vFile = null;
		File f = new File("Vendor.ser");
		boolean flag = false;
		try {
			if (!f.exists()) {
				f.createNewFile();
				flag = true;
			}
			if(flag) {
				vFile = new LinkedHashMap<>();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
				oos.writeObject(vFile);
				return vFile;
			}
			else {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				vFile = (Map<String, Vendor>) ois.readObject();;
				return vFile;
			}
		}catch (Exception e) {
			// TODO: handle exception

			System.out.println(e.getMessage());
		}
		return vFile;
	}
	
}
