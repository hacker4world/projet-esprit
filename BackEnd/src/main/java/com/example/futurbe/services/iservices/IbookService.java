package com.example.futurbe.services.iservices;

import com.example.futurbe.dto.BookDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IbookService {
    BookDTO createBook(BookDTO bookDto, MultipartFile file);

    List<BookDTO> getAllBooks();
    void deleteBook(long id);
    BookDTO getBookById(long id);
    BookDTO updateBook(BookDTO bookDto, long id, MultipartFile file);
    List<BookDTO> getAllBooksByTitle(String title);
    List<BookDTO> getAllBooksWithEndDate();
    List<BookDTO> getBooksByCategoryId(Long categoryId);
    List<BookDTO> getRecommendedBooks();
}
