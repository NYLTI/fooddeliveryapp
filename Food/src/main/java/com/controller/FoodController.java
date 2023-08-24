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

import com.model.Food;
import com.service.FoodService;

@RestController
@RequestMapping("/food")
@Component
public class FoodController {
	
	@Autowired
	private FoodService foodService;
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllFood(){
		try{
			return new ResponseEntity<List<Food>>(foodService.getAllFood(), HttpStatus.OK);
		} catch(Exception e){
			return new ResponseEntity<>("No food available", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<?> createFood(@RequestBody Food food){
		try {
			return new ResponseEntity<Food>(foodService.createFood(food), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<?> getFoodById(@PathVariable("id") Long id){	
		try{
			return new ResponseEntity<>(foodService.getFoodById(id), HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>("Food not found", HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateFood(@PathVariable("id") Long id, @RequestBody Food food){
		try{
			return new ResponseEntity<>(foodService.updateFood(id, food), HttpStatus.OK);
		} catch(Exception e) {
			return  new ResponseEntity<>("Food not found", HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteFood(@PathVariable("id") Long id){
		try {
			return new ResponseEntity<>(foodService.deleteFood(id), HttpStatus.OK);
		} catch (Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/discount")
	public List<Food> getdiscountedFood(){
		return foodService.getDiscountedFood();
	}

	@GetMapping("/getcart/{list}")
	public List<Food> getCart(@PathVariable List<Integer> list ){
		
		return foodService.getCart(list);
	}
}
