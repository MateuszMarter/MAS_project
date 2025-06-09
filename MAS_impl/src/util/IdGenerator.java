package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IdGenerator {
    public static long genId() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        return System.currentTimeMillis();
    }
}
