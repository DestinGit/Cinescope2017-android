package utilities.classes;

/**
 * Created by formation on 03/11/2017.
 */
public class myGlobaleClass {
    private static Boolean statusConnection = false;

    public static void setStatusConnection(Boolean statusConnection) {
        myGlobaleClass.statusConnection = statusConnection;
    }

    public static Boolean getStatusConnection() {
        return statusConnection;
    }
}
