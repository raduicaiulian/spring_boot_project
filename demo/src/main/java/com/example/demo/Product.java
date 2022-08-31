package com.example.demo;

public class Product {
	private String name;
	private float weight;
	private float price;
	private String description;
    
    public Product(String name) {
        this.name = name;
    }
	
    public Product(String name2, float weight2, float price2, String description2) {
		this.name = name2;
		this.weight = weight2;
		this.price = price2;
		this.description = description2;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
    public String getName() {
            return name;
    }
    
	public void setName(String name) {
		this.name = name;
	}

}

