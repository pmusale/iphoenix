package com.phoenix.user;
import com.phoenix.user.infrastructure.SpringProfiles;
import com.phoenix.user.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Component //<1>
@Profile(SpringProfiles.DEV) //<2>
public class DevelopmentDbInitializer implements ApplicationRunner {

    private final UserService userService;
    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    private List<Address> address = new ArrayList<Address>();
    private HashSet<UserRole> roles = new HashSet<>();

    @Autowired
    public DevelopmentDbInitializer(UserService userService) { //<3>
        this.userService = userService;
        address.add(new Address("40","40 Green Ash st","Monroe","USA","08831"));    	
    	roles.add(UserRole.STUDENT);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) { //<4>
        createTestUsers();
    }

    private void createTestUsers() {
        userService.createUser(UUID.randomUUID(),"atharwa","officer@example.com",PASSWORD_ENCODER.encode("atharwapass"), address, UserStatus.ACTIVE, roles); //<5>
    }
}
