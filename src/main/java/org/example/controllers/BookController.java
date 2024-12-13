package org.example.controllers;

import org.example.models.Book;
import org.example.services.BookService;

import java.util.List;
import java.util.Scanner;

public class BookController {

    private static final Scanner scanner = new Scanner(System.in);
    private static final BookService service = new BookService();

    public void addBook() {
        String title = "";
        while (title.trim().isEmpty()) {
            System.out.print("\nDigite o título do livro (mínimo 3 caracteres): ");
            title = scanner.nextLine();
            if (title.trim().isEmpty()) {
                System.out.println("\nErro: O título não pode ser vazio ou composto apenas por espaços.");
                return;
            } else if (title.length() < 3) {
                System.out.println("\nErro: O título deve ter pelo menos 3 caracteres.");
                return;
            }
        }

        String author = "";
        while (author.trim().isEmpty()) {
            System.out.print("Digite o autor do livro (mínimo 3 caracteres): ");
            author = scanner.nextLine();
            if (author.trim().isEmpty()) {
                System.out.println("\nErro: O nome do autor não pode ser vazio ou composto apenas por espaços.");
                return;
            } else if (author.length() < 3) {
                System.out.println("\nErro: O nome do autor deve ter pelo menos 3 caracteres.");
                return;
            }
        }

        Book book = new Book(0, title, author, true);
        service.addBook(book);
        System.out.println("\nLivro adicionado com sucesso!");
    }

    public void updateBook() {
        int bookId = -1;
        while (bookId < 0) {
            System.out.print("\nID do livro que deseja alterar: ");
            if (scanner.hasNextInt()) {
                bookId = scanner.nextInt();
                scanner.nextLine();
                if (bookId <= 0) {
                    System.out.println("\nErro: O ID do livro deve ser um número positivo.");
                    return;
                }
            } else {
                System.out.println("\nPor favor, insira um número válido para o ID do livro.");
                return;
            }
        }

        if (service.findBookById(bookId) == null) {
            System.out.println("Erro: Nenhum livro encontrado com o ID fornecido.");
            return;
        }

        String title = "";
        while (title.trim().isEmpty()) {
            System.out.print("Digite o título do livro (mínimo 3 caracteres): ");
            title = scanner.nextLine();
            if (title.trim().isEmpty()) {
                System.out.println("\nErro: O título não pode ser vazio ou composto apenas por espaços.");
                return;
            } else if (title.length() < 3) {
                System.out.println("\nErro: O título deve ter pelo menos 3 caracteres.");
                return;
            }
        }

        String author = "";
        while (author.trim().isEmpty()) {
            System.out.print("Digite o autor do livro (mínimo 3 caracteres): ");
            author = scanner.nextLine();
            if (author.trim().isEmpty()) {
                System.out.println("\nErro: O nome do autor não pode ser vazio ou composto apenas de espaços.");
                return;
            } else if (author.length() < 3) {
                System.out.println("\nErro: O nome do autor deve ter pelo menos 3 caracteres.");
                return;
            }
        }

        Book book = new Book(bookId, title, author, true);
        service.updateBook(book);
        System.out.println("\nLivro atualizado com sucesso!");
    }

    public void deleteBook() {
        int bookId = -1;
        while (bookId < 0) {
            System.out.print("\nDigite o ID do livro que deseja excluir: ");
            if (scanner.hasNextInt()) {
                bookId = scanner.nextInt();
                scanner.nextLine();
                if (bookId < 0) {
                    System.out.println("\nErro: O ID do livro deve ser um número positivo.");
                    return;
                }
            } else {
                System.out.println("Por favor, insira um número válido para o ID do livro.");
                return;
            }
        }

        if (service.findBookById(bookId) == null) {
            System.out.println("\nErro: Não há nenhum livro registrado com o ID fornecido.");
            return;
        }

        System.out.print("Tem certeza que deseja excluir o livro com ID " + bookId + "? (s/n): ");
        String confirmation = scanner.nextLine();
        if (confirmation.equalsIgnoreCase("s")) {
            service.deleteBook(bookId);
            System.out.println("\nLivro excluído com sucesso!");
        } else {
            System.out.println("\nExclusão cancelada!");
        }
    }

    public void findBookById() {
        int bookId = -1;
        while (bookId <= 0) {
            System.out.print("/Digite o ID do livro que deseja buscar: ");
            if (scanner.hasNextInt()) {
                bookId = scanner.nextInt();
                scanner.nextLine();
                if (bookId <= 0) {
                    System.out.println("O ID do livro deve ser um número positivo. Tente novamente.");
                    return;
                }
            } else {
                System.out.println("Por favor, insira um número válido para o ID do livro.");
                return;
            }
        }

        Book book = service.findBookById(bookId);
        if (book == null) {
            System.out.println("Erro: Não há nenhum livro registrado com o ID informado.");
        } else {
            System.out.println("\nID: " + book.getId()
                    + "\nTítulo: " + book.getTitle()
                    + "\nAutor: " + book.getAuthor()
                    + "\nDisponibilidade: " + (book.isAvailable() ? "Disponível" : "Indisponível")
            );
        }
    }

    public void getBooks() {
        List<Book> books = service.getBooks();
        if (books == null || books.isEmpty()) {
            System.out.println("\nErro: Não há livros cadastrados no sistema.");
            return;
        }

        System.out.println("\nLivros encontrados: ");
        for (Book book : books) {
            System.out.println("\n[ID: " + book.getId() + "] "
                    + (book.isAvailable() ? "DISPONÍVEL" : "INDISPONÍVEL")
                    + "\nTítulo: " + book.getTitle()
                    + "\nAutor: " + book.getAuthor()
            );
        }
    }

    public void searchBook() {

        String query = "";
        while (query.trim().isEmpty()) {
            System.out.print("\nDigite qual livro deseja buscar: ");
            query = scanner.nextLine();
            if (query.trim().isEmpty()) {
                System.out.println("Erro: A pesquisa não pode ser vazia. Por favor, insira um título ou autor para realizar a busca.");
                return;
            }
        }

        List<Book> books = service.searchBooks(query);

        if (books.isEmpty()) {
            System.out.println("\"Nenhum livro encontrado com o termo de pesquisa: \\\"\" + query + \"\\\".\"");
        } else {
            System.out.println("\nLivros encontrados: ");
            for (Book book : books) {
                System.out.println("\n[ID: " + book.getId() + "] "
                        + (book.isAvailable() ? "DISPONÍVEL" : "INDISPONÍVEL")
                        + "\nTítulo: " + book.getTitle()
                        + "\nAutor: " + book.getAuthor()
                );
            }
        }
    }

    public void getAvailableBooks() {
        List<Book> books = service.getAvailableBooks();

        if (books == null || books.isEmpty()) {
            System.out.println("Atenção: Não há livros disponíveis no momento.");
            return;
        }

        System.out.println("\nLivros Disponíveis:");
        for (Book book : books) {
            System.out.println("\n[ID: " + book.getId() + "] " + (book.isAvailable() ? "DISPONÍVEL" : "INDISPONÍVEL")
                    + "\nTítulo: " + book.getTitle()
                    + "\nAutor: " + book.getAuthor()
            );
        }
    }

    public void getUnavailableBooks() {
        List<Book> books = service.getUnavailableBooks();

        if (books == null || books.isEmpty()) {
            System.out.println("\nTodos os livros estão disponíveis no momento.");
            return;
        }

        System.out.println("\nLivros Indisponíveis:");
        for (Book book : books) {
            System.out.println("\n[ID: " + book.getId() + "] "
                    + (book.isAvailable() ? "DISPONÍVEL" : "INDISPONÍVEL")
                    + "\nTítulo: " + book.getTitle()
                    + "\nAutor: " + book.getAuthor()
            );
        }
    }
}
