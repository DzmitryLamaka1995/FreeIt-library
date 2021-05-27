package by.lamaka.library.dao;

import by.lamaka.library.bean.Author;
import by.lamaka.library.bean.Book;

import java.util.List;

public interface LibraryDAO {
    boolean addBook(Book book) throws DAOException;
    boolean removeBookById(int idBook) throws DAOException;
    List<Book> getAllBooks() throws DAOException;
    boolean editBookById(int idBook, Book newBook) throws DAOException;
    List<Book> getBooksByAuthor(Author author) throws DAOException;
    List<Author> getAllAuthors() throws DAOException;
}
