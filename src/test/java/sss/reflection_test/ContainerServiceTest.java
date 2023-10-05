package sss.reflection_test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerServiceTest {

    @Test
    void withoutInject() {
        BookRepository bookRepository = ContainerService.getObjectCustom(BookRepository.class);

        assertNotNull(bookRepository);
        bookRepository.desc();
    }

    @Test
    void withInject() {
        BookService bookService = ContainerService.getObjectCustom(BookService.class);

        assertNotNull(bookService);
        assertNotNull(bookService.bookRepository);
        bookService.bookRepository.desc();
        assertNull(bookService.bookRepositoryWithoutInject);
    }
}