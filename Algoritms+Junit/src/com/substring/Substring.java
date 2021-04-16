package com.substring;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Substring {
    public int subString(String a, String b) {

        for (char character : b.toCharArray()) {
            if (a.indexOf(character) == -1) {
                throw new IllegalArgumentException("Bledne dane");
            }
        }

        int i = 0;
        String temp = a;
        int index;
        Boolean loop = false;

        do {
            index = 0;
            for (char character : b.toCharArray()) {
                index = a.indexOf(character, index);
                if (index == -1) {
                    loop = true;
                    break;
                }
                loop = false;
            }
            i++;
            a += temp;


        } while (loop);

        return i;
    }

}
