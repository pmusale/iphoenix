package com.phoenix.user;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.HashSet;


@ContextConfiguration(classes={MongoConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)

public class UserRepositoryTest {

    @Autowired private UserRepository userrepo;
    User prashant, atharwa;
    List<User> all;
    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    
    @Before
	public void setUp() throws InterruptedException {

    	userrepo.deleteAll();
    	List<Address> address = new ArrayList<Address>();
    	address.add(new Address("40","40 Green Ash st","Monroe","USA","08831"));
    	HashSet<UserRole> roles = new HashSet<>();
    	roles.add(UserRole.STUDENT);

		prashant = new User(UUID.randomUUID(),"prashant","p@g.com",PASSWORD_ENCODER.encode("prashpass"),address, UserStatus.ACTIVE,roles);
		atharwa = new User(UUID.randomUUID(),"Atharwa","a@g.com",PASSWORD_ENCODER.encode("athpass"),address, UserStatus.ACTIVE, roles);
		all = userrepo.saveAll(Arrays.asList(prashant,atharwa));
	}	

	@Test
	public void findsUserById() throws Exception {

		assertThat((userrepo.findById(prashant.getId())).toString(),is(Optional.of(prashant).toString()));
		 		
	}
}