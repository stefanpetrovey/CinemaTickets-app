package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();
    Optional<User> saveUser(String username, String password, String name, String surname, LocalDate dateOfBirth);
    UserDetails loadUserByUsername(String username);
}
