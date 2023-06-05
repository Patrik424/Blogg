package com.patrik.blogg.service;

import com.patrik.blogg.exception.CategoryNotFoundException;
import com.patrik.blogg.model.Category;
import com.patrik.blogg.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategory(){return categoryRepository.findAll();}

    public Category getCategoryById(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public Category newCategory(Category category){return categoryRepository.save(category);}

    public Category updateCategory(Long id, Category editCategory){

        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(editCategory.getName());
                    return categoryRepository.save(category);
                })
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public void deleteCategory(Long id){
        categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
        categoryRepository.deleteById(id);
    }
}
