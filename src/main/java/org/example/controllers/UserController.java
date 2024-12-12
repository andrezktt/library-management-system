package org.example.controllers;

import org.example.exceptions.EmailAlreadyExistsException;
import org.example.exceptions.InvalidInputException;
import org.example.exceptions.UserNotFoundException;
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
            throw new InvalidInputException("O nome do usuário não pode estar vazio.");
        }

        System.out.print("Digite o email do usuário: ");
        String email = scanner.nextLine();

        if (email.isEmpty()) {
            throw new InvalidInputException("O email não pode estar vazio.");
        }
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new InvalidInputException("O email informado não é válido.");
        }
        if (service.emailExists(email)) {
            throw new EmailAlreadyExistsException("O email informado já está em uso.");
        }

        User user = new User(0, name, email);
        service.addUser(user);
    }

    public void updateUser() {
        System.out.print("\nID do usuário que deseja alterar: ");
        int userId = readPositiveInteger("ID do usuário");

        if (service.findUserById(userId) == null) {
            throw new UserNotFoundException("Não existe um usuário com o ID informado.");
        }

        System.out.print("Digite o novo nome do usuário: ");
        String name = scanner.nextLine();

        if (name.isEmpty()) {
            throw new InvalidInputException("O nome do usuário não pode estar vazio.");
        }

        System.out.print("Digite o novo email do usuário: ");
        String email = scanner.nextLine().trim();

        if (email.isEmpty()) {
            throw new InvalidInputException("O email do usuário não pode estar vazio.");
        }
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new InvalidInputException("O email informado não é válido.");
        }

        User user = new User(userId, name, email);
        service.updateUser(user);
    }

    public void deleteUser() {
        System.out.print("\nDigite o ID do usuário que deseja excluir: ");
        int userId = readPositiveInteger("ID do usuário");

        if (service.findUserById(userId) == null) {
            throw new UserNotFoundException("Nenhum usuário encontrado com o ID fornecido.");
        }

        service.deleteUser(userId);
    }

    public void findUserById() {
        System.out.print("\nDigite o ID do usuário que buscar: ");
        int userId = readPositiveInteger("ID do usuário");

        User user = service.findUserById(userId);
        if (user == null) {
            throw new UserNotFoundException("Nenhum usuário encontrado com o ID informado.");
        }

        System.out.println("\nID: " + user.getId()
                + "\nNome: " + user.getName()
                + "\nEmail: " + user.getEmail()
        );
    }

    public void getUsers() {
        List<User> users = service.getUsers();

        if (users == null || users.isEmpty()) {
            throw new UserNotFoundException("Nenhum usuário encontrado.");
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
            throw new InvalidInputException("O nome não pode estar vazio. Tente novamente.");
        }

        List<User> users = service.searchUser(name);

        if (users == null || users.isEmpty()) {
            throw new UserNotFoundException("Nenhum usuário encontrado com o nome fornecido.");
        }

        System.out.println("\nUsuários encontrados:");
        for (User user : users) {
            System.out.println("\nID: " + user.getId()
                    + "\nNome: " + user.getName()
                    + "\nEmail: " + user.getEmail()
            );
        }
    }

    private int readPositiveInteger(String fieldName) {
        try {
            int value = Integer.parseInt(scanner.nextLine());
            if (value <= 0) {
                throw new InvalidInputException(fieldName + " deve ser um número positivo.");
            }
            return value;
        } catch (NumberFormatException e) {
            throw new InvalidInputException(fieldName + " deve ser um número inteiro.");
        }
    }
}
