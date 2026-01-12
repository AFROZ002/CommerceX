package com.ecommerce.project.service;

import com.ecommerce.project.exception.APIException;
import com.ecommerce.project.exception.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;



@Service
public class CategoryServiceImpl implements  CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {

       List<Category>categories=categoryRepository.findAll();
       if(categories.isEmpty()){
           throw new APIException("No categories found  !!!!");

       }
       return categories;
    }
    @Override
    public void createCategory(Category category) {

        Category savedCategory=categoryRepository.findByCategoryName(category.getCategoryName());

        if(savedCategory!=null){
             throw  new APIException("Category with name "+category.getCategoryName()+" already exists  !!!!");
        }

       if(category.getCategoryName()==null || category.getCategoryName().trim().isEmpty()){
           throw  new RuntimeException("Category name cannot be empty");
       }


       categoryRepository.save(category);

    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category","CategoryId",categoryId)
                );

        categoryRepository.delete(category);
        return "Category with id " + categoryId + " deleted successfully";

    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category ","category",categoryId)
                );

        existingCategory.setCategoryName(category.getCategoryName());

        return categoryRepository.save(existingCategory);



    }


}
