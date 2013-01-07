package org.isk.gwttips.client.util;

public class StringUtils {
    private StringUtils() {
    }

    /**
     * Return the string in the camel case version.<br>
     * All letters are lower case beside the ones after a separator (an underscore, a dot, etc.)<br>
     * Examples: <code>MY_VARIABLE</code>, <code>my.variable</code>, <code>My_variABLE</code><br>
     * will all be transformed in <code>myVariable</code><br>
     * 
     * @param string
     *            is the string to be transformed.
     * @param separator
     *            is the separator after which the next character must be upper case.
     * @return the transformed string.
     */
    public static String camelCase(final String string, final char separator) {
        final char[] input = string.toCharArray();
        final StringBuilder builder = new StringBuilder();

        boolean isPreviousSeparator = false;
        boolean toUpperCase = false;
        boolean toLowerCase = false;
        char currentChar = 0;
        
        for (int i = 0; i < input.length; i++) {
            currentChar = input[i];
            toUpperCase = false;
            toLowerCase = false;

            if (currentChar != separator) {
                if (isPreviousSeparator && Character.isLowerCase(currentChar)) {
                    toUpperCase = true;
                } else if (!isPreviousSeparator && Character.isUpperCase(currentChar)) {
                    toLowerCase = true;
                }

                if (toUpperCase) {
                    builder.append((char) (currentChar - 0x20));
                } else if (toLowerCase) {
                    builder.append((char) (currentChar + 0x20));
                } else {
                    builder.append(currentChar);
                }

                isPreviousSeparator = false;
            } else {
                isPreviousSeparator = true;
            }
        }

        return builder.toString();
    }
}
