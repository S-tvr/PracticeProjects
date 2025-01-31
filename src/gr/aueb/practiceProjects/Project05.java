package gr.aueb.practiceProjects;

import java.util.Scanner;

public class Project05 {
    static boolean[][] hall = new boolean[30][12];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int currentRow;
        char currentColumn;
        String function = "";


        while (true) {
            System.out.println();
            function = readAndValidateFunction(sc);
            currentRow = readAndValidateRow(sc);
            currentColumn = readAndValidateColumn(sc);

            if (function.equals("book"))   book(currentColumn, currentRow);
            if (function.equals("cancel")) cancel(currentColumn, currentRow);
        }
    }

    public static String readAndValidateFunction(Scanner sc ) {
        String stingToValidate = "";
        String function = "";
        System.out.println("If you want to book, write \"book\".\n" +
                "If you want to cancel your booking, write \"cancel\".");

        while (true) {
            stingToValidate = sc.nextLine().trim();
            if (stingToValidate.equals("book") || stingToValidate.equals("cancel")) {
                function = stingToValidate;
                return function;
            }
            System.out.println("Invalid function input.\n" +
                    "Please, enter \"book\" or \"cancel\"");
        }
    }



    public static int readAndValidateRow (Scanner sc) {
        int currentRow = -1;
        while (true) {
            System.out.println("Enter the number of the row.");
            if (sc.hasNextInt()) {
                currentRow = sc.nextInt();
                sc.nextLine();
                if (currentRow <= 30 && currentRow > 0) {
                    return currentRow;
                }
                System.out.println("The number must be positive and up to 30.");
            } else {
                System.out.println("Invalid Entry.");
                sc.nextLine();
            }
        }
    }

    public static char readAndValidateColumn(Scanner sc) {
        String character;
        while (true) {
            System.out.println("Enter the character of the column.");
            character = sc.nextLine();
            if (character.length() == 1 && ('A' <= character.charAt(0)) && character.charAt(0) <= 'L') {
                return character.charAt(0);
            } else {
                System.out.println("Invalid input.");
                System.out.println("Use capital case and the available columns are from A to L.");
            }
        }
    }

    public static void book(char column, int row) {
//        System.out.println("row:" + (row-1) +"\n" + "col: " + (column-65));
        if (!hall[row-1][column-65]) {
            hall[row-1][column-65] = true;
            System.out.println("The seat is booked. Thank you.");
        } else {
            System.err.println("The seat is already booked. Please, choose another seat.");
        }
    }

    public static void cancel(char column, int row) {
//        System.out.println("row:" + (row-1) +"\n" + "col: " + (column-65));
        if (hall[row-1][column-65]) {
            hall[row-1][column-65] = false;
            System.out.println("The reservation of the seat has been cancelled.");
        } else {
            System.err.println("The seat is not booked so the system can not cancel it.");
        }
    }


}
