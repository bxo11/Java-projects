package com.sorting;


public class Quick_Sort implements SortingStrategy {
    @Override
    public double Sort(int[] tab) {
        long tStart = System.currentTimeMillis();

        qSort(tab, 0, tab.length - 1);

        long tEnd = System.currentTimeMillis();
        long tDelta = tEnd - tStart;
        //System.out.println("Quick");
        return tDelta / 1000.0;
    }

    static int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            if (arr[j] <= pivot) {
                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }


    static void qSort(int arr[], int low, int high) {
        if (low < high) {

            int pi = partition(arr, low, high);

            qSort(arr, low, pi - 1);
            qSort(arr, pi + 1, high);
        }
    }

}
