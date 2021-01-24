#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.crypto;

import ${package}.${artifactId}.util.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigesterTemplate {

    private MessageDigest messageDigest;

    public DigesterTemplate(String algorithm) {
        try {
            messageDigest = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("DigesterTemplate initialization is failed", e);
        }
    }

    public String digest(String str) {
        byte[] digest = messageDigest.digest(StringUtils.stringToBytes(str));
        return StringUtils.base64Encode(digest);
    }
}
