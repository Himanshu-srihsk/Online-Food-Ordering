package com.fun.controller;

import com.fun.model.Category;
import com.fun.model.User;
import com.fun.service.CategoryService;
import com.fun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @PostMapping("/admin/category")
    public ResponseEntity<Category> createCategory(
            @RequestBody Category category,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user =  userService.findUserByJwtToken(jwt);
        Category createdCategory = categoryService.createCategory(category.getName(), user.getId());
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping("/category/resturant/{id}")
    public ResponseEntity<List<Category>> getResturantCategory(
           @PathVariable Long id,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user =  userService.findUserByJwtToken(jwt);
        List<Category> categories = categoryService.findCategoryByResturantId(id);
        return new ResponseEntity<>(categories, HttpStatus.CREATED);
    }
}
