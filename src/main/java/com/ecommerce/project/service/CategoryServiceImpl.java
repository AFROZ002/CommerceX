package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImpl implements  CategoryService {

//    private List<Category> categories=new ArrayList<>();
//    private  Long nextId=1L;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    @Override
    public void createCategory(Category category) {

       if(category.getCategoryName()==null || category.getCategoryName().trim().isEmpty()){
           throw  new RuntimeException("Category name cannot be empty");
       }
//       category.setCategoryId(nextId++);

       categoryRepository.save(category);

    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found")
                );

        categoryRepository.delete(category);
        return "Category with id " + categoryId + " deleted successfully";

    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found")
                );

        existingCategory.setCategoryName(category.getCategoryName());

        return categoryRepository.save(existingCategory);



    }


}
