package com.vincentmegia;

import com.vincentmegia.repositories.UserRepository;
import org.h2.engine.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserRepositoryTest {
    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateUserName() {
        var result = userRepository.updateUsername(1, "fake-username");
        assertEquals(result, true);
    }
}
