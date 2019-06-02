package org.naivedb.utils;
import java.io.*;

public class ServerResult implements java.io.Serializable {
    public boolean succ;
    public String data;
    public String err_msg;
}