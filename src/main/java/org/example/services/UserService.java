package org.example.services;

import org.example.daos.UserDAO;
import org.example.exceptions.DatabaseOperationException;
import org.example.models.User;

import java.sql.*;
import java.util.List;

public class UserService {

    UserDAO userDAO = new UserDAO();

    public void addUser(User user) {
        try {
            userDAO.add(user);
        } catch (SQLException e) {
            throw new DatabaseOperationException("Erro ao adicionar usuário: " + e.getMessage());
        }
    }

    public void updateUser(User user) {
        try {
            userDAO.update(user);
            System.out.println("Usuário atualizado com sucesso!");
        } catch (SQLException e) {
            throw new DatabaseOperationException("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    public void deleteUser(int id) {
        try {
            userDAO.delete(id);
            System.out.println("Usuário excluído com sucesso!");
        } catch (SQLException e) {
            throw new DatabaseOperationException("Erro ao deletar usuário: " + e.getMessage());
        }
    }

    public User findUserById(int id) {
        try {
            return userDAO.findById(id);
        } catch (SQLException e) {
            throw new DatabaseOperationException("\nErro ao buscar usuário: " + e.getMessage());
        }
    }

    public List<User> getUsers() {
        try {
            List<User> users = userDAO.getAll();
            if (users.isEmpty()) {
                System.out.println("Nenhum usuário encontrado!");
            }
            return users;
        } catch (SQLException e) {
            throw new DatabaseOperationException("Erro ao listar usuários: " + e.getMessage());
        }
    }

    public List<User> searchUser(String name) {
        try {
            List<User> users = userDAO.searchByName(name);
            if (users.isEmpty()) {
                System.out.println("Nenhum usuário encontrado!");
            } else {
                return users;
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Erro ao buscar usuário: " + e.getMessage());
        }
        return null;
    }

    public boolean emailExists(String email) {
        try {
            return userDAO.emailExists(email);
        } catch (SQLException e) {
            throw new DatabaseOperationException("Erro: O email já está em uso.");
        }
    }
}
