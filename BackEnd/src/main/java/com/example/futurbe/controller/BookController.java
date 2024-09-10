package com.example.futurbe.controller;

import com.example.futurbe.dto.BookDTO;
import com.example.futurbe.services.iservices.IbookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
@CrossOrigin("*")
public class BookController {
    private final IbookService bookService;

    @PostMapping("/add")
    public ResponseEntity<BookDTO> addBook(@RequestParam("title") String title,
                                           @RequestParam("author") String author,
                                           @RequestParam("description") String description,
                                           @RequestParam("categoryId") Long categoryId,
                                           @RequestParam("imageUrl") MultipartFile imageUrl,
                                           @RequestParam("nbPage") int nbPage) {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(title);
        bookDTO.setAuthor(author);
        bookDTO.setDescription(description);
        bookDTO.setNbPage(nbPage);
        bookDTO.setCategoryId(categoryId);

        BookDTO book = bookService.createBook(bookDTO, imageUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }
    @GetMapping("/")
    public ResponseEntity<List<BookDTO>> getBooks() {
        List <BookDTO> booksDto = bookService.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(booksDto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable long id) {
        BookDTO bookDTO = bookService.getBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(bookDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable long id,
                                              @RequestParam("title") String title,
                                              @RequestParam("author") String author,
                                              @RequestParam("description") String description,

                                              @RequestParam("categoryId") Long categoryId,
                                              @RequestParam(value = "imageUrl", required = false) MultipartFile imageUrl,
                                              @RequestParam("nbPage") int nbPage) {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(title);
        bookDTO.setAuthor(author);
        bookDTO.setDescription(description);
        bookDTO.setNbPage(nbPage);
        bookDTO.setCategoryId(categoryId);

        BookDTO updatedBook = bookService.updateBook(bookDTO, id, imageUrl);
        return ResponseEntity.status(HttpStatus.OK).body(updatedBook);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    //get books by title
    @GetMapping("/search/{title}")
    public ResponseEntity<List<BookDTO>> getBooksByTitle(@PathVariable String title) {
        List <BookDTO> booksDto = bookService.getAllBooksByTitle(title);
        return ResponseEntity.status(HttpStatus.OK).body(booksDto);
    }

    @GetMapping("/books-with-endDate")
    public ResponseEntity<List<BookDTO>> getBooksWithEndDate() {
        List<BookDTO> booksDto = bookService.getAllBooksWithEndDate();
        return ResponseEntity.status(HttpStatus.OK).body(booksDto);
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<BookDTO>> getBooksByCategoryId(@PathVariable Long categoryId) {
        List<BookDTO> books = bookService.getBooksByCategoryId(categoryId);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/recommended")
    public List<BookDTO> getRecommendedBooks() {
        return bookService.getRecommendedBooks();
    }

}
