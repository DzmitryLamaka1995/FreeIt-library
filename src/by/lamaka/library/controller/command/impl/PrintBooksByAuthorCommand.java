package by.lamaka.library.controller.command.impl;

import by.lamaka.library.bean.Author;
import by.lamaka.library.bean.Book;
import by.lamaka.library.controller.command.Command;
import by.lamaka.library.service.LibraryService;
import by.lamaka.library.service.ServiceException;
import by.lamaka.library.service.ServiceProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PrintBooksByAuthorCommand implements Command {
    @Override
    public String doAction(String request) {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        LibraryService libraryService = serviceProvider.getLibraryService();
        String response = "";
        String spliterator = "\\s?[,]\\s?";
        if (request.split(spliterator).length != 2) {
            return "Error";
        }
        Author author = new Author();
        author.setFirstName(request.split(spliterator)[0]);
        author.setLastName(request.split(spliterator)[1]);

        try {
            List<Book> books = new ArrayList<>();
            books = libraryService.getBooksByAuthor(author);
            if (books.isEmpty()) {
                return "Books is empty";
            } else {
                response = books.stream().map(book -> {
                    return book.toString() + "\n";
                }).collect(Collectors.joining("\n"));
            }

        } catch (ServiceException e) {
            return "Error " + e.getMessage();
        }
        return response;
    }
}
