package com.example.futurbe.services.iservices;

import com.example.futurbe.dto.CategoryBookDTO;
import com.example.futurbe.entitys.CategoryBook;

import java.util.List;

public interface ICategoryBookService {
    CategoryBook addCategoryBook(CategoryBookDTO categoryBook);
    List<CategoryBook> getAllCategoryBooks();
    CategoryBook updateCategoryBook(long id, CategoryBookDTO categoryBook);
    CategoryBook getCategoryBookById(long id);
    void deleteCategoryBook(long id);
}
