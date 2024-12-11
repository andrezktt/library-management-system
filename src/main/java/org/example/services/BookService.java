package org.example.services;

import org.example.daos.BookDAO;
import org.example.models.Book;

import java.sql.*;
import java.util.List;

public class BookService {

    private final BookDAO bookDAO = new BookDAO();

    public void addBook(Book book) {
        try {
            bookDAO.add(book);
            System.out.println("Livro adicionado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateBook(Book book) {
        try {
            bookDAO.update(book);
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar livro: " + e.getMessage());
        }
        return false;
    }

    public void deleteBook(int id) {
        try {
            bookDAO.delete(id);
            System.out.println("Livro excluído com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar livro: " + e.getMessage());
        }
    }

    public Book findBookById(int id) {
        try {
            return bookDAO.findById(id);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar livro: " + e.getMessage());
        }
        return null;
    }

    public List<Book> getBooks() {
        try {
            List<Book> books = bookDAO.getAll();
            if (books.isEmpty()) {
                System.out.println("Nenhum livro encontrado!");
            }
            return books;
        } catch (SQLException e) {
            System.err.println("Erro ao listar livros: " + e.getMessage());
        }
        return null;
    }

    public List<Book> searchBooks(String query) {
        try {
            List<Book> books = bookDAO.searchBooks(query);
            if (books.isEmpty()) {
                System.out.println("Nenhum livro encontrado!");
            } else {
                return books;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar livro: " + e.getMessage());
        }
        return null;
    }

    public List<Book> getAvailableBooks() {
        try {
            List<Book> books = bookDAO.getAvailable();
            if (books.isEmpty()) {
                System.out.println("Nenhum livro encontrado!");
            }
            return books;
        } catch (SQLException e) {
            System.err.println("Erro ao listar livros disponíveis: " + e.getMessage());
        }
        return null;
    }

    public List<Book> getUnavailableBooks() {
        try {
            List<Book> books = bookDAO.getUnavailable();
            if (books.isEmpty()) {
                System.out.println("Nenhum livro encontrado!");
            }
            return books;
        } catch (SQLException e) {
            System.err.println("Erro ao listar livros indisponíveis: " + e.getMessage());
        }
        return null;
    }
}
