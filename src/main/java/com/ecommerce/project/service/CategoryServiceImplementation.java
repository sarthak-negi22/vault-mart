package com.ecommerce.project.service;

import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;
import com.ecommerce.project.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service            // tells spring that it needs to manage this class as a bean
public class CategoryServiceImplementation implements CategoryService {
//    private List<Category> categories = new ArrayList<>();
//    private Long nextId = 1L;       // L specifies that its a long literal, not to be confused with int

    @Autowired      // Tells spring  to automatically inject instance of this type, automatically searches  for a bean of the type CategoryRepository and assigns it to the object
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize) {

//        allows us to specify page number, page size, sortin criteria for fetching a subset of records
        Pageable pageDetails = PageRequest.of(pageNumber, pageSize);
        Page<Category> categoryPage = categoryRepository.findAll(pageDetails);
//        extracting actual list of categories from the Page, as it contains metadata related to page details
        List<Category> categories = categoryPage.getContent();

//        checking if no category is being created or not
        if(categories.isEmpty())
            throw new APIException("No category created till now!");

//        for every category in the list, map it to the category DTO via ModelMapper
        List<CategoryDTO> categoryDTOS = categories.stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        return categoryResponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);

//        checking if same category name exists already or not
        Category categoryFromDb = categoryRepository.findByCategoryName(category.getCategoryName());
        if(categoryFromDb != null)
            throw new APIException("Category with the name "+category.getCategoryName()+" already exists!");

//        saving the category to DB
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO deleteCategory(Long categoryId) {
//        return custom "not found" status if category with that id doesnt exist
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));

//        deleting the category if exists, and then returning the deleted category in the form of DTO
        categoryRepository.delete(category);
        return modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Long categoryId) {
        Category savedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", categoryId));       // if category doesnt exist, throw a custom status of "not found"

//        getting the category from its DTO then assigning the id provided by the user, to the object.
        Category category = modelMapper.map(categoryDTO, Category.class);
        category.setCategoryId(categoryId);
//        updating the changes in DB and returning its DTO.
        savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);

    }

}
