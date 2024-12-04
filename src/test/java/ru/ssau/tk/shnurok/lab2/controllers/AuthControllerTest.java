package ru.ssau.tk.shnurok.lab2.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.ssau.tk.shnurok.lab2.model.LoginRequest;
import ru.ssau.tk.shnurok.lab2.model.User;
import ru.ssau.tk.shnurok.lab2.repository.UserRepository;
import ru.ssau.tk.shnurok.lab2.security.JwtResponse;
import ru.ssau.tk.shnurok.lab2.security.JwtUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAuthenticateUser() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("user", "password");
        UserDetails userDetails = mock(UserDetails.class);
        String jwt = "mockJwt";

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(userDetailsService.loadUserByUsername("user")).thenReturn(userDetails);
        when(jwtUtils.generateJwtToken(userDetails)).thenReturn(jwt);

        // Act
        ResponseEntity<JwtResponse> response = authController.authenticateUser(loginRequest);

        // Assert
        assertEquals(ResponseEntity.ok(new JwtResponse(jwt)), response);
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userDetailsService).loadUserByUsername("user");
        verify(jwtUtils).generateJwtToken(userDetails);
    }

    @Test
    public void testRegisterUser_Success() {
        // Arrange
        User user = new User();
        user.setUsername("newuser");
        user.setPassword("password");

        when(userRepository.findByUsername("newuser")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        // Act
        ResponseEntity<?> response = authController.registerUser(user);

        // Assert
        assertEquals(ResponseEntity.ok("User registered successfully!"), response);
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void testRegisterUser_UsernameTaken() {
        // Arrange
        User user = new User();
        user.setUsername("existinguser");
        user.setPassword("password");

        when(userRepository.findByUsername("existinguser")).thenReturn(Optional.of(new User()));

        // Act
        ResponseEntity<?> response = authController.registerUser(user);

        // Assert
        assertEquals(ResponseEntity.badRequest().body("Error: Username is already taken!"), response);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testDeleteUser_Success() {
        // Arrange
        String username = "user";
        UserDetails currentUser = mock(UserDetails.class);
        when(currentUser.getUsername()).thenReturn(username);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(new User()));

        // Act
        ResponseEntity<?> response = authController.deleteUser(username, currentUser);

        // Assert
        assertEquals(ResponseEntity.ok("User deleted successfully!"), response);
        verify(userRepository).delete(any(User.class));
    }

    @Test
    public void testDeleteUser_Unauthorized() {
        // Arrange
        String username = "user";
        UserDetails currentUser = mock(UserDetails.class);
        when(currentUser.getUsername()).thenReturn("otheruser");

        // Act
        ResponseEntity<?> response = authController.deleteUser(username, currentUser);

        // Assert
        assertEquals(ResponseEntity.badRequest().body("Error: You can only delete your own account."), response);
        verify(userRepository, never()).delete(any(User.class));
    }

    @Test
    public void testDeleteUser_NotFound() {
        // Arrange
        String username = "user";
        UserDetails currentUser = mock(UserDetails.class);
        when(currentUser.getUsername()).thenReturn(username);
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<?> response = authController.deleteUser(username, currentUser);

        // Assert
        assertEquals(ResponseEntity.badRequest().body("Error: User not found"), response);
        verify(userRepository, never()).delete(any(User.class));
    }
}
