package com.zuicoding.platform.blog.utils;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Stephen.lin on 2017/8/5.
 */
public class MD5UtilTester {

    @Test
    public void test() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        System.err.println(MD5Util.EncoderByMd5("123"));
    }
}
