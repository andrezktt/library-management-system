package org.example;

import org.example.controllers.BookController;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final BookController bookController = new BookController();

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
            case 1 -> bookController.addBook();
            case 2 -> bookController.getBooks();
            case 3 -> bookController.deleteBook();
            case 4 -> bookController.updateBook();
            case 5 -> bookController.searchBook();
            case 0 -> System.out.println("\nVoltando ao menu principal...");
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
//
//    private static void addUser() {
//        System.out.print("Digite o nome do usuário: ");
//        String name = scanner.nextLine();
//        System.out.print("Digite o email do usuário: ");
//        String email = scanner.nextLine();
//
//        User user = new User(0, name, email);
//        try {
//            userService.addUser(user);
//            System.out.println("Usuário adicionado com sucesso!");
//        } catch (SQLException e) {
//            System.err.println("Erro ao adicionar usuário: " + e.getMessage());
//        }
//    }
//
//    public static void getUsers() {
//        try {
//            List<User> users = userService.getUsers();
//            if (users.isEmpty()) {
//                System.out.println("Nenhum usuário encontrado!");
//            } else {
//                System.out.println("Usuários encontrados: ");
//                for (User user : users) {
//                    System.out.println("\nID: " + user.getId()
//                            + "\nNome: " + user.getName()
//                            + "\nEmail: " + user.getEmail()
//                    );
//                }
//            }
//        } catch (SQLException e) {
//            System.err.println("Erro ao listar usuários: " + e.getMessage());
//        }
//    }
//
//    public static void deleteUser() {
//        System.out.print("Digite o ID do usuário que deseja excluir: ");
//        int userId = scanner.nextInt();
//
//        try {
//            userService.deleteUser(userId);
//            System.out.println("Usuário excluído com sucesso!");
//        } catch (SQLException e) {
//            System.err.println("Erro ao deletar usuário: " + e.getMessage());
//        }
//    }
//
//    public static void updateUser() {
//        System.out.print("ID do usuário que deseja alterar: ");
//        int userId = scanner.nextInt();
//        scanner.nextLine();
//        System.out.print("Digite o novo nome do usuário: ");
//        String name = scanner.nextLine();
//        System.out.print("Digite o novo email do usuário: ");
//        String email = scanner.nextLine();
//
//        User user = new User(userId, name, email);
//        try {
//            userService.updateUser(user);
//            System.out.println("Usuário atualizado com sucesso!");
//        } catch (SQLException e) {
//            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
//        }
//    }
//
//    public static void searchUser() {
//        System.out.print("Digite o nome do usuário que deseja buscar: ");
//        String userName = scanner.nextLine();
//
//        try {
//            List<User> users = userService.searchUser(userName);
//            if (users.isEmpty()) {
//                System.out.println("Nenhum usuário encontrado!");
//            } else {
//                System.out.println("Usuários encontrados:");
//                for (User user : users) {
//                    System.out.println("\nID: " + user.getId()
//                            + "\nNome: " + user.getName()
//                            + "\nEmail: " + user.getEmail()
//                    );
//                }
//            }
//        } catch (SQLException e) {
//            System.err.println("Erro ao buscar usuário: " + e.getMessage());
//        }
//    }

//    public static void borrowBook() {
//        System.out.print("Digite o ID do livro a ser emprestado: ");
//        int bookId = scanner.nextInt();
//        System.out.print("Digite o ID do usuário que fará o empréstimo: ");
//        int userId = scanner.nextInt();
//
//        try {
//            bookService.borrowBook(bookId, userId);
//            System.out.println("Livro emprestado com sucesso!");
//        } catch (SQLException e) {
//            System.err.println("Erro emprestar livro: " + e.getMessage());
//        }
//    }
//
//    public static void returnBook() {
//        System.out.print("Digite o ID do livro a ser devolvido: ");
//        int bookId = scanner.nextInt();
//
//        try {
//            bookService.returnBook(bookId);
//            System.out.println("Livro devolvido com sucesso!");
//        } catch (SQLException e) {
//            System.err.println("Erro devolver livro: " + e.getMessage());
//        }
//    }
}