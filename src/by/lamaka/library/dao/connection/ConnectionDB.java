package by.lamaka.library.dao.connection;

import java.sql.SQLException;


public interface ConnectionDB {
    void closeConnection() throws SQLException;
    void closeStatement() throws SQLException;
}
