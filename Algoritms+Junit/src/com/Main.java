package com;

import com.sorting.Merge_Sort;
import com.sorting.SortingStrategy;

import java.util.ArrayList;
import java.util.List;

import com.maxTriplet.Triplet;
import com.spiral.Spiral;
import com.spot.Spot;
import com.substring.Substring;

import java.util.Random;

public class Main extends Random {

    public static void main(String[] args) {
        //sorting
        Random generator = new Random();
        int n = 1000000;
        int[] tab_best = new int[n];
        for (int i = 0; i < n; i++) {
            tab_best[i] = i;
        }
        int[] tab_worst = new int[n];
        for (int i = 0; i < n; i++) {
            tab_worst[i] = n - i;
        }
        int[] tab_av = new int[n];
        for (int i = 0; i < n; i++) {
            tab_av[i] = generator.nextInt(n);
        }

        SortingStrategy sort1 = new Merge_Sort();
        System.out.println("Best: " + sort1.Sort(tab_best));
        System.out.println("Worst: " + sort1.Sort(tab_worst));
        System.out.println("Av: " + sort1.Sort(tab_av));

        //maxtriplet
        List list = new ArrayList();

        for (int j : new int[]{-5, -7, 10, 1}) {
            list.add(j);
        }

        Triplet t = new Triplet();
        System.out.println("MaxTriplet: " + t.maxTriplet(list));

        //substring
        String a = new String("abcd");
        String b = new String("cdabcdab");

        Substring substring = new Substring();
        System.out.println("SubString: " + substring.subString(a, b));

        //spot
        int[] tab_spot = new int[2];
        List<Integer> l = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            //l.add(generator.nextInt(2000000)-1000000);
            l.add(generator.nextInt(20));
        }
        Spot s = new Spot();
        tab_spot = s.spot(l, 60);

        //spiral
        Spiral m = new Spiral(4, 7);
        m.print_counterSpiral();
    }
}

