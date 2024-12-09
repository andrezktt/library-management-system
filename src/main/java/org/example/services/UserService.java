package org.example.services;

import org.example.config.DatabaseConnection;
import org.example.daos.UserDAO;
import org.example.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    UserDAO dao = new UserDAO();

    public void addUser(User user) {
        try {
            dao.add(user);
            System.out.println("Usuário adicionado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar usuário: " + e.getMessage());
        }
    }

    public void updateUser(User user) {
        try {
            dao.update(user);
            System.out.println("Usuário atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    public void deleteUser(int id) {
        try {
            dao.delete(id);
            System.out.println("Usuário excluído com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar usuário: " + e.getMessage());
        }
    }

    public User findUserById(int id) {
        try {
            return dao.findById(id);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
        }
        return null;
    }

    public List<User> getUsers() {
        try {
            List<User> users = dao.getAll();
            if (users.isEmpty()) {
                System.out.println("Nenhum usuário encontrado!");
            }
            return users;
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
        }
        return null;
    }

    public List<User> searchUser(String name) {
        try {
            List<User> users = dao.searchByName(name);
            if (users.isEmpty()) {
                System.out.println("Nenhum usuário encontrado!");
            } else {
                return users;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
        }
        return null;
    }
}
