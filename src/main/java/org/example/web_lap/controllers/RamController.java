package org.example.web_lap.controllers;

import jakarta.validation.Valid;
import org.example.web_lap.dtos.fillter.RamParam;
import org.example.web_lap.dtos.fillter.RoleParam;
import org.example.web_lap.dtos.request.RamRequest;
import org.example.web_lap.entities.Ram;
import org.example.web_lap.entities.Roles;
import org.example.web_lap.services.RamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/api/v1/ram")
public class RamController {

    @Autowired
    private RamService ramService;

    @PostMapping
    public Ram createRam (@Valid @RequestBody RamRequest request) {
        return ramService.save(request);
    }

    @GetMapping("/{id}")
    public Ram getRam(@PathVariable Long id) {
        return ramService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRam(@PathVariable Long id) {
        ramService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Ram updateRam(@PathVariable Long id, @Valid @RequestBody RamRequest request) {
        return ramService.update(id, request);
    }

    @GetMapping
    public Page<Ram> filter(RamParam param , Pageable pageable) {
        return ramService.filter(param, pageable);
    }
}
