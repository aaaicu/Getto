package com.yummythings.getto.encrypt;

import org.assertj.core.api.Assertions;
import org.jasypt.encryption.pbe.StandardPBEByteEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;

public class JasyptTest {

    @Test
    void 암호화테스트(){
        String url = "jdbc:oracle:thin:@test_high?TNS_ADMIN=/test/Wallet_TestDB";
        String username = "username";
        String password = "password";

        final String KEY = "key";
        String encryptUrl = jasyptEncrypt(url, KEY);
        String encryptUsername = jasyptEncrypt(username, KEY);
        String encryptPassword = jasyptEncrypt(password, KEY);

        System.out.println("encryptUrl : " + encryptUrl);
        System.out.println("encryptUsername : " + encryptUsername);
        System.out.println("encryptPassword : " + encryptPassword);

        Assertions.assertThat(url).isEqualTo(jasyptDecrypt(encryptUrl, KEY));
        Assertions.assertThat(username).isEqualTo(jasyptDecrypt(encryptUsername, KEY));
        Assertions.assertThat(password).isEqualTo(jasyptDecrypt(encryptPassword, KEY));

        String s = jasyptDecrypt("ip5pQuH0CoxuB3WCVp9PDNtYqtZfiUuaMZKtAA+LP9sKH4JlyRAANVrAitzoNDfP+OTz/2BsYWbkTEm8p900JZO2ABTgHASJDgPJSoBCAeOLHOgRcpoxKg==", KEY);
        System.out.println("s = " + s);
    }

    private String jasyptEncrypt(String input, String key) {
        StandardPBEStringEncryptor encryptor = getStandardPBEStringEncryptor(key);
        return encryptor.encrypt(input);
    }

    private String jasyptDecrypt(String input, String key){
        StandardPBEStringEncryptor encryptor = getStandardPBEStringEncryptor(key);
        return encryptor.decrypt(input);
    }

    private StandardPBEStringEncryptor getStandardPBEStringEncryptor(String key) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setAlgorithm(StandardPBEByteEncryptor.DEFAULT_ALGORITHM);
        encryptor.setPassword(key);
        return encryptor;
    }
}
