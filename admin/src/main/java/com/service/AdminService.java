package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Admin;
import com.repo.AdminRepo;

@Service
public class AdminService {

	@Autowired
	AdminRepo adminRepo;
	
	public List<Admin> getAllAdmins(){
		return (List<Admin>) adminRepo.findAll();
	}
	
	public Optional<Admin> findAdminByUserName(String userName){
		return adminRepo.findAdminByUserName(userName);
	}
	
	public Optional<Admin> findAdminById(Long id){
		return adminRepo.findById(id);
	}
	
	public String addAdmin(Admin admin) {
		if(getAllAdmins().size()>0) {
			return "USERALREADYEXIST";
		}else {
			adminRepo.save(admin);
			return "REGISTERED";
		}
	}
	
	public String deleteAdmin(Long id) {
		if(findAdminById(id).isPresent()) {
			adminRepo.deleteById(id);
			return "Admin deleted";
		}else {
			return "Admin not found";
		}
	}
	
	public Admin logIn(String userName, String password) {
		for(Admin as : adminRepo.findAll()) {
			if(userName.equals(as.getUserName()) && password.equals(as.getPassword())) {
				return as;
			}
		}
		return null;
	}
	
	public String resetPassword(String userName, String email, String password) {
		for(Admin as : adminRepo.findAll()) {
			if(userName.equals(as.getUserName()) && email.equals(as.getEmail())) {
				as.setPassword(password);
				adminRepo.save(as);
				return "PASSWORDRESET";
			}
		}
		return "PASSWORDRESETFAILED";
	}
}
