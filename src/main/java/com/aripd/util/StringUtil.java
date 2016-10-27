package com.aripd.util;

import com.google.common.base.CharMatcher;
import com.google.common.base.Strings;
import java.text.Normalizer;

public class StringUtil {

    public static void main(String[] args) {
        System.out.println(convertToLatin("  ÖÖßÖ  öçşığü ÖÇŞİĞÜ"));
    }

    public static String slugify(String input) {
        String ret = trim(input);
        if (Strings.isNullOrEmpty(input)) {
            return "";
        }

        ret = convertToLatin(ret);
        ret = removeDuplicateWhiteSpaces(ret);
        return ret.replace(" ", "-").toLowerCase();
    }

    public static String convertToLatin(String input) {
        input = input.replace("ß", "ss");
        input = input.replace("ı", "i");
        input = input.replace("ğ", "g");
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("[^a-zA-Z0-9 ]", "");
    }

    private static String removeDuplicateWhiteSpaces(String input) {
        String ret = trim(input);
        if (Strings.isNullOrEmpty(ret)) {
            return "";
        }

        return ret.replaceAll("\\s+", " ");
    }

    private static String trim(String input) {
        return CharMatcher.WHITESPACE.trimFrom(input);
    }
}
