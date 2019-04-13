package com.phoenix.user;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
    private final UserRepository repository;
    
    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
     }
	
    @Override
    public User createUser(UUID id, String name, String email, String encodedPassword, List<Address> address, UserStatus status,Set<UserRole> roles) {
    	User user = User.createUser(id, name, email, encodedPassword, address, status, roles);
    	return repository.save(user);
    	
    }
    
    @Override
	public Optional<User> getUser(UUID id){
		return repository.findById(id);
	}
    
    @Override
    public List<User> getUsersByName(String name){
    	return this.repository.findByName(name);
    }

}
