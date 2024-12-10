package org.example.controllers;

import org.example.models.Book;
import org.example.models.Borrow;
import org.example.models.User;
import org.example.services.BookService;
import org.example.services.BorrowService;
import org.example.services.UserService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class BorrowController {

    private final Scanner scanner = new Scanner(System.in);
    private final BorrowService borrowService = new BorrowService();
    private final BookService bookService = new BookService();
    private final UserService userService = new UserService();

    public void borrowBook() {
        List<Book> books = bookService.getAvailableBooks();
        System.out.println("\nLivros Disponíveis:");
        for (Book book : books) {
            System.out.println("\n[ID: " + book.getId() + "] " + (book.isAvailable() ? "DISPONÍVEL" : "AGUARDANDO DEVOLUÇÃO")
                    + "\nTítulo: " + book.getTitle()
                    + "\nAutor: " + book.getAuthor()
            );
        }
        System.out.print("\nDigite o ID do livro que deseja emprestar: ");
        int bookId = scanner.nextInt();

        List<User> users = userService.getUsers();
        System.out.println("\nUsuários encontrados: ");
        for (User user : users) {
            System.out.println("\nID: " + user.getId()
                    + "\nNome: " + user.getName()
                    + "\nEmail: " + user.getEmail()
            );
        }
        System.out.print("\nDigite o ID do usuário que vai emprestá-lo: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite a data de empréstimo (dd/MM/yyyy): ");
        LocalDate borrowDate = Objects.equals(scanner.nextLine(), "") ? LocalDate.now() : LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.print("Digite a data de retorno (dd/MM/yyyy): ");
        LocalDate returnDate = Objects.equals(scanner.nextLine(), "") ? LocalDate.now().plusMonths(1) : LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Borrow borrow = new Borrow(0, bookId, userId, borrowDate, returnDate);

        borrowService.borrowBook(borrow);

        Book book = bookService.findBookById(bookId);
        book.setAvailable(false);
        bookService.updateBook(book);
    }

    public void returnBook() {
        List<Book> books = bookService.getUnavailableBooks();
        System.out.println("\nLivros a serem devolvidos:");
        for (Book book : books) {
            System.out.println("\n[ID: " + book.getId() + "] " + (book.isAvailable() ? "DISPONÍVEL" : "AGUARDANDO DEVOLUÇÃO")
                    + "\nTítulo: " + book.getTitle()
                    + "\nAutor: " + book.getAuthor()
            );
        }

        System.out.print("\nDigite o ID do livro que deseja retornar: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite a data de retorno (dd/MM/yyyy): ");
        LocalDate returnDate = Objects.equals(scanner.nextLine(), "") ? LocalDate.now() : LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        borrowService.returnBook(bookId, returnDate);

        Book book = bookService.findBookById(bookId);
        book.setAvailable(true);
        bookService.updateBook(book);
    }

    public void updateBorrowRecord() {
        System.out.print("Digite o ID do livro: ");
        int bookId = scanner.nextInt();
        System.out.print("Digite o ID do usuário: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite a data de empréstimo (dd/MM/yyyy): ");
        LocalDate borrowDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.print("Digite a nova data de retorno (dd/MM/yyyy): ");
        LocalDate returnDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Borrow borrow = new Borrow(0, bookId, userId, borrowDate, returnDate);

        borrowService.updateBorrowRecord(borrow);
    }

    public void deleteBorrowRecord() {
        System.out.print("Digite o ID do empréstimo que deseja excluir: ");
        int borrowId = scanner.nextInt();

        borrowService.deleteBorrowRecord(borrowId);

        Book book = bookService.findBookById(borrowService.findById(borrowId).getBookId());
        book.setAvailable(true);
        bookService.updateBook(book);
    }

    public void getBorrows() {
        List<Borrow> borrows = borrowService.getBorrows();
        System.out.println("\nEmpréstimos encontrados: ");
        for (Borrow borrow : borrows) {
            User user = borrow.getUser();
            Book book = borrow.getBook();
            System.out.println(
                    "\nEMPRÉSTIMO [#" + borrow.getId() + "]" +
                    "\n" + "Livro: " + "[#" + book.getId() + "] " + book.getTitle() + " (" + book.getAuthor() + ")" +
                    "\n" + "Usuário: " + "[#" + user.getId() + "] " + user.getName() + " (" + user.getEmail() + ")" +
                    "\nDe: " + borrow.getBorrowDate() + "  --->  Até: " + borrow.getReturnDate()
            );
        }
    }

    public void getBooksByUser() {
        System.out.print("\nDigite o ID do usuário: ");
        int userId = scanner.nextInt();

        List<Borrow> borrows = borrowService.getBooksByUser(userId);
        for (Borrow borrow : borrows) {
            User user = borrow.getUser();
            Book book = borrow.getBook();
            System.out.println(
                            "\nEMPRÉSTIMO [#" + borrow.getId() + "]" +
                            "\n" + "Título: " + book.getTitle() + " [ID: " + book.getId() + "] " +
                            "\nAutor: " + book.getAuthor() +
                            "\nDe: " + borrow.getBorrowDate() + "  --->  Até: " + borrow.getReturnDate()
            );
        }
    }

    public void getUsersByBook() {
        System.out.print("\nDigite o ID do livro: ");
        int bookId = scanner.nextInt();

        List<Borrow> borrows = borrowService.getUsersByBook(bookId);
        for (Borrow borrow : borrows) {
            User user = borrow.getUser();
            Book book = borrow.getBook();
            System.out.println(
                    "\nEMPRÉSTIMO [#" + borrow.getId() + "]" +
                            "\n" + "Nome: " + user.getName() + " [ID: " + user.getId() + "] " +
                            "\nEmail: " + user.getEmail() +
                            "\nDe: " + borrow.getBorrowDate() + "  --->  Até: " + borrow.getReturnDate()
            );
        }
    }
}
