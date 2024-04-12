package accounts;

public class UserManager {

    private static int loggedInUserId;

    public static int getLoggedInUserId() {
        return loggedInUserId;
    }

    public static void setLoggedInUserId(int accountId) {
        loggedInUserId = accountId;
    }

    public static void logout() {
        loggedInUserId = -1;
    }
}
