package com.fun.controller;

import com.fun.model.IngredientsCategory;
import com.fun.model.IngredientsItem;
import com.fun.request.IngredientsCategoryRequest;
import com.fun.request.IngredientsItemRequest;
import com.fun.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientsController {
    // Add CRUD operations for ingredients
    @Autowired
    private IngredientsService ingredientsService;
    @PostMapping("/category")
    public ResponseEntity<IngredientsCategory> createIngredientCategory(
            @RequestBody IngredientsCategoryRequest ingredientsCategoryRequest
            ) throws Exception {
           IngredientsCategory ingredientsCategory = ingredientsService.createIngredientCategory(
                   ingredientsCategoryRequest.getName(),
                   ingredientsCategoryRequest.getRestaurantId()
                   );
           return new ResponseEntity<>(ingredientsCategory, HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<IngredientsItem> createIngredientItem(
            @RequestBody IngredientsItemRequest ingredientsItemRequest
    ) throws Exception {
        IngredientsItem ingredientsItem = ingredientsService.createIngredientItem(
                ingredientsItemRequest.getResturantId(),
                ingredientsItemRequest.getName(),
                ingredientsItemRequest.getCategoryId()
        );
        return new ResponseEntity<>(ingredientsItem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/stock")
    public ResponseEntity<IngredientsItem> updateIngredientStock(
            @PathVariable Long id
    ) throws Exception {
        IngredientsItem ingredientsItem = ingredientsService.updateStock(id);
        return new ResponseEntity<>(ingredientsItem, HttpStatus.OK);
    }

    @GetMapping("/resturant/{id}")
    public ResponseEntity<List<IngredientsItem>> getResturantIngredients(
            @PathVariable Long id
    ) throws Exception {
        List<IngredientsItem> ingredientsItems = ingredientsService.findResturantIngredient(id);
        return new ResponseEntity<>(ingredientsItems, HttpStatus.OK);
    }

    @GetMapping("/resturant/{id}/category")
    public ResponseEntity<List<IngredientsCategory>> getResturantIngredientsCategory(
            @PathVariable Long id
    ) throws Exception {
        List<IngredientsCategory> ingredientsCategories = ingredientsService.findIngredientCategoryByResturantId(id);
        return new ResponseEntity<>(ingredientsCategories, HttpStatus.OK);
    }
}
