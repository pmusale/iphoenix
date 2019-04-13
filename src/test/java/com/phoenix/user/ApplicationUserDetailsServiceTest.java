package com.phoenix.user;

	
import com.phoenix.user.UserRepository;
import com.phoenix.user.Users;
import com.phoenix.user.infrastructure.security.ApplicationUserDetailsService;
import com.phoenix.user.infrastructure.security.ApplicationUserDetails;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApplicationUserDetailsServiceTest {

    @Test
    public void givenExistingUsername_whenLoadingUser_userIsReturned() {
        UserRepository repository = mock(UserRepository.class);
        ApplicationUserDetailsService service = new ApplicationUserDetailsService(repository); // <1>
        when(repository.findByEmailIgnoreCase(Users.STUDENT_EMAIL)) // <2>
                                                                    .thenReturn(Optional.of(Users.student()));

        UserDetails userDetails = service.loadUserByUsername(Users.STUDENT_EMAIL); //<3>
        assertThat(userDetails).isNotNull();
        assertThat(userDetails.getUsername()).isEqualTo(Users.STUDENT_EMAIL); //<4>
        assertThat(userDetails).isInstanceOfSatisfying(ApplicationUserDetails.class, //<6>
                                                       applicationUserDetails -> {
                                                           assertThat(applicationUserDetails.getUserId())
                                                                   .isEqualTo(Users.student().getId());
                                                       });
    }

    @Test(expected = UsernameNotFoundException.class) //<7>
    public void givenNotExistingUsername_whenLoadingUser_exceptionThrown() {
        UserRepository repository = mock(UserRepository.class);
        ApplicationUserDetailsService service = new ApplicationUserDetailsService(repository);
        when(repository.findByEmailIgnoreCase(anyString())).thenReturn(Optional.empty());

        service.loadUserByUsername("i@donotexist.com");
    }
}


