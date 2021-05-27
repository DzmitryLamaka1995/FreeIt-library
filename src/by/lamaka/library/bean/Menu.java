package by.lamaka.library.bean;

public class Menu {
    private static final String MENU1 = "1. Вывод всех книг \n2. Добавление книги\n3. Удаление книги" +
            "\n 4.Редактирование книги \n5.Вывод всех авторов \n6.Вывод всех книг по автору \n7. Выход";
    private static final String MENU2 = "1. По алфавиту (возрастание) \n" +
            "2. По алфавиту (убывание) \n" +
            "3. По добавлению(сначала новые, потом более старые) \n";
    private static final String MENU3 = "Введите 4 параметра книги через ',' ." +
            "Пример : название, жанр, имя автора, фамилия автора";
    private static final String MENU4 = "Введите id книги, которую нужно удалить";
    private static final String MENU5 = "Введите 5 параметров книги через ',' ." +
            "Пример : id книги для редактирования , новое название, новый жанр, новое имя автора, новая фамилия автора";
    private static final String MENU6 = "Введите имя автора и фамилию автора через ',' . Пример : имя автора, фамилия автора";


    public static String getMenu1() {
        return MENU1;
    }

    public static String getMenu2() {
        return MENU2;
    }

    public static String getMenu3() {
        return MENU3;
    }

    public static String getMenu4() {
        return MENU4;
    }

    public static String getMenu5() {
        return MENU5;
    }

    public static String getMenu6() {
        return MENU6;
    }
}
