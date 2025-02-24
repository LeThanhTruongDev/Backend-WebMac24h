package org.example.web_lap.services;

import org.example.web_lap.dtos.fillter.MemoryParam;
import org.example.web_lap.dtos.request.MemoryRequest;
import org.example.web_lap.entities.Memory;
import org.example.web_lap.repository.MemoryRepository;
import org.example.web_lap.repository.RamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class MemoryService {
    @Autowired
    private MemoryRepository memoryRepository;


    @Transactional
    public Memory save(MemoryRequest request) {
        Memory memory = new Memory();
        memory.setName(request.getName());
        return memoryRepository.save(memory);
    }

    public Memory findById (Long id) {
        return memoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Memory not found"));
    }

    @Transactional
    public void deleteById (Long id) {
        Memory memory = findById(id);
        memory.setIsDeleted(true);
        memoryRepository.save(memory);
    }

    @Transactional
    public Memory update(Long id ,MemoryRequest request) {
        Memory memory = findById(id);
        memory.setName(request.getName());
        return memoryRepository.save(memory);
    }

    public Page<Memory> filter(MemoryParam param, Pageable pageable) {
        return memoryRepository.filter(param, pageable);
    }
}
