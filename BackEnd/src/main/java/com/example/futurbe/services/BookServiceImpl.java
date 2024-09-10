package com.example.futurbe.services;

import com.example.futurbe.dto.BookDTO;
import com.example.futurbe.entitys.Book;
import com.example.futurbe.entitys.CategoryBook;
import com.example.futurbe.repositorys.BookRepository;
import com.example.futurbe.repositorys.CategoryRepository;
import com.example.futurbe.repositorys.ReservationBookRepository;
import com.example.futurbe.services.iservices.IbookService;
import jakarta.servlet.ServletContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class BookServiceImpl implements IbookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final ReservationBookRepository reservationBookRepository;
    private final ServletContext servletContext;
    private static final String UPLOAD_DIR = "C:\\Users\\Lenovo\\Documents\\FuturEprit\\FrontEnd\\src\\assets\\img";

    public List<BookDTO> getRecommendedBooks() {
        List<Object[]> counts = reservationBookRepository.countReservationsByBook();
        List<Long> recommendedBookIds = counts.stream()
                .filter(count -> (long) count[1] >= 3)
                .map(count -> (Long) count[0])
                .collect(Collectors.toList());

        List<Book> books = new ArrayList<>();
        bookRepository.findAllById(recommendedBookIds).forEach(books::add);

        return books.stream()
                .map(book -> {
                    BookDTO bookDTO = book.getDto();
                    bookDTO.setRecommended(true);
                    return bookDTO;
                })
                .collect(Collectors.toList());
    }
    public BookDTO createBook(BookDTO bookDto, MultipartFile imageUrl) {
        Book book = new Book();
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(bookDto.getTitle());
        book.setDescription(bookDto.getDescription());
        book.setNbPage(bookDto.getNbPage());
        book.setState(bookDto.getState() != null ? bookDto.getState() : false);

        if (imageUrl != null && !imageUrl.isEmpty()) {
            String fileName = saveImage(imageUrl);
            book.setImageUrl(fileName);
        }

        CategoryBook categoryBook = categoryRepository.findById(bookDto.getCategoryId()).orElseThrow();
        book.setCategory(categoryBook);
        return bookRepository.save(book).getDto();
    }

    public List<BookDTO> getAllBooks() {
        List<Book> books = (List<Book>) bookRepository.findAll();
        return books.stream().map(Book::getDto).collect(Collectors.toList());
    }
    public BookDTO updateBook(BookDTO bookDto, long id, MultipartFile imageUrl) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isEmpty()) {
            throw new RuntimeException("Book not found");
        }
        Book book = bookOpt.get();
        book.setAuthor(bookDto.getAuthor());
        book.setTitle(bookDto.getTitle());
        book.setDescription(bookDto.getDescription());
        book.setNbPage(bookDto.getNbPage());
        if (imageUrl != null && !imageUrl.isEmpty()) {
            String fileName = saveImage(imageUrl);
            book.setImageUrl(fileName);
        }

        CategoryBook categoryBook = categoryRepository.findById(bookDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        book.setCategory(categoryBook);
        return bookRepository.save(book).getDto();
    }


    @Override
    public BookDTO getBookById(long id) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isEmpty()) {
            throw new RuntimeException("Book not found");
        }

        Book book = bookOpt.get();
        return book.getDto();
    }

    public void deleteBook(long id) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isEmpty()) {
            throw new RuntimeException("Book not found");
        }
        bookRepository.deleteById(id);
    }
    private String saveImage(MultipartFile imageUrl) {
        String fileName = UUID.randomUUID().toString() + "_" + imageUrl.getOriginalFilename();
        File file = new File(UPLOAD_DIR + File.separator + fileName);
        try {
            imageUrl.transferTo(file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + fileName, e);
        }
        return fileName;
    }

    public List<BookDTO> getAllBooksByTitle(String title) {
        List<Book> books = (List<Book>) bookRepository.findAllByTitleContaining(title);
        return books.stream().map(Book::getDto).collect(Collectors.toList());
    }

    public List<BookDTO> getAllBooksWithEndDate() {
        // Fetch the count of reservations for each book
        List<Object[]> counts = reservationBookRepository.countReservationsByBook();
        List<Long> recommendedBookIds = counts.stream()
                .filter(count -> (long) count[1] >= 3)
                .map(count -> (Long) count[0])
                .collect(Collectors.toList());

        // Fetch all books
        List<Book> books = (List<Book>) bookRepository.findAll();

        // Convert books to DTOs and mark the recommended ones
        return books.stream()
                .map(book -> {
                    BookDTO bookDTO = book.getDto();
                    if (recommendedBookIds.contains(book.getId())) {
                        bookDTO.setRecommended(true);
                    } else {
                        bookDTO.setRecommended(false);
                    }
                    return bookDTO;
                })
                .collect(Collectors.toList());

    }

    @Override
    public List<BookDTO> getBooksByCategoryId(Long categoryId){
        List<Book> books;
        if (categoryId == -1) { // Special case for "All Categories"
            books = (List<Book>) bookRepository.findAll();
        } else {
            books = bookRepository.findAllByCategory_Id (categoryId);
        }
        return books.stream().map(Book::getDto).collect(Collectors.toList());
    }

}
