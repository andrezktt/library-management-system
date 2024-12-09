package org.example.controllers;

import org.example.models.Book;
import org.example.models.User;
import org.example.services.UserService;

import java.util.List;
import java.util.Scanner;

public class UserController {

    private static Scanner scanner = new Scanner(System.in);
    private static UserService service = new UserService();

    public void addUser() {
        System.out.print("\nDigite o nome do usuário: ");
        String name = scanner.nextLine();
        System.out.print("Digite o email do usuário: ");
        String email = scanner.nextLine();

        User user = new User(0, name, email);

        service.addUser(user);
    }

    public void updateUser() {
        System.out.print("\nID do usuário que deseja alterar: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite o novo nome do usuário: ");
        String name = scanner.nextLine();
        System.out.print("Digite o novo email do usuário: ");
        String email = scanner.nextLine();

        User user = new User(userId, name, email);
        service.updateUser(user);
    }

    public void deleteUser() {
        System.out.print("\nDigite o ID do usuário que deseja excluir: ");
        int userId = scanner.nextInt();
        service.deleteUser(userId);
    }

    public void findUserById() {
        System.out.print("\nDigite o ID do usuário que buscar: ");
        int userId = scanner.nextInt();
        User user = service.findUserById(userId);
        System.out.println("\nID: " + user.getId()
                + "\nNome: " + user.getName()
                + "\nEmail: " + user.getEmail()
        );
    }

    public void getUsers() {
        List<User> users = service.getUsers();
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

        List<User> users = service.searchUser(name);
        System.out.println("\nUsuários encontrados:");
        for (User user : users) {
            System.out.println("\nID: " + user.getId()
                    + "\nNome: " + user.getName()
                    + "\nEmail: " + user.getEmail()
            );
        }
    }
}
