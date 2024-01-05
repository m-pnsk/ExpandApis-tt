package com.mpnsk.expandapistask.security;

import com.mpnsk.expandapistask.dto.request.UserLoginDto;
import com.mpnsk.expandapistask.model.User;
import com.mpnsk.expandapistask.security.jwt.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public String authenticate(UserLoginDto requestDto) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword())
        );

        return jwtUtil.generateToken(authentication.getName(), List.of((
                (User) authentication.getPrincipal())
                .getRole().name()
        ));
    }
}
