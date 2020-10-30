package utils;

import java.io.InputStream;
import java.util.Scanner;

public class Credentials {
    public static String login;
    public static String password;

    static {
        InputStream inputStream = Credentials.class.getClassLoader().getResourceAsStream("credentials");
        Scanner s = new Scanner(inputStream).useDelimiter("\n");
        login = s.next();
        password = s.next();
    }
}
