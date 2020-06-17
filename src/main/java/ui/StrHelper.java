package main.java.ui;

import java.util.StringJoiner;

/**
 * A helper class to do some operation on strings.
 */
public class StrHelper {


    /**
     *  refer to {@link #center(String, int, char)} doc
     */
    public static String center(Object obj, int length) {
        return center(obj.toString(), length, ' ');
    }

    /**
     *
     * @param val The text that is going to be in center of box
     * @param length Length of box
     * @param fillingCharacter trailing and leading filling character
     * @return centered val with length of box fill other indices with fillingCharacter
     */
    public static String center(String val, int length, char fillingCharacter) {
        if (val == null || length <= val.length())
            return val;

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < (length - val.length()) / 2; i++) {
            sb.append(fillingCharacter);
        }
        sb.append(val);
        while (sb.length() < length) {
            sb.append(fillingCharacter);
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
