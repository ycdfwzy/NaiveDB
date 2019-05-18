package utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.util.Arrays;
import java.io.IOException;

public class StreamUtils {

    public static int readInt(BufferedInputStream input)
        throws IOException, utilsException {
        byte[] tmp = new byte[Consts.intSize];
        input.read(tmp, 0, Consts.intSize);
        String st = new String(tmp);
        return NumberUtils.parseInt(st, 0, st.length());
    }

    public static long readLong(BufferedInputStream input)
            throws IOException, utilsException {
        byte[] tmp = new byte[Consts.longSize];
        input.read(tmp, 0, Consts.longSize);
        String st = new String(tmp).trim();
        return NumberUtils.parseLong(st, 0, st.length());
    }

    public static float readFloat(BufferedInputStream input)
            throws IOException, utilsException {
        byte[] tmp = new byte[Consts.floatSize];
        input.read(tmp, 0, Consts.floatSize);
        String st = new String(tmp).trim();
        return NumberUtils.parseFloat(st, 0, st.length());
    }

    public static double readDouble(BufferedInputStream input)
            throws IOException, utilsException {
        byte[] tmp = new byte[Consts.doubleSize];
        input.read(tmp, 0, Consts.doubleSize);
        String st = new String(tmp).trim();
        return NumberUtils.parseDouble(st, 0, st.length());
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
}