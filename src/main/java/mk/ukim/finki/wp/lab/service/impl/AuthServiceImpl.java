package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.wp.lab.repository.jpa.UserRepositoryInterface;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.wp.lab.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepositoryInterface userRepositoryInterface;

    public AuthServiceImpl(PasswordEncoder passwordEncoder, UserRepositoryInterface userRepositoryInterface) {
        this.passwordEncoder = passwordEncoder;
        this.userRepositoryInterface = userRepositoryInterface;
    }
    private boolean credentialsInvalid(String username, String password) {
        return username == null || password == null || username.isEmpty() || password.isEmpty();
    }

    @Override
    public User login(String username, String password) {
        if (credentialsInvalid(username, password)) {
            throw new InvalidArgumentsException();
        }

        User user = userRepositoryInterface.findByUsername(username)
                .orElseThrow(InvalidUserCredentialsException::new);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidUserCredentialsException();
        }

        return user;
    }


    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, LocalDate dateOfBirth) {
        if(credentialsInvalid(username,password)){
            throw new InvalidArgumentsException();
        }
        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setName(name);
        user.setSurname(surname);
        user.setDateOfBirth(dateOfBirth);

        return userRepositoryInterface.save(user);
    }
}
