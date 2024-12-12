package org.example.services;

import org.example.daos.BorrowDAO;
import org.example.exceptions.BorrowDatabaseException;
import org.example.exceptions.BorrowNotFoundException;
import org.example.models.Borrow;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class BorrowService {

    private final BorrowDAO borrowDAO = new BorrowDAO();

    public boolean borrowBook(Borrow borrow) {
        try {
            borrowDAO.add(borrow);
        } catch (SQLException e) {
            throw new BorrowDatabaseException("Erro ao registrar empréstimo no banco de dados!", e);
        }
        return true;
    }

    public void returnBook(int id, LocalDate returnDate) {
        try {
            borrowDAO.returnBook(id, returnDate);
        } catch (SQLException e) {
            throw new BorrowDatabaseException("Erro ao registrar devolução no banco de dados!", e);
        }
    }

    public boolean updateBorrowRecord(Borrow borrow) {
        try {
            borrowDAO.update(borrow);
        } catch (SQLException e) {
            throw new BorrowDatabaseException("Erro ao atualizar dados do empréstimo no banco de dados!", e);
        }
        return true;
    }

    public boolean deleteBorrowRecord(int id) {
        try {
            borrowDAO.delete(id);
            System.out.println("\nRegistro de empréstimo excluído com sucesso!");
        } catch (SQLException e) {
            throw new BorrowDatabaseException("Erro ao excluir registro de empréstimo no banco de dados!", e);
        }
        return true;
    }

    public List<Borrow> getBorrows() {
        try {
            List<Borrow> borrows = borrowDAO.getAll();
            if (borrows.isEmpty()) {
                throw new BorrowNotFoundException("Nenhum empréstimo encontrado.");
            }
            return borrows;
        } catch (SQLException e) {
            throw new BorrowDatabaseException("Erro ao listar empréstimos!", e);
        }
    }

    public Borrow findById(int id) {
        try {
            return borrowDAO.findById(id);
        } catch (SQLException e) {
            throw new BorrowDatabaseException("Erro ao buscar empréstimo!", e);
        }
    }

    public List<Borrow> getBooksByUser(int userId) {
        try {
            List<Borrow> borrows = borrowDAO.getBooksByUser(userId);
            if (borrows.isEmpty()) {
                throw new BorrowNotFoundException("Nenhum livro foi emprestado para este usuário.");
            }
            return borrows;
        } catch (SQLException e) {
            throw new BorrowDatabaseException("Erro ao buscar os livros emprestados para este usuário", e);
        }
    }

    public List<Borrow> getUsersByBook(int bookId) {
        try {
            List<Borrow> borrows = borrowDAO.getUsersByBook(bookId);
            if (borrows.isEmpty()) {
                throw new BorrowNotFoundException("Nenhum usuário emprestou este livro por enquanto.");
            }
            return borrows;
        } catch (SQLException e) {
            throw new BorrowDatabaseException("Erro ao buscar os usuários que emprestaram este livro!", e);
        }
    }
}
