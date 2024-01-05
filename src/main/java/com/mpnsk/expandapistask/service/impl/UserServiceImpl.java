package com.mpnsk.expandapistask.service.impl;

import com.mpnsk.expandapistask.exception.RegistrationException;
import com.mpnsk.expandapistask.model.User;
import com.mpnsk.expandapistask.repository.UserRepository;
import com.mpnsk.expandapistask.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public User add(User user) throws RegistrationException {
        if (userRepository.findUserByUsername(user.getUsername()).isPresent()) {
            throw new RegistrationException("Unable to complete registration");
        }
        return userRepository.save(user
                .setPassword(encoder.encode(user.getPassword()))
                .setRole(User.Role.USER));
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException("Not found user by id: " + userId)
        );
    }

    @Override
    public User findByEmail(String username) {
        return userRepository.findUserByUsername(username).orElseThrow(
                () -> new NoSuchElementException("Not found user by username: " + username));
    }

}
