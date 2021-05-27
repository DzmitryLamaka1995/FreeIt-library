package by.lamaka.library.service.impl;

import by.lamaka.library.bean.Author;
import by.lamaka.library.bean.Book;
import by.lamaka.library.dao.DAOException;
import by.lamaka.library.dao.DAOProvider;
import by.lamaka.library.dao.LibraryDAO;
import by.lamaka.library.service.LibraryService;
import by.lamaka.library.service.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class LibraryServiceImpl implements LibraryService {
    @Override
    public boolean addBook(Book book) throws ServiceException {
        boolean result = false;
        DAOProvider daoProvider = DAOProvider.getInstance();
        LibraryDAO libraryDAO = daoProvider.getLibraryDAO();
        try {
            if (book != null && !checkBookByNull(book)) {
                result = libraryDAO.addBook(book);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }


    @Override
    public boolean editBook(int idBook, Book newBook) throws ServiceException {
        boolean result = false;
        DAOProvider daoProvider = DAOProvider.getInstance();
        LibraryDAO libraryDAO = daoProvider.getLibraryDAO();
        try {
            if (newBook != null && !checkBookByNull(newBook)) {
                result = libraryDAO.editBookById(idBook, newBook);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public List<Author> getAllAuthors() throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        LibraryDAO libraryDAO = daoProvider.getLibraryDAO();
        List<Author> authors = new ArrayList<>();
        try {
            authors = libraryDAO.getAllAuthors();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return authors;
    }

    @Override
    public List<Book> getAllBooks() throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        LibraryDAO libraryDAO = daoProvider.getLibraryDAO();
        List<Book> books = new ArrayList<>();
        try {
            books = libraryDAO.getAllBooks();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return books;
    }

    @Override
    public List<Book> getBooksByAuthor(Author author) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        LibraryDAO libraryDAO = daoProvider.getLibraryDAO();
        List<Book> books = new ArrayList<>();
        try {
            if (author != null && !"".equals(author.getFirstName()) || !"".equals(author.getLastName())) {
                books = libraryDAO.getBooksByAuthor(author);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return books;
    }

    @Override
    public boolean removeBookById(int idBook) throws ServiceException {
        boolean result = false;
        DAOProvider daoProvider = DAOProvider.getInstance();
        LibraryDAO libraryDAO = daoProvider.getLibraryDAO();
        try {
            if (idBook>0) {
                result = libraryDAO.removeBookById(idBook);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    private boolean checkBookByNull(Book book) {
        boolean result = true;
        if (book.getAuthor() == null || "".equals(book.getAuthor().getFirstName()) || "".equals(book.getAuthor().getLastName())) {
            return result;
        } else if ("".equals(book.getTitle()) || "".equals(book.getGenre().toString())) {
            return result;
        } else {
            result = false;
        }
        return result;
    }
}
