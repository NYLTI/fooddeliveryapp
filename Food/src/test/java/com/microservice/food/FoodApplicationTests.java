package com.microservice.food;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.model.Food;
import com.service.FoodService;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class FoodApplicationTests {
	
	@Autowired
	FoodService fs;
	
	public static Food f1;
	public static Food f2;

	@BeforeAll
	public static void setUp(){
		f1 = new Food(1L,"food1.jpg", "pizza", "domino", 5L, 1L);
		f2 = new Food(2L,"food2.jpg", "hamburger", "innout", 3L, 0L);
	}

	@Test
	@Order(1)
	void testEmptyRepo() {
		assertEquals(fs.getAllFood(), null);
	}
	
	@Test
	@Order(2)
	void testAddFood() {
		fs.createFood(f1);
		assertEquals(fs.getAllFood().size(), 1);
		fs.createFood(f2);
		assertEquals(fs.getAllFood().size(), 2);
		fs.deleteAll();
	}


	
	@Test
	@Order(3)
	void testGetFoodById() {
		fs.createFood(f1);
		fs.createFood(f2);
		Optional<Food> f = fs.getFoodById(1L);
		assertEquals(f.isPresent(), true);
		assertEquals(f.get().getFoodName(), f1.getFoodName());
		assertEquals(f.get().getVendorName(), f1.getVendorName());
		f = fs.getFoodById(3L);
		assertEquals(f, null);
		fs.deleteAll();
	}
	
	@Test
	@Order(4)
	void testUpdateFood() {
		fs.createFood(f1);
		Food new_f1 = new Food(1L,"food1.jpg", "newpizza", "domino", 5L, 1L);
		Food f = fs.updateFood(3L, new_f1);
		assertEquals(f, null);
		f = fs.updateFood(1L, new_f1);
		assertEquals(f.getFoodName(), "newpizza");
		fs.deleteAll();
	}
	
	@Test
	@Order(5)
	void testDeleteFood() {
		fs.createFood(f1);
		fs.createFood(f2);
		String result = fs.deleteFood(3L);
		assertEquals(result, "Food Not Found");
		result = fs.deleteFood(2L);
		assertEquals(result, "Food Deleted");
		assertEquals(fs.getAllFood().size(), 1);
		fs.deleteAll();
	}


}

