package com.mazouri.ketangpai.common.utils;

import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author mazouri
 * @create 2021-06-16 17:40
 */
public class GenerateCourseCode {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("bhfbd5");
        strings.add("5cdfc6");
        System.out.println(getCode(strings));
    }


    public static String getCode(List<String> code) {
        while (true) {
            StringBuilder s = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < 6; i++) {
                String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

                if ("char".equalsIgnoreCase(charOrNum)) // 字符串
                {
                    int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母
                    s.append((char) (choice + random.nextInt(26)));
                } else {
                    s.append(random.nextInt(10));
                }
            }
            s = new StringBuilder(s.toString().toUpperCase());
            if (!code.contains(s.toString())) {
                return s.toString();
            }
        }
    }
}
