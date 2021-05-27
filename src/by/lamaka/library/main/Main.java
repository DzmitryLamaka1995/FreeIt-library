package by.lamaka.library.main;


import by.lamaka.library.logic.LibraryLogic;
import by.lamaka.library.logic.impl.LibraryLogicImpl;

public class Main {
    public static void main(String[] args) {
        LibraryLogic libraryLogic = new LibraryLogicImpl();
        libraryLogic.startApplication();
    }
}
