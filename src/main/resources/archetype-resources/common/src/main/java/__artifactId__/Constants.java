#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId};

public interface Constants {

    String PROJECT = "misolab-java-multi";

    String CR = "\r";
    String LF = "\n";
    String CRLF = CR + LF;

    String PLUS = "+";
    String ESCAPE_SPACE = "%20";
    String COMMA = ",";

    String FILE_SEPARATOR = System.getProperty("file.separator");
    String LINE_SEPARATOR = System.getProperty("line.separator");

    boolean IS_MAC = CR.equals(LINE_SEPARATOR);
    boolean IS_UNIX = LF.equals(LINE_SEPARATOR);
    boolean IS_WINDOWS = CRLF.equals(LINE_SEPARATOR);

    String CHARSET_UTF_8 = "UTF-8";
    String CHARSET_ISO_8859_1 = "ISO8859-1";
    String CHARSET_EUC_KR = "EUC-KR";

    String HTTP_METHOD_GET = "GET";
    String HTTP_METHOD_POST = "POST";
    String HTTP_METHOD_HEAD = "HEAD";
    String HTTP_METHOD_PUT = "PUT";
    String HTTP_METHOD_DELETE = "DELETE";
    String HTTP_METHOD_OPTIONS = "OPTIONS";
    String HTTP_METHOD_TRACE = "TRACE";

    String X_TOKEN = "X-Token";

    Integer MEMBER_ERROR_COUNT = 5;

}
