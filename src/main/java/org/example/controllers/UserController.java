package org.example.controllers;

import org.example.models.User;
import org.example.services.UserService;

import java.util.List;
import java.util.Scanner;

public class UserController {

    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService service = new UserService();

    public void addUser() {
        System.out.print("\nDigite o nome do usuário: ");
        String name = scanner.nextLine();

        if (name.isEmpty()) {
            System.out.println("Erro: O nome do usuário não pode estar vazio.");
            return;
        }

        System.out.print("Digite o email do usuário: ");
        String email = scanner.nextLine();

        if (email.isEmpty()) {
            System.out.println("Erro: O email não pode estar vazio.");
            return;
        }

        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            System.out.println("Erro: O email informado não é válido.");
            return;
        }

        if (service.emailExists(email)) {
            System.out.println("Erro: O email informado já está em uso.");
            return;
        }

        User user = new User(0, name, email);
        service.addUser(user);
    }

    public void updateUser() {
        System.out.print("\nID do usuário que deseja alterar: ");
        int userId;

        try {
            userId = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Erro: O ID do usuário deve ser um número válido.");
            return;
        }
        if (userId <= 0) {
            System.out.println("Erro: O ID do usuário deve ser maior que zero.");
            return;
        }
        if (service.findUserById(userId) == null) {
            System.out.println("Erro: Não existe um usuário com o ID informado.");
            return;
        }

        System.out.print("Digite o novo nome do usuário: ");
        String name = scanner.nextLine();

        if (name.isEmpty()) {
            System.out.println("Erro: O nome do usuário não pode estar vazio.");
            return;
        }

        System.out.print("Digite o novo email do usuário: ");
        String email = scanner.nextLine().trim();

        if (email.isEmpty()) {
            System.out.println("Erro: O email do usuário não pode estar vazio.");
            return;
        }
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            System.out.println("Erro: O email informado não é válido.");
            return;
        }
        if (service.emailExists(email)) {
            System.out.println("Erro: O email informado já está em uso por outro usuário.");
            return;
        }

        User user = new User(userId, name, email);
        service.updateUser(user);
    }

    public void deleteUser() {
        System.out.print("\nDigite o ID do usuário que deseja excluir: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Erro: O ID deve ser um número inteiro.");
            scanner.nextLine();
            return;
        }

        int userId = scanner.nextInt();
        scanner.nextLine();

        if (userId <= 0) {
            System.out.println("Erro: O ID deve ser um número positivo.");
            return;
        }

        if (service.findUserById(userId) == null) {
            System.out.println("Erro: Nenhum usuário encontrado com o ID fornecido.");
            return;
        }

        service.deleteUser(userId);
    }

    public void findUserById() {
        System.out.print("\nDigite o ID do usuário que buscar: ");
        if (!scanner.hasNextInt()) {
            System.out.println("Erro: O ID deve ser um número inteiro.");
            scanner.nextLine();
            return;
        }

        int userId = scanner.nextInt();
        scanner.nextLine();

        User user = service.findUserById(userId);
        if (user == null) {
            System.out.println("Erro: nenhum usuário encontrado com o ID informado.");
            return;
        }

        System.out.println("\nID: " + user.getId()
                + "\nNome: " + user.getName()
                + "\nEmail: " + user.getEmail()
        );
    }

    public void getUsers() {
        List<User> users = service.getUsers();

        if (users == null || users.isEmpty()) {
            System.out.println("\nNenhum usuário encontrado.");
            return;
        }

        System.out.println("\nUsuários encontrados: ");
        for (User user : users) {
            System.out.println("\nID: " + user.getId()
                    + "\nNome: " + user.getName()
                    + "\nEmail: " + user.getEmail()
            );
        }
    }

    public void searchUserByName() {
        System.out.print("\nDigite o nome do usuário que deseja buscar: ");
        String name = scanner.nextLine();

        if (name == null || name.trim().isEmpty()) {
            System.out.println("\nO nome não pode estar vazio. Tente novamente.");
            return;
        }

        List<User> users = service.searchUser(name);

        if (users == null || users.isEmpty()) {
            System.out.println("\nNenhum usuário encontrado com o nome fornecido.");
            return;
        }

        System.out.println("\nUsuários encontrados:");
        for (User user : users) {
            System.out.println("\nID: " + user.getId()
                    + "\nNome: " + user.getName()
                    + "\nEmail: " + user.getEmail()
            );
        }
    }
}
