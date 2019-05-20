package org.naivedb.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

class CustomFormatter extends Formatter {
    // DateFormat
    private static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss ");

    public String format(LogRecord record) {
        StringBuilder builder = new StringBuilder(1000);
        builder.append("[").append(record.getLevel()).append("] ");
        builder.append(df.format(new Date(record.getMillis()))).append(" ");
        builder.append("(").append(record.getLoggerName()).append(": ");
        builder.append(record.getSourceClassName()).append(".");
        builder.append(record.getSourceMethodName()).append(") - ");
        builder.append(formatMessage(record));
        builder.append("\n");
        return builder.toString();
    }
}

public class MyLogger {
    public static Logger getLogger(String name) {
        Logger logger = Logger.getLogger(name);
        logger.setUseParentHandlers(false);
        
        CustomFormatter formatter = new CustomFormatter();
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(formatter);

        logger.addHandler(handler);
        return logger;
    }
}