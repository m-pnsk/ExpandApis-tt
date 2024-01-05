package com.mpnsk.expandapistask.repository.impl;

import com.mpnsk.expandapistask.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.*;

@AllArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public void save(String table, List<Map<String, String>> products) {
        EntityManager entityManager;
        EntityTransaction transaction = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.createNativeQuery(generateCreateTableQuery(table, products.get(0).keySet())).executeUpdate();
            entityManager.createNativeQuery(generateInsertQuery(table, products)).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save product to db");
        }
    }

    @Override
    public List<Map<String, String>> getAll(String table) {
        EntityManager entityManager;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            return parseResponseToMap(entityManager.createNativeQuery("SHOW COLUMNS FROM " + table).getResultList(),
                    entityManager.createNativeQuery("SELECT * FROM " + table).getResultList()
            );
        } catch (Exception e) {
            throw new RuntimeException("Can't get product from db");
        }
    }

    private List<Map<String, String>> parseResponseToMap(List<?> columnNames, List<?> records) {
        List<Map<String, String>> list = new ArrayList<>();
        for (Object record : records) {
            Map<String, String> map = new HashMap<>();
            Object[] r = (Object[]) record;
            for (int i = 0; i < columnNames.size(); i++) {
                map.put((String) ((Object[]) columnNames.get(i))[0], (String) r[i]);
            }
            list.add(map);
        }
        return list;
    }


    private String generateCreateTableQuery(String table, Set<String> productKeySet) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS ")
                .append(table)
                .append(" (");
        for (String s : productKeySet) {
            sb.append(s).append(" VARCHAR(100),");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(");");
        return sb.toString();
    }

    private String generateInsertQuery(String table, List<Map<String, String>> products) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ")
                .append(table)
                .append(" (");
        for (String s : products.get(0).keySet()) {
            sb.append(s).append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(") VALUES ");

        for (Map<String, String> product : products) {
            generateInsertRow(product, sb);
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
    private void generateInsertRow(Map<String, String> product, StringBuilder sb) {
        sb.append("('");
        for (String s : product.values()) {
            sb.append(s).append("','");
        }
        sb.delete(sb.length()-2, sb.length());
        sb.append("),");
    }
}
