package com.mpnsk.expandapistask.service.impl;

import com.mpnsk.expandapistask.dto.request.TableRequestDto;
import com.mpnsk.expandapistask.repository.ProductRepository;
import com.mpnsk.expandapistask.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private static final String TABLE_NAME = "products";
    private ProductRepository productRepository;

    @Override
    public boolean save(TableRequestDto tableRequestDto) {
        String table = tableRequestDto.getTable();
        List<Map<String, String>> records = tableRequestDto.getRecords();
        if (records.size() > 0) {
            productRepository.save(table, records);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Map<String, String>> getAll() {
        return productRepository.getAll(TABLE_NAME);
    }
}
