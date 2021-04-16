package com.sorting;

public class Insertion_sort implements SortingStrategy {
    @Override
    public double Sort(int[] tab) {
        int n = tab.length;
        long tStart = System.currentTimeMillis();
        for (int i = 1; i < n; ++i) {
            int key = tab[i];
            int j = i - 1;
            while (j >= 0 && tab[j] > key) {
                tab[j + 1] = tab[j];
                j = j - 1;
            }
            tab[j + 1] = key;
        }
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        // System.out.println("Insert");
        return tDelta / 1000.0;
    }
}
