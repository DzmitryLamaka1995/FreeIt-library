package by.lamaka.library.controller.command.impl;

import by.lamaka.library.bean.Author;
import by.lamaka.library.bean.Book;
import by.lamaka.library.controller.command.Command;
import by.lamaka.library.service.LibraryService;
import by.lamaka.library.service.ServiceException;
import by.lamaka.library.service.ServiceProvider;

public class AddBookCommand implements Command {
    @Override
    public String doAction(String request) {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        LibraryService libraryService = serviceProvider.getLibraryService();

        String response = "";
        String spliterator = "\\s?[,]\\s?";
        if (request.split(spliterator).length != 4) {
            return "Error";
        }
        Book book = new Book();
        Author author = new Author();

        author.setFirstName(request.split(spliterator)[2]);
        author.setLastName(request.split(spliterator)[3]);
        book.setAuthor(author);
        book.setTitle(request.split(spliterator)[0]);
        book.setGenre(request.split(spliterator)[1]);

        try {
            libraryService.addBook(book);
            response = "Книга была успешно добавлена";
        } catch (ServiceException e) {
            return  "Error " + e.getMessage();
        }
        return response;
    }
}
