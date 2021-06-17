package by.lamaka.library.controller.command.impl;

import by.lamaka.library.bean.Book;
import by.lamaka.library.controller.command.Command;
import by.lamaka.library.service.LibraryService;
import by.lamaka.library.service.ServiceException;
import by.lamaka.library.service.ServiceProvider;

import java.util.List;
import java.util.stream.Collectors;

public class PrintAllBooksCommand implements Command {
    @Override
    public String doAction(String request) {
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        LibraryService libraryService = serviceProvider.getLibraryService();
        String response = "";
        try {
            List<Book> books = libraryService.getAllBooks();
            if (books.isEmpty()) {
                return "Books is empty";
            }
            if(request.equals("по алфавиту (возрастание)")){
                response = books.stream()
                        .sorted((b1,b2)->{return b1.getTitle().compareTo(b2.getTitle());})
                        .map(book -> {return book.toString()+"\n";}).collect(Collectors.joining("\n"));
            }
            else if(request.equals("по алфавиту (убывание)")){
                response = books.stream()
                        .sorted((b1,b2)->{return b2.getTitle().compareTo(b1.getTitle());})
                        .map(book -> {return book.toString()+"\n";}).collect(Collectors.joining("\n"));
            }
            else if(request.equals("по добавлению(сначала новые, потом более старые)")){
                response = books.stream()
                        .sorted((b1,b2)->{return b1.getDateCreated().compareTo(b2.getDateCreated());})
                        .map(book -> {return book.toString()+"\n";}).collect(Collectors.joining("\n"));
            }
        } catch (ServiceException e) {
            return "Error" + e.getMessage();
        }
        return response;
    }
}
