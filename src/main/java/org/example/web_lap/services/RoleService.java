package org.example.web_lap.services;

import org.example.web_lap.dtos.fillter.RoleParam;
import org.example.web_lap.dtos.request.RoleRequest;
import org.example.web_lap.entities.Roles;
import org.example.web_lap.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public Roles save(RoleRequest request) {
        Roles role = new Roles();
        role.setName(request.getName());
       return roleRepository.save(role);
    }

    public static void main(String[] args) {
        String pass = "123456789";
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println( encoder.encode(pass));
    }

    public Roles findById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    @Transactional
    public void deleteById(Long id) {
        Roles role = findById(id);
        role.setIsDeleted(true);
        roleRepository.save(role);
    }

    @Transactional
    public Roles update(Long id ,RoleRequest request) {
        Roles role = findById(id); // TIM KIEM BANG GHI THEO ID
        role.setName(request.getName()); //LAY BANG GHI CAP NHAT LAI DU LIEU
        return roleRepository.save(role); //LUU XUONG DB
    }


    public Page<Roles> filter(RoleParam param , Pageable pageable){
        return roleRepository.filter(param, pageable);
    }



}
