package net.joedoe.mvcsech2.services;

import net.joedoe.mvcsech2.domains.User;
import net.joedoe.mvcsech2.repositories.IRoleRepository;
import net.joedoe.mvcsech2.repositories.IUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private UserService service;
    @Mock
    private IUserRepository userRepository;
    @Mock
    private IRoleRepository roleRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new UserService(userRepository, roleRepository);
    }

    @Test
    public void listAll() {
        User user = new User();
        List<User> usersData = new ArrayList<>();
        usersData.add(user);
        when(service.listAll()).thenReturn(usersData);
        List<User> users = service.listAll();
        assertEquals(1, users.size());
        verify(userRepository, times(1)).findAll();
    }
}