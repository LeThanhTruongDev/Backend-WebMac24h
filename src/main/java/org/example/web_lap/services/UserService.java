package org.example.web_lap.services;

import jakarta.transaction.Transactional;
import org.example.web_lap.dtos.fillter.UserParam;
import org.example.web_lap.dtos.request.LoginRequest;
import org.example.web_lap.dtos.request.UserRequest;
import org.example.web_lap.dtos.response.ApiResponse;
import org.example.web_lap.entities.Cart;
import org.example.web_lap.entities.Roles;
import org.example.web_lap.entities.User;
import org.example.web_lap.repository.CartRepository;
import org.example.web_lap.repository.RoleRepository;
import org.example.web_lap.repository.UserRepository;
import org.example.web_lap.security.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private JwtService jwtService;




    public static void main(String[] args) {
        String a = "123456789";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode(a));
    }


    @Transactional
    public User createUser(UserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setAddress(request.getAddress());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole(findByName("ROLE_USER"));
        // luu cart
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setCartItems(Collections.emptyList());
        cartRepository.save(cart);

        return userRepository.save(user);
    }


    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = findById(id);
        user.setIsDeleted(true);
        userRepository.save(user);
    }

    @Transactional
    public User updateUser(Long id, UserRequest request) {
        User user = findById(id);
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setAddress(request.getAddress());
        user.setPhoneNumber(request.getPhoneNumber());
        return userRepository.save(user);

    }


    private Roles findByName(String name) {
        return roleRepository.findByName(name);
    }

    public Page<User> filter(UserParam param, Pageable pageable) {
        return userRepository.filter(param, pageable);
    }

    public ApiResponse login(LoginRequest request) {
        // Xử lý xác thực người dùng
        try {
            // authentication sẽ giúp ta lấy dữ liệu từ db để kiểm tra
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            // Nếu xác thực thành công
            if (authentication.isAuthenticated()) {
                // Tạo token cho người dùng
                User user = userRepository.findByUsername(request.getUsername());
                Cart cart = cartRepository.findByUserId(user.getId()).orElseThrow(() -> new RuntimeException("Cart not found") );
                final String jwtToken = jwtService.generateToken(request.getUsername());
                return ApiResponse.builder()
                        .token(jwtToken)
                        .user(user).cart(cart).build();

            }
        } catch (AuthenticationException e) {
            throw new RuntimeException("Vui long kiem tra lai mk");
        }
        return null;
    }


}
