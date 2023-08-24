package com.microservice.admin;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.model.Admin;
import com.service.AdminService;

@SpringBootTest
class AdminApplicationTests {
	
	@Autowired
	AdminService as;
	
	public static Admin a;
	public static Admin b;
	
	@BeforeAll
	static void setup() {
		a = new Admin(1l,"adminname", "admin", 1234L, "dallas", "password", "email");
		b = new Admin(2l,"adminname2", "admin2", 1234L, "austin", "password", "email");
	}
	@Test
	void testEmptyRepo() {
		assertEquals(as.getAllAdmins().size(),0);
		Optional<Admin> cur_admin = as.findAdminByUserName("admin");
		assertEquals(cur_admin.isPresent(), false);
	}
	
	@Test
	void testAddWhenNoAdmin() {
		
		String result = as.addAdmin(a);
		assertEquals(result,"REGISTERED");
		assertEquals(as.getAllAdmins().size(),1);
		Optional<Admin> cur_admin = as.findAdminByUserName("admin");
		assertEquals(cur_admin.isPresent(), true);
		as.deleteAdmin(cur_admin.get().getId());
	}
	
	@Test
	void testAddWhenAdminExisted() {
		as.addAdmin(a);
		String result = as.addAdmin(b);
		assertEquals(result,"USERALREADYEXIST");
		assertEquals(as.getAllAdmins().size(),1);
		Optional<Admin> cur_admin = as.findAdminByUserName("admin2");
		assertEquals(cur_admin.isPresent(), false);
		cur_admin = as.findAdminByUserName("admin");
		as.deleteAdmin(cur_admin.get().getId());
	}
	
	@Test
	void testLoginWhenNoAdmin() {

		Admin cur_admin =  as.logIn("admin", "password");
		assertEquals(cur_admin, null);
	}
	
	@Test
	void testLoginWhenAdminExisted() {
		as.addAdmin(a);
		Admin cur_admin =  as.logIn("admin", "password");
		assertEquals(cur_admin.getPassword(), a.getPassword());
		assertEquals(cur_admin.getPhoneNumber(), a.getPhoneNumber());
		assertEquals(cur_admin.getAddress(), a.getAddress());
		assertEquals(cur_admin.getEmail(), a.getEmail());
		as.deleteAdmin(cur_admin.getId());
	}
	
	@Test
	void testLoginWithIncorrectPassword() {
		as.addAdmin(a);
		Admin wrong_admin =  as.logIn("admin", "wrongpassword");
		assertEquals(wrong_admin, null);
		Optional<Admin> cur_admin = as.findAdminByUserName("admin");
		as.deleteAdmin(cur_admin.get().getId());
		
	}
	

}
