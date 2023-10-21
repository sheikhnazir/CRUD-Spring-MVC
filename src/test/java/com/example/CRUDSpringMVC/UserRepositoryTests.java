package com.example.CRUDSpringMVC;

import com.example.CRUDSpringMVC.users.User;
import com.example.CRUDSpringMVC.users.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository repo;

    @Test
    public void testAddNew() {
        User user = new User();
        user.setEmail("shiekhnazir@gmail.com");
        user.setPassword("jjshhsh");
        user.setFirstName("Shiekh Mohammad");
        user.setLastName("Hammad");
        user.setEnabled(true);
        User savedUser = repo.save(user);
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        Iterable<User> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for(User user:users) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate() {
        Integer userId = 1;
        Optional<User> optionalUser = repo.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword("hello2000");
            repo.save(user);

            User updatedUser = repo.findById(userId).get();
            Assertions.assertThat(updatedUser.getPassword()).isEqualTo("hello2000");
        } else {
            // Handle the case when the user with the given userId is not found
            // You can fail the test with an assertion or log a message.
            Assertions.fail("User with ID " + userId + " not found."); // Fails the test
            // Alternatively, you can log a message:
            // System.out.println("User with ID " + userId + " not found.");
            // Or use a logger to log the message in your preferred logging framework.
        }
    }

    @Test
    public void testGet() {
        Integer userId = 2;
        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete() {
        Integer userId = 2;
        repo.deleteById(userId);

        Optional<User> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }

}
