package com.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Vendor;
import com.service.VendorService;

@Component
@RestController
@RequestMapping("/ms/vendor")
public class VendorController {
	@Autowired
	VendorService vendorService;
	
	@PostMapping(value = "/register")
	public String register(@RequestBody Vendor vendor) {
		return vendorService.addVendor(vendor);
	}
	
	@GetMapping("/get/{userName}")
	public Optional<Vendor> findVendorByUserName(@PathVariable String userName){
		return vendorService.findVendorByUserName(userName);
	}

	@GetMapping("/all")
	public List<Vendor> getAllVendors(){
		return vendorService.getAllVendors();
	}

	@PostMapping("/login")
	public Vendor logIn(@RequestBody Map<String, String> vendor) {
		return vendorService.logIn(vendor.get("userName"), vendor.get("password"));
	}
	@PutMapping("/reset/{pass}")
	public String resetPassword(@RequestBody Map<String,String> vendor,@PathVariable String pass) {
		return vendorService.resetPassword(vendor.get("userName"), vendor.get("email"),pass);
	}
	@PutMapping("/disable/{vendorName}")
	public String disableVendor(@PathVariable String userName) {
		return vendorService.disableVendor(userName);
	}
}
