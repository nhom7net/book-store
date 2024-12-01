package spring_boot.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_boot.dto.BookDTO;
import spring_boot.entity.BookEntity;
import spring_boot.repository.BookRepository;
import spring_boot.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public BookDTO createBook(BookDTO bookDTO) {
		BookEntity book = new BookEntity();
		book.setTitle(bookDTO.getTitle());
		book.setAuthor(bookDTO.getAuthor());
		book.setGenre(bookDTO.getGenre());
		book.setDescription(bookDTO.getDescription());
		book.setPrice(bookDTO.getPrice());
		book.setStock(bookDTO.getStock());
        book = bookRepository.save(book);
        bookDTO.setId(book.getId());
        return bookDTO;
	}
	
	@Override
    public BookDTO updateBook(BookDTO bookDTO) {
        BookEntity book = bookRepository.findById(bookDTO.getId()).orElseThrow();
        book.setTitle(bookDTO.getTitle());
		book.setAuthor(bookDTO.getAuthor());
		book.setGenre(bookDTO.getGenre());
		book.setDescription(bookDTO.getDescription());
		book.setPrice(bookDTO.getPrice());
		book.setStock(bookDTO.getStock());
        book = bookRepository.save(book);
        return bookDTO;
    }
	
	@Override
	public void deleteBookById(Long id) {
	    bookRepository.deleteById(id);
	}

	
	@Override
    public BookDTO getBookById(Long id) {
        BookEntity book = bookRepository.findById(id).orElseThrow();
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getDescription(), book.getPrice(), book.getStock());
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream().map(book -> new BookDTO(
            book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getDescription(), book.getPrice(), book.getStock())).collect(Collectors.toList());
    }
}
