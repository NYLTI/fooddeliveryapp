package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Cart;
import com.repo.CartRepo;

@Service
public class CartService {

	@Autowired
	CartRepo cartRepo;

	public List<Cart> getAllCarts() {
		List<Cart> carts  = cartRepo.findAll();
		if(carts.size()>0) {
		return carts;
		}
		return null;
	}

	public Cart createCart(Cart cart) {
		return cartRepo.save(cart);
	}

	public Optional<Cart> getCartById(String id) {
		Optional<Cart> cart = cartRepo.findById(id);
		if (cart.isPresent()) {
			return cartRepo.findById(id);
		}
		return null;
	}

	public Cart updateCart(String id, Cart cart) {
		Optional<Cart> old_cart = cartRepo.findById(id);
		if (old_cart.isPresent()) {
			return cartRepo.save(cart);
		}
		return null;
	}

	public String deleteCart(String id) {
		if (getCartById(id).isPresent()) {
			cartRepo.deleteById(id);
			return "Cart Deleted";
		} else {
			return "Cart Not Found";
		}
	}
	public void deleteAll() {
		cartRepo.deleteAll();
	}
	
	public Cart resetCart(String id) {
		Cart cart = new Cart();
		cart = getCartById(id).get();
		cart.getItemIds().clear();
		cartRepo.save(cart);
		return cart;
	}
}
