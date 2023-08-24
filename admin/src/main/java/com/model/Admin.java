package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Admin {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String userName;
	private Long phoneNumber;
	private String address;
	private String password;
	private String email;
}
