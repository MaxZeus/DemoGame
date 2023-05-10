package main.java.com.KiriyakMaxim.gameDemo;

import java.util.Arrays;

class Field {
    private final String[][] gameField = new String[3][3]; // двойной массив для хранения игрового поля

    Field() {
        fillField(); // при инициализации обьекта Field конструктор вызывает метод для создания и заполнения игрового поля
    }
    // метод выводит игровое поле в консоль
    public void showField() {
        System.out.println("   0  1  2"); // выводим координаты по Оси У

        for (int i = 0; i < 3; i++) {
            System.out.print((i) + " "); // выводим координаты по Оси Х

            for (int j = 0; j < 3; j++) {
                System.out.print("[" + gameField[i][j] + "]"); // выводим ячейки игрового поля
            }
            System.out.println();
        }
    }
    // метод необходим для заполнения поля(использовал Arrays.fill() потому интелидж предложил, мне понравилось, можно заменить на вложенный цикл)
    private void fillField() {
        for (int i = 0; i < 3; i++) {
            Arrays.fill(gameField[i], "_");
        }

    }
    // метод возвращает ссылку на массив, который хранит игровое поле
    public String[][] getField() {
        return gameField;
    }
    // метод изменяет содержимое ячейки
    public void setGameField(String symbol, int x, int y) {
        gameField[x][y] = symbol;
    }
}
