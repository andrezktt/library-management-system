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
            System.err.println("Erro ao adicionar livro: " + e.getMessage());
        }
    }

    public List<Book> getBooks() {
        try {
            List<Book> books = bookDAO.getAll();
            if (books.isEmpty()) {
                System.out.println("Nenhum livro encontrado!");
            }
            return books;
        } catch (SQLException e) {
            System.err.println("Erro ao listar os livros: " + e.getMessage());
        }
        return null;
    }

    public void updateBook(Book book) {
        try {
            bookDAO.update(book);
            System.out.println("Livro atualizado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar livro: " + e.getMessage());
        }
    }

    public void deleteBook(int id) {
        try {
            bookDAO.delete(id);
            System.out.println("Livro excluído com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao deletar livro: " + e.getMessage());
        }
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

//    public  void borrowBook(int bookId, int userId) throws SQLException {
//        String sql = "UPDATE books " +
//                "SET available = FALSE, borrowed_for = ? " +
//                "WHERE id = ? AND available = TRUE";
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, userId);
//            stmt.setInt(2, bookId);
//            int rowsUpdated = stmt.executeUpdate();
//            if (rowsUpdated == 0) {
//                throw  new SQLException("This book is not available right now!");
//            }
//        }
//    }
//
//    public void returnBook(int bookId) throws SQLException {
//        String sql = "UPDATE books " +
//                "SET available = TRUE, borrowed_for = NULL " +
//                "WHERE id = ?";
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setInt(1, bookId);
//            stmt.executeUpdate();
//        }
//    }
}
