package com.sorting;

public class Bubble_Sort implements SortingStrategy {
    @Override
    public double Sort(int[] tab) {
        int n = tab.length;
        int i, j, temp;
        boolean swapped;
        long tStart = System.currentTimeMillis();
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (tab[j] > tab[j + 1]) {
                    temp = tab[j];
                    tab[j] = tab[j + 1];
                    tab[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        //System.out.println("Bubble");
        return tDelta / 1000.0;
    }
}
