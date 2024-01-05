package com.mpnsk.expandapistask.dto.request;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class TableRequestDto {
    private String table;
    private List<Map<String, String>> records;
}
