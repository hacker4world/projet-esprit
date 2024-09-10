package com.example.futurbe.controller;

import com.example.futurbe.dto.CategoryBookDTO;
import com.example.futurbe.entitys.CategoryBook;
import com.example.futurbe.services.iservices.ICategoryBookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/categories")
@AllArgsConstructor
public class CategoryBookController {
    private final ICategoryBookService categoryBookService;

    @PostMapping("/")
    public ResponseEntity<CategoryBook> createCategoryBook(@RequestBody CategoryBookDTO categoryBookDTO)
    {
        CategoryBook categoryBook = categoryBookService.addCategoryBook(categoryBookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryBook) ;
    }

    @GetMapping("/")
    public ResponseEntity <List<CategoryBook>> getAllCategories()
    {
        List<CategoryBook> categoryBooks = categoryBookService.getAllCategoryBooks();
        return ResponseEntity.status(HttpStatus.OK).body(categoryBooks);
    }
    @PutMapping("/{id}")

    public ResponseEntity<CategoryBook> updateCategoryBook(@PathVariable long id, @RequestBody CategoryBookDTO categoryBookDTO){
        CategoryBook categoryBook = categoryBookService.updateCategoryBook(id, categoryBookDTO);
        return ResponseEntity.status(HttpStatus.OK).body(categoryBook) ;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryBook(@PathVariable long id){
        categoryBookService.deleteCategoryBook(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoryBook> getCategoryById(@PathVariable long id){
        CategoryBook categoryBook = categoryBookService.getCategoryBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryBook);
    }
}
