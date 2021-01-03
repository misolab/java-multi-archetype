#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.util;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class CheckUtils {

    /**
     * 인수 값의 참/거짓을 나타내는 값을 반환한다.
     *
     * @param value
     * @return
     */
    public static boolean isTrue(Boolean value) {
        return value != null && value;
    }

    /**
     * 값의 참/거짓을 나타내는 값을 반환한다.
     *
     * @param value
     * @return
     */
    public static boolean isFalse(Boolean value) {
        return !isTrue(value);
    }

    /**
     * 컬렉션의 존재 여부를 체크한다.
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    /**
     * 배열의 존재 여부를 체크한다.
     *
     * @param array
     * @return
     */
    public static boolean isEmpty(Object[] array) {
        return (array == null || array.length == 0);
    }

    /**
     * 맵의 존재 여부를 체크한다.
     *
     * @param map
     * @return
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    /**
     * 존재 여부를 체크한다.
     *
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 존재 여부를 체크한다.
     *
     * @param array
     * @return
     */
    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

    /**
     * 존재 여부를 체크한다.
     *
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 인수끼리 동일한지 체크한다.
     *
     * @param obj1
     * @param obj2
     * @return
     */
    public static boolean isEquals(Object obj1, Object obj2) {
        return Objects.equals(obj1, obj2);
    }

    /**
     * 인수끼리 동일한지 체크한다.
     *
     * @param obj1
     * @param obj2
     * @return
     */
    public static boolean isNotEquals(Object obj1, Object obj2) {
        return !isEquals(obj1, obj2);
    }
}
