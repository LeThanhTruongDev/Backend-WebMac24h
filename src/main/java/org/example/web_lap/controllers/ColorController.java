package org.example.web_lap.controllers;

import jakarta.validation.Valid;
import org.example.web_lap.dtos.fillter.ColorParam;
import org.example.web_lap.dtos.fillter.RamParam;
import org.example.web_lap.dtos.request.ColorRequest;
import org.example.web_lap.entities.Color;
import org.example.web_lap.entities.Ram;
import org.example.web_lap.services.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/color")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @PostMapping
    public Color createColor(@Valid @RequestBody ColorRequest request) {
        return colorService.save(request);

    }

    @GetMapping("/{id}")
    public Color getColor(@PathVariable Long id) {
        return colorService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteColor(@PathVariable Long id) {
        colorService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Color updateColor(@PathVariable Long id, @Valid @RequestBody ColorRequest request) {
        return colorService.update(id, request);
    }

    @GetMapping
    public Page<Color> filter(ColorParam param , Pageable pageable) {
        return colorService.filter(param, pageable);
    }
}
