package com.mpnsk.expandapistask.controller;

import com.mpnsk.expandapistask.dto.request.UserLoginDto;
import com.mpnsk.expandapistask.dto.request.UserRegistrationDto;
import com.mpnsk.expandapistask.dto.response.UserResponseDto;
import com.mpnsk.expandapistask.exception.RegistrationException;
import com.mpnsk.expandapistask.security.AuthenticationService;
import com.mpnsk.expandapistask.service.UserService;
import com.mpnsk.expandapistask.service.mapper.UserMapper;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class AuthenticationController {
    private final UserMapper userMapper;
    private final UserService userService;
    private final AuthenticationService authService;

    @PermitAll
    @PostMapping("/add")
    public UserResponseDto add(@RequestBody @Valid UserRegistrationDto requestDto) throws RegistrationException {
        return userMapper.mapToDto(
                userService.add(userMapper.mapToEntity(requestDto))
        );
    }

    @PermitAll
    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody @Valid UserLoginDto userLoginDto) {
        return new ResponseEntity<>(Map.of("token", authService.authenticate(userLoginDto)), HttpStatus.OK);
    }
}
