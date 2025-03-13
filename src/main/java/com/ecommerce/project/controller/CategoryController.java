package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.service.CategoryService;
import com.ecommerce.project.service.CategoryServiceImplementation;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;      // allows us to set HTTP codes and custom error messages while throwing an exception in REST API's

import java.util.ArrayList;
import java.util.List;

@RestController
// @RequestMapping("/api")      one of the use case of this annotation is to specify that all handles defined here, begins with this route, hence no need to write it again and again below.

public class CategoryController {

//    @Autowired - without constructor injection, this would also work the same
    private CategoryService categoryService;

//    here we are using Constructor injection.
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/public/categories")
//  @RequestMapping(value = "/api/public/categories", method = RequestMethod.GET) this is equivalent to above annotation, another use case of this annotation.
    public ResponseEntity<CategoryResponse> getAllCategories() {
        CategoryResponse categoryResponse = categoryService.getAllCategories();
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }

    @PostMapping("/api/public/categories")
    public ResponseEntity<String> createCategory(@Valid  @RequestBody Category category) {     // @Valid is used to validate method parameters, used in only RequestBody, returns a 400 Bad Request by default.
//        @Valid throws MethodArgumentNotValidException by default, if validation fails.
//        If request is valid as per the model defined, then only it allows the request to controller, else it does not allow the request to controller
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category Added successfully!", HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
//        try {
            String status = categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(status, HttpStatus.OK);
//          return ResponseEntity.ok(status)        another way of doing this
//          return ResponseEntity.status(HttpStatus.ok).body(status);
//
//        } catch (ResponseStatusException e) {
//            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
//        }
    }

    @PutMapping("/api/public/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody Category category, @PathVariable Long categoryId) {
//        try {
            Category savedCategory = categoryService.updateCategory(category, categoryId);
            return new ResponseEntity<>("Category with category ID "+ categoryId + " is updated!", HttpStatus.OK);
//        } catch (ResponseStatusException e) {
//            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
//        }
    }
}
