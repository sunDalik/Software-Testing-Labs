import java.io.InputStream;
import java.util.Scanner;

public class Credentials {
    static String login;
    static String password;

    static {
        InputStream inputStream = Credentials.class.getClassLoader().getResourceAsStream("credentials");
        Scanner s = new Scanner(inputStream).useDelimiter("\n");
        login = s.next();
        password = s.next();
    }
}
