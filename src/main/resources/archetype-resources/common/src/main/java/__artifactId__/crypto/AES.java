#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.crypto;

import ${package}.${artifactId}.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

@Slf4j
public class AES extends CipherTemplate {

    SecretKeySpec keySpec;
    AlgorithmParameterSpec ivSpec;

    public AES(String algorithm, String secretKey, int length) {
        super(algorithm);
        makeKey(secretKey, length);
    }

    public AES(String algorithm, String secretKey, int length, byte[] iv) {
        super(algorithm);
        makeKey(secretKey, length);
        if (iv != null) {
            ivSpec = new IvParameterSpec(iv);
        }
    }

    void makeKey(String secretKey, int length) {
        byte[] key = StringUtils.stringToBytes(secretKey, length);
        keySpec = new SecretKeySpec(key, "AES");
    }

    @Override
    protected AlgorithmParameterSpec getAlgorithmParameterSpec() {
        return ivSpec;
    }

    @Override
    protected Key getSecretKey() {
        return keySpec;
    }

//    @Override
//    protected byte[] decode(String source) {
//        return Hex.decodeHex(source.toCharArray());
//    }

//    @Override
//    protected String encode(byte[] source) {
//        return Hex.encodeHex(source);
//    }
}
