package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryServiceImpl implements  CategoryService {

    private List<Category> categories=new ArrayList<>();
    private  Long nextId=1L;

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }
    @Override
    public void createCategory(Category category) {

       if(category.getCategoryName()==null || category.getCategoryName().trim().isEmpty()){
           throw  new RuntimeException("Category name cannot be empty");
       }
       category.setCategoryId(nextId++);

       categories.add(category);

    }




}
