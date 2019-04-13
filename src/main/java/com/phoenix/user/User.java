package com.phoenix.user;

import java.util.List;
import java.util.UUID;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


@Document(collection = "phoenix_user")
public class User {
	@Id
    private UUID id;
	private String name;
	private String email;
	private String password;
	private List<Address> address;
	private UserStatus status;	
	private Set<UserRole> roles;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Address> getAddress() {
		return address;
	}
	public void setAddress(List<Address> address) {
		this.address = address;
	}
	public UserStatus getStatus() {
		return status;
	}
	public void setStatus(UserStatus status) {
		this.status = status;
	}
	
	public Set<UserRole> getRoles() {
		return roles;
	}
	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public User(UUID id, String name, String email, String encodedPassword, List<Address> address, UserStatus status,Set<UserRole> roles) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = encodedPassword;
		this.address = address;
		this.status = status;
		this.roles = roles;
		
	}
	
	/**
	 * @param id
	 * @param name
	 * @param email
	 * @param address
	 * @return
	 */
	public static User createUser(UUID id, String name, String email, String encodedPassword,List<Address> address, UserStatus status, Set<UserRole> roles) {
		return new User(id, name, email, encodedPassword, address, status, roles);
	}
	
	@Override
    public String toString() {
		ObjectMapper mapper = new ObjectMapper();
     
		String jsonString = "";
		
		try {
				mapper.enable(SerializationFeature.INDENT_OUTPUT);
				jsonString = mapper.writeValueAsString(this);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
 
		return jsonString;
    	}
}
