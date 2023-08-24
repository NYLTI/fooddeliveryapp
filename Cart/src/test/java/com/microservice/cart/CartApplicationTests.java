package com.microservice.cart;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.model.Cart;
import com.service.CartService;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class CartApplicationTests {
	@Autowired
	CartService cs;
	public static Cart c1;
	public static Cart c2;

	@BeforeAll
	public static void setUp(){

		c1 = new Cart("james", Arrays.asList("1", "3", "7"));
		c2 = new Cart("david", Arrays.asList("4", "5", "6"));
	}
	
	@Test
	@Order(1)
	void testEmptyRepo() {
		assertEquals(cs.getAllCarts(), null);
	}
	
	@Test
	@Order(2)
	void testAddCart() {
		cs.createCart(c1);
		assertEquals(cs.getAllCarts().size(),1);
		cs.createCart(c2);
		assertEquals(cs.getAllCarts().size(),2);
		cs.deleteAll();
	}
	
	@Test
	@Order(3)
	void testGetCardById() {
		cs.createCart(c1);
		cs.createCart(c2);
		Optional<Cart> cart = cs.getCartById("minh");
		assertEquals(cart, null);
		cart = cs.getCartById("david");
		assertEquals(cart.isPresent(), true);
		assertEquals(cart.get().getItemIds(), c2.getItemIds());
		cs.deleteAll();
	}
	
	@Test
	@Order(4)
	void testUpdateCart() {
		cs.createCart(c1);
		cs.createCart(c2);
		Cart new_cart = new Cart("james", Arrays.asList("10", "30", "70"));
		Cart cart = cs.updateCart("minh", new_cart);
		assertEquals(cart, null);
		cart = cs.updateCart("james", new_cart);
		assertEquals(cs.getCartById("james").get().getItemIds(), Arrays.asList("10", "30", "70"));
		cs.deleteAll();
		
	}
	
	@Test
	@Order(5)
	void testDelete() {
		cs.createCart(c1);
		cs.createCart(c2);
		String result = cs.deleteCart("minh");
		assertEquals(result, "Cart Not Found");
		result = cs.deleteCart("james");
		assertEquals(result, "Cart Deleted");
		assertEquals(cs.getAllCarts().size(),1);
		cs.deleteCart("david");
		assertEquals(cs.getAllCarts(), null);
		cs.deleteAll();
		
	}
}
