package game.utils;

import java.util.Scanner;

public class InputUtils {
    public static Scanner scanner = new Scanner(System.in);
    public static String inputString(String title, String regex, String errMessage) {
        String input;
        boolean isValid;
        do {
            System.out.print(title);
            input = scanner.nextLine().trim(); // Lấy chuỗi và loại bỏ khoảng trắng ở đầu và cuối
            isValid = !input.isEmpty() && ValidateUtils.isStringValid(input, regex);

            if (input.isEmpty()) {
                System.out.println("Không được để trống.");
            } else if (!isValid) {
                System.out.println(errMessage);
            }
        } while (!isValid);

        return input;
    }
}
