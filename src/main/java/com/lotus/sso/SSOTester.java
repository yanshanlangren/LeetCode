package com.lotus.sso;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class SSOTester {
    public static String encrypt(byte[] encryptBytes,String publicKeyString) {
        try {
            //publicKeyString是公钥，需要使用获取公钥的接口获取系统公钥http://{server Uri}/mgt/getRSAPublicKey
            //String publicKeyString = "xWLDWl95+JkJFBfh4do4aTt8ePUtsvwV7XsxwDyHeT/c5aIxTGc5ML/wh2URH/Z8mQAB";
            byte[] decoded = Base64.getDecoder().decode(publicKeyString);
            PublicKey pubKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));

            Cipher cipher = Cipher.getInstance("RSA");
            // 设置为加密模式,并将公钥给cipher。
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            // 获得密文
            byte[] secret = cipher.doFinal(encryptBytes);
            // 进行Base64编码并返回
            return Base64.getEncoder().encodeToString(secret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
