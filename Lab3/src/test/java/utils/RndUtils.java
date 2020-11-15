package utils;

import java.util.Random;

public class RndUtils {
    public static String getRandomLetterSequence(int length, String letters) {
        Random random = new Random();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(letters.charAt(random.nextInt(letters.length())));
        }
        return sb.toString();
    }

    public static String getRandomAlphabetSequence(int length) {
        return getRandomLetterSequence(length, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
    }

    public static String getRandomAlphaNumericSequence(int length) {
        return getRandomLetterSequence(length, "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
    }

    public static String getRandomEmail(int length) {
        return getRandomAlphaNumericSequence(length) + "@" + getRandomAlphabetSequence(4) + "." + getRandomAlphabetSequence(3);
    }

    public static int randomInt(int min, int max) {
        return min + new Random().nextInt(max - min + 1);
    }
}