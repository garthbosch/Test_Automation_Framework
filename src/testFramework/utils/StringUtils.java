package testFramework.utils;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 *
 * @author B.W.Hart - Alacrity (Pty) Ltd
 */
public final class StringUtils {

    private StringUtils() {
    }

    /**
     * Converts a String (Y, YES, N, NO) to a Boolean.
     *
     * @param s the String to evaluate
     * @return a Boolean value
     */
    public static Boolean determineBoolean(String s) {
        if (s == null) {
            return Boolean.FALSE;
        } else if (s.trim().toUpperCase().equals("Y") || s.trim().toUpperCase().equals("YES")) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * Removes the leading hash (#) from a plancode.
     *
     * @param planCode the plancode
     * @return the modified plancode
     */
    public static String removeHash(String planCode) {
        if (planCode.startsWith("#")) {
            String substring = planCode.trim().substring("#".length());
            return substring;
        } else {
            return planCode.trim();
        }
    }

    /**
     * Determines whether a String is blank (contains text). Does not consider null as blank.
     *
     * @param s the String to evaluate
     * @return the Boolean result
     */
    public static Boolean isBlank(String s) {
        return org.apache.commons.lang.StringUtils.isBlank(s);
    }

    /**
     * Determines whether a String is blank or null.
     *
     * @param s the String to evaluate
     * @return the Boolean result
     */
    public static Boolean absent(String s) {
        if (s == null || s.trim().length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Converts a byte array to a String.
     *
     * @param ba the byte array
     * @return the String
     */
    public static String convertToString(byte[] ba) {
        return new String(ba);
    }

    /**
     * If a String is null, return a blank, else return the String provided.
     *
     * @param s the String to evaluate
     * @return the resultant String
     */
    public static String nullString(String s) {
        if (s == null) {
            return "";
        } else {
            return s.trim();
        }
    }

    /**
     * Provides padding to the left of s String.
     *
     * @param s the String to pad
     * @param n number of characters to provide
     * @param ch the character to use
     * @return the padded String
     */
    public static String leftPad(String s, Integer n, String ch) {
        return org.apache.commons.lang.StringUtils.leftPad(s, n, ch);
    }

    /**
     * Repeats a string str 'repeat' times.
     *
     * @param str the String to repeat
     * @param repeat the number of repeats
     * @return the String
     */
    public static String repeatString(String str, Integer repeat) {
        return org.apache.commons.lang.StringUtils.repeat(str, repeat);
    }

    /**
     * Pads a number with zero's.
     *
     * @param number the initial number
     * @param length total length of resultant String
     * @param left true if left fill, else false for right fill
     * @return the resultant String
     */
    public static String zeroFill(String number, Integer length, Boolean left) {
        String ret = null;

        int diff = length - number.length();
        if (diff > 0) {
            if (left) {
                ret = repeatString("0", diff) + number;
            } else {
                ret = number + repeatString("0", diff);
            }
        } else {
            ret = number;
        }

        return ret;
    }

    /**
     * Determines whether any of the Strings in arr[] startswith any of the Strings in s[].
     *
     * @param arr the Strings to evaluate
     * @param s the Strings that 'startswith'
     * @return true if at least one 'startsWith' is found
     */
    public static Boolean startsWith(String[] arr, String[] s) {
        if (arr == null || s == null || arr.length == 0 || s.length == 0) {
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < s.length; j++) {
                if (arr[i].startsWith(s[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determines whether any of the Strings in s[] are found in arr[].
     *
     * @param arr the Strings to evaluate
     * @param s the Strings to look for
     * @return true if at least one match is found
     */
    public static Boolean isInArray(String[] arr, String[] s) {
        if (arr == null || s == null || arr.length == 0 || s.length == 0) {
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < s.length; j++) {
                if (arr[i].equals(s[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determines whether none of the Strings in s[] are found in arr[].
     *
     * @param arr the Strings to evaluate
     * @param s the Strings to look for
     * @return false if at least one match is found
     */
    public static Boolean isNotInArray(String[] arr, String[] s) {
        if (arr == null || s == null || arr.length == 0 || s.length == 0) {
            return true;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < s.length; j++) {
                if (arr[i].equals(s[j])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Generic method for an object's toString() method default.
     *
     * @param obj the object to output
     * @return the object as a String
     */
    public static String toStringBuilder(Object obj) {
        StringBuilder sb = new StringBuilder();
        sb.append(ToStringBuilder.reflectionToString(obj));
        return sb.toString();
    }

    /**
     * Checks to see that string has alphabetic chracters.
     * 
     * @param s the String to evaluate
     * @return true if at least one alpha character is found
     */
    public static boolean hasLetter(String s) {
        boolean isAlpha;
        String tempStr = s.toUpperCase();
        for (int count = 0; count < s.length(); count++) {
            isAlpha = Character.isLetter(tempStr.charAt(count));
            if (isAlpha) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks to see that string has blank space characters.
     *
     * @param s the String to evaluate
     * @return true if at least one blank is found
     */
    public static boolean hasBlankSpace(String s) {
        boolean isSpace;
        for (int i = 0; i < s.length(); i++) {
            isSpace = Character.isSpaceChar(s.charAt(i));
            if (isSpace) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks to see that string has special chracters - eg. any char that is not a digit or letter.
     *
     * @param s the String to evaluate
     * @return true if at least one special character is found
     */
    public static boolean hasSpecialChar(String s) {
        boolean isLetterOrDigit;
        for (int i = 0; i < s.length(); i++) {
            isLetterOrDigit = Character.isLetterOrDigit(s.charAt(i));
            if (!isLetterOrDigit) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes all occurrences of a character from a String.
     *
     * @param str the String to evaluate
     * @param removeChar the character to remove
     * @return the modified String
     */
    public static String removeChar(String str, char removeChar) {
        String tempStr = "";
        char tempChar;
        for (int i = 0; i < str.length(); i++) {
            tempChar = str.charAt(i);
            if (tempChar != removeChar) {
                tempStr += tempChar;
            }
        }
        return tempStr;
    }

    /**
     * Checks to see if string contains a numeric character.
     *
     * @param s the String to evaluate
     * @return true if at least one numeric character is found
     */
    public static boolean hasDigit(String s) {
        boolean isNumeric;
        for (int count = 0; count < s.length(); count++) {
            isNumeric = Character.isDigit(s.charAt(count));
            if (isNumeric) {
                return true;
            }
        }
        return false;
    }
}
