package org.example;

import org.example.models.Book;
import org.example.models.User;
import org.example.services.BookService;
import org.example.services.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final BookService bookService = new BookService();
    private static final UserService userService = new UserService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n=== Sistema de Gerenciamento de Biblioteca ===");
            System.out.println("1. Gerenciar Livros");
            System.out.println("2. Gerenciar Usuários");
            System.out.println("3. Gerenciar Empréstimos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> bookMenu();
//                case 2 -> userMenu();
//                case 3 -> borrowMenu();
                case 0 -> {
                    System.out.println("Saindo do sistema... Até logo!");
                    System.exit(0);
                }
            }
        }

    }

    private static void bookMenu() {
        System.out.println("\n=== Gerenciamento de Livros ===");
        System.out.println("1. Adicionar livro");
        System.out.println("2. Listar livros");
        System.out.println("3. Remover livro");
        System.out.println("4. Atualizar livro");
        System.out.println("5. Pesquisar livros");
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1 -> addBook();
            case 2 -> getBooks();
            case 3 -> deleteBook();
            case 4 -> updateBook();
            case 5 -> searchBook();
            case 0 -> System.out.println("Voltando ao menu principal.");
            default -> System.out.println("Opção inválida. Tente novamente.");
        }
    }

//    private static void userMenu() {
//        System.out.println("\n=== Gerenciamento de Usuários ===");
//        System.out.println("1. Adicionar usuário");
//        System.out.println("2. Listar usuários");
//        System.out.println("3. Remover usuário");
//        System.out.println("4. Atualizar usuário");
//        System.out.println("5. Pesquisar usuários");
//        System.out.println("0. Voltar");
//        System.out.print("Escolha uma opção: ");
//        int option = scanner.nextInt();
//        scanner.nextLine();
//
//        switch (option) {
//            case 1 -> addUser();
//            case 2 -> getUsers();
//            case 3 -> deleteUser();
//            case 4 -> updateUser();
//            case 5 -> searchUser();
//            case 0 -> System.out.println("Voltando ao menu principal.");
//            default -> System.out.println("Opção inválida. Tente novamente.");
//        }
//    }
//
//    private static void borrowMenu() {
//        System.out.println("\n=== Gerenciamento de Empréstimos ===");
//        System.out.println("1. Emprestar livro");
//        System.out.println("2. Devolver livro");
//        System.out.println("0. Voltar");
//        System.out.print("Escolha uma opção: ");
//        int option = scanner.nextInt();
//        scanner.nextLine();
//
//        switch (option) {
//            case 1 -> borrowBook();
//            case 2 -> returnBook();
//            case 0 -> System.out.println("Voltando ao menu principal.");
//            default -> System.out.println("Opção inválida. Tente novamente.");
//        }
//    }

    private static void addBook() {
        System.out.print("Digite o título do livro: ");
        String title = scanner.nextLine();
        System.out.print("Digite o autor do livro: ");
        String author = scanner.nextLine();

        Book book = new Book(0, title, author, true);
        try {
            bookService.addBook(book);
            System.out.println("Livro adicionado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar livro: " + e.getMessage());
        }
    }

    public static void getBooks() {
        try {
            List<Book> books = bookService.getBooks();
            if (books.isEmpty()) {
                System.out.println("Nenhum livro encontrado!");
            } else {
                for (Book book : books) {
                    System.out.println("\nID: " + book.getId()
                            + "\nTítulo: " + book.getTitle()
                            + "\nAutor: " + book.getAuthor()
                            + "\nDisponibilidade: " + (book.isAvailable() ? "Disponível" : "Indisponível"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar os livros: " + e.getMessage());
        }
    }

    public static void deleteBook() {
        System.out.print("Digite o ID do livro que deseja excluir: ");
        int bookId = scanner.nextInt();

        try {
            bookService.deleteBook(bookId);
            System.out.println("Livro excluído com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar livro: " + e.getMessage());
        }
    }

    public static void updateBook() {
        System.out.print("ID do livro que deseja alterar: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite o título do livro: ");
        String title = scanner.nextLine();
        System.out.print("Digite o autor do livro: ");
        String author = scanner.nextLine();

        Book book = new Book(bookId, title, author, true);
        try {
            bookService.updateBook(book);
            System.out.println("Livro atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar livro: " + e.getMessage());
        }
    }

    public static void searchBook() {
        System.out.print("Digite qual livro deseja buscar: ");
        String query = scanner.nextLine();

        try {
            List<Book> books = bookService.searchBooks(query);
            if (books.isEmpty()) {
                System.out.println("Nenhum livro encontrado!");
            } else {
                for (Book book : books) {
                    System.out.println("\nID: " + book.getId()
                            + "\nTítulo: " + book.getTitle()
                            + "\nAutor: " + book.getAuthor()
                            + "\nDisponibilidade: " + (book.isAvailable() ? "Disponível" : "Indisponível"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar livro: " + e.getMessage());
        }
    }
}