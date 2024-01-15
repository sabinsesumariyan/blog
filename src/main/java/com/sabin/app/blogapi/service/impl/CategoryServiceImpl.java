package com.sabin.app.blogapi.service.impl;

import com.sabin.app.blogapi.entity.Category;
import com.sabin.app.blogapi.exception.ResourceNotFoundException;
import com.sabin.app.blogapi.payload.CategoryDTO;
import com.sabin.app.blogapi.repository.CategoryRepo;
import com.sabin.app.blogapi.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = dtoToCategory(categoryDTO);
        categoryRepo.save(category);
        return categoryToDto(category);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
        Category category= categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));
        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        category.setCategoryDescription(categoryDTO.getCategoryDescription());
        categoryRepo.save(category);
        return categoryToDto(category);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));
        categoryRepo.deleteById(categoryId);
    }

    @Override
    public CategoryDTO getCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category Id",categoryId));
        return categoryToDto(category);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDTO> categoryDTOS = categories.stream().map(category-> this.categoryToDto(category)).collect(Collectors.toList());
        return categoryDTOS;
    }

    public Category dtoToCategory(CategoryDTO categoryDTO){
        Category category = modelMapper.map(categoryDTO,Category.class);
        return  category;
    }
    public CategoryDTO categoryToDto(Category category){
        CategoryDTO categoryDTO = modelMapper.map(category,CategoryDTO.class);
        return categoryDTO;
    }

}
