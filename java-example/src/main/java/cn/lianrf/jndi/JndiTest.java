package cn.lianrf.jndi;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @version: v1.0
 * @date: 2019/10/12
 * @author: lianrf
 */
public class JndiTest {
    public static void main(String[] args) throws NamingException {
        InitialContext context = new InitialContext();

        context.lookup("");
    }
}
