package by.lamaka.library.logic.impl;

import by.lamaka.library.bean.Menu;
import by.lamaka.library.controller.command.CommandProvider;
import by.lamaka.library.dao.connection.ConnectionDB;
import by.lamaka.library.logic.LibraryLogic;
import by.lamaka.library.logic.UserLogic;

import java.util.Scanner;

public class LibraryLogicImpl implements LibraryLogic {
    @Override
    public void startApplication() {
        Scanner scanner = new Scanner(System.in);
        CommandProvider commandProvider = new CommandProvider();
        UserLogic userInput = new UserLogicImpl();
        boolean isEnd = true;
        int number = 0;

        while (isEnd) {
            System.out.println("Введите номер операции..");


            System.out.println(Menu.getMenu1());

            number = userInput.getUserInputNumber(7);

            switch (number) {
                case 1:
                    System.out.println("Выберите вариант вывода");
                    System.out.println(Menu.getMenu2());
                    switch (userInput.getUserInputNumber(3)) {
                        case 1:
                            System.out.println(commandProvider.getCommand(3).doAction("по алфавиту (возрастание)"));
                            break;
                        case 2:
                            System.out.println(commandProvider.getCommand(3).doAction("по алфавиту (убывание)"));
                            break;
                        case 3:
                            System.out.println(commandProvider.getCommand(3).doAction("по добавлению(сначала новые, потом более старые)"));
                            break;
                    }
                    break;
                case 2:
                    System.out.println(Menu.getMenu3());
                    System.out.println(commandProvider.getCommand(1).doAction(userInput.getUserString()));
                    break;
                case 3:
                    System.out.println(Menu.getMenu4());
                    System.out.println(commandProvider.getCommand(6).doAction(userInput.getUserString()));
                    break;
                case 4:
                    System.out.println(Menu.getMenu5());
                    System.out.println(commandProvider.getCommand(2).doAction(userInput.getUserString()));
                    break;
                case 5:
                    System.out.println(commandProvider.getCommand(4).doAction(""));
                    break;
                case 6:
                    System.out.println(Menu.getMenu6());
                    System.out.println(commandProvider.getCommand(5).doAction(userInput.getUserString()));
                    break;
                case 7:
                    System.out.println("Программа завершена");
                    return;

            }


        }
    }
}
