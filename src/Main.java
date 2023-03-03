/*
Проверка парадокса Монти Холла.
Можно выбрать количество попыток через консоль.
В коде можно изменить количество строк в сводке - rowQtty (не меньше 2).
 */

import java.util.Scanner;

public class Main {
    public static String BLACK = "\u001B[30m";
    public static String GREEN = "\u001B[32m";
    public static String BLUE = "\u001B[34m";
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.println("Test for Monty Hall paradox");
        System.out.println("Enter number of attempts:");
        int attemptsQtty = kb.nextInt();
        int rowQtty = 6;
        int[] rows = new int[rowQtty];
        double radical = Math.pow((double) attemptsQtty, 1.0/rowQtty);
        for (int i = 0; i < rowQtty - 1; i++) {
            rows[i] = (int) Math.pow(radical, i + 1);
        }
        rows[rowQtty-1] = attemptsQtty-1;
//счетчик выигрышей с первой попытки
        int firstChoiceWin = 0;
//счетчик выигрышей со второй  попытки
        int newChoiceWin = 0;
        int k = 0;
        System.out.println();
        System.out.println(
                "Attempt     Happy     First      New           Total won              Total won");
        System.out.println(
                "number      door      choice    choice     by the first choice     by the new choice");
        System.out.println(
                "------------------------------------------------------------------------------------");
        System.out.print("\u001B[36m");
        for (int i = 0; i < attemptsQtty; i++) {
            int happyDoor = (int) (Math.random() * 3);
            int firstChoice = (int) (Math.random() * 3);
            int hostDoor = 0;
//ведущий открывает одну из оставшихся дверей
//если игрок выбрал счастливую дверь - то случайным образом одну из оставшихся
            if (firstChoice == happyDoor) {
                int firstOrSecondOfTheRemaining = (int) (Math.random() * 2);
                switch (firstChoice) {
                    case 0 -> hostDoor = firstOrSecondOfTheRemaining == 0 ? 1 : 2;
                    case 1 -> hostDoor = firstOrSecondOfTheRemaining == 0 ? 0 : 2;
                    case 2 -> hostDoor = firstOrSecondOfTheRemaining == 0 ? 0 : 1;
                }
            }
// если нет - то вторую несчастливую
            else {
                hostDoor = 3 - firstChoice - happyDoor;
            }
            int newChoice = 3 - hostDoor - firstChoice;
            if (happyDoor == firstChoice) firstChoiceWin++;
            if (happyDoor == newChoice) newChoiceWin++;
//печатаем сводку - первые пять попыток и еще rowQtty по экспоненте
            if (i < 5 | i == rows[k]) {
                if (i == 0) System.out.print(GREEN);
                if (i > 5) System.out.print(BLUE);
                if (i == attemptsQtty-1) System.out.print(BLACK);
                System.out.printf(
                        "%7d       %d         %d         %d      %8d   %5.2f%%      %8d   %5.2f%%\n",
                        i+1, happyDoor+1, firstChoice+1, newChoice+1,
                        firstChoiceWin, (double)firstChoiceWin/(i+1)*100,
                        newChoiceWin, (double)newChoiceWin/(i+1)*100);
            }
            if (i == rows[k]) k++;
        }
        System.out.print("\u001B[30m");
        System.out.println(
                "------------------------------------------------------------------------------------");
    }
}
