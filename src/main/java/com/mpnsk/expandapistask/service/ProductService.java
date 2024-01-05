package com.mpnsk.expandapistask.service;

import com.mpnsk.expandapistask.dto.request.TableRequestDto;

import java.util.List;
import java.util.Map;

public interface ProductService {
    boolean save(TableRequestDto tableRequestDto);
    List<Map<String, String>> getAll();
}
