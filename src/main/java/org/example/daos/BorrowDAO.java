package org.example.daos;

import org.example.config.DatabaseConnection;
import org.example.models.Book;
import org.example.models.Borrow;
import org.example.models.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowDAO implements GenericDAO<Borrow> {

    @Override
    public void add(Borrow entity) throws SQLException {
        String sql = "INSERT INTO borrows (book_id, user_id, borrow_date, return_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, entity.getBookId());
            stmt.setInt(2, entity.getUserId());
            stmt.setDate(3, Date.valueOf(entity.getBorrowDate()));
            stmt.setDate(4, Date.valueOf(entity.getReturnDate()));
            stmt.executeUpdate();
        }
    }

    @Override
    public void update(Borrow entity) throws SQLException {
        String sql = "UPDATE borrows SET book_id = ?, user_id = ?, borrow_date = ?, return_date = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, entity.getBookId());
            stmt.setInt(2, entity.getUserId());
            stmt.setDate(3, Date.valueOf(entity.getBorrowDate()));
            stmt.setDate(4, Date.valueOf(entity.getReturnDate()));
            stmt.executeUpdate();
        }
    }

    public void returnBook(int id, LocalDate returnDate) throws SQLException {
        String sql = "UPDATE borrows SET return_date = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(returnDate));
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM borrows WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public Borrow findById(int id) throws SQLException {
        String sql = "SELECT * FROM borrows WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return new Borrow(
                    rs.getInt("id"),
                    rs.getInt("book_id"),
                    rs.getInt("user_id"),
                    rs.getDate("borrow_date").toLocalDate(),
                    rs.getDate("return_date").toLocalDate()
            );
        }
    }

    @Override
    public List<Borrow> getAll() throws SQLException {
        String sql = "SELECT " +
                "a.id AS borrow_id, " +
                "a.borrow_date, " +
                "a.return_date, " +
                "b.id AS user_id, " +
                "b.name AS user_name, " +
                "b.email AS user_email, " +
                "c.id AS book_id, " +
                "c.title AS book_title, " +
                "c.author AS book_author, " +
                "c.available AS book_available " +
                "FROM borrows a " +
                "JOIN users b ON a.user_id = b.id " +
                "JOIN books c ON a.book_id = c.id " +
                "ORDER BY borrow_id DESC";
        List<Borrow> borrows = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_email")
                );

                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("book_title"),
                        rs.getString("book_author"),
                        rs.getBoolean("book_available")
                );

                Borrow borrow = new Borrow(
                        rs.getInt("borrow_id"),
                        book,
                        user,
                        rs.getDate("borrow_date").toLocalDate(),
                        rs.getDate("return_date").toLocalDate()
                );
                borrows.add(borrow);
            }
        }
        return borrows;
    }

    public List<Borrow> getBooksByUser(int id) throws SQLException {
        String sql = "SELECT " +
                "a.id AS borrow_id, " +
                "a.borrow_date, " +
                "a.return_date, " +
                "b.id AS user_id, " +
                "b.name AS user_name, " +
                "b.email AS user_email, " +
                "c.id AS book_id, " +
                "c.title AS book_title, " +
                "c.author AS book_author, " +
                "c.available AS book_available " +
                "FROM borrows a " +
                "JOIN users b ON a.user_id = b.id " +
                "JOIN books c ON a.book_id = c.id " +
                "WHERE user_id = ? " +
                "ORDER BY borrow_id DESC";
        List<Borrow> borrows = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_email")
                );

                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("book_title"),
                        rs.getString("book_author"),
                        rs.getBoolean("book_available")
                );

                Borrow borrow = new Borrow(
                        rs.getInt("borrow_id"),
                        book,
                        user,
                        rs.getDate("borrow_date").toLocalDate(),
                        rs.getDate("return_date").toLocalDate()
                );
                borrows.add(borrow);
            }
        }
        return borrows;
    }

    public List<Borrow> getUsersByBook(int id) throws SQLException {
        String sql = "SELECT " +
                "a.id AS borrow_id, " +
                "a.borrow_date, " +
                "a.return_date, " +
                "b.id AS user_id, " +
                "b.name AS user_name, " +
                "b.email AS user_email, " +
                "c.id AS book_id, " +
                "c.title AS book_title, " +
                "c.author AS book_author, " +
                "c.available AS book_available " +
                "FROM borrows a " +
                "JOIN users b ON a.user_id = b.id " +
                "JOIN books c ON a.book_id = c.id " +
                "WHERE book_id = ? " +
                "ORDER BY user_name";
        List<Borrow> borrows = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_email")
                );

                Book book = new Book(
                        rs.getInt("book_id"),
                        rs.getString("book_title"),
                        rs.getString("book_author"),
                        rs.getBoolean("book_available")
                );

                Borrow borrow = new Borrow(
                        rs.getInt("borrow_id"),
                        book,
                        user,
                        rs.getDate("borrow_date").toLocalDate(),
                        rs.getDate("return_date").toLocalDate()
                );
                borrows.add(borrow);
            }
        }
        return borrows;
    }
}
