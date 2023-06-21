package com.patrik.blogg.controller;

import com.patrik.blogg.model.Category;
import com.patrik.blogg.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(value = "http://localhost:5173")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public List<Category> getAllCategories(){return categoryService.getCategory();}

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategoriesById(@PathVariable("id") Long id){
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @PostMapping("/newcategory")
    public ResponseEntity<Category> newCategory(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.newCategory(category), HttpStatus.CREATED);
    }

    @PutMapping("/editcategory/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id, @RequestBody Category category){
        return new ResponseEntity<>(categoryService.updateCategory(id,category), HttpStatus.OK);
    }

    @DeleteMapping("/deletecategory/{id}")
    public ResponseEntity<String>deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<String>( HttpStatus.OK);
    }
}
