package com.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Admin;
import com.service.AdminService;

@Component
@RestController
@RequestMapping("admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@PostMapping("/register")
	public String register(@RequestBody Admin admin) {
		return adminService.addAdmin(admin);
	}
	
	@PostMapping("/login")
	public Admin logIn(@RequestBody Map<String,String> admin) {
		return adminService.logIn(admin.get("userName"), admin.get("password"));
	}
	
	@GetMapping("/get/{userName}")
	public Optional<Admin> findAdminByUserName(@PathVariable String userName){
		return adminService.findAdminByUserName(userName);
	}
	
	@GetMapping("/find/{id}")
	public Optional<Admin> findAdminById(@PathVariable Long id){
		return adminService.findAdminById(id);
	}
	
	@GetMapping("/all")
	public List<Admin> getAllAdmins(){
		return adminService.getAllAdmins();
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteAdminById(@PathVariable Long id) {
		return adminService.deleteAdmin(id);
	}
	
	@PutMapping("/reset/{pass}")
	public String resetPassword(@RequestBody Map<String,String> admin, @PathVariable String pass) {
		return adminService.resetPassword(admin.get("userName"), admin.get("email"), pass);
	}
}
