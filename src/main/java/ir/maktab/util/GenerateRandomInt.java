package ir.maktab.util;

import java.util.Random;

/**
 * @author Negin Mousavi
 */
public class GenerateRandomInt {
    public static int generateNumber() {
        Random random = new Random();
        return random.nextInt(1000) + 1;
    }
}
