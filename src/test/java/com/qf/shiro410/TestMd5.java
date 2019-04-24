package com.qf.shiro410;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

public class TestMd5 {

    @Test
    public void md5(){
        /*algorithmName, source, salt*/
        String algorithmName = "MD5";
        String source = "admin2";
        String salt = "admin2";
        SimpleHash simpleHash = new SimpleHash(algorithmName, source);
        System.out.println("simpleHash :" + simpleHash);
        Md5Hash md5Hash = new Md5Hash(source);
        System.out.println("md5Hash :" + md5Hash);

        SimpleHash simpleHash2 = new SimpleHash(algorithmName, source,salt);
        System.out.println("simpleHash2 :" + simpleHash2);
        Md5Hash md5Hash2 = new Md5Hash(source,salt);
        System.out.println("md5Hash2 :" + md5Hash2);

        SimpleHash simpleHash3 = new SimpleHash(algorithmName, source,salt,2);
        System.out.println("simpleHash3 :" + simpleHash3);
        Md5Hash md5Hash3 = new Md5Hash(source,salt,2);
        System.out.println("md5Hash3 :" + md5Hash3);
    }

}
