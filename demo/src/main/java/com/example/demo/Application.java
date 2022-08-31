package com.example.demo;

import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
///http://localhost:8080/add-product?name=pantofi&weight=2&price=10&description=O%20pereche%20de%20pantofi%20portocali%20de%20excep%C5%A3ie

@SpringBootApplication
@RestController
public class Application {
	private Store s = new Store("myStore");
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@GetMapping("/add-product")
	public String addProduct(@RequestParam(value = "name", defaultValue = "N/A") String name
				,@RequestParam(value = "weight", defaultValue = "0f") float weight
				,@RequestParam(value = "price", defaultValue = "0f") float price
				,@RequestParam(value = "description", defaultValue = "No description") String description
			) {

		int valid = s.addProduct(name, weight, price, description);

		if(valid == 0)
			return String.format("Produsul pe care încerci să îl inserei este invalid!", name);
		else
			return String.format("Produsul " + name + " a fost adăugat cu succes!");
	}
	
	@GetMapping("/list-products")
    public List<Product> findProduct() {
			return s.products;
            //return String.format("The products available in the stare are these:\n %s!", s.listProducts());
    }
	
	@GetMapping("/find-product")
        public String findProduct(@RequestParam(value = "name", defaultValue = "World") String name) {
                return String.format("Hello %s!", name);
        }
	@GetMapping("/remove-product")
        public String removeProduct(@RequestParam(value = "name", defaultValue = "World") String name) {
                return String.format("Hello %s!", name);
        }
	@GetMapping("/change-price")
        public String changePrice(@RequestParam(value = "name", defaultValue = "World") String name) {
                return String.format("Hello %s!", name);
        }



}
