package com.mpnsk.expandapistask.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDto {
    @NotBlank(message = "Username can't be null or blank!")
    private String username;
    @NotBlank(message = "Password can't be null or blank!")
    private String password;
}
