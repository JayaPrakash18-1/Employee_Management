package com.jp.e_m_s.Controller;

import com.jp.e_m_s.DTO.LoginRequestDTO;
import com.jp.e_m_s.DTO.RegisterRequestDTO;
import com.jp.e_m_s.Service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequestDTO request) {
        return authService.login(request);
    }

}