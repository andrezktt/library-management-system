package org.example.controllers;

import org.example.models.User;
import org.example.services.UserService;

import java.util.List;
import java.util.Scanner;

public class UserController {

    private static final Scanner scanner = new Scanner(System.in);
    private static final UserService service = new UserService();

    public void addUser() {
        String name = "";
        while (name.trim().isEmpty()) {
            System.out.print("\nDigite o nome do usuário: ");
            name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                System.out.println("\nErro: O nome do usuário não pode estar vazio.");
                return;
            } else if (name.length() < 3) {
                System.out.println("\nErro: O nome deve ter pelo menos 3 caracteres.");
                return;
            }
        }

        String email = "";
        while (email.trim().isEmpty()) {
            System.out.print("Digite o email do usuário: ");
            email = scanner.nextLine();
            if (email.isEmpty()) {
                System.out.println("\nErro: O email não pode estar vazio. Tente novamente.");
                return;
            } else if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                System.out.println("\nErro: O email informado não é válido. Tente novamente.");
                return;
            } else if (service.emailExists(email)) {
                System.out.println("\nErro: O email informado já está em uso. Tente novamente.");
                return;
            }
        }

        User user = new User(0, name, email);
        service.addUser(user);

        System.out.println("\nUsuário adicionado com sucesso!");
    }

    public void updateUser() {
        int userId = -1;
        while (userId < 0) {
            System.out.print("\nID do usuário que deseja alterar: ");
            if (scanner.hasNextInt()) {
                userId = scanner.nextInt();
                scanner.nextLine();
                if (userId <= 0) {
                    System.out.println("\nErro: O ID do usuário deve ser um número positivo.");
                    return;
                }
            } else {
                System.out.println("\nPor favor, insira um número válido para o ID do usuário.");
                return;
            }
        }

        if (service.findUserById(userId) == null) {
            System.out.println("\nErro: Não existe um usuário com o ID informado.");
            return;
        }

        String name = "";
        while (name.trim().isEmpty()) {
            System.out.print("Digite o novo nome do usuário (mínimo 3 caracteres): ");
            name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                System.out.println("\nErro: O nome do usuário não pode estar vazio.");
                return;
            } else if (name.length() < 3) {
                System.out.println("/Erro: O nome deve ter pelo menos 3 caracteres.");
                return;
            }
        }

        String email = "";
        while (email.trim().isEmpty()) {
            System.out.print("Digite o novo email do usuário: ");
            email = scanner.nextLine();
            if (email.trim().isEmpty()) {
                System.out.println("\nErro: O email do usuário não pode estar vazio.");
                return;
            } else if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                System.out.println("\nErro: O email informado não é válido.");
                return;
            }
        }

        User user = new User(userId, name, email);
        service.updateUser(user);
    }

    public void deleteUser() {
        int userId = -1;
        while (userId <= 0) {
            System.out.print("\nDigite o ID do usuário que deseja excluir: ");
            if (scanner.hasNextInt()) {
                userId = scanner.nextInt();
                scanner.nextLine();
                if (userId < 0) {
                    System.out.println("\nErro: O ID do usuário deve ser um número positivo.");
                    return;
                }
            } else {
                System.out.println("\nPor favor, insira um número válido para o ID do usuário.");
                return;
            }
        }

        if (service.findUserById(userId) == null) {
            System.out.println("\nErro: Nenhum usuário encontrado com o ID fornecido.");
            return;
        }

        System.out.print("Tem certeza que deseja excluir o usuário com ID " + userId + "? (s/n): ");
        String confirmation = scanner.nextLine();
        if (confirmation.equalsIgnoreCase("s")) {
            service.deleteUser(userId);
            System.out.println("\nUsuário excluído com sucesso!");
        } else {
            System.out.println("\nExclusão cancelada!");
        }
    }

    public void findUserById() {
        int userId = -1;
        while (userId <= 0) {
            System.out.print("\nDigite o ID do usuário que buscar: ");
            if (scanner.hasNextInt()) {
                userId = scanner.nextInt();
                scanner.nextLine();
                if (userId <= 0) {
                    System.out.println("\nErro: O ID do usuário deve ser um número positivo.");
                    return;
                }
            } else {
                System.out.println("\nPor favor, insira um número válido para o ID do usuário.");
                return;
            }
        }

        User user = service.findUserById(userId);
        if (user == null) {
            System.out.println("\nErro: Nenhum usuário encontrado com o ID informado.");
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
            System.out.println("\nErro: Nenhum usuário encontrado.");
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
        String nameOrEmail = "";
        while (nameOrEmail.trim().isEmpty()) {
            System.out.print("\nDigite o nome do usuário que deseja buscar: ");
            nameOrEmail = scanner.nextLine();
            if (nameOrEmail.trim().isEmpty()) {
                System.out.println("\nErro: A pesquisa não pode estar vazia. Tente novamente.");
                return;
            }
        }

        List<User> users = service.searchUser(nameOrEmail);

        if (users == null || users.isEmpty()) {
            System.out.println("\nErro: Nenhum usuário encontrado com o nome fornecido.");
        } else {
            System.out.println("\nUsuários encontrados:");
            for (User user : users) {
                System.out.println("\nID: " + user.getId()
                        + "\nNome: " + user.getName()
                        + "\nEmail: " + user.getEmail()
                );
            }
        }
    }
}
