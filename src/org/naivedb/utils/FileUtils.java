package org.naivedb.utils;

import java.io.*;

public class FileUtils {

    // this function is extremely dangerous, be careful
    public static void deleteAll(File file) throws IOException{
        if (file.isFile() || file.list().length == 0) file.delete();
        else {
            for (File f: file.listFiles()) {
                deleteAll(f);
            }
            file.delete();
        }
    }
}