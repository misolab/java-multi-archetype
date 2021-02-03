#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.util;

import ${package}.${artifactId}.Constants;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

public class StringUtils {

    public static byte[] base64Decode(String source) {
        return Base64.getDecoder().decode(source);
    }

    public static String base64Encode(byte[] source) {
        return Base64.getEncoder().encodeToString(source);
    }

    public static byte[] stringToBytes(String str) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        return Arrays.copyOf(bytes, bytes.length);
    }

    public static byte[] stringToBytes(String str, int length) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        return Arrays.copyOf(bytes, length);
    }

    public static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, Constants.CHARSET_UTF_8).replace(Constants.PLUS, Constants.ESCAPE_SPACE);
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

    public static String urlDecode(String s) {
        try {
            return URLDecoder.decode(s, Constants.CHARSET_UTF_8);
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isAscii(String value) {
        return Charset.forName("US-ASCII").newEncoder().canEncode(value);
    }

    /**
     * 문자열과 정규표현의 매치 여부를 체크한다.
     *
     * @param value
     * @param regex
     * @return
     */
    public static boolean matches(String value, String regex) {
        return isNotEmpty(value) && value.matches(regex);
    }

    public static boolean isNotEmpty(String value) {
        return org.springframework.util.StringUtils.hasText(value);
    }

    public static boolean isEmpty(String value) {
        return !isNotEmpty(value);
    }

    public static boolean contains(String value, String s) {
        if (isNotEmpty(value) && isNotEmpty(s)) {
            return value.contains(s);
        }
        return false;
    }

    public static boolean equals(String src, String dest) {
        if (isNotEmpty(src) && isNotEmpty(dest)) {
            return src.equals(dest);
            // return src.contentEquals(dest);
        }
        return false;
    }
}
