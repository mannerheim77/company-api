package com.bellamy.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
public class OfficeDTO {
    private UUID id;
    private String name;
    private Timestamp recordCreateTs;
    private Timestamp recordUpdateTs;
}
