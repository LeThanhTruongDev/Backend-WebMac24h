package org.example.web_lap.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.web_lap.entities.values.File;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponse {
    private List<File> file;

}