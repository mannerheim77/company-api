package com.bellamy.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class EmployeeDTO {
    private UUID id;
    private String firstName;
    private String lastName;
    private String emailId;
    private OfficeDTO office;
}
