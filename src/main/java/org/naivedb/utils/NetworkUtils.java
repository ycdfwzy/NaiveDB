package org.naivedb.utils;

public class NetworkUtils {
    
    public static boolean ipValid(String ip_addr) {
        if (ip_addr == null || ip_addr.length() < 7 || ip_addr.length() > 15) return false;

        String[] arr = ip_addr.split(".");
        if (arr.length != 4) return false;

        try{
            for (int i = 0; i < 4; i++) {
                int num = Integer.parseInt(arr[i]);
                if (i == 0) { if (num < 1 || num > 255) return false; }
                else if (num < 0 || num > 255) return false;
            }
        } catch(Exception e) { return false; }

        return true;
    }
    
}
