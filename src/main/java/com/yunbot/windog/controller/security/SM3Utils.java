package com.yunbot.windog.controller.security;

import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.util.encoders.Hex;


//https://www.cnblogs.com/nihaorz/p/14435695.html

public class SM3Utils {

    public static void main(String[] args) {
        System.out.println(generateSM3HASH("admin"));
    }

    /**
     * SM3 摘要计算
     *
     * @param src
     * @return
     */
    public static String generateSM3HASH(String src) {
        byte[] md = new byte[32];
        byte[] msg1 = src.getBytes();
        SM3Digest sm3 = new SM3Digest();
        sm3.update(msg1, 0, msg1.length);
        sm3.doFinal(md, 0);
        String s = new String(Hex.encode(md));
        return s.toUpperCase();
    }

}