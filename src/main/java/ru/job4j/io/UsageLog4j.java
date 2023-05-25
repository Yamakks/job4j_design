package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
        byte b = 127;
        short sh = 32767;
        int i = 2000000;
        long l = 2L;
        float f = 3.5f;
        double d = 3.5;
        boolean bool = false;
        char ch = 'a';
        LOG.debug("debug message: byte {}, short {}, int {}, long {}," +
                "float {}, double {}, boolean {}, char {}", b, sh, i, l, f, d, bool, ch);
    }
}
