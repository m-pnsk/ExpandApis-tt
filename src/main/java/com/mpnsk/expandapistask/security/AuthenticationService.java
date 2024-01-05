package com.mpnsk.expandapistask.security;


import com.mpnsk.expandapistask.dto.request.UserLoginDto;

public interface AuthenticationService {
    String authenticate(UserLoginDto requestDto);
}
