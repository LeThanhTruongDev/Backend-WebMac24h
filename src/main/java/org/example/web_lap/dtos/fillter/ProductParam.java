package org.example.web_lap.dtos.fillter;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProductParam {

    private String name;

    private String code;

    private Long categoryId;
}
