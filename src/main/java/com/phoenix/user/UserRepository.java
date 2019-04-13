package com.phoenix.user;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
public interface UserRepository extends MongoRepository<User, UUID>{
	
	 List<User> findByName(String name);
	 Optional<User> findByEmailIgnoreCase(String email);
}

