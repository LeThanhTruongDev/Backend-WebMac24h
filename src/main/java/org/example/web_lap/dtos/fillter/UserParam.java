package org.example.web_lap.dtos.fillter;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserParam {

    private String username;


    private String fullName;


    private String phoneNumber;

    private Long roleId;
}
