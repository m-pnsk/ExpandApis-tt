package com.mpnsk.expandapistask.repository;

import java.util.List;
import java.util.Map;

public interface ProductRepository {
    void save(String table, List<Map<String, String>> products);
    List<Map<String, String>> getAll(String table);
}
