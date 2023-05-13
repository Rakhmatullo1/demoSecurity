package com.rakhmatullo.demo.rest;


import com.rakhmatullo.demo.config.JwtUtil;
import com.rakhmatullo.demo.dao.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private  final AuthenticationManager manager;

    private final UserDAO userDAO;

    private final JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<String> auth(@RequestBody AuthenticationRequest request) {
        manager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        final UserDetails user = userDAO.findByUserEmail(request.getEmail());
        System.out.println(user);
        if(user!=null) {
            return ResponseEntity.ok(jwtUtil.generateToken(user));
        }
        return ResponseEntity.status(400).body("Some error has occurred");
    }
}
