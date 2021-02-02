#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import ${package}.common.Constants;

/**
 * 리스트 유틸리티
 */
public class ListUtils {

    /**
     * 리스트의 사이즈를 반환한다.
     *
     * @param list
     * @return
     */
    public static int size(List<?> list) {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /**
     * 인수 값을 리스트로 추가한다.
     *
     * @param list
     * @param value
     * @return
     */
    public static <T> List<T> add(List<T> list, T value) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(value);
        return list;
    }

    /**
     * 리스트의 값을 문자열로 변경
     * @param list
     * @return
     */
    public static String toString(List<?> list) {
        return toString(list, Constants.COMMA);
    }

    /**
     * 리스트의 값을 문자열로 변경
     * @param list
     * @param delimiter
     * @return
     */
    public static String toString(List<?> list, String delimiter) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.stream().map(Object::toString).collect(Collectors.joining(delimiter));
    }

    /**
     * 문자열을 리스트로 변경
     * @param value
     * @return
     */
    public static List<?> asList(String value) {
        return asList(value, Constants.COMMA);
    }

    /**
     * 문자열을 리스트로 변경
     * @param value
     * @param regex - 정규식으로
     * @return
     */
    public static List<?> asList(String value, String regex) {
        if (value == null) {
            return null;
        }
        String[] values = value.split(regex);
        return Arrays.asList(values);
    }
}
