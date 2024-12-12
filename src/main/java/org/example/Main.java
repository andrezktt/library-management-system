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
            System.out.println("1. Listar Disponíveis");
            System.out.println("2. Registrar empréstimo");
            System.out.println("3. Realizar devolução");
            System.out.println("4. Listar Indisponíveis");
            System.out.println("-----------------------");
            System.out.println("5. Gerenciar livros");
            System.out.println("6. Gerenciar usuários");
            System.out.println("7. Gerenciar empréstimos");
            System.out.println("-----------------------");
            System.out.println("0. Sair");
            System.out.print("\nEscolha uma opção: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> bookController.getAvailableBooks();
                case 2 -> borrowController.borrowBook();
                case 3 -> borrowController.returnBook();
                case 4 -> bookController.getUnavailableBooks();
                case 5 -> bookMenu();
                case 6 -> userMenu();
                case 7 -> borrowMenu();
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
            System.out.println("1. Listar livros");
            System.out.println("2. Buscar por título ou autor");
            System.out.println("3. Adicionar livro");
            System.out.println("4. Atualizar livro");
            System.out.println("5. Remover livro");
            System.out.println("0. Voltar");
            System.out.print("\nEscolha uma opção: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> bookController.getBooks();
                case 2 -> bookController.searchBook();
                case 3 -> bookController.addBook();
                case 4 -> bookController.updateBook();
                case 5 -> bookController.deleteBook();
                case 0 -> status = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void userMenu() {

        boolean status = true;
        while (status) {
            System.out.println("\n=== Gerenciamento de Usuários ===");
            System.out.println("1. Listar usuários.");
            System.out.println("2. Buscar usuário por nome ou email.");
            System.out.println("3. Cadastrar usuário.");
            System.out.println("4. Atualizar dados de cadastro.");
            System.out.println("5. Remover usuário.");
            System.out.println("0. Voltar.");
            System.out.print("Escolha uma opção: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> userController.getUsers();
                case 2 -> userController.searchUserByName();
                case 3 -> userController.addUser();
                case 4 -> userController.updateUser();
                case 5 -> userController.deleteUser();
                case 0 -> status = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void borrowMenu() {
        boolean status = true;
        while (status) {
            System.out.println("\n=== Gerenciamento de Empréstimos ===");
            System.out.println("1. Relatório de empréstimos");
            System.out.println("2. Relatório de livros por usuário");
            System.out.println("3. Relatório de usuários por livro");
            System.out.println("4. Atualizar dados de empréstimo");
            System.out.println("5. Remover registro de empréstimo");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> borrowController.getBorrows();
                case 2 -> borrowController.getBooksByUser();
                case 3 -> borrowController.getUsersByBook();
                case 4 -> borrowController.updateBorrowRecord();
                case 5 -> borrowController.deleteBorrowRecord();
                case 0 -> status = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}