package org.naivedb.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Arrays;
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

    public static boolean isNumber(String s) {
        return isFloat(s);
    }

    public static Integer parseInt(String s, int startIndex, int len, boolean isData)
        throws NDException {
        String t = s.substring(startIndex, startIndex+len).trim();
        if (isData && t.isEmpty()) {
            return null;
        }
        if (isInteger(t)) {
            return Integer.parseInt(t);
        }
        throw new NDException(t + " is not a Int");
    }

    public static Integer parseInt(String s, int startIndex, int len)
            throws NDException {
        return parseInt(s, startIndex, len, false);
    }

    public static Long parseLong(String s, int startIndex, int len, boolean isData)
            throws NDException {
        String t = s.substring(startIndex, startIndex+len).trim();
        if (isData && t.isEmpty()) {
            return null;
        }
        if (isInteger(t)) {
            return Long.parseLong(t);
        }
        throw new NDException(t + " is not a Long");
    }

    public static Long parseLong(String s, int startIndex, int len)
            throws NDException {
        return parseLong(s, startIndex, len, false);
    }

    public static Float parseFloat(String s, int startIndex, int len, boolean isData)
            throws NDException {
        String t = s.substring(startIndex, startIndex+len).trim();
        if (isData && t.isEmpty()) {
            return null;
        }
        if (isFloat(t)) {
            return Float.parseFloat(t);
        }
        throw new NDException(t + " is not a Long");
    }

    public static Float parseFloat(String s, int startIndex, int len)
            throws NDException {
        return parseFloat(s, startIndex, len, false);
    }

    public static Double parseDouble(String s, int startIndex, int len, boolean isData)
            throws NDException {
        String t = s.substring(startIndex, startIndex+len).trim();
        if (isData && t.isEmpty()) {
            return null;
        }
        if (isFloat(t)) {
            return Double.parseDouble(t);
        }
        throw new NDException(t + " is not a Long");
    }

    public static Double parseDouble(String s, int startIndex, int len)
            throws NDException {
        return parseDouble(s, startIndex, len, false);
    }


    public static int fromBytes(List list, String s, int pos, String type, boolean isData)
        throws NDException{
        int ret = 0;
        switch (type) {
            case "Int":
                ret = Consts.intSize;
                list.add(NumberUtils.parseInt(s, pos, Consts.intSize, isData));
                break;
            case "Long":
                ret = Consts.longSize;
                list.add(NumberUtils.parseLong(s, pos, Consts.longSize, isData));
                break;
            case "Float":
                ret = Consts.floatSize;
                list.add(NumberUtils.parseFloat(s, pos, Consts.floatSize, isData));
                break;
            case "Double":
                ret = Consts.doubleSize;
                list.add(NumberUtils.parseDouble(s, pos, Consts.doubleSize, isData));
                break;
            default:
                if (type.startsWith("String")) {
                    ret = Consts.Type2Size(type);
                    String tmp = s.substring(pos, pos + ret).trim();
                    if (tmp.startsWith("0"))
                        list.add(tmp.substring(1));
                    else
                        list.add(null);
                }
        }
        return ret;
    }

    public static int toBytes(byte[] bytes, int pos, Object value, String type)
        throws NDException {
        byte[] tmp;
        if (value == null) {
            if (type.startsWith("String")) {
                tmp = "1".getBytes();
                System.arraycopy(tmp, 0, bytes, pos, tmp.length);
            }
            return Consts.Type2Size(type);
        }
        switch (type) {
            case "Int":
                tmp = Integer.toString((int)value).getBytes();
                break;
            case "Long":
                tmp = Long.toString((long)value).getBytes();
                break;

            case "Float":
                tmp = Float.toString((float)value).getBytes();
                break;
            case "Double":
                tmp = Double.toString((double)value).getBytes();
                break;
            default:
                if (type.startsWith("String")) {
                    tmp = ("0" + value.toString()).getBytes();
                    if (tmp.length > Consts.Type2Size(type)) {
                        throw new NDException("String data is too long!");
                    }
                } else {
                    throw new NDException("Unknown Type " + type);
                }
        }
        System.arraycopy(tmp, 0, bytes, pos, tmp.length);
        return Consts.Type2Size(type);
    }
}
