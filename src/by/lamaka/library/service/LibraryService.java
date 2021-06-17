package by.lamaka.library.service;

import by.lamaka.library.bean.Author;
import by.lamaka.library.bean.Book;

import java.util.List;

public interface LibraryService {
    boolean addBook(Book book) throws ServiceException;

    boolean editBook(int idBook, Book newBook) throws ServiceException;

    List<Author> getAllAuthors() throws ServiceException;

    List<Book> getAllBooks() throws ServiceException;

    List<Book> getBooksByAuthor(Author author) throws ServiceException;

    boolean removeBookById(int idBook) throws ServiceException;
}
