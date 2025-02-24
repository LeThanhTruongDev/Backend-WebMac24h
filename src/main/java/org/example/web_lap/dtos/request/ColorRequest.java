package org.example.web_lap.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColorRequest {
    @NotBlank(message = "Tên không được để trống")
    private String name;
}
