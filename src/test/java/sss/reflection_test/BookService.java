package sss.reflection_test;

public class BookService {
    @CustomInject
    BookRepository bookRepository;

    BookRepository bookRepositoryWithoutInject;
}
