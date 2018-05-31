package net.joedoe.mvcsech2.domains;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {
    private User user;

    @Before
    public void setUp() {
        user = new User();
    }

    @Test
    public void getId() {
        Integer id = 1;
        user.setId(id);
        assertEquals(id, user.getId());
    }

    @Test
    public void getUsername() {
        String username = "joe";
        user.setUsername(username);
        assertEquals(username, user.getUsername());
    }
}