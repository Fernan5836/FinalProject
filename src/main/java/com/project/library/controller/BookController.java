package com.project.library.controller;

import com.project.library.repository.BookRepository;
import com.project.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    //Obtener libros
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    //Crear libros
    @PostMapping
    public Book createBook(@RequestBody Book book){
        return bookRepository.save(book);
    }

    //Obtener libro por id
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id){
        Optional<Book> book = bookRepository.findById(id);
        return book.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable String id, @RequestBody Book bookDetails) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            Book updatedBook = bookRepository.save(book);
            return ResponseEntity.ok(updatedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //Eliminar libro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id){
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()){
            bookRepository.delete(bookOptional.get());
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }



}
