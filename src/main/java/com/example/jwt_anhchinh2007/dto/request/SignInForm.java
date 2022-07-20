package com.example.jwt_anhchinh2007.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInForm {
    private String username;
    private String password;

}
