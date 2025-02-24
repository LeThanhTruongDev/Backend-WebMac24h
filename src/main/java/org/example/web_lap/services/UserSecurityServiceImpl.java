package org.example.web_lap.services;

import org.example.web_lap.entities.Roles;
import org.example.web_lap.entities.User;
import org.example.web_lap.repository.RoleRepository;
import org.example.web_lap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserSecurityServiceImpl implements UserSecurityService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Khi "dap" ở Security Configuration được gọi thì nó sẽ chay hàm này để lấy ra user trong csdl
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Tài khoản không tồn tại!");
        }

        org.springframework.security.core.userdetails.User userDetail = new org.springframework.security.core.userdetails.User
                (user.getUsername(), user.getPassword(), rolesToAuthorities(List.of(user.getRole())));
        return userDetail;
    }

    // Hàm để lấy role
    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Roles> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}