package utils;

public class NumberUtils {
    public static boolean isPureInteger(String s) {
        if (s.isEmpty())
            return false;
        for (int i = 0; i < s.length(); ++i)
            if (!Character.isDigit(s.charAt(i))){
                return false;
            }
        return true;
    }

    public static boolean isPositiveInteger(String s) {
        return (s.startsWith("+") && isPureInteger(s.substring(1))) || isPureInteger(s);
    }

    public static boolean isNegativeInteger(String s) {
        return s.startsWith("-") && isPureInteger(s.substring(1));
    }

    public static boolean isInteger(String s) {
        return isPositiveInteger(s) || isNegativeInteger(s);
    }

    public static boolean isFloat(String s) {
        int idx = s.indexOf('.');
        if (idx == -1)
            return isInteger(s);
        return (isInteger(s.substring(0, idx)) && isPureInteger(s.substring(idx+1)));
    }

}
