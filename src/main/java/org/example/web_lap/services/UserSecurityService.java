package org.example.web_lap.services;


import org.example.web_lap.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserSecurityService extends UserDetailsService {
    public User findByUsername(String username);
}