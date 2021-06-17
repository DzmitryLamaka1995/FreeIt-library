package by.lamaka.library.controller.command.impl;

import by.lamaka.library.bean.Author;
import by.lamaka.library.controller.command.Command;
import by.lamaka.library.service.LibraryService;
import by.lamaka.library.service.ServiceException;
import by.lamaka.library.service.ServiceProvider;

import java.util.List;
import java.util.stream.Collectors;

public class PrintAllAuthorsCommand implements Command {
    @Override
    public String doAction(String request) {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        LibraryService libraryService = serviceProvider.getLibraryService();
        String response = "";
        try {
            List<Author> authors = libraryService.getAllAuthors();
            if (authors.isEmpty()) {
                return "Authors is empty";
            }
            response = authors.stream().map(author -> author.toString()).collect(Collectors.joining("\n"));
        } catch (ServiceException e) {
            return "Error" + e.getMessage();
        }
        return response;
    }
}
