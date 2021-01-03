#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.util;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.SearchControls;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.SortControl;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ock
 */
@Slf4j
public class LDAPAuthenticator {
    final static String[] ATTR_IDS = {"sAMAccountName", "mobile", "mail"};

    final String principal;
    final String providerUrl;

    /**
     * @param principal
     * @param providerUrl
     */
    public LDAPAuthenticator(String principal, String providerUrl) {
        this.principal = principal;
        this.providerUrl = providerUrl;
    }

    String getPrincipal(String id) {
        String result = String.format(principal, id);
        log.debug("SECURITY_PRINCIPAL:" + result);
        return result;
    }

    void initLdapContext(String id, String pass) throws NamingException, IOException {
        Hashtable<String, String> properties = new Hashtable<>();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        properties.put(Context.SECURITY_AUTHENTICATION, "simple");
        properties.put(Context.PROVIDER_URL, providerUrl);
        properties.put(Context.SECURITY_PRINCIPAL, getPrincipal(id));
        properties.put(Context.SECURITY_CREDENTIALS, pass);

        SearchControls searchControls = new SearchControls();
        searchControls.setReturningAttributes(ATTR_IDS);
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        LdapContext context = new InitialLdapContext(properties, null);
        context.setRequestControls(new Control[]{new SortControl("sAMAccountName", Control.CRITICAL)});
    }


    /**
     * @param userId
     * @param password
     * @return
     */
    public boolean isSimpleLogin(String userId, String password) {
        Result result = login(userId, password);
        return result.isOk();
    }

    /**
     * @param userId
     * @param password
     * @return
     */
    public Result login(String userId, String password) {
//        https://stackoverflow.com/a/12370710
        if (StringUtils.isEmpty(password)) {
            return Result.E52e;
        }

        try {
            initLdapContext(userId, password);
        } catch (Exception e) {
            log.debug(e.getMessage());

            String message = e.getMessage();
            String data = parseErrorCode(message);
            Result result = Result.parse(data);
            return result;
        }
        return Result.OK;
    }

    String parseCode(String regex, String removeKeyword, String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        boolean find = matcher.find();
        if (find) {
            return matcher.group().replace(removeKeyword, "");
        }
        return null;
    }

    String parseErrorCode(String message) {
        String code = "-1";
        final String ERROR_CODE = "error code ";
        if (message.contains(ERROR_CODE)) {
            String regex = ERROR_CODE + "(\\d{2})";
            code = parseCode(regex, ERROR_CODE, message);

            // error code reference - https://ldap.or.kr/ldap-result-code/
            if ("49".equals(code)) {
                final String DATA = "data ";
                String regex2 = DATA + "(\\d{3}|52e)";
                code = parseCode(regex2, DATA, message);
                if (StringUtils.isEmpty(code)) {
                    code = "52e";
                }
            }
        }
        return code;
    }

    @Getter
    public enum Result {
        OK("", ""),
        UnknownError("-1", "알수 없는 오류"),

        //  https://www.ibm.com/support/knowledgecenter/SSVJJU_6.4.0/com.ibm.IBMDS.doc_6.4/ds_ag_srv_adm_pass_thru_auth_pwd_policy.html
        E525("525", "사용자를 찾을 수 없음"),
        E52e("52e", "잘못된 자격 증명"),
        E530("530", "현재 로그온 할 수 없습니다."),
        E531("531", "이 워크 스테이션에서 로그온 할 수 없습니다."),
        E532("532", "암호가 만료되었습니다."),
        E533("533", "계정 사용 안 함"),
        E534("534", "이 시스템에서 요청한 로그온 유형이 사용자에게 부여되지 않았습니다."),
        E701("701", "계정 만료"),
        E773("773", "사용자는 암호를 재설정해야합니다"),
        E775("775", "사용자 계정 잠김");

        String data;
        String message;

        Result(String data, String message) {
            this.data = data;
            this.message = message;
        }

        /**
         * @return
         */
        public boolean isOk() {
            return this.equals(OK);
        }

        static public Result parse(String data) {
            if (StringUtils.isEmpty(data))
                return OK;

            return Arrays.stream(Result.values()).filter(e -> e.data.equalsIgnoreCase(data)).findFirst().orElseThrow(NullPointerException::new);
        }
    }
}
