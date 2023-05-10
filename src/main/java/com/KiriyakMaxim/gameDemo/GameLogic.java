package main.java.com.KiriyakMaxim.gameDemo;

import java.util.Scanner;

public class GameLogic {
    private final Field field = new Field();
    private static int stepCount = 0; // переменная необходима для проверки кол-во шагов чтобы прервать игру, когда поле заполнено
    private Scanner sc = new Scanner(System.in); // использовал Сканнер для считывания информации с консоли
    private String ch; // переменная для хранения символа
    private int x, y; // переменные для хранения координат

    // метод запускает игру
    // возможно данный метод можно сделать static чтобы не создавать обьект класса, а сразу вызвать метод и играть
    // но в таком случае почти все придется делать static, не знаю насколько это эффективно
    public void startGame() {
        boolean win = false; // локальная переменная для условия в цикле

        // цикл закончится только при чье-то победе либо ничьи
        while(!win) {
            stepCount++; // при каждом заходе в игру, увеличиваем статическую переменную, как только знаение достиграет 9, игра прерывается, обьявляется ничья
            field.showField(); // выводим поле на каждом ходе

            // ранее в цикле были запросы на символ и координаты, но позже решил убрать чтобы очистить цикл от лишнего кода
            enterSymbol(); // запрашивает символ игрока
            enterCoordinates(); // запрашивает координаты ячейки

            /*
            * Возможно методы(setSymbol и isWin) можно обьединить в один, так как они оба используют одинаковые переменные и связаны,
            * но я решил разделить их чтобы не перегружать лишней функциональностью
            * */
            setSymbol(ch, x, y); // изменяется значение ячейки массива на Х или У по переданным координатам

            /* проверяет выйиграл ли кто-то из игроков, переменная ch нужна для условия проверки ячеек,
             * если просто сравнивать ячейки(как было вначале) то всегда будет true при первой же проверке
             * */
            win = isWin(ch, x, y); //
        }

    }
    // метод для получения координат и проверки, для избежания ArrayOutOfBoundException
    private void enterCoordinates() {
        do {
            System.out.println("Please choose coordinates: x/y(from 0 to 2)");
            System.out.print("X: ");
            x = sc.nextInt();

            System.out.print("Y: ");
            y = sc.nextInt();
        } while ((x < 0 || x > 2) || (y < 0 || y > 2));
    }
    // метод для получения символа, к сожалению не смог придумать адекватный способ проверки на строгое получение Х или О
    private void enterSymbol() {
        System.out.println("Please enter X or O: ");
        ch = sc.nextLine();
    }

    // метод для поиска победителя
    private boolean isWin(String ch, int x, int y) {
        boolean win = false;

        /*
         * Самую большую трудность у меня вызвало условия проверки победителя, в итоге все равно вышел монстр.
         * Я не стал смотреть какие-то готовые решения, уже после критики, чтобы ничего не переделывать,
         * и понять где я не сделал поворот не туда)
         *  */
        win = (ch.equals(field.getField()[x][0]) && ch.equals(field.getField()[x][1]) && ch.equals(field.getField()[x][2])) ||
                (ch.equals(field.getField()[0][y]) && ch.equals(field.getField()[1][y]) && ch.equals(field.getField()[2][y])) ||
                (ch.equals(field.getField()[0][0]) && ch.equals(field.getField()[1][1]) && ch.equals(field.getField()[2][2])) ||
                (ch.equals(field.getField()[0][2]) && ch.equals(field.getField()[1][1]) && ch.equals(field.getField()[2][0]));

        if (win) { // выводим поле и поздравление
            field.showField();
            System.out.println("Congratulations to " + ch);
        }

        if (stepCount == 9 && !win) { // проверяет сколько ходов было сделанно, если поле заполнено, победителя нет, то ничья
            field.showField();
            System.out.println("Draw. You could try again.");
            win = true;
        }
        return win;
    }
    // метод изменяет элемент массива плюс проверяет не занята ли ячейка
    // если занята то используя рекурсию, запрашивает новые координаты
    private void setSymbol(String symbol, int x, int y) {
        sc = new Scanner(System.in);

        if (field.getField()[x][y].equals("_")) {
            field.setGameField(symbol,x, y);
        } else {
            System.out.println("The field is taken, please choose another coordinates x/y:");
            setSymbol(symbol, sc.nextInt(), sc.nextInt());
        }
    }
}
