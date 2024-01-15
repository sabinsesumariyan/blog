package com.sabin.app.blogapi.controller;
import com.sabin.app.blogapi.entity.Category;
import com.sabin.app.blogapi.payload.ApiResponse;
import com.sabin.app.blogapi.payload.CategoryDTO;
import com.sabin.app.blogapi.repository.CategoryRepo;
import com.sabin.app.blogapi.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepo categoryRepo;

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO createCategoryDto = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(createCategoryDto, HttpStatus.CREATED);
    }
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO,@PathVariable("categoryId") Integer categoryId){
        CategoryDTO updateCategoryDto = categoryService.updateCategory(categoryDTO,categoryId);
        return ResponseEntity.ok(categoryDTO);
    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("Category deleted successfully",true),HttpStatus.OK);
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable("categoryId") Integer categoryId){
        CategoryDTO categoryDTO = categoryService.getCategory(categoryId);
        return ResponseEntity.ok(categoryDTO);
    }
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategory(){
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategory();
        return ResponseEntity.ok(categoryDTOS);
    }
    @GetMapping("/oho")
    public ResponseEntity<Category> getExtra(){
        Category category=categoryRepo.findById(2).orElseThrow();
        int a=10;
        return  ResponseEntity.ok(category);
    }

}
