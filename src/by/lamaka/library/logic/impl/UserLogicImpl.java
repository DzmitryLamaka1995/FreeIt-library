package by.lamaka.library.logic.impl;

import by.lamaka.library.logic.UserLogic;

import java.util.Scanner;

public class UserLogicImpl implements UserLogic {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public int getUserInputNumber(int maxValue) {
        int number = 0;
        do {
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Вы ввели не цифру");
            }
            number = scanner.nextInt();
            if (number > maxValue || number < 0) {
                System.out.println("Такой цифры не существует");
            }
        }
        while (number > maxValue || number < 0);
        return number;
    }

    @Override
    public String getUserString() {
        String result = "";
        do {
            result = scanner.nextLine();
        }
        while ("".equals(result) || result.equals(null));
        return result;
    }
}
