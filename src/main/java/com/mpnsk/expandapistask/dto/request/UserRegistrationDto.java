package com.mpnsk.expandapistask.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationDto {
    @NotBlank
    private String username;
    @Size(min = 6, message = "Too short")
    private String password;
}
