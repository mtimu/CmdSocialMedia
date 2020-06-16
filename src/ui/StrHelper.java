package ui;

import java.util.StringJoiner;

public class StrHelper {

    public static String center(Object t, int size) {
        return center(t.toString(), size, ' ');
    }

    public static String center(String s, int size, char pad) {
        if (s == null || size <= s.length())
            return s;

        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < (size - s.length()) / 2; i++) {
            sb.append(pad);
        }
        sb.append(s);
        while (sb.length() < size) {
            sb.append(pad);
        }
        return sb.toString();
    }

    public static String join(Object[] objects , String delimiter) {
        StringJoiner joiner = new StringJoiner(delimiter);
        for (Object object : objects) joiner.add(object.toString());
        return joiner.toString();
    }

    public static String repeat(String text , int size) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) result.append(text);
        return result.toString();
    }
}
