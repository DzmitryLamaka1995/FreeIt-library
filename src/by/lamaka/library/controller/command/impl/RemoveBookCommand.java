package by.lamaka.library.controller.command.impl;

import by.lamaka.library.controller.command.Command;
import by.lamaka.library.service.LibraryService;
import by.lamaka.library.service.ServiceException;
import by.lamaka.library.service.ServiceProvider;

public class RemoveBookCommand implements Command {
    @Override
    public String doAction(String request) {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        LibraryService libraryService = serviceProvider.getLibraryService();
        String response = "";
        int id = Integer.parseInt(request);
        try {
            libraryService.removeBookById(id);
            response = "Книга с id " + id + " была успешно удалена";
        } catch (ServiceException e) {
            return "Error " + e.getMessage();
        }
        return response;
    }
}
