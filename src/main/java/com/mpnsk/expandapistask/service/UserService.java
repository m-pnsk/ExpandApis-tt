package com.mpnsk.expandapistask.service;

import com.mpnsk.expandapistask.exception.RegistrationException;
import com.mpnsk.expandapistask.model.User;

public interface UserService {
    User add(User request) throws RegistrationException;
    User findById(Long userId);
    User findByEmail(String email);
}
