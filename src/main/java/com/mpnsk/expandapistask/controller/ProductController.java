package com.mpnsk.expandapistask.controller;

import com.mpnsk.expandapistask.dto.request.TableRequestDto;
import com.mpnsk.expandapistask.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @PostMapping("/add")
    public boolean add(@RequestBody TableRequestDto tableRequestDto) {
        productService.save(tableRequestDto);
        return !tableRequestDto.getRecords().isEmpty();
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<>(Map.of("records", productService.getAll()), HttpStatus.OK);
    }
}
