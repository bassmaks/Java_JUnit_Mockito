package edu.school21.services;

import edu.school21.exeptions.AlreadyAuthenticatedException;
import edu.school21.exeptions.EntityNotFoundException;
import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UsersServiceImplTest {
    private UsersRepository usersRepository;
    private User user1;
    private User user2;

    @BeforeEach
    public void init() {
        user1 = new User(1, "maksim", "password", true );
        user2 = new User(2, "boris", "nopass", false);

        usersRepository = Mockito.mock(UsersRepository.class);
        Mockito.when(usersRepository.findByLogin("maksim")).thenReturn(
                new User(1, "maksim", "password", true ));
        Mockito.when(usersRepository.findByLogin("boris")).thenReturn(
                new User(2, "boris", "nopass", false));
        Mockito.when(usersRepository.findByLogin("smbody")).thenThrow(new EntityNotFoundException());
    }

    @Test
    public void testCorrectPasswordFalse() {
        Assertions.assertTrue(new UsersServiceImpl(usersRepository).
                authenticate(user2.getLogin(), user2.getPassword()));
    }

    @Test
    public void testIncorrectPasswordFalse() {
        Assertions.assertFalse(new UsersServiceImpl(usersRepository).
                authenticate(user2.getLogin(), "wrongpass"));
    }

    @Test
    public void testCorrectPasswordTrue() {
        Assertions.assertThrows(AlreadyAuthenticatedException.class,
                ()-> new UsersServiceImpl(usersRepository).authenticate(user1.getLogin(), user1.getPassword()));
    }

    @Test
    public void testIncorrectPasswordTrue() {
        Assertions.assertThrows(AlreadyAuthenticatedException.class,
                ()-> new UsersServiceImpl(usersRepository).authenticate(user1.getLogin(), "wrongpass"));
    }

    @Test
    public void testIncorrectUser() {
        Assertions.assertThrows(RuntimeException.class,
                () -> new UsersServiceImpl(usersRepository).authenticate("smbody", "wrongpass"));
    }
}
