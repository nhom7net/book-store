package spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import spring_boot.dto.BookDTO;
import spring_boot.service.BookService;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/index")
    public String viewBooksList(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new BookDTO());
        return "create";
    }

    @PostMapping("/new")
    public String createBook(@ModelAttribute BookDTO bookDTO) {
        bookService.createBook(bookDTO);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        BookDTO bookDTO = bookService.getBookById(id);
        model.addAttribute("book", bookDTO);
        return "edit";
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute BookDTO bookDTO) {
        bookService.updateBook(bookDTO);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
        return "redirect:/index";
    }
}
