package accounts;
/**
 *
 * @author MARITIME 02
 */
public class UserManager {

    private static String loggedInUser;

    public static String getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(String username) {
        loggedInUser = username;
    }

    public static void logout() {
        loggedInUser = null;
    }
}
