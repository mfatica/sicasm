package com.mfatica;

public class StringOps {
    public static String StripExtension(String filename) {
        int extIndex = filename.lastIndexOf('.');

        if (extIndex == 0) return filename.substring(1);
        if (extIndex > 0) return filename.substring(0, extIndex);
        return filename;
    }

    public static String LeftPadString(String s, char padding, int length) {
        if (s.length() >= length) return s.substring(s.length() - length, length);
        return String.format("%" + (length - s.length()) + "s", "").replace(' ', padding) + s;
    }

    public static String RightPadString(String s, char padding, int length) {
        if (s.length() >= length) return s.substring(0, length);
        return s + String.format("%" + (length - s.length()) + "s", "").replace(' ', padding);
    }
}
