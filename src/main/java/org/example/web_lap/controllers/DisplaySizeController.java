package org.example.web_lap.controllers;

import jakarta.validation.Valid;
import org.example.web_lap.dtos.fillter.DisplaySizeParam;
import org.example.web_lap.dtos.fillter.MemoryParam;
import org.example.web_lap.dtos.request.DisplaySizeRequest;
import org.example.web_lap.entities.DisplaySize;
import org.example.web_lap.entities.Memory;
import org.example.web_lap.services.DisplaySizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/displaysize")
public class DisplaySizeController {

    @Autowired
    private DisplaySizeService displaySizeService;

    @PostMapping
    public DisplaySize createDisplaySize(@Valid @RequestBody DisplaySizeRequest request) {
        return displaySizeService.save(request);
    }

    @GetMapping("/{id}")
    public DisplaySize getDisplaySize(@PathVariable long id) {
        return displaySizeService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteDisplaySize(@PathVariable long id) {
        displaySizeService.deleteById(id);
    }

    @PutMapping("/{id}")
    public DisplaySize updateDisplaySize(@PathVariable long id, @Valid @RequestBody DisplaySizeRequest request) {
        return displaySizeService.update(id, request);
    }

    @GetMapping
    public Page<DisplaySize> filter(DisplaySizeParam param , Pageable pageable) {
        return displaySizeService.filter(param, pageable);
    }
}
