package org.example.controllers;

import org.example.models.Book;
import org.example.models.Borrow;
import org.example.models.User;
import org.example.services.BookService;
import org.example.services.BorrowService;
import org.example.services.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class BorrowController {

    private final Scanner scanner = new Scanner(System.in);
    private final BorrowService borrowService = new BorrowService();
    private final BookService bookService = new BookService();
    private final UserService userService = new UserService();

    public void borrowBook() {
        List<Book> books = bookService.getAvailableBooks();

        if (books.isEmpty()) {
            System.out.println("\nNenhum livro disponível para empréstimo.");
            return;
        }

        System.out.println("\nLivros Disponíveis:");
        for (Book book : books) {
            System.out.println("\n[ID: " + book.getId() + "] " + (book.isAvailable() ? "DISPONÍVEL" : "AGUARDANDO DEVOLUÇÃO")
                    + "\nTítulo: " + book.getTitle()
                    + "\nAutor: " + book.getAuthor()
            );
        }

        System.out.print("\nDigite o ID do livro que deseja emprestar: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        Book selectedBook = bookService.findBookById(bookId);
        if (selectedBook == null || !selectedBook.isAvailable()) {
            System.out.println("\nErro: Livro não encontrado ou não está disponível para empréstimo!");
            return;
        }

        List<User> users = userService.getUsers();

        if (users.isEmpty()) {
            System.out.println("\nNenhum usuário cadastrado.");
            return;
        }

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

        User selectedUser = userService.findUserById(userId);
        if (selectedUser == null) {
            System.out.println("\nErro: Usuário não encontrado.");
            return;
        }

        LocalDate borrowDate;
        System.out.print("Digite a data de empréstimo (dd/MM/yyyy): ");
        try {
            String borrowDateString = scanner.nextLine();
            borrowDate = borrowDateString.isEmpty() ? LocalDate.now() : LocalDate.parse(borrowDateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            System.out.println("\nErro: Data inválida. Tente novamente.");
            return;
        }

        LocalDate returnDate;
        System.out.print("Digite a data de retorno (dd/MM/yyyy): ");
        try {
            String returnDateString = scanner.nextLine();
            returnDate  = returnDateString.isEmpty() ? borrowDate.plusMonths(1) : LocalDate.parse(returnDateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            System.out.println("\nErro: Data inválida. Tente novamente.");
            return;
        }

        Borrow borrow = new Borrow(0, bookId, userId, borrowDate, returnDate);

        boolean success = borrowService.borrowBook(borrow);
        if (!success) {
            System.out.println("\nErro: impossível realizar o empréstimo.");
            return;
        }

        selectedBook.setAvailable(false);
        bookService.updateBook(selectedBook);
        System.out.println("\nEmpréstimo realizado com sucesso!");
    }

    public void returnBook() {
        List<Book> books = bookService.getUnavailableBooks();

        if (books.isEmpty()) {
            System.out.println("\nNenhum livro a ser devolvido.");
            return;
        }

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

        Book selectedBook = bookService.findBookById(bookId);
        if (selectedBook == null) {
            System.out.println("\nErro: Livro não encontrado.");
            return;
        }

        if (selectedBook.isAvailable()) {
            System.out.println("\nEste livro já foi devolvido ou não está marcado como emprestado.");
            return;
        }

        LocalDate returnDate;
        System.out.print("Digite a data de retorno (dd/MM/yyyy): ");
        try {
            String returnDateString = scanner.nextLine();
            returnDate = returnDateString.isEmpty() ? LocalDate.now() : LocalDate.parse(returnDateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            if (returnDate.isAfter(LocalDate.now())) {
                System.out.println("\nErro: A data de retorno não pode ser no futuro. Tente novamente.");
                return;
            }
        } catch (DateTimeParseException e) {
            System.out.println("\nErro: Data inválida. Tente novamente.");
            return;
        }

        System.out.println("Data de retorno: " + returnDate);
        borrowService.returnBook(bookId, returnDate);

        selectedBook.setAvailable(true);
        bookService.updateBook(selectedBook);

        System.out.println("\nLivro devolvido com sucesso!");
    }

    public void updateBorrowRecord() {
        System.out.print("\nDigite o ID do livro: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        Book selectedBook = bookService.findBookById(bookId);
        if (selectedBook == null) {
            System.out.println("\nErro: Livro não encontrado.");
            return;
        }

        System.out.print("Digite o ID do usuário: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        User selectedUser = userService.findUserById(userId);
        if (selectedUser == null) {
            System.out.println("\nErro: Usuário não encontrado.");
            return;
        }

        if (!selectedBook.isAvailable()) {
            System.out.println("\nO livro não esá disponível para empréstimo.");
            return;
        }

        LocalDate borrowDate = null;
        while (borrowDate == null) {
            System.out.print("Digite a data de empréstimo (dd/MM/yyyy): ");
            try {
                String borrowDateString = scanner.nextLine();
                borrowDate = borrowDateString.isEmpty() ? LocalDate.now() : LocalDate.parse(borrowDateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("\nErro: Data inválida. Tente novamente.");
                return;
            }
        }

        LocalDate returnDate;
        System.out.print("Digite a nova data de retorno (dd/MM/yyyy): ");
        try {
            String returnDateString = scanner.nextLine();
            returnDate = returnDateString.isEmpty() ? borrowDate.plusMonths(1) : LocalDate.parse(returnDateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            System.out.println("\nErro: Data inválida. Tente novamente.");
            return;
        }

        Borrow borrow = new Borrow(0, bookId, userId, borrowDate, returnDate);

        boolean success = borrowService.updateBorrowRecord(borrow);
        if (!success) {
            System.out.println("\nErro: não foi possível atualizar o registro de empréstimo.");
            return;
        }

        System.out.println("\nRegistro de empréstimo atualizado com sucesso.");
    }

    public void deleteBorrowRecord() {
        System.out.print("Digite o ID do empréstimo que deseja excluir: ");
        int borrowId = scanner.nextInt();
        scanner.nextLine();

        Borrow borrow = borrowService.findById(borrowId);
        if (borrow == null) {
            System.out.println("\nErro: Registro de empréstimo não encontrado.");
            return;
        }

        Book book = bookService.findBookById(borrow.getBookId());
        if (book == null) {
            System.out.println("\nErro: Livro não encontrado.");
            return;
        }

        boolean success = borrowService.deleteBorrowRecord(borrowId);
        if (!success) {
            System.out.println("\nErro: não foi possível excluir o registro de empréstimo.");
            return;
        }

        book.setAvailable(true);
        boolean bookUpdated = bookService.updateBook(book);
        if (!bookUpdated) {
            System.out.println("\nErro: não foi possível atualizar a disponibilidade do livro.");
            return;
        }

        System.out.println("\nRegistro de empréstimo excluído com sucesso e livro disponível para empréstimo.");
    }

    public void getBorrows() {
        List<Borrow> borrows = borrowService.getBorrows();

        if (borrows.isEmpty()) {
            System.out.println("\nErro: Nenhum empréstimo encontrado.");
            return;
        }

        System.out.println("\nEmpréstimos encontrados: ");
        for (Borrow borrow : borrows) {
            User user = borrow.getUser();
            Book book = borrow.getBook();

            if (user == null) {
                System.out.println("\nErro: Usuário não encontrado.");
                return;
            } else if (book == null) {
                System.out.println("\nErro: Livro não encontrado.");
                return;
            } else {
                System.out.println(
                        "\nEMPRÉSTIMO [#" + borrow.getId() + "]" +
                                "\n" + "Livro: " + "[BOOK_ID: " + book.getId() + "] " + book.getTitle() + " (" + book.getAuthor() + ")" +
                                "\n" + "Usuário: " + "[USER_ID: " + user.getId() + "] " + user.getName() + " (" + user.getEmail() + ")" +
                                "\nDe: " + borrow.getBorrowDate() + "  --->  Até: " + borrow.getReturnDate()
                );
            }
        }
    }

    public void getBooksByUser() {
        List<User> users = userService.getUsers();
        System.out.println("\nUsuários cadastrados:");
        for (User user : users) {
            System.out.println("\nID: " + user.getId()
                    + "\nNome: " + user.getName()
                    + "\nEmail: " + user.getEmail()
            );
        }

        System.out.print("\nDigite o ID do usuário: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        User user = userService.findUserById(userId);
        if (user == null) {
            System.out.println("\nErro: Usuário não encontrado.");
            return;
        }

        List<Borrow> borrows = borrowService.getBooksByUser(userId);

        if (borrows.isEmpty()) {
            System.out.println("\nEste usuário ainda não emprestou nenhum livro.");
            return;
        }

        System.out.println("\n[USER_ID: " + user.getId() + "]"
                + "\nNome: " + user.getName()
                + "\nEmail: " + user.getEmail()
                + "\n\n--------------------------------------------------"
        );

        for (Borrow borrow : borrows) {
            Book book = borrow.getBook();
            if (book == null) {
                System.out.println("\nErro: Livro não encontrado.");
                return;
            }
            System.out.println(
                            "\nEMPRÉSTIMO [#" + borrow.getId() + "]" +
                            "\nTítulo: " + book.getTitle() + " [ID: " + book.getId() + "] " +
                            "\nAutor: " + book.getAuthor() +
                            "\nDe: " + borrow.getBorrowDate() + "  --->  Até: " + borrow.getReturnDate()
            );
        }
    }

    public void getUsersByBook() {
        List<Book> books = bookService.getBooks();
        System.out.println("\nLivros Disponíveis:");
        for (Book book : books) {
            System.out.println("\n[BOOK_ID: " + book.getId() + "]"
                    + "\nTítulo: " + book.getTitle()
                    + "\nAutor: " + book.getAuthor()
            );
        }

        System.out.print("\nDigite o ID do livro: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        Book book = bookService.findBookById(bookId);
        if (book == null) {
            System.out.println("\nErro: Livro não encontrado.");
            return;
        }

        List<Borrow> borrows = borrowService.getUsersByBook(bookId);

        if (borrows.isEmpty()) {
            System.out.println("\nNenhum usuário emprestou este livro.");
            return;
        }

        System.out.println("\n[BOOK_ID: " + book.getId() + "]"
                + "\nTítulo: " + book.getTitle()
                + "\nAutor: " + book.getAuthor()
                + "\n\n--------------------------------------------------"
        );

        for (Borrow borrow : borrows) {
            User user = borrow.getUser();
            if (user == null) {
                System.out.println("\nErro: Usuário não encontrado.");
                return;
            }
            System.out.println("\nEMPRÉSTIMO [#" + borrow.getId() + "]" +
                            "\n" + "Nome: " + user.getName() + " [ID: " + user.getId() + "] " +
                            "\nEmail: " + user.getEmail() +
                            "\nDe: " + borrow.getBorrowDate() + "  --->  Até: " + borrow.getReturnDate()
            );
        }
    }
}
