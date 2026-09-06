package com.jp.e_m_s.DTO;


import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class LoginRequestDTO {

        @Email
        @NotBlank
        private String email;

        @NotBlank
        private String password;
    }

