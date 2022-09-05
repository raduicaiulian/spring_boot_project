package com.example.demo;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class Store {

	private String name;
	List<Product> products = new ArrayList<Product>();
	ArrayList<GrantedAuthority> availableRoles= new ArrayList<>();
	List<Account> accounts = new ArrayList<Account>();
	
	public Store(String name) {
		accounts.add(new Account("user","user","USER"));
		accounts.add(new Account("admin","admin","ADMIN"));
		this.name = name;
	}
 
	//products-----------------------------------------------------------------------
	//isBlank() - function introduced in java 11 to check if a string is blank 
	public boolean addProduct(String name2, float weight, float price, String description) {
		Product p = new Product(name2, weight, price, description);
		if(!p.getName().isBlank() &&
		p.getPrice()>0f &&
		p.getWeight()>0f &&
		!p.getDescription().isBlank()){
			products.add(p);
			return true;
		}
		return false;
	}

	public boolean rmProduct(Product p){
		int index = products.indexOf(p);
		if (index == -1)
			return false;
		products.remove(index);
		return true;
	}

	public Product isInStore(String name2) {
		for (Product i:products)
			if (i.getName().equals(name2))
				return i;
		return null;
	}

	public boolean remove(String name2) {
		Product p = isInStore(name2);
		if (p == null)
			return false;
		products.remove(products.indexOf(p));
		return true;
	}

	public boolean changePrice(String name2, float newPrice) {
		Product p = isInStore(name2);
		if(p == null)
			return false;
		p.setPrice(newPrice);
		return true;
	}

	public Object getName() {
		return name;
	}
	//accounts ----------------------------------------------------
	@Autowired
	public InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
	
	@Autowired
	public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public Account findUser(String username) {
		for( Account i:accounts )
			if( i.getUsername().equals(username))
				return i;
		return null;
	}
	
	public String checkIfUserExists( String username)
	{
		boolean flag = inMemoryUserDetailsManager.userExists(username);
		if (flag)
			return username + " exist in InMemoryUserDetailsManager";
		else
			return username + " does not exist in InMemoryUserDetailsManager";
	}
	
	public  String registerAccount( String username, String password, String role) {
		availableRoles.add(new SimpleGrantedAuthority(role));
		accounts.add(new Account(username, password, role));
		User usr = new User(username, passwordEncoder.encode(password), availableRoles);
		inMemoryUserDetailsManager.createUser(usr);
		//TODO replace the content of checkIfUserExists in order to complete the tests
		return checkIfUserExists(username);
	}
	
	public String unregisterAccount(String username) {
		
		inMemoryUserDetailsManager.deleteUser(username);//buba
		Account user = findUser(username);
		if (user != null) {
			accounts.remove(user);//not sure if it works
			return "user removed";
		}
		return "user not found";//checkIfUserExists(username);
	}
	
	//accounts
}
