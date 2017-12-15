package cn.mcg.security;

import org.junit.Test;

import javax.crypto.KeyGenerator;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyStore;

/**
 * @author: maocg
 * @date: 2017/10/31
 * @desc: 安全测试
 */
public class KeyStoreDemo {
    @Test
    public void testKeyStore(){
        try{
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(null, null);

            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            Key key = keyGen.generateKey();
            keyStore.setKeyEntry("secret", key, "password".toCharArray(), null);

            keyStore.store(new FileOutputStream("output.p12"), "password".toCharArray());
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    @Test
    public void testLoadKeyStore(){
        try{
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
//            keyStore.load(new FileInputStream("output.p12"), "password".toCharArray());
//
//            Key pvtKey = keyStore.getKey("secret", "password".toCharArray());
            FileInputStream instream = new FileInputStream("apiclient_cert.p12");
            try {
                keyStore.load(instream, "1272529401".toCharArray());
            } finally {
                instream.close();
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
