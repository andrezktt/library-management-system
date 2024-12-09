package org.example;

import org.example.controllers.BookController;
import org.example.controllers.BorrowController;
import org.example.controllers.UserController;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final BookController bookController = new BookController();
    private static final UserController userController = new UserController();
    private static final BorrowController borrowController = new BorrowController();

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
                case 2 -> userMenu();
                case 3 -> borrowMenu();
                case 0 -> {
                    System.out.println("\nSaindo do sistema... Até logo!");
                    System.exit(0);
                }
            }
        }
    }

    private static void bookMenu() {
        boolean status = true;
        while (status) {
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
                case 1 -> bookController.addBook();
                case 2 -> bookController.getBooks();
                case 3 -> bookController.deleteBook();
                case 4 -> bookController.updateBook();
                case 5 -> bookController.searchBook();
                case 0 -> status = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void userMenu() {

        boolean status = true;
        while (status) {
            System.out.println("\n=== Gerenciamento de Usuários ===");
            System.out.println("1. Adicionar usuário");
            System.out.println("2. Listar usuários");
            System.out.println("3. Remover usuário");
            System.out.println("4. Atualizar usuário");
            System.out.println("5. Pesquisar usuários");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> userController.addUser();
                case 2 -> userController.getUsers();
                case 3 -> userController.deleteUser();
                case 4 -> userController.updateUser();
                case 5 -> userController.searchUserByName();
                case 0 -> status = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void borrowMenu() {
        boolean status = true;
        while (status) {
            System.out.println("\n=== Gerenciamento de Empréstimos ===");
            System.out.println("1. Listar empréstimos");
            System.out.println("2. Emprestar livro");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> borrowController.getBorrows();
                case 2 -> borrowController.borrowBook();
                case 0 -> status = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}