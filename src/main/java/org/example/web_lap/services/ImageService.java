package org.example.web_lap.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.example.web_lap.dtos.request.ImageRequest;
import org.example.web_lap.dtos.response.ImageResponse;
import org.example.web_lap.entities.Image;
import org.example.web_lap.entities.values.File;
import org.example.web_lap.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    private String uploadDir = "uploads";


    private String portServer = "8080";

    private String hostServer = "http://localhost";

    public ImageResponse uploadImage(ImageRequest dto) throws Exception {
        List<Image> images = new ArrayList<>();
        List<File> files = new ArrayList<>();

        if (dto.getFile() == null || dto.getFile().isEmpty()) {
            throw new Exception("File is empty");
        }

        List<String> codes = upLoadToServer(dto.getFile());

        for (int i = 0; i < dto.getFile().size(); i++) {
            MultipartFile file = dto.getFile().get(i);
            if (file != null && !file.isEmpty()) {
                String originalFilename = file.getOriginalFilename();
                String code = codes.get(i);

                Image image =
                        createAndSaveImage(code, originalFilename);
                images.add(image);

                File fileResponse = new File();
                fileResponse.setUrl(image.getUrl());
                files.add(fileResponse);
            }
        }

        ImageResponse response = new ImageResponse();
        response.setFile(files);

        return response;
    }

    private List<String> upLoadToServer(List<MultipartFile> files) throws Exception {
        if (files == null || files.isEmpty()) {
            throw new Exception("File is empty");
        }

        Path path = Paths.get(uploadDir);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        List<String> codes = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file != null && !file.isEmpty()) {
                String code = RandomStringUtils.randomAlphanumeric(10);
                String originalFilename = file.getOriginalFilename();
                Path fileSave = path.resolve(code + "-" + Objects.requireNonNull(originalFilename));
                try (InputStream is = file.getInputStream()) {
                    Files.copy(is, fileSave, StandardCopyOption.REPLACE_EXISTING);
                }
                codes.add(code);
            }
        }

        return codes;
    }

    private String generateImageUrl(String code, String originalFilename) {
        return hostServer + ":" + portServer + "/" + code + "-" + originalFilename;
    }

    private Image createAndSaveImage(
            String code, String originalFilename) {
        Image image =
                Image.builder()
                        .url(generateImageUrl(code, originalFilename))
                        .build();
        return imageRepository.save(image);
    }
}
