package org.naivedb.utils;

import java.io.*;

public class FileUtils {

    // this function is extremely dangerous, be careful
    public static void deleteAll(File file) throws IOException{
        if (!file.exists()) return;
        if (file.isFile() || file.list().length == 0) file.delete();
        else {
            for (File f: file.listFiles()) {
                deleteAll(f);
            }
            file.delete();
        }
    }

    public static String readFile(File file) throws IOException, NDException {
        if (!file.exists() || file.isDirectory())
            throw new NDException("file \"" + file + "\" can't be read");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        String in = "";
        while ((line = reader.readLine()) != null)
            in += line + " ";
        reader.close();
        return in;
    }

    public static String readFileMultiLine(File file) throws IOException, NDException {
        if (!file.exists() || file.isDirectory())
            throw new NDException("file \"" + file + "\" can't be read");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        String in = "";
        while ((line = reader.readLine()) != null)
            in += line + "\n";
        reader.close();
        return in;
    }

    public static void writeToFile(String str, File file) throws IOException, NDException {
        if (file.isDirectory())
            throw new NDException("file \"" + file + "\" can't be read");
        if (!file.exists()) file.createNewFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(str);
        writer.close();
    }
}