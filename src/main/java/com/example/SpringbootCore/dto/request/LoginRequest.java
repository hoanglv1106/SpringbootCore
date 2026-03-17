// dto/request/LoginRequest.java
package com.example.SpringbootCore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter
public class LoginRequest {

    @NotBlank(message = "Username must not be blank")

    private String username;

    @NotBlank(message = "Password must not be blank")

    private String password;
}
