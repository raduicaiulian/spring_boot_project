package com.example.demo;

import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.context.ConfigurableApplicationContext;
///http://localhost:8080/add-product?name=pantofi&weight=2&price=10&description=O%20pereche%20de%20pantofi%20portocali%20de%20excep%C5%A3ie
//http://localhost:8080/add-product?name=geanta&weight=1&price=100&description=Geanta%20abibas!@SpringBootApplication
@SpringBootApplication
@RestController
public class Application {
	private Store s = new Store("myStore");
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
//		ConfigurableApplicationContext apc = SpringApplication.run(Application.class, args);
//		for(String s:apc.getBeanDefinitionNames())
//			System.out.println(s);
	}
	
	@GetMapping("/")
    public String root() {
		//logger.info("you are actualy in the store");
		return String.format("Welcome to %s !", s.getName());
    }

	@GetMapping("/add-product")
	public String addProduct(@RequestParam(value = "name") String name
				,@RequestParam(value = "weight") float weight
				,@RequestParam(value = "price") float price
				,@RequestParam(value = "description") String description) {
		
		boolean valid = s.addProduct(name, weight, price, description);
		
		if(valid != true)
			return String.format("Produsul pe care încerci să îl inserei este invalid!", name);
		return String.format("Produsul " + name + " a fost adăugat cu succes!");
	}
	
	@GetMapping("/list-products")
    public List<Product> findProduct() {
			return s.products;
    }
	
	@GetMapping("/find-product")
    public Product findProduct(@RequestParam(value = "name") String name) {
		Product p = s.isInStore(name);
		if(p != null) 
			return p;
		return null;
    }
	@GetMapping("/remove-product")
    public String removeProduct(@RequestParam(value = "name") String name) {
		boolean status = s.remove(name);
		if(status==true)
			return String.format("%s was removed!", name);
		else
			return String.format("%s not removed because was not found!", name);
    }
	@GetMapping("/change-price")
    public String changePrice(@RequestParam(value = "name") String name,
    							@RequestParam(value = "newPrice") float newPrice) {
		if(s.changePrice(name, newPrice) == true)
			return String.format("Price changed!");
		return String.format("Product not found!");
    }
	
	//Account
	//localhost:8080/add-account?username=usr1&password=usr1&role=USER
	@GetMapping("/add-account")
	public String addAccount(@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password,
			@RequestParam(value = "role") String role) {
		return s.registerAccount(username, password, role);
	}
	
	//localhost:8080/remove-account?username=usr1
	@GetMapping("/remove-account")
	public String removeAccount(@RequestParam(value = "username") String username) {
		System.out.println(username);
		return s.unregisterAccount(username);
	}
	
	@GetMapping("/list-accounts")
	public List<Account> listAccount() {
		return s.accounts;
	}
	
	@GetMapping("/error")
    public String error() {
			return String.format(":(");
    }
}
