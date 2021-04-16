package com.maxTriplet;


import java.util.Collections;
import java.util.List;


public class Triplet {

    public int maxTriplet(List list) {
        if (list.size() < 3) {
            throw new IllegalArgumentException("Zbyt mala lista");
        }
        int temp = (int) Collections.max(list);
        int sum = 0;
        int temp_i = -1;
        for (int j = 0; j < 3; j++) {
            temp = (int) Collections.max(list);
            for (int i = 0; i < list.size(); i++) {
                if ((int) list.get(i) >= temp) {
                    temp = (int) list.get(i);
                    temp_i = i;
                }
            }
            sum += temp;
            list.remove(temp_i);
        }

        return sum;
    }
}
