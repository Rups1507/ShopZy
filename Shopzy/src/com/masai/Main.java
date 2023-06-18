package com.masai;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.masai.entities.Customer;
import com.masai.entities.Product;
import com.masai.entities.Transaction;
import com.masai.entities.Vendor;
import com.masai.exceptions.DuplicateDataException;
import com.masai.exceptions.InvalidDetailsException;
import com.masai.exceptions.ProductException;
import com.masai.exceptions.TransactionException;
import com.masai.service.CustomerService;
import com.masai.service.CustomerServiceImpl;
import com.masai.service.ProductService;
import com.masai.service.ProductServicesImpl;
import com.masai.service.TransactionService;
import com.masai.service.TransactionServiceImpl;
import com.masai.service.VendorService;
import com.masai.service.VendorServiceImpl;
import com.masai.utility.Admin;
import com.masai.utility.FileExists;
import com.masai.utility.IDGeneration;

public class Main {
		
		public static void adminFunctionality(Scanner sc, Map<Integer, Product> products, Map<String, Customer> customers,
				List<Transaction> transactions,Map<String, Vendor> vendors) throws InvalidDetailsException, ProductException, TransactionException {
			
			adminLogin(sc);
			
			ProductService prodService = new ProductServicesImpl();
			CustomerService cusService = new CustomerServiceImpl();
			TransactionService trnsactionService = new TransactionServiceImpl();
			VendorService venService = new VendorServiceImpl();
			int choice = 0;
			
			try {
				do {
					System.out.println("Press 1 to View all Products");
					System.out.println("Press 2 to View all Customers");
					System.out.println("Press 3 to View all Vendors");
					System.out.println("Press 4 to View all Transactions");
					System.out.println("Press 5 to Log Out");
					
					choice = sc.nextInt();
					
					switch(choice) {
					case 1 : 
						adminViewAllProducts(products, prodService);
							
						break;
					
					case 2 : 
						adminViewAllCustomers(customers, cusService);

						break;
					
					case 3: 
						adminViewAllVendors(vendors, venService);
						
						break;
						
					case 4:
						adminViewAllTransactions(transactions, trnsactionService);
						
						break;
						
					case 5:
						System.out.println("admin has successfully logout");
						
						break;
					
					default:
						throw new IllegalArgumentException("Unexpected value: " + choice);
					}
					
					
				}while(choice<=5);
				
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		public static void adminLogin(Scanner sc) throws InvalidDetailsException {

			System.out.println("Enter the user name");
			String userName = sc.next();
			System.out.println("Enter the password");
			String password = sc.next();
			if (userName.equals(Admin.username) && password.equals(Admin.password)) {

				System.out.println("successfully login");
			} else {
				throw new InvalidDetailsException("Invalid Admin Credentials");
			}
		}
		
		public static void adminViewAllProducts(Map<Integer, Product> products, ProductService prodService)
				throws ProductException {
			prodService.viewAllProducts(products);
		}
		
		public static void adminViewAllCustomers(Map<String, Customer> customers, CustomerService cusService)
				throws ProductException {
			List<Customer> list = cusService.viewAllCustomers(customers);

			for (Customer c : list) {
				System.out.println(c);
			}
		}
		
		public static void adminViewAllVendors(Map<String, Vendor> vendors, VendorService venService) 
				throws ProductException{
			List<Vendor> list = venService.viewAllVendors(vendors);
			
			for(Vendor v : list) {
				System.out.println(v);
			}
		}
		
		public static void adminViewAllTransactions(List<Transaction> transactions, TransactionService trnsactionService)
				throws TransactionException {
			List<Transaction> allTransactions = trnsactionService.viewAllTransactions(transactions);

			for (Transaction tr : allTransactions) {
				System.out.println(tr);
			}

		}
		
		
		public static void customerFunctionality(Scanner sc, Map<String, Customer> customers,
				Map<Integer, Product> products, List<Transaction> transactions)
				throws InvalidDetailsException, TransactionException {
			
			CustomerService cusService = new CustomerServiceImpl();
			ProductService prodService = new ProductServicesImpl();
			TransactionService trnsactionService = new TransactionServiceImpl();
			
			System.out.println("please enter the following details to login");
			System.out.println("please enter the email");
			String email = sc.next();
			System.out.println("Enter the password");
			String pass = sc.next();
			customerLogin(email,pass, customers, cusService);
			
			try {
				int choice = 0;
				do {
					System.out.println("Select the option of your choice");
					System.out.println("Press 1 to view all products");
					System.out.println("Press 2 to buy a product");
					System.out.println("Press 3 to add money to a wallet");
					System.out.println("Press 4 view wallet balance");
					System.out.println("Press 5 view my details");
					System.out.println("Press 6 view my transactions");
					System.out.println("Press 7 to logout");
					choice = sc.nextInt();

					switch (choice) {
					case 1:
						customerViewAllProducts(products, prodService);
						break;
					case 2:
						String result = customerBuyProduct(sc, email, products, customers, transactions, cusService);
						System.out.println(result);
						break;
					case 3:
						String moneyAdded = customerAddMoneyToWallet(sc, email, customers, cusService);
						System.out.println(moneyAdded);
						break;
					case 4:
						double walletBalance = customerViewWalletBalance(email, customers, cusService);
						System.out.println("Wallet balance is: " + walletBalance);
						break;
					case 5:
						customerViewMyDetails(email, customers, cusService);
						break;
					case 6:
						customerViewCustomerTransactions(email, transactions, trnsactionService);
						break;
					case 7:
						System.out.println("you have successsfully logout");
						break;
					default:
						System.out.println("invalid choice");
						break;
					}

				} while (choice <= 6);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
		
		public static void customerSignup(Scanner sc, Map<String, Customer> customers) throws DuplicateDataException {
			System.out.println("please enter the following details to Signup");
			System.out.println("please enter the user name");
			String name = sc.next();
			System.out.println("Enter the password");
			String pass = sc.next();
			System.out.println("enter the address");
			String address = sc.next();
			System.out.println("Enter the email id");
			String email = sc.next();
			System.out.println("Enter the balance to be added into the wallet");
			double balance = sc.nextDouble();
			Customer cus = new Customer(balance, name, pass, address, email);

			CustomerService cusService = new CustomerServiceImpl();
			cusService.signUp(cus, customers);
			System.out.println("customer has Succefully sign up");

		}
		
		
		public static void customerLogin(String email,String pass, Map<String, Customer> customers, CustomerService cusService)
				throws InvalidDetailsException {
			cusService.login(email, pass,customers);
			System.out.println("Customer has successfully login");

		}
		
		
		public static void customerViewAllProducts(Map<Integer, Product> products, ProductService prodService)
				throws ProductException {
			prodService.viewAllProducts(products);
		}

		public static String customerBuyProduct(Scanner sc, String email, Map<Integer, Product> products,
				Map<String, Customer> customers, List<Transaction> transactions, CustomerService cusService)
				throws InvalidDetailsException, ProductException {
			System.out.println("Enter the product id");
			int id = sc.nextInt();
			System.out.println("enter the quantity you want to buy");
			int qty = sc.nextInt();
			cusService.buyProduct(id, qty, email, products, customers, transactions);

			return "You have successfully bought the product";

		}

		public static String customerAddMoneyToWallet(Scanner sc, String email, Map<String, Customer> customers,
				CustomerService cusService) {
			System.out.println("please enter the amount");
			double money = sc.nextDouble();
			boolean added = cusService.addMoneyToWallet(money, email, customers);

			return "Amount: " + money + " successfully added to your wallet";
		}

		public static double customerViewWalletBalance(String email, Map<String, Customer> customers,
				CustomerService cusService) {
			double walletBalance = cusService.viewWalletBalance(email, customers);
			return walletBalance;
		}

		public static void customerViewMyDetails(String email, Map<String, Customer> customers,
				CustomerService cusService) {
			Customer cus = cusService.viewCustomerDetails(email, customers);
			System.out.println("name : " + cus.getUsername());
			System.out.println("address : " + cus.getAddress());
			System.out.println("email : " + cus.getEmail());
			System.out.println("wallet balance : " + cus.getWalletBalance());
		}

		public static void customerViewCustomerTransactions(String email, List<Transaction> transactions,
				TransactionService trnsactionService) throws TransactionException {
			List<Transaction> myTransactions = trnsactionService.viewCustomerTransactions(email, transactions);

			for (Transaction tr : myTransactions) {
				System.out.println(tr);
			}
		}
		
		public static void vendorFunctionality(Scanner sc, Map<Integer, Product> products, Map<String, Customer> customers,
				List<Transaction> transactions,Map<String, Vendor> vendors) throws InvalidDetailsException, ProductException, TransactionException  {
				
			ProductService prodService = new ProductServicesImpl();
			CustomerService cusService = new CustomerServiceImpl();
			TransactionService trnsactionService = new TransactionServiceImpl();
			VendorService venService = new VendorServiceImpl();
			
			System.out.println("please enter the following details to login");
			System.out.println("please enter the email");
			String email = sc.next();
			System.out.println("Enter the password");
			String pass = sc.next();
			vendorLogin(email,pass, vendors, venService);
				
				
				try {
					int choice =0;
					do {
						System.out.println("Press 1 add the product");
						System.out.println("Press 2 view all the product");
						System.out.println("Press 3 delete the product");
						System.out.println("Press 4 update the product");
						System.out.println("Press 5 to Logout");
						
						switch(choice) {
						case 1:
							String added = vendorAddProduct(sc, products, prodService);
							System.out.println(added);
							break;
						
						case 2:
							adminViewAllProducts(products, prodService);
							break;
							
						case 3:
							vendorDeleteProduct(sc, products, prodService);
							break;
						
						case 4:
							String upt = vendorUpdateProduct(sc, products, prodService);
							System.out.println(upt);
							break;
						
						case 5:
							System.out.println("you have successsfully logout");
							break;
						
						default:
							System.out.println("invalid choice");
							break;
						}
					}while(choice<=5);
					
				
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
		}
		
		public static void vendorSignup(Scanner sc, Map<String, Vendor> vendors) throws DuplicateDataException {
			System.out.println("please enter the following details to Signup");
			System.out.println("please enter the user name");
			String name = sc.next();
			System.out.println("Enter the password");
			String pass = sc.next();
			System.out.println("enter the address");
			String address = sc.next();
			System.out.println("Enter the email id");
			String email = sc.next();
			Vendor ven = new Vendor( name, pass, address, email);

			VendorService venService = new VendorServiceImpl();
			venService.signUp(ven, vendors);
			System.out.println("Vendor has Succefully sign up");

		}
		
		public static void vendorLogin(String email,String pass, Map<String, Vendor> vendors, VendorService venService)
				throws InvalidDetailsException {
			venService.login(email, pass,vendors);
			System.out.println("Vendor has successfully login");
						
		}
		
		public static String vendorAddProduct(Scanner sc, Map<Integer, Product> products, ProductService prodService) {

			String str = null;
			System.out.println("please enter the product details");
			System.out.println("Enter the product name");
			String name = sc.next();
			System.out.println("Enter the product qty");
			int qty = sc.nextInt();
			System.out.println("Enter the product price/piece");
			double price = sc.nextDouble();
			System.out.println("Enter the product category");
			String cate = sc.next();

			Product prod = new Product(IDGeneration.generateId(), name, qty, price, cate);

			str = prodService.addProduct(prod, products);// considering all details are valid

			return str;

		}
		
		public static void vendorDeleteProduct(Scanner sc, Map<Integer, Product> products, ProductService prodService)
				throws ProductException {
			System.out.println("please enter the id of product to be deleted");
			int id = sc.nextInt();
			prodService.deleteProduct(id, products);
		}
		
		public static String vendorUpdateProduct(Scanner sc, Map<Integer, Product> products, ProductService prodService) 
				throws ProductException {
			String result = null;
			System.out.println("please enter the id of the product which is to be updated");
			int id = sc.nextInt();
			System.out.println("Enter the updated details ");

			System.out.println("Enter the product name");
			String name = sc.next();

			System.out.println("Enter the  product qty");
			int qty = sc.nextInt();

			System.out.println("Enter the product price/piece");
			double price = sc.nextDouble();

			System.out.println("Enter the product category");
			String cate = sc.next();

			Product update = new Product(id, name, qty, price, cate);

			result = prodService.updateProduct(id, update, products);
			return result;
		}
		
		public static void main(String[] arg) {
			Map<Integer, Product> products = FileExists.productFile();
			Map<String, Customer> customers = FileExists.customerFile();
			Map<String, Vendor> vendor = FileExists.vendorFile();
			List<Transaction> transactions = FileExists.transactionFile();
			
			Scanner sc = new Scanner(System.in);

			System.out.println("Welcome to ShopZy");
			
			try {
				int preference=0;
				
				do {
					System.out.println("Please enter your preference, " + " '1' Admin login , '2' Customer login , "
							+ "'3' for Customer signup, '4' for Vendor Login"+" '5' for Vendor signup, '0' for exit" );
					preference = sc.nextInt();
					switch(preference) {
					case 1:
						adminFunctionality(sc, products, customers, transactions, vendor);
						break;
					
					case 2: 
						customerFunctionality(sc, customers, products, transactions);
						break;
					
					case 3:
						customerSignup(sc, customers);
						break;
					
					case 4:
						vendorFunctionality(sc, products, customers, transactions, vendor);
						break;
					
					case 5:
						vendorSignup(sc,vendor);
						break;
					case 0:
						System.out.println("Existed from the ShopZy");

						break;

					default:
						throw new IllegalArgumentException("Invalid Selection");
					
					}
					
					
				}while(preference<=6);
				
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}finally {
				try {
					ObjectOutputStream poos = new ObjectOutputStream(new FileOutputStream("Product.ser"));
					poos.writeObject(products);
					ObjectOutputStream coos = new ObjectOutputStream(new FileOutputStream("Customer.ser"));
					coos.writeObject(customers);

					ObjectOutputStream toos = new ObjectOutputStream(new FileOutputStream("Transactions.ser"));
					toos.writeObject(transactions);
					
					ObjectOutputStream voos = new ObjectOutputStream(new FileOutputStream("Vendor.ser"));
					voos.writeObject(vendor);
					
					
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}

		}
		
		
}
