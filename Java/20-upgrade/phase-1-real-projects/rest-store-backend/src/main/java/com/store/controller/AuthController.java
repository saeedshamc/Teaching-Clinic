package com.store.controller;

import com.store.dto.*;
import com.store.entity.User;
import com.store.security.JwtUtil;
import com.store.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest req) {
        User user = userService.register(req.username(), req.email(), req.password());
        return ResponseEntity.status(201).body(Map.of(
            "id", user.getId(), "username", user.getUsername(), "email", user.getEmail()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req) {
        try {
            User user = userService.findByUsername(req.username());
            if (!userService.checkPassword(req.password(), user.getPassword())) {
                return ResponseEntity.status(401).body(Map.of("error", "رمز عبور اشتباه است"));
            }
            String token = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(Map.of(
                "token", token,
                "username", user.getUsername(),
                "role", user.getRole().name()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication auth) {
        if (auth == null) return ResponseEntity.status(401).build();
        User user = userService.findByUsername(auth.getName());
        return ResponseEntity.ok(Map.of(
            "username", user.getUsername(), "email", user.getEmail(),
            "role", user.getRole().name()));
    }
}
