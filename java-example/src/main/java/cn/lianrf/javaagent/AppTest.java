package cn.lianrf.javaagent;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

/**
 * @version: v1.0
 * @date: 2020/11/19
 * @author: lianrf
 */
public class AppTest {
    public static void main(String[] args) throws URISyntaxException {
        ProtectionDomain protectionDomain = AppTest.class.getProtectionDomain();

        CodeSource codeSource = protectionDomain.getCodeSource();
        URL location = codeSource.getLocation();

        final File agentJar = new File(location.toURI());

        System.out.println();
    }
}
