package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.model.exceptions.UserNameNotFoundException;
import mk.ukim.finki.wp.lab.repository.jpa.UserRepositoryInterface;
import mk.ukim.finki.wp.lab.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepositoryInterface userRepositoryInterface;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepositoryInterface userRepositoryInterface, PasswordEncoder passwordEncoder) {
        this.userRepositoryInterface = userRepositoryInterface;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public List<User> getAllUsers() {
        return userRepositoryInterface.findAll();
    }

    @Override
    public Optional<User> saveUser(String username, String password, String name,String surname, LocalDate dateOfBirth) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setName(name);
        user.setSurname(surname);
        user.setDateOfBirth(dateOfBirth);

        return Optional.of(userRepositoryInterface.save(user));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNameNotFoundException {
        User userObj = userRepositoryInterface
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(userObj.getUsername())
                .password(userObj.getPassword())
                .build();
    }

}
