package com.example.jwt_anhchinh2007.controller;

import com.example.jwt_anhchinh2007.dto.request.SignInForm;
import com.example.jwt_anhchinh2007.dto.request.SignUpForm;
import com.example.jwt_anhchinh2007.dto.response.JwtResponse;
import com.example.jwt_anhchinh2007.dto.response.ResponseMessage;
import com.example.jwt_anhchinh2007.model.Role;
import com.example.jwt_anhchinh2007.model.RoleName;
import com.example.jwt_anhchinh2007.model.Users;
import com.example.jwt_anhchinh2007.security.jwt.JwtProvider;
import com.example.jwt_anhchinh2007.security.userprincal.UserPrinciple;
import com.example.jwt_anhchinh2007.service.IRoleService;
import com.example.jwt_anhchinh2007.service.IUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RequestMapping("/api/auth")
@RestController
public class AuthController {
    @Autowired
    private IUsersService usersService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm) {
        if (usersService.existsByUsername(signUpForm.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("The username existed! Please try again! "), HttpStatus.OK);
        }
        if (usersService.existsByEmail(signUpForm.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("The email existed! Please try again! "), HttpStatus.OK);
        }

        Users user = new Users(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getEmail(), passwordEncoder.encode(signUpForm.getPassword()));
        Set<String> strRoles = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleService.findByName(RoleName.ADMIN).orElseThrow(
                            () -> new RuntimeException("Role not found")
                    );
                    roles.add(adminRole);
                    break;
                case "pm":
                    Role pmRole = roleService.findByName(RoleName.PM).orElseThrow(
                            () -> new RuntimeException("Role not found")
                    );
                    roles.add(pmRole);
                    break;
                default:
                    Role userRole = roleService.findByName(RoleName.USER).orElseThrow(
                            () -> new RuntimeException("Role not found")
                    );
                    roles.add(userRole);
                    break;
            }
        });
        user.setRoles(roles);
        usersService.save(user);
        return new ResponseEntity<>(new ResponseMessage("Create user success"), HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(),signInForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getName(), userPrinciple.getAuthorities()));

    }
}
