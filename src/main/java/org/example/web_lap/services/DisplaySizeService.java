package org.example.web_lap.services;

import org.example.web_lap.dtos.fillter.DisplaySizeParam;
import org.example.web_lap.dtos.fillter.RamParam;
import org.example.web_lap.dtos.request.DisplaySizeRequest;
import org.example.web_lap.entities.DisplaySize;
import org.example.web_lap.entities.Ram;
import org.example.web_lap.repository.DisplaySizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DisplaySizeService {
    @Autowired
    private DisplaySizeRepository displaySizeRepository;

    @Transactional
    public DisplaySize save(DisplaySizeRequest request) {
        DisplaySize displaySize = new DisplaySize();
        displaySize.setName(request.getName());
        return displaySizeRepository.save(displaySize);
    }

    public DisplaySize findById(Long id) {
        return displaySizeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Memory not found"));
    }

    @Transactional
    public void deleteById(Long id) {
        DisplaySize displaySize = findById(id);
        displaySize.setIsDeleted(true);
        displaySizeRepository.save(displaySize);
    }

    @Transactional
    public DisplaySize update(Long id , DisplaySizeRequest request) {
        DisplaySize displaySize = findById(id);
        displaySize.setName(request.getName());
        return displaySizeRepository.save(displaySize);
    }

    public Page<DisplaySize> filter(DisplaySizeParam param , Pageable pageable) {
        return displaySizeRepository.filter(param, pageable);
    }
}
