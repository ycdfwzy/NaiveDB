package utils;

import BPlusTree.BPlusException;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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

    public static int parseInt(String s, int startIndex, int len)
        throws utilsException {
        String t = s.substring(startIndex, startIndex+len).trim();
        if (isInteger(t)) {
            return Integer.parseInt(t);
        }
        throw new utilsException(t + " is not a Int");
    }

    public static long parseLong(String s, int startIndex, int len)
            throws utilsException {
        String t = s.substring(startIndex, startIndex+len).trim();
        if (isInteger(t)) {
            return Long.parseLong(t);
        }
        throw new utilsException(t + " is not a Long");
    }

    public static float parseFloat(String s, int startIndex, int len)
            throws utilsException {
        String t = s.substring(startIndex, startIndex+len).trim();
        if (isFloat(t)) {
            return Float.parseFloat(t);
        }
        throw new utilsException(t + " is not a Long");
    }

    public static Double parseDouble(String s, int startIndex, int len)
            throws utilsException {
        String t = s.substring(startIndex, startIndex+len).trim();
        if (isFloat(t)) {
            return Double.parseDouble(t);
        }
        throw new utilsException(t + " is not a Long");
    }

    public static int fromBytes(List list, String s, int pos, String type)
        throws utilsException{
        int ret = 0;
        switch (type) {
            case "Int":
                ret = Consts.intSize;
                list.add(NumberUtils.parseInt(s, pos, Consts.intSize));
                break;
            case "Long":
                ret = Consts.longSize;
                list.add(NumberUtils.parseLong(s, pos, Consts.longSize));
                break;
            case "String":
                ret = Consts.stringSize;
                list.add(s.substring(pos, pos + Consts.stringSize).trim());
                break;
            case "Float":
                ret = Consts.floatSize;
                list.add(NumberUtils.parseFloat(s, pos, Consts.floatSize));
                break;
            case "Double":
                ret = Consts.doubleSize;
                list.add(NumberUtils.parseDouble(s, pos, Consts.doubleSize));
                break;
        }
        return ret;
    }

    public static int toBytes(byte[] bytes, int pos, Object value, String type)
        throws BPlusException {
        int ret = 0;
        byte[] tmp;
        switch (type) {
            case "Int":
                ret = Consts.intSize;
                tmp = Integer.toString((int)value).getBytes();
                break;
            case "Long":
                ret = Consts.longSize;
                tmp = Long.toString((long)value).getBytes();
                break;
            case "String":
                ret = Consts.stringSize;
                tmp = value.toString().getBytes();
                if (tmp.length > Consts.stringSize) {
                    throw new BPlusException("String data is too long!");
                }
                break;
            case "Float":
                ret = Consts.floatSize;
                tmp = Float.toString((float)value).getBytes();
                break;
            case "Double":
                ret = Consts.doubleSize;
                tmp = Double.toString((double)value).getBytes();
                break;
            default:
                throw new BPlusException("Unknown Type " + type);
        }
        System.arraycopy(tmp, 0, bytes, pos, tmp.length);
        return ret;
    }
}
