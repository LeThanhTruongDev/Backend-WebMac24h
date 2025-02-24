package org.example.web_lap.services;

import org.example.web_lap.dtos.fillter.RamParam;
import org.example.web_lap.dtos.request.RamRequest;
import org.example.web_lap.entities.Ram;
import org.example.web_lap.repository.RamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RamService {
    @Autowired
    private RamRepository ramRepository;

    @Transactional
    public Ram save(RamRequest request) {
        Ram ram = new Ram();
        ram.setName(request.getName());
        return ramRepository.save(ram);
    }

    public Ram findById (Long id) {
        return ramRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ram not found")) ;
    }

    @Transactional
    public void deleteById (Long id) {
        Ram ram = findById(id);
        ram.setIsDeleted(true);
        ramRepository.save(ram);
    }

    @Transactional
    public Ram update(Long id ,RamRequest request) {
        Ram ram = findById(id);
        ram.setName(request.getName());
        return ramRepository.save(ram);
    }

    public Page<Ram> filter(RamParam param , Pageable pageable) {
        return ramRepository.filter(param, pageable);
    }




}


