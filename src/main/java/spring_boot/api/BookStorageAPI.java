package spring_boot.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring_boot.dto.BookDTO;
import spring_boot.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookStorageAPI {
	
	@Autowired
    private BookService bookService;
	
	@PostMapping
    public BookDTO createBook(@RequestBody BookDTO model) {
        return bookService.createBook(model);
    }
	
	@PutMapping
    public BookDTO updateBook(@RequestBody BookDTO model) {
        return bookService.updateBook(model);
    }

    @DeleteMapping
    public void deleteBook(@RequestBody long id) {
        bookService.deleteBookById(id);
    }

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }
}