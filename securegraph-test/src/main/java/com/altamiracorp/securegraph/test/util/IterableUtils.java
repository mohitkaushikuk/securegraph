package com.altamiracorp.securegraph.test.util;

import static org.junit.Assert.assertTrue;

public class IterableUtils {
    public static <T> int count(Iterable<T> iterable) {
        int count = 0;
        for (T ignore : iterable) {
            count++;
        }
        return count;
    }

    public static <T> void assertContains(Object expected, Iterable<T> iterable) {
        StringBuilder found = new StringBuilder();
        boolean first = true;
        for (T o : iterable) {
            if (expected.equals(o)) {
                return;
            }
            if (!first) {
                found.append(", ");
            }
            found.append(o);
            first = false;
        }
        assertTrue("Iterable does not contain [" + expected + "], found [" + found + "]", false);
    }
}
