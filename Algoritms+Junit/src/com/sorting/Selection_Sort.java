package com.sorting;

public class Selection_Sort implements SortingStrategy {
    @Override
    public double Sort(int[] tab) {
        int n = tab.length;
        long tStart = System.currentTimeMillis();
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (tab[j] < tab[min_idx]) {
                    min_idx = j;
                }
            }
            int temp = tab[min_idx];
            tab[min_idx] = tab[i];
            tab[i] = temp;
        }
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        //System.out.println("Select");
        return tDelta / 1000.0;
    }

}
