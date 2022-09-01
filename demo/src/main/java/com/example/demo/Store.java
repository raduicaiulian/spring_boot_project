package com.example.demo;
import java.util.*;  

public class Store {

	private String name;
	List<Product> products = new ArrayList<Product>();  
	
	public Store(String name) {
		this.name = name;
	}

	public boolean addProduct(String name2, float weight, float price, String description) {
		Product p = new Product(name2, weight, price, description);
		if(p.getName().length() > 0 &&
		p.getPrice()>0f &&
		p.getWeight()>0f &&
		p.getDescription().length()>0){
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
}
