package utils;

import BPlusTree.BPlusException;
import BPlusTree.BPlusTreeConfiguration;

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

    public static Integer parseInt(String s, int startIndex, int len, boolean isData)
        throws utilsException {
        String t = s.substring(startIndex, startIndex+len).trim();
        if (isData && t.isEmpty()) {
            return null;
        }
        if (isInteger(t)) {
            return Integer.parseInt(t);
        }
        throw new utilsException(t + " is not a Int");
    }

    public static Integer parseInt(String s, int startIndex, int len)
            throws utilsException {
        return parseInt(s, startIndex, len, false);
    }

    public static Long parseLong(String s, int startIndex, int len, boolean isData)
            throws utilsException {
        String t = s.substring(startIndex, startIndex+len).trim();
        if (isData && t.isEmpty()) {
            return null;
        }
        if (isInteger(t)) {
            return Long.parseLong(t);
        }
        throw new utilsException(t + " is not a Long");
    }

    public static Long parseLong(String s, int startIndex, int len)
            throws utilsException {
        return parseLong(s, startIndex, len, false);
    }

    public static Float parseFloat(String s, int startIndex, int len, boolean isData)
            throws utilsException {
        String t = s.substring(startIndex, startIndex+len).trim();
        if (isData && t.isEmpty()) {
            return null;
        }
        if (isFloat(t)) {
            return Float.parseFloat(t);
        }
        throw new utilsException(t + " is not a Long");
    }

    public static Float parseFloat(String s, int startIndex, int len)
            throws utilsException {
        return parseFloat(s, startIndex, len, false);
    }

    public static Double parseDouble(String s, int startIndex, int len, boolean isData)
            throws utilsException {
        String t = s.substring(startIndex, startIndex+len).trim();
        if (isData && t.isEmpty()) {
            return null;
        }
        if (isFloat(t)) {
            return Double.parseDouble(t);
        }
        throw new utilsException(t + " is not a Long");
    }

    public static Double parseDouble(String s, int startIndex, int len)
            throws utilsException {
        return parseDouble(s, startIndex, len, false);
    }

    public static int readInt(BufferedInputStream input)
        throws IOException, utilsException {
        byte[] tmp = new byte[Consts.intSize];
        input.read(tmp, 0, Consts.intSize);
        String st = new String(tmp);
        return parseInt(st, 0, st.length());
    }

    public static long readLong(BufferedInputStream input)
            throws IOException, utilsException {
        byte[] tmp = new byte[Consts.longSize];
        input.read(tmp, 0, Consts.longSize);
        String st = new String(tmp).trim();
        return parseLong(st, 0, st.length());
    }

    public static float readFloat(BufferedInputStream input)
            throws IOException, utilsException {
        byte[] tmp = new byte[Consts.floatSize];
        input.read(tmp, 0, Consts.floatSize);
        String st = new String(tmp).trim();
        return parseFloat(st, 0, st.length());
    }

    public static double readDouble(BufferedInputStream input)
            throws IOException, utilsException {
        byte[] tmp = new byte[Consts.doubleSize];
        input.read(tmp, 0, Consts.doubleSize);
        String st = new String(tmp).trim();
        return parseDouble(st, 0, st.length());
    }

    public static String readString(BufferedInputStream input, int length)
            throws IOException {
        byte[] tmp = new byte[length];
        input.read(tmp, 0, length);
        String st = new String(tmp);
//        if (st.startsWith("0")) {
//            return null;
//        }
        return st.trim();
    }

    public static String readString(BufferedInputStream input)
            throws IOException {
        return readString(input, Consts.stringSize);
    }

    public static void writeInt(BufferedOutputStream output, int value)
            throws IOException {
        byte[] tmp = new byte[Consts.intSize];
        Arrays.fill(tmp, (byte)0);
        byte[] data = Integer.toString(value).getBytes();
        System.arraycopy(data, 0, tmp, 0, data.length);
        output.write(tmp, 0, Consts.intSize);
    }

    public static void writeLong(BufferedOutputStream output, long value)
            throws IOException {
        byte[] tmp = new byte[Consts.longSize];
        Arrays.fill(tmp, (byte)0);
        byte[] data = Long.toString(value).getBytes();
        System.arraycopy(data, 0, tmp, 0, data.length);
        output.write(tmp, 0, Consts.longSize);
    }

    public static void writeFloat(BufferedOutputStream output, float value)
            throws IOException {
        byte[] tmp = new byte[Consts.floatSize];
        Arrays.fill(tmp, (byte)0);
        byte[] data = Float.toString(value).getBytes();
        System.arraycopy(data, 0, tmp, 0, data.length);
        output.write(tmp, 0, Consts.floatSize);
    }

    public static void writeDouble(BufferedOutputStream output, double value)
            throws IOException {
        byte[] tmp = new byte[Consts.doubleSize];
        Arrays.fill(tmp, (byte)0);
        byte[] data = Double.toString(value).getBytes();
        System.arraycopy(data, 0, tmp, 0, data.length);
        output.write(tmp, 0, Consts.doubleSize);
    }

    public static void writeString(BufferedOutputStream output, String value, int length)
            throws IOException {
        byte[] tmp = new byte[length];
        Arrays.fill(tmp, (byte)0);
        System.arraycopy(value.getBytes(), 0, tmp, 0, value.getBytes().length);
        output.write(tmp, 0 , length);
    }

    public static void writeString(BufferedOutputStream output, String value)
            throws IOException {
        writeString(output, value, Consts.stringSize);
    }

    public static int fromBytes(List list, String s, int pos, String type, boolean isData)
        throws utilsException{
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
            case "String":
                ret = Consts.stringSize;
                String tmp = s.substring(pos, pos + Consts.stringSize).trim();
                if (tmp.startsWith("0"))
                    list.add(tmp.substring(1));
                else
                    list.add(null);
                break;
            case "Float":
                ret = Consts.floatSize;
                list.add(NumberUtils.parseFloat(s, pos, Consts.floatSize, isData));
                break;
            case "Double":
                ret = Consts.doubleSize;
                list.add(NumberUtils.parseDouble(s, pos, Consts.doubleSize, isData));
                break;
        }
        return ret;
    }

    public static int toBytes(byte[] bytes, int pos, Object value, String type)
        throws BPlusException {
        byte[] tmp;
        if (value == null) {
            if (type == "String") {
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
            case "String":
                tmp = ("0"+value.toString()).getBytes();
                if (tmp.length > Consts.stringSize) {
                    throw new BPlusException("String data is too long!");
                }
                break;
            case "Float":
                tmp = Float.toString((float)value).getBytes();
                break;
            case "Double":
                tmp = Double.toString((double)value).getBytes();
                break;
            default:
                throw new BPlusException("Unknown Type " + type);
        }
        System.arraycopy(tmp, 0, bytes, pos, tmp.length);
        return Consts.Type2Size(type);
    }
}
