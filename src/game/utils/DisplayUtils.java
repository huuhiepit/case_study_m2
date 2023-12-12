package game.utils;

public class DisplayUtils {
    public static void drawDashSquare(int size) {
        String str = "";
        for (int i = 0; i < size; i++) {
            str += "- ";
        }
        System.out.println(str);
    }
}
