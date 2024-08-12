package com.project.library.service;


import com.project.library.model.Book;
import com.project.library.model.Loan;
import com.project.library.model.User;
import com.project.library.repository.BookRepository;
import com.project.library.repository.LoanRepository;
import com.project.library.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class LibraryService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final LoanRepository loanRepository;

    public LibraryService(BookRepository bookRepository, UserRepository userRepository, LoanRepository loanRepository){
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.loanRepository = loanRepository;
    }

    @Transactional
    public void loanBook(Long userId, String bookId){
        //Buscar el libro
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (!bookOptional.isPresent()) {
            throw new RuntimeException("Libro no encontrado");
        }
        Book book = bookOptional.get();

        //Buscar el usuario
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new RuntimeException("Usuario no encontrado");
        }
        User user = userOptional.get();


        // Crear un nuevo préstamo
        Loan loan = new Loan();
        loan.setBook(book);
        loan.setUser(user);
        loan.setLoanDate(LocalDate.now());
        loan.setReturnDate(null); // El libro no ha sido devuelto todavía


        // Guardar los cambios
        bookRepository.save(book);
        loanRepository.save(loan);
    }
}
