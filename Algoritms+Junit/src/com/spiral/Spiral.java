package com.spiral;

public class Spiral {
    private int m, n;
    private int[][] tab;

    public Spiral(int n, int m) {
        this.n = n;
        this.m = m;
        tab = new int[n][m];

        int temp = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tab[i][j] = temp;
                temp++;
            }

        }
    }

    public void print_counterSpiral() {
        int k = 0;
        int l = 0;
        int i = 0;

        while (k < n && l < m) {
            for (i = k; i < n; i++) {
                System.out.print(tab[i][l] + " ");
            }
            l++;

            for (i = l; i < m; ++i) {
                System.out.print(tab[n - 1][i] + " ");
            }
            n--;

            // if (l < m) {
            for (i = n - 1; i >= k; --i) {
                System.out.print(tab[i][m - 1] + " ");
            }
            m--;
            // }

            // if (k < n) {
            for (i = m - 1; i >= l; --i) {
                System.out.print(tab[k][i] + " ");
            }
            k++;
            // }
        }
    }


}
