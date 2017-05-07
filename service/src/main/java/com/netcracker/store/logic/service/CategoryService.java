package com.netcracker.store.logic.service;

import com.netcracker.store.persistence.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by A-one on 07.05.2017.
 */
@Service
public interface CategoryService {
    List<Category> getAllCategories();
    void addCategory(Category category);
    void deleteCategory(int id);
    void updateCategory(Category category);
}
