package org.example.services;

import org.example.daos.BookDAO;
import org.example.exceptions.DatabaseOperationException;
import org.example.models.Book;

import java.sql.*;
import java.util.List;

public class BookService {

    private final BookDAO bookDAO = new BookDAO();

    public void addBook(Book book) {
        try {
            bookDAO.add(book);
        } catch (SQLException e) {
            throw new DatabaseOperationException("Erro ao adicionar livro: " + e.getMessage());
        }
    }

    public boolean updateBook(Book book) {
        try {
            bookDAO.update(book);
        } catch (SQLException e) {
            throw new DatabaseOperationException("Erro ao atualizar livro: " + e.getMessage());
        }
        return false;
    }

    public void deleteBook(int id) {
        try {
            bookDAO.delete(id);
        } catch (SQLException e) {
            throw new DatabaseOperationException("Erro ao deletar livro: " + e.getMessage());
        }
    }

    public Book findBookById(int id) {
        try {
            return bookDAO.findById(id);
        } catch (SQLException e) {
            throw new DatabaseOperationException("Erro ao buscar livro: " + e.getMessage());
        }
    }

    public List<Book> getBooks() {
        try {
            return bookDAO.getAll();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Erro ao listar livros: " + e.getMessage());
        }
    }

    public List<Book> searchBooks(String query) {
        try {
            return bookDAO.searchBooks(query);
        } catch (SQLException e) {
            throw new DatabaseOperationException("Erro ao buscar livro: " + e.getMessage());
        }
    }

    public List<Book> getAvailableBooks() {
        try {
            return bookDAO.getAvailable();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Erro ao listar livros disponíveis: " + e.getMessage());
        }
    }

    public List<Book> getUnavailableBooks() {
        try {
            return bookDAO.getUnavailable();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Erro ao listar livros indisponíveis: " + e.getMessage());
        }
    }
}
