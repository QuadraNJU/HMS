package nju.quadra.hms.util;

import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static StringProperty target;

    public static void init(StringProperty property) {
        target = property;
    }

    public static void log(String type, String message) {
        String logText = "[" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("uu/MM/dd HH:mm:ss.SSS")) + "] ";
        logText += type + ": " + message + System.getProperty("line.separator");
        if (target != null) {
            target.set(target.get() + logText);
        }
    }

    public static void log(Throwable e) {
        log("E", e.getClass().getSimpleName() + "/" + e.getLocalizedMessage());
    }

}
