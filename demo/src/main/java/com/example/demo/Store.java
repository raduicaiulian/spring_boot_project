package com.example.demo;
import java.util.*;  

public class Store {

	private String name;
	List<Product> products = new ArrayList<Product>();  
	
	public Store(String name) {
		this.name = name;
	}

	public int addProduct(String name2, float weight, float price, String description) {
		Product p = new Product(name2, weight, price, description);
		if(p.getName().length() > 0 && p.getPrice()>0f && p.getWeight()>0f && p.getDescription().length()>0){
			products.add(p);
			return 1;
		}
		return 0;
	}

	public int rmProduct(Product p){
		int index = products.indexOf(p);
		products.remove(index);
		return 1;
	}

	public String getName() {
		return name;
	}
	
}
