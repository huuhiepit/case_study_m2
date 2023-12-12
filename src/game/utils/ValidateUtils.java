package game.utils;

import java.util.regex.Pattern;

public class ValidateUtils {
    public static final String CONTENT_REGEX = "^[a-zA-Z].{3,}[\\s\\S]*(?:[?:\\s?:].*|[?:\\s?:])$";
    public static final String ANSWER_REGEX = "^[a-zA-Z]?.*$";
    public static final String ANSWER_PLAYER_REGEX = "^[ABCD]$";
    public static boolean isStringValid(String name, String regex) {
        return Pattern.matches(regex, name);
    }
}
