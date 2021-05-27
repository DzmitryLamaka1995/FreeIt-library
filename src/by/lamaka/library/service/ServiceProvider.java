package by.lamaka.library.service;

import by.lamaka.library.service.impl.LibraryServiceImpl;

public class ServiceProvider {
    private final static ServiceProvider instance = new ServiceProvider();
    private final LibraryService libraryService = new LibraryServiceImpl();

    private ServiceProvider(){}

    public static ServiceProvider getInstance() {
        return instance;
    }

    public LibraryService getLibraryService() {
        return libraryService;
    }
}
