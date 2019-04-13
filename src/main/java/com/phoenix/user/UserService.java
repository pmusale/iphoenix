package com.phoenix.user;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserService {
	User createUser(UUID id, String name, String email, String encodedPassword, List<Address> address, UserStatus status, Set<UserRole> roles);
	Optional<User> getUser(UUID id);
	List<User> getUsersByName(String name);
}
