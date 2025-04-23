package io.log;

// import java.text.SimpleDateFormat;
// import java.util.Date;

/*public class LogConfigUtil {
    public static void setLogFileForClass(Class<?> clazz) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String className = clazz.getSimpleName();
        String fileName = className + "_" + timestamp;
        System.setProperty("scenario.name", fileName);
    }
} */
public class LogConfigUtil {

    public static void setLogFileForClass(Class<?> clazz) {
        String className = clazz.getSimpleName();

        // Set system property only if it's not already set
        if (System.getProperty("scenario.name") == null) {
            System.setProperty("scenario.name", className);
        }
    }
}
