package com.mazouri.ketangpai.common.utils;

import java.util.UUID;

/**
 * @author mazouri
 * @create 2020-11-12 22:04
 */
public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static void main(String[] args) {
        System.out.println(getUUID().length());
    }
}

