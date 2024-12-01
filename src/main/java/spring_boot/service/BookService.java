package spring_boot.service;

import java.util.List;
import spring_boot.dto.BookDTO;

public interface BookService {
    BookDTO createBook(BookDTO bookDTO);
    BookDTO updateBook(BookDTO bookDTO);
    void deleteBookById(Long id);
    BookDTO getBookById(Long id);
    List<BookDTO> getAllBooks();	
}
