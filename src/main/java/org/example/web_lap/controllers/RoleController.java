package org.example.web_lap.controllers;

import jakarta.validation.Valid;
import org.example.web_lap.dtos.fillter.RoleParam;
import org.example.web_lap.dtos.request.RoleRequest;
import org.example.web_lap.entities.Roles;
import org.example.web_lap.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public Roles createRole(@Valid @RequestBody RoleRequest request) {
        return roleService.save(request);
    }

    @GetMapping("/{id}")
    public Roles getRole(@PathVariable Long id) {
        return roleService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id) {  //void k co return
        roleService.deleteById(id);
    }

    @PutMapping ("/{id}")
    public Roles updateRole(@PathVariable Long id, @Valid @RequestBody RoleRequest request) {
        return roleService.update(id, request);
    }

    @GetMapping
    public Page<Roles> filter(RoleParam param , Pageable pageable) {
        return roleService.filter(param, pageable);
    }




}
