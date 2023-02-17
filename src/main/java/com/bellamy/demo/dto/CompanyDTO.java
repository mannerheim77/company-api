package com.bellamy.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class CompanyDTO {
    private UUID id;
    private String name;
    private Timestamp recordCreateTs;
    private Timestamp recordUpdateTs;
    private List<OfficeDTO> offices;
}
