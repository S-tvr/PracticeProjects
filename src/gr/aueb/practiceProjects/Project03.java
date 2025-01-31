package gr.aueb.practiceProjects;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Project03 {

    public static void main(String[] args) {
        Object[][] grid = new Object[128][2];  // First column for char, second column for frequency (Integer)

        Scanner readFileName = new Scanner(System.in);
        String fileName;
        String fileOutStr;
        File fd;

        System.out.println("Please, provide a file.");
        fileName = readFileName.nextLine();
        fd = new File(fileName);
        readFileName.close();

        fileOutStr = fileName.replace(".txt", "-out.txt");

        try (FileReader fr = new FileReader(fileName)) {
            int chToInt;
            char currentChar;
            int uniqueCharCount = 0;

            while ((chToInt = fr.read()) != -1) { // End of file
                currentChar = (char) chToInt;

                if (Character.isWhitespace(currentChar)) continue; // if whitespace, ignore.

                // Check if the character is already in the grid and if so, get the index.
                int index = getIndex(grid, uniqueCharCount, currentChar);
                if (index == -1) {
                    // currentChar has not been found before. Add it.
                    grid[uniqueCharCount][0] = currentChar;
                    grid[uniqueCharCount][1] = 1;  // Initialize frequency as 1
                    uniqueCharCount++;
                } else {
                    // Increment the frequency of the existing character
                    grid[index][1] = (int) grid[index][1] + 1;
                }
            }

        } catch (IOException e) {
            System.err.println("Error...File was not found or denied access.");
            System.err.println("Please provide a file that exists.");
            System.exit(1);
        }

        // First, sort by character (ascending order)
        arraySortByChar(grid);

        // Then, sort by frequency (descending order)
        arraySortByFrequency(grid);

        // Write the sorted grid to the output file
        writeGridToFile(grid, fileOutStr);
    }

    /**
     * Sorts the grid by the first column (characters) in ascending order.
     * @param grid The grid to sort.
     */
    public static void arraySortByChar(Object[][] grid) {
        Arrays.sort(grid, new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
                if (o1[0] == null || o2[0] == null) return 0;  // Skip null values
                return ((Character) o1[0]).compareTo((Character) o2[0]);  // Compare by character
            }
        });
    }

    /**
     * Sorts the grid by the second column (frequency) in descending order.
     * @param grid The grid to sort.
     */
    public static void arraySortByFrequency(Object[][] grid) {
        Arrays.sort(grid, new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
                if (o1[1] == null || o2[1] == null) return 0;  // Skip null values
                return ((Integer) o2[1]).compareTo((Integer) o1[1]);  // Compare by frequency (descending)
            }
        });
    }

    /**
     * Writes the sorted grid to a file using FileWriter.
     * @param grid The grid to write.
     * @param fileOutStr The file path to write the grid into.
     */
    public static void writeGridToFile(Object[][] grid, String fileOutStr) {
        try (FileWriter writer = new FileWriter(fileOutStr)) {
            // Writing header to the file
            writer.write("Character\tFrequency\n");
            for (int rows = 0; rows < grid.length; rows++) {
                if (grid[rows][0] == null) break;  // Stop if we encounter an empty row (null character)
                // Writing each row to the file: character and frequency
                writer.write(grid[rows][0] + "\t\t" + grid[rows][1] + "\n");
            }
            System.out.println("Grid has been written to the file: " + fileOutStr);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Checks if the current char is in the grid by creating a loop and traversing
     * in the first column of all the rows created up to now. It compares with null values.
     * @param grid The corresponding grid.
     * @param uniqueCharCount (int) How many unique chars the grid has. It also
     *                        sets the number of iterations.
     * @param currentChar (char) The char we are comparing.
     * @return the index value if the char is found before and -1 if not found.
     */
    public static int getIndex(Object[][] grid, int uniqueCharCount, char currentChar) {
        for (int i = 0; i < uniqueCharCount; i++) {
            if (grid[i][0] != null && (char) grid[i][0] == currentChar) {
                return i;  // Return the index if the character is found
            }
        }
        return -1;  // Return -1 if the character is not found
    }
}
