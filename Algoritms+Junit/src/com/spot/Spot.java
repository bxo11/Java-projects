package com.spot;

import java.util.ArrayList;
import java.util.List;

public class Spot {
    public int[] spot(List<Integer> a, int target) {
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
        List<Integer> prop_results = new ArrayList<>();

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) == 0) {
                continue;
            }
            if (target % a.get(i) == 0) {
                prop_results.add(a.get(i));
            }
        }

        for (int i = 0; i < prop_results.size(); i++) {
            for (int j = 0; j < prop_results.size(); j++) {
                if (a.get(i) == 0) {
                    continue;
                }
                if (prop_results.get(i) * prop_results.get(j) == target) {
                    result[0] = prop_results.get(i);
                    result[1] = prop_results.get(j);
                    return result;
                }
            }
        }
        throw new RuntimeException("Brak rozwiazan");
    }
}