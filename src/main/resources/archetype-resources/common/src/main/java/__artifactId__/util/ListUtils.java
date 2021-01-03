#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.util;

import java.util.ArrayList;
import java.util.List;

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
}
