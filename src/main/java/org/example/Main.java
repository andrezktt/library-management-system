package org.example;

import org.example.models.Book;
import org.example.models.User;
import org.example.services.BookService;
import org.example.services.UserService;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        BookService bookService = new BookService();
        UserService userService = new UserService();

        try {
            List<Book> books = bookService.searchBooks("IDIOTA");
            for (Book book : books) {
                System.out.println("\nTítulo: " + book.getTitle() + "\nAutor: " + book.getAuthor() + "\nDisponibilidade: " + (book.isAvailable() ? "Disponível" : "Indisponível"));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

//        try {
//
//        } catch (SQLException e) {
//            System.err.println("Error: " + e.getMessage());
//        }
    }
}