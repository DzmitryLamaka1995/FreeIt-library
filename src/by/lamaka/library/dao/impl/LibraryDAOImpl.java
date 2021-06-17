package by.lamaka.library.dao.impl;

import by.lamaka.library.bean.Author;
import by.lamaka.library.bean.Book;
import by.lamaka.library.dao.DAOException;
import by.lamaka.library.dao.LibraryDAO;

import by.lamaka.library.dao.connection.impl.ConnectionDBImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibraryDAOImpl implements LibraryDAO {
    ConnectionDBImpl connectionDB = new ConnectionDBImpl();

    @Override
    public boolean addBook(Book book) throws DAOException {
        boolean result = false;
        int idAuthor = -1;
        try {
            PreparedStatement preparedStatement = connectionDB.getConnection().
                    prepareStatement("SELECT * FROM author WHERE first_name =? AND last_name =?");
            preparedStatement.setString(1, book.getAuthor().getFirstName());
            preparedStatement.setString(2, book.getAuthor().getLastName());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                idAuthor = Integer.parseInt(resultSet.getString("id"));
                preparedStatement.close();
                resultSet.close();
            } else {
                resultSet.close();
                preparedStatement.close();
                preparedStatement = connectionDB.getConnection().prepareStatement("INSERT INTO author (first_name,last_name) VALUES (? , ?)");
                preparedStatement.setString(1, book.getAuthor().getFirstName());
                preparedStatement.setString(2, book.getAuthor().getLastName());
                preparedStatement.executeUpdate();
                preparedStatement.close();

                preparedStatement = connectionDB.getConnection().
                        prepareStatement("SELECT * FROM author WHERE first_name =? AND last_name =?");
                preparedStatement.setString(1, book.getAuthor().getFirstName());
                preparedStatement.setString(2, book.getAuthor().getLastName());

                ResultSet resultSet2 = preparedStatement.executeQuery();
                if (resultSet2.next()) {
                    idAuthor = Integer.parseInt(resultSet.getString("id"));
                }
                resultSet2.close();
                preparedStatement.close();

            }
            resultSet.close();
            preparedStatement = connectionDB.getConnection().prepareStatement("INSERT INTO book (title,genre,id_author,date_created)" +
                    " VALUES (?,?,?,?)");
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getGenre().toString());
            preparedStatement.setString(3, String.valueOf(idAuthor));
            preparedStatement.setString(4, LocalDate.now().toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            result = true;

        } catch (SQLException throwables) {
            throw new DAOException(throwables);

        }
        return result;
    }

    @Override
    public boolean removeBookById(int idBook) throws DAOException {
        boolean result = false;
        Statement statement = connectionDB.getStatement();
        String sql = "DELETE FROM book WHERE id = " + idBook;
        try {
            if (statement.executeUpdate(sql) > 0) {
                result = true;
            }
        } catch (SQLException throwables) {
            throw new DAOException(throwables);
        }
        return result;
    }

    @Override
    public List<Book> getAllBooks() throws DAOException {
        List<Book> books = new ArrayList<>();
        Statement statement = connectionDB.getStatement();
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery("SELECT * FROM book b JOIN author a ON b.id_author=a.id");
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(Integer.parseInt(resultSet.getString("id")));
                book.setAuthor(new Author(resultSet.getString("first_name"), resultSet.getString("last_name")));
                book.setTitle(resultSet.getString("title"));
                book.setGenre(resultSet.getString("genre"));
                book.setDateCreated(LocalDate.parse(resultSet.getString("date_created")));
                books.add(book);
            }
        } catch (SQLException throwables) {
            throw new DAOException(throwables);
        }
        return books;
    }

    @Override
    public boolean editBookById(int idBook, Book newBook) throws DAOException {
        boolean result = false;
        try {
            int idAuthor = getIdAuthorByAuthor(newBook.getAuthor());
            if (idAuthor < 0) {
                PreparedStatement preparedStatement = connectionDB.getConnection()
                        .prepareStatement("INSERT INTO author (first_name,last_name) VALUES (? , ?)");
                preparedStatement.setString(1, newBook.getAuthor().getFirstName());
                preparedStatement.setString(2, newBook.getAuthor().getLastName());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
            idAuthor = getIdAuthorByAuthor(newBook.getAuthor());
            PreparedStatement preparedStatement = connectionDB.getConnection().
                    prepareStatement("UPDATE book SET title=?,genre=?,id_author=?,date_created=? WHERE id =?");
            preparedStatement.setString(1, newBook.getTitle());
            preparedStatement.setString(2, newBook.getGenre().toString());
            preparedStatement.setInt(3, idAuthor);
            preparedStatement.setString(4, newBook.getDateCreated().toString());
            preparedStatement.setInt(5, idBook);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            result = true;
        } catch (SQLException throwables) {
            result = false;
            throw new DAOException(throwables);
        }

        return result;
    }

    @Override
    public List<Book> getBooksByAuthor(Author author) throws DAOException {
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement prepareStatement = connectionDB.getConnection()
                    .prepareStatement("SELECT * FROM book b JOIN author a ON b.id_author=a.id WHERE first_name =? AND last_name=?");
            prepareStatement.setString(1, author.getFirstName());
            prepareStatement.setString(2, author.getLastName());
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(Integer.parseInt(resultSet.getString("id")));
                book.setAuthor(new Author(resultSet.getString("first_name"), resultSet.getString("last_name")));
                book.setTitle(resultSet.getString("title"));
                book.setGenre(resultSet.getString("genre"));
                book.setDateCreated(LocalDate.parse(resultSet.getString("date_created")));
                books.add(book);
            }
            resultSet.close();
        } catch (SQLException throwables) {
            throw new DAOException(throwables);
        }
        return books;
    }

    @Override
    public List<Author> getAllAuthors() throws DAOException {
        List<Author> authors = new ArrayList<>();
        Statement statement = connectionDB.getStatement();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM author");
            while (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getInt("id"));
                author.setFirstName(resultSet.getString("first_name"));
                author.setLastName(resultSet.getString("last_name"));
                authors.add(author);
            }
            resultSet.close();
        } catch (SQLException throwables) {
            throw new DAOException(throwables);
        }
        return authors;
    }

    private int getIdAuthorByAuthor(Author author) throws DAOException {
        int idAuthor = -1;
        try {
            PreparedStatement preparedStatement = connectionDB.getConnection()
                    .prepareStatement("SELECT * FROM author WHERE first_name =? AND last_name =?");
            preparedStatement.setString(1, author.getFirstName());
            preparedStatement.setString(2, author.getLastName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                idAuthor = Integer.parseInt(resultSet.getString("id"));
            }
            resultSet.close();
        } catch (SQLException throwables) {
            throw new DAOException(throwables);
        }
        return idAuthor;
    }
}
