package org.example.controllers;

import org.example.models.Book;
import org.example.services.BookService;

import java.util.List;
import java.util.Scanner;

public class BookController {

    private static final Scanner scanner = new Scanner(System.in);
    private static final BookService service = new BookService();

    public void addBook() {
        System.out.print("\nDigite o título do livro: ");
        String title = scanner.nextLine();
        System.out.print("Digite o autor do livro: ");
        String author = scanner.nextLine();
        Book book = new Book(0, title, author, true);
        service.addBook(book);
    }

    public void getBooks() {
        List<Book> books = service.getBooks();
        for (Book book : books) {
            System.out.println("\nID: " + book.getId()
                    + "\nTítulo: " + book.getTitle()
                    + "\nAutor: " + book.getAuthor()
                    + "\nDisponibilidade: " + (book.isAvailable() ? "Disponível" : "Indisponível")
            );
        }
    }

    public void deleteBook() {
        System.out.print("\nDigite o ID do livro que deseja excluir: ");
        int bookId = scanner.nextInt();
        service.deleteBook(bookId);
    }

    public void updateBook() {
        System.out.print("\nID do livro que deseja alterar: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite o título do livro: ");
        String title = scanner.nextLine();
        System.out.print("Digite o autor do livro: ");
        String author = scanner.nextLine();

        Book book = new Book(bookId, title, author, true);
        service.updateBook(book);
    }

    public void searchBook() {
        System.out.print("\nDigite qual livro deseja buscar: ");
        String query = scanner.nextLine();
        List<Book> books = service.searchBooks(query);
        for (Book book : books) {
            System.out.println("\nID: " + book.getId()
                    + "\nTítulo: " + book.getTitle()
                    + "\nAutor: " + book.getAuthor()
                    + "\nDisponibilidade: " + (book.isAvailable() ? "Disponível" : "Indisponível")
            );
        }
    }
}
