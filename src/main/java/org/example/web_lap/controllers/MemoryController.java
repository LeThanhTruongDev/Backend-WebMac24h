package org.example.web_lap.controllers;

import jakarta.validation.Valid;
import org.example.web_lap.dtos.fillter.MemoryParam;
import org.example.web_lap.dtos.fillter.RamParam;
import org.example.web_lap.dtos.request.MemoryRequest;
import org.example.web_lap.entities.Memory;
import org.example.web_lap.entities.Ram;
import org.example.web_lap.services.MemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/memory")
public class MemoryController {

    @Autowired
    private MemoryService memoryService;

    @PostMapping
    public Memory createMemory (@Valid @RequestBody MemoryRequest request) {
        return memoryService.save(request);
    }
    @GetMapping("/{id}")
    public Memory getMemory (@PathVariable Long id) {
        return memoryService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMemory (@PathVariable Long id) {
        memoryService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Memory updateMemory (@PathVariable Long id, @Valid @RequestBody MemoryRequest request) {
        return memoryService.update(id, request);
    }

    @GetMapping
    public Page<Memory> filter(MemoryParam param , Pageable pageable) {
        return memoryService.filter(param, pageable);
    }

}

