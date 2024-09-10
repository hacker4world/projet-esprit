@Service
@AllArgsConstructor
public class BookService implements IbookService {
    private final BookRepository bookRepository;

    @Override
    public List<BookDTO> getBooksByCategoryId(Long categoryId) {
        List<Book> books = bookRepository.findByCategory_Id(categoryId);
        return books.stream().map(Book::getDto).collect(Collectors.toList());
    }
}
public class BookController {
    private final IbookService bookService;

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<BookDTO>> getBooksByCategory(@PathVariable Long categoryId) {
        List<BookDTO> books = bookService.getBooksByCategoryId(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }
}