package util;

import java.util.Scanner;

/**
 * @author Negin Mousavi
 */
public class CreateScanner {
    private static Scanner scanner;

    private CreateScanner() {
    }

    public synchronized static Scanner getInstance() {
        if (scanner == null)
            scanner = new Scanner(System.in);
        return scanner;
    }
}
