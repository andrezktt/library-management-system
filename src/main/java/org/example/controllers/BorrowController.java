package org.example.controllers;

import org.example.daos.BorrowDAO;
import org.example.models.Book;
import org.example.models.Borrow;
import org.example.models.User;
import org.example.services.BookService;
import org.example.services.BorrowService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class BorrowController {

    private final Scanner scanner = new Scanner(System.in);
    private final BorrowService service = new BorrowService();
    private final BookService bookService = new BookService();

    public void borrowBook() {
        System.out.println("Digite o ID do livro que deseja emprestar: ");
        int bookId = scanner.nextInt();
        System.out.println("Digite o ID do usuário que vai emprestá-lo: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite a data de empréstimo (dd/MM/yyyy): ");
        LocalDate borrowDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("Digite a data de retorno (dd/MM/yyyy): ");
        LocalDate returnDate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Borrow borrow = new Borrow(0, bookId, userId, borrowDate, returnDate);
        service.borrowBook(borrow);

        bookService.findBookById(bookId).setAvailable(false);
    }

    public void getBorrows() {
        List<Borrow> borrows = service.getBorrows();
        System.out.println("\nEmpréstimos encontrados: ");
        for (Borrow borrow : borrows) {
            User user = borrow.getUser();
            Book book = borrow.getBook();
            System.out.println(
                    "\nEMPRÉSTIMO #" + borrow.getId() +
                    "\n" + "Livro: " + "[#" + book.getId() + "] " + book.getTitle() + " (" + book.getAuthor() + ")" +
                    "\n" + "Usuário: " + "[#" + user.getId() + "] " + user.getName() + " (" + user.getEmail() + ")" +
                    "\nDe: " + borrow.getBorrowDate() + "\nAté: " + borrow.getReturnDate()
            );
        }
    }
}
