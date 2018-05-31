package net.joedoe.mvcsech2.repositories;

import net.joedoe.mvcsech2.domains.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class IUserRepositoryIT {
    @Autowired
    private IUserRepository userRepository;


    @Before
    public void setUp() {
    }

    @Test
    public void findByUsername() {
        userRepository.save(new User("Joe", "Doe", "joe", "doe"));
        User user = userRepository.findByUsername("joe");
        assertEquals("doe", user.getPassword());
    }
}