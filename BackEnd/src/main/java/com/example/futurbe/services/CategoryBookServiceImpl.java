package com.example.futurbe.services;

import com.example.futurbe.dto.CategoryBookDTO;
import com.example.futurbe.entitys.CategoryBook;
import com.example.futurbe.repositorys.CategoryRepository;
import com.example.futurbe.services.iservices.ICategoryBookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryBookServiceImpl implements ICategoryBookService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryBook addCategoryBook(CategoryBookDTO categoryBookDTO) {
    CategoryBook category = new CategoryBook();
    category.setCategoryName(categoryBookDTO.getCategoryName());
    category.setDescription(categoryBookDTO.getDescription());
    return categoryRepository.save(category);
    }

    @Override
    public List<CategoryBook> getAllCategoryBooks() {
        return (List<CategoryBook>) categoryRepository.findAll();
    }

    @Override
    public CategoryBook updateCategoryBook(long id, CategoryBookDTO categoryBookDto) {
        Optional<CategoryBook> categoryBookopt = categoryRepository.findById(id);
        if(categoryBookopt.isEmpty()){

            throw new RuntimeException("Category not found");
        }
        CategoryBook category = categoryBookopt.get();
        category.setCategoryName(categoryBookDto.getCategoryName());
        category.setDescription(categoryBookDto.getDescription());
        return categoryRepository.save(category);
    }

    @Override
    public CategoryBook getCategoryBookById(long id) {
        Optional<CategoryBook> categoryBookopt = categoryRepository.findById(id);
        if(categoryBookopt.isEmpty()){
            throw new RuntimeException("Category not found");
        }
        return categoryBookopt.get();
    }

    @Override
    public void deleteCategoryBook(long id) {
        Optional<CategoryBook> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty())
            throw new IllegalArgumentException("Category not found");
        categoryRepository.deleteById(id);
    }

}
