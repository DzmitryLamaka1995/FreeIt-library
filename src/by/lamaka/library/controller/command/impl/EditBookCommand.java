package by.lamaka.library.controller.command.impl;

import by.lamaka.library.bean.Author;
import by.lamaka.library.bean.Book;
import by.lamaka.library.controller.command.Command;
import by.lamaka.library.service.LibraryService;
import by.lamaka.library.service.ServiceException;
import by.lamaka.library.service.ServiceProvider;

public class EditBookCommand implements Command {
    @Override
    public String doAction(String request) {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        LibraryService libraryService = serviceProvider.getLibraryService();

        String response = "";
        String spliterator = "\\s?[,]\\s?";
        if (request.split(spliterator).length != 5) {
            return "Error";
        }
        Book book = new Book();
        Author author = new Author();

        author.setFirstName(request.split(spliterator)[3]);
        author.setLastName(request.split(spliterator)[4]);
        book.setAuthor(author);
        book.setTitle(request.split(spliterator)[1]);
        book.setGenre(request.split(spliterator)[2]);
        int idBookForEdit = Integer.parseInt(request.split(spliterator)[0]);

        try {
            libraryService.editBook(idBookForEdit, book);
            response = "Книга была успешно изменена";
        } catch (ServiceException e) {
            return  "Error " + e.getMessage();
        }
        return response;
    }
}
