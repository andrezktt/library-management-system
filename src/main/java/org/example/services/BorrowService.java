package org.example.services;

import org.example.daos.BorrowDAO;
import org.example.models.Borrow;

import java.sql.SQLException;
import java.util.List;

public class BorrowService {

    private final BorrowDAO dao = new BorrowDAO();

    public void borrowBook(Borrow borrow) {
        try {
            dao.add(borrow);
            System.out.println("Empréstimo feito com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Borrow> getBorrows() {
        try {
            List<Borrow> borrows = dao.getAll();
            if (borrows.isEmpty()) {
                System.out.println("Nenhum empréstimo encontrado!");
            }
            return borrows;
        } catch (SQLException e) {
            System.err.println("Erro ao listar empréstimos: " + e.getMessage());
        }
        return null;
    }

}
