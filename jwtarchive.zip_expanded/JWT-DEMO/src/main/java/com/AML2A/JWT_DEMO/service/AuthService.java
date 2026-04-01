package com.AML2A.JWT_DEMO.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AML2A.JWT_DEMO.repository.UserRepository;
import com.AML2A.JWT_DEMO.security.JwtUtil;

@Service
public class AuthService {
	@Autowired
    UserRepository repo;

    @Autowired
    JwtUtil jwtUtil;

    public String login(String username, String password) {

        var user = repo.findByUsername(username);
        System.out.println(username);
        System.out.println(password);

        if(user != null && user.getPassword().equals(password)) {
            return jwtUtil.generateToken(username);
        }

        return "Invalid Credentials";
    }
}