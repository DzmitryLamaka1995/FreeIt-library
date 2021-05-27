package by.lamaka.library.dao;

import by.lamaka.library.dao.impl.LibraryDAOImpl;

public class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();
    private final LibraryDAO libraryDAO = new LibraryDAOImpl();

    private DAOProvider(){
    }

    public static DAOProvider getInstance() {
        return instance;
    }

    public LibraryDAO getLibraryDAO() {
        return libraryDAO;
    }
}
