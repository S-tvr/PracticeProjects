package gr.aueb.practiceProjects;

import java.util.Scanner;

public class Project04 {

    public static void main(String[] args) {
        //initiate variables.
        String welcomeMessage = "Welcome!!!\n" +
                "You have just entered the tic-tac-toe championship.\n" +
                "A new game is about to start.\n" +
                "Player one has symbol 'X' and plays first.\n" +
                "Player two follows with the symbol 'O'.\n" +
                "You first enter the number of the row and then the column.\n" +
                "If you want to choose the first line second column you enter 1 and 2 respectively\n" +
                "If you got bored and want to crash the program and enter an infinite loop, enter a value bigger than '2,147,483,647'\n" +
                "You already know the rules.\n" +
                "The new game starts now!!!";
        char[][] deck = new char[3][3];
        int player = 0;
        int counter = 0;
        Scanner sc = new Scanner(System.in);

        System.out.println("\n" + welcomeMessage + "\n");


        do {
            if (player != 1) {
                player = 1;
            } else {
                player = 2;
            }
            counter++;

            if (player == 1) System.out.println("It's first player's turn.");
            if (player == 2) System.out.println("It's second player's turn.");

            System.out.println("Please, select the row and the column.");

            try {
                readAndWrite(deck, sc, player);
            } catch (Exception e) {
                if (player != 1) {
                    player = 1;
                } else {
                    player = 2;
                }
                counter--;
                System.err.println("Invalid entry. Please, try again.");
                System.out.println();
                print(deck);
                continue;
            }
            print(deck);
            System.out.println();

            if (counter == 9 && !weHaveAWinner(deck) ) {
                System.out.println("Seems like we have a tie!");
                System.exit(1);
            }
        } while (!weHaveAWinner(deck));



        if (weHaveAWinner(deck) && player == 1) {
            System.out.println("The game has finished. Player one won the game");
        }
        if (weHaveAWinner(deck) && player == 2) {
            System.out.println("The game has finished. Player two won the game");
        }

        // Or simply:
//        System.out.printf("The game has finished. Player %d won the game", player);



    }

    public static void readAndWrite(char[][] deck, Scanner sc, int player) {
        int row = sc.nextInt() - 1;
        int col = sc.nextInt() - 1;

        try {
            if ((row > 2 || col > 2) || (deck[row][col] != '\u0000')){
                throw new Exception("Invalid entry.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if(player == 1) {
            deck[row][col] = 'X';
        }

        if(player == 2) {
            deck[row][col] = 'O';
        }
    }


    public static boolean weHaveAWinner(char[][] deck) {
        boolean weHaveAWinner = false;
        //check horizontally
        for (int row = 0; row <= 2 ; row++) {
            if (deck[row][0] != '\u0000' && deck[row][0] == deck[row][1] && deck[row][1] == deck[row][2]) {
                weHaveAWinner = true;
                break;
            }

        }
        //check vertically
        for (int col = 0; col <= 2; col++) {
            if (deck[0][col] != '\u0000' && deck[0][col] == deck[1][col] && deck[1][col] == deck[2][col]) {
                weHaveAWinner = true;
                break;
            }

        }
        //check diagonally
        if ((deck[0][0] != '\u0000' &&
                (deck[0][0] == deck[1][1]  && deck[1][1] == deck[2][2])) || (
                deck[2][0] != '\u0000' &&
                        (deck[2][0] == deck[1][1]  && deck[1][1] == deck[0][2])))
        {
            weHaveAWinner = true;
        }



        return weHaveAWinner;
    }

    public static void print(char[][] deck) {
        for(char[] row : deck) {
            for(char el : row) {
                System.out.print(el + "\t");
            }
            System.out.println();
        }

    }
}
