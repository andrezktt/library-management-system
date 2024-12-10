package org.example.services;

import org.example.daos.BorrowDAO;
import org.example.models.Borrow;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class BorrowService {

    private final BorrowDAO borrowDAO = new BorrowDAO();

    public void borrowBook(Borrow borrow) {
        try {
            borrowDAO.add(borrow);
            System.out.println("\nEmpréstimo feito com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao realizar empréstimo: " + e.getMessage());
        }
    }

    public void returnBook(int id, LocalDate returnDate) {
        try {
            borrowDAO.returnBook(id, returnDate);
            System.out.println("\nLivro devolvido com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao devolver livro: " + e.getMessage());
        }
    }

    public void updateBorrowRecord(Borrow borrow) {
        try {
            borrowDAO.update(borrow);
            System.out.println("\nDados do empréstimo atualizados com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar os dados do empréstimo: " + e.getMessage());
        }
    }

    public void deleteBorrowRecord(int id) {
        try {
            borrowDAO.delete(id);
            System.out.println("\nRegistro de empréstimo excluído com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao excluir registro: " + e.getMessage());
        }
    }

    public List<Borrow> getBorrows() {
        try {
            List<Borrow> borrows = borrowDAO.getAll();
            if (borrows.isEmpty()) {
                System.out.println("\nNenhum empréstimo encontrado!");
            }
            return borrows;
        } catch (SQLException e) {
            System.err.println("Erro ao listar empréstimos: " + e.getMessage());
        }
        return null;
    }

    public Borrow findById(int id) {
        try {
            return borrowDAO.findById(id);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar empréstimo: " + e.getMessage());
        }
        return null;
    }

    public List<Borrow> getBooksByUser(int userId) {
        try {
            List<Borrow> borrows = borrowDAO.getBooksByUser(userId);
            if (borrows.isEmpty()) {
                System.out.println("\nNenhum livro encontrado!");
            }
            return borrows;
        } catch (SQLException e) {
            System.err.println("Erro ao listar empréstimos: " + e.getMessage());
        }
        return null;
    }

    public List<Borrow> getUsersByBook(int bookId) {
        try {
            List<Borrow> borrows = borrowDAO.getUsersByBook(bookId);
            if (borrows.isEmpty()) {
                System.out.println("\nNenhum usuário encontrado!");
            }
            return borrows;
        } catch (SQLException e) {
            System.err.println("Erro ao listar empréstimos: " + e.getMessage());
        }
        return null;
    }
}
