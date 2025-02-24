package org.example.web_lap.controllers;

import io.jsonwebtoken.io.IOException;
import org.example.web_lap.dtos.request.ImageRequest;
import org.example.web_lap.dtos.response.ImageResponse;
import org.example.web_lap.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {
    @Autowired
    private ImageService imageUploadService;

    @PostMapping
    public ResponseEntity<ImageResponse> uploadImage(@ModelAttribute ImageRequest dto)
            throws Exception {
        return ResponseEntity.ok(imageUploadService.uploadImage(dto));
    }


}
