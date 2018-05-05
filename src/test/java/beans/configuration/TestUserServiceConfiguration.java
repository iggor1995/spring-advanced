package beans.configuration;

import beans.daos.mocks.UserDAOMock;
import beans.models.Role;
import beans.models.User;
import beans.services.api.UserService;
import beans.services.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class TestUserServiceConfiguration {

    @Bean
    public User testUser1() {
        return new User(0, "dmitriy.vbabichev@gmail.com", "Dmytro Babichev", java.time.LocalDate.of(1992, 4, 29), "asd", Role.ROLE_REGISTERED_USER);
    }

    @Bean
    public User testUser2() {
        return new User(1, "laory@yandex.ru", "Dmytro Babichev", java.time.LocalDate.of(1992, 4, 29), "asd", Role.ROLE_REGISTERED_USER);
    }

    @Bean
    public UserDAOMock userDAO() {
        return new UserDAOMock(Arrays.asList(testUser1(), testUser2()));
    }

    @Bean(name = "testUserServiceImpl")
    public UserService userServiceImpl() {
        return new UserServiceImpl(userDAO());
    }
}
