package com.example.demo;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private Application controller;
	
	private ResultActions addProduct(String name, float weight, float price, String description) throws Exception {
		return this.mockMvc.perform(get("http://localhost:8080/add-product?"
				+ "name="+name
				+ "&weight="+weight
				+ "&price="+price
				+ "&description="+description));
	}
	
	@Test
	public void contextLoads() {
		assertThat(controller).isNotNull();
	}
	
	@Test
	@Order(1)
	@WithMockUser
	void add_product_and_list_products() throws Exception{
		
		addProduct("pantofi",2.0f,10.0f,"O pereche de pantofi portocali de excepţie")
			.andExpect(content().string(containsString("Produsul pantofi a fost adăugat cu succes!")))
			.andExpect(status().isOk());//.andExpect(status().isBadRequest())
		
		this.mockMvc.perform(get("http://localhost:8080/list-products"))
			.andExpect(jsonPath("$[0].name", comparesEqualTo("pantofi")))
			.andExpect(jsonPath("$[0].weight", comparesEqualTo(2.0)))
			.andExpect(jsonPath("$[0].price", comparesEqualTo(10.0)))
			.andExpect(jsonPath("$[0].description", comparesEqualTo("O pereche de pantofi portocali de excepţie")));
	}
	
	@Test
	@Order(2)
	@WithMockUser
	void find_product() throws Exception{
		addProduct("geanta",1.0f,100.0f,"Geanta abibas!")
			.andExpect(content().string(containsString("Produsul geanta a fost adăugat cu succes!")));
		
		this.mockMvc.perform(get("http://localhost:8080/find-product?"
				+ "name=geanta"))
			.andExpect(jsonPath("$.name", comparesEqualTo("geanta")))
			.andExpect(jsonPath("$.weight", comparesEqualTo(1.0)))
			.andExpect(jsonPath("$.price", comparesEqualTo(100.0)))
			.andExpect(jsonPath("$.description", comparesEqualTo("Geanta abibas!")));
	}
	
	@Test
	@Order(3)
	@WithMockUser(roles= {"ADMIN"})
	void remove_product() throws Exception{
		addProduct("geanta",1.0f,100.0f,"Geanta abibas!")
			.andExpect(content().string(containsString("Produsul geanta a fost adăugat cu succes!")));
		
		this.mockMvc.perform(get("http://localhost:8080/remove-product?"
				+ "name=geanta"))
			.andExpect(content().string(containsString("geanta was removed!")));
	}
	
	@Test
	@Order(4)
	@WithMockUser(roles= {"ADMIN"})
	void change_price() throws Exception{
		addProduct("geanta",1.0f,100.0f,"Geanta abibas!")
			.andExpect(content().string(containsString("Produsul geanta a fost adăugat cu succes!")));
		
		this.mockMvc.perform(get("http://localhost:8080/change-price?"
				+ "name=geanta&"
				+ "newPrice=300"))
				.andExpect(content().string(containsString("Price changed!")));
		
		this.mockMvc.perform(get("http://localhost:8080/find-product?"
				+ "name=geanta"))
			.andExpect(jsonPath("$.name", comparesEqualTo("geanta")))
			.andExpect(jsonPath("$.price", comparesEqualTo(300.0)));
	}
	
	//accounts tests
	@Test
	@Order(5)
	@WithMockUser(roles= {"ADMIN"})
	void add_acount() throws Exception{
		
		this.mockMvc.perform(get("http://localhost:8080/add-account?"
				+ "username=usr1&"
				+ "password=usr1&"
				+ "role=USER"))
				.andExpect(content().string(containsString("usr1 exist in InMemoryUserDetailsManager")));
	}

	@Test
	@Order(6)
	@WithMockUser(roles= {"ADMIN"})
	void remove_acount() throws Exception{
		
		this.mockMvc.perform(get("http://localhost:8080/remove-account?"
				+ "username=usr1"))
				.andExpect(content().string(containsString("user removed")));
	}
}