package gr.aueb.practiceProjects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Project01 {
    public static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        String filePath;
        String fileOutStr;

        int arrLength = 0;
        int[] initialArray;
        int[] row = new int[6];
        int arrValidCount = 0;

        System.out.println("Please, copy and paste the file's path and name");
        filePath = scanner.nextLine();
        scanner.close();
        fileOutStr = filePath.replace(".txt", "-out.txt");



        // Σε μια try catch βρίσκω το length του array μέσα στον φάκελο
        try {
            arrLength = getArraySize(filePath);
        } catch (FileNotFoundException e) {
            System.err.print("The file either does not exist or the path is incorrect." +
                    "Please, restart the app.");
            System.exit(1);
        }

        //set το initialArray.length και εκτυπώνω.
        initialArray = new int[arrLength];
        System.out.println("The file contains " + arrLength + " digits.");

        // αφού έχω θέσει το length, κάνω αντιγραφή των τιμών στο initialArray.
        try {
            initialArray = readFile(filePath, arrLength);
        } catch (FileNotFoundException e) {
            System.err.print("The file either does not exist or the path is incorrect." +
                    "Please, restart the app.");
            System.exit(1);
        }

        System.out.print("Which are the following: ");
        for (int el : initialArray){
            System.out.print(el + " ");
        }
        System.out.println(".");




        // το αρχείο πρέπει να περιέχει περισσότερους από 6 αριθμούς και το πολύ
        //49 αριθμούς με τιμές από 1 έως 49
        // Σε μια άλλη try catch, ελέγχω τις παρακάτω συνθήκες και με χρήση exception
        // δίνω τα πιθανά σφάλματα.
        try {
            arrLengthAndValueValidation(initialArray);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            System.err.println("Please run the application again, providing the correct inputs.");
            System.exit(2);
        }

        // ταξινόμηση
        Arrays.sort(initialArray);
        System.out.print("After sorting is: ");
        for (int el : initialArray) {
            System.out.print(el + " " );
        }
        System.out.println(".");

        // create an ArrayList and pass the values of the int[] arr.
        // more functional .
        ArrayList<Integer> arrayList;
        arrayList = convertArrToList(initialArray);

        // loop to find all the possible arrays and print with PrintStream
        try (PrintStream ps = new PrintStream(fileOutStr)){
            for (int i = 0; i < arrayList.size() - 5; i++) {
                for (int j = i + 1; j < arrayList.size() - 4; j++) {
                    for (int k = j + 1; k < arrayList.size() - 3; k++) {
                        for (int l = k + 1; l < arrayList.size() - 2; l++) {
                            for (int m = l + 1; m < arrayList.size() - 1; m++) {
                                for (int n = m + 1; n < arrayList.size(); n++) {
                                    row[0] = arrayList.get(i);
                                    row[1] = arrayList.get(j);
                                    row[2] = arrayList.get(k);
                                    row[3] = arrayList.get(l);
                                    row[4] = arrayList.get(m);
                                    row[5] = arrayList.get(n);



                                    if (
    //                                        true
                                            noMoreThan4Evens(row)
                                                    &&
                                            noMoreThan4Odds(row)
                                                    &&
                                            noMoreThan2Contiguous(row)
                                                    &&
                                            noMoreThan3SameEndings(row)
                                                    &&
                                            noMoreThan3SameTen(row)
                                        )
                                    {
                                        arrValidCount++;
                                        ps.printf("%d %d %d %d %d %d.\n", arrayList.get(i), arrayList.get(j),
                                                arrayList.get(k), arrayList.get(l), arrayList.get(m), arrayList.get(n));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            ps.println("The total valid arrays are: " + arrValidCount);
        } catch (FileNotFoundException e) {
            System.out.println("There may be a problem with PrintStream");
        }

    }




    public static int getArraySize (String filePath) throws FileNotFoundException {
        File fd = new File(filePath);
        int counter = 0;


        try (Scanner sc = new Scanner(fd)) {
            while (sc.hasNextInt()){
                sc.nextInt();
//                System.out.println("Reading integer: " + sc.nextInt()); // Debugging
                counter++;
            }

        } catch (FileNotFoundException e) {
            e.getStackTrace();
            throw e;
        }

        return counter;
    }


    public static int[] readFile (String filePath, int arrLength) throws FileNotFoundException {
        File fd = new File(filePath);
        int[] initialArray = new int[arrLength];
        int index = 0;


        try (Scanner sc = new Scanner(fd)) {
            while (sc.hasNextInt()){
            initialArray[index++] = sc.nextInt();
            }

        } catch (FileNotFoundException e) {
            e.getStackTrace();
            throw e;
        }

        return initialArray;
    }



    public static boolean arrLengthAndValueValidation(int[] arr) throws RuntimeException {
        if (arr.length < 6 || arr.length > 49) {
            throw new RuntimeException("Error. The file contains " + arr.length
                    + " digits but must not contain less than 6 or more than 49 numbers.");
        } else {
            for (int el : arr) {
                if (el < 1) {
                    throw new RuntimeException("Error. The value " + el + " is lower than 1.");
                }
                if (el > 49) {
                    throw new RuntimeException("Error. The value " + el + " is higher than 49.");
                }
            }
        }
        return true;
    }

    public static ArrayList<Integer> convertArrToList(int[] arr) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int el : arr){
            arrayList.add(el);
        }
        return arrayList;
    }

    public static boolean noMoreThan4Evens (int[] arr) {
        final int THRESHOLD = 4;
        int counter = 0;

        for (int el : arr) {
            if (el % 2 == 0) {
                counter++;
            }
        }
//        System.out.println("Evens " + counter);
        return counter <= THRESHOLD;
    }

    public static boolean noMoreThan4Odds (int[] arr) {
        final int THRESHOLD = 4;
        int counter = 0;

        for (int el : arr) {
            if (el % 2 != 0) {
                counter++;
            }
        }
//        System.out.println("Odds: " + counter);
        return counter <= THRESHOLD;
    }

    public static boolean noMoreThan2Contiguous(int[] arr){
        final int THRESHOLD = 2;
        int counter = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            if ((arr[i+1] - 1) == arr[i]) {
                counter++;
            }
        }
//        System.out.println("Contiguous: " + counter);
        return counter <= THRESHOLD;
    }

    public static boolean noMoreThan3SameEndings(int[] arr) {
        final int THRESHOLD = 3;
        int[] countEndings = new int[10];

        for (int el : arr) {
            int lastDigit = el % 10;
            countEndings[lastDigit]++;

            if (countEndings[lastDigit] > THRESHOLD) {
                return false;
            }
        }
        return true;
    }

    private static boolean noMoreThan3SameTen(int[] arr) {
        final int THRESHOLD = 3;
        int[] countEndings = new int[5];

        for (int el : arr) {
            int firstDigit = el / 10;
            countEndings[firstDigit]++;

            if (countEndings[firstDigit] > THRESHOLD) {
                return false;
            }
        }
        return true;
    }
}