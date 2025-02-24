package org.example.web_lap.services;

import org.example.web_lap.dtos.fillter.ColorParam;
import org.example.web_lap.dtos.request.ColorRequest;
import org.example.web_lap.entities.Color;
import org.example.web_lap.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ColorService {
    @Autowired
    private ColorRepository colorRepository;

    @Transactional
    public Color save(ColorRequest request) {
        Color color = new Color();
        color.setName(request.getName());
        return colorRepository.save(color);
    }

    public Color findById (Long id) {
        return colorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ram not found"));
    }

    @Transactional
    public void deleteById (Long id) {
        Color color = findById(id);
        color.setIsDeleted(true);
        colorRepository.save(color);
    }

    @Transactional
    public Color update(Long id , ColorRequest request) {
        Color color = findById(id);
        color.setName(request.getName());
        return colorRepository.save(color);
    }

    public Page<Color> filter(ColorParam param , Pageable pageable) {
        return colorRepository.filter(param, pageable);
    }

}

