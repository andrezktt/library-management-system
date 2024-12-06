import org.example.models.Book;
import org.example.services.BookService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BookServiceTest {
    @Test
    void testAddBook() {
        BookService bookService = new BookService();
        Book book = new Book(0, "Ensaio sobre a cegueira", "José Saramago", true);

        assertDoesNotThrow(() -> bookService.addBook(book));
    }
}
