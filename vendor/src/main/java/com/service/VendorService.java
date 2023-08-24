package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Vendor;
import com.repo.VendorRepo;

@Service
public class VendorService {
	@Autowired
	VendorRepo vendorRepo;
	
	public List<Vendor> getAllVendors(){
		return vendorRepo.findAll();
	}
	public Optional<Vendor> findVendorByUserName(String vendorName){
		return vendorRepo.findVendorByUserName(vendorName);
	}
	public String addVendor(Vendor vendor) {
		if(findVendorByUserName(vendor.getUserName()).isPresent()){
			return("USERALREADYEXIST");
		}else {
			vendorRepo.save(vendor);
			return ("REGISTERED");
		}
	}
	
	public Vendor logIn(String userName, String password) {
		for(Vendor vn : vendorRepo.findAll()) {
			if(userName.equals(vn.getUserName()) 
					&& password.equals(vn.getPassword())) {
				return vn;
			}
		}
		return null;
	}
	public String resetPassword(String userName, String email, String password) {
		for(Vendor vn : vendorRepo.findAll()) {
			if(userName.equals(vn.getUserName()) && email.equals(vn.getEmail())) {
				vn.setPassword(password);
				vendorRepo.save(vn);
				return "PASSWORDRESET";
			}
		}
		return "PASSWORDRESETFAILED";
	}
	public String disableVendor(String userName) {
		for(Vendor vn : vendorRepo.findAll()) {
			if(userName.equals(vn.getUserName())) {
				vn.setEnabled(false);
				vendorRepo.save(vn);
				return "VENDORDISABLED";
			}
		}
		return "VENDORNOTFOUND";
	}
}