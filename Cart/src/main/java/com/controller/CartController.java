package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Cart;
import com.service.CartService;

@RestController
@RequestMapping("/cart")
@Component
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllCarts(){		
		try {
			return new ResponseEntity<List<Cart>>(cartService.getAllCarts(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("No carts available", HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> createCart(@RequestBody Cart cart){
		try {
			return new ResponseEntity<Cart>(cartService.createCart(cart), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getCartById(@PathVariable("id") String id){
		try {
			return new ResponseEntity<>(cartService.getCartById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Cart not found", HttpStatus.NOT_FOUND);
		}	
	}
	
	
	@PostMapping("/update/{id}")
	public ResponseEntity<?> updateCart(@PathVariable("id") String id, @RequestBody Cart cart){
		try {
			return new ResponseEntity<>(cartService.updateCart(id, cart), HttpStatus.OK);	
		} catch (Exception e) {
			return new ResponseEntity<>("Cart not found", HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCart(@PathVariable("id") String id){
		try {
			return new ResponseEntity<>(cartService.deleteCart(id), HttpStatus.OK);
		} catch (Exception e) {
			return new  ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/reset/{id}")
	public Cart resetCart(@PathVariable String id) {
		return cartService.resetCart(id);
	}
}
