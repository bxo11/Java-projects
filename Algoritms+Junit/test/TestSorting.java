import com.sorting.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

public class TestSorting {

    @Test
    void testBubble() {
        Random generator = new Random();
        int n = 10000;
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
        SortingStrategy sort = new Bubble_Sort();
        assertTrue(sort.Sort(tab_worst) >= sort.Sort(tab_av));
        assertTrue(sort.Sort(tab_av) >= sort.Sort(tab_best));
    }

    @Test
    void testInsertion() {
        Random generator = new Random();
        int n = 10000;
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
        SortingStrategy sort = new Insertion_sort();
        assertTrue(sort.Sort(tab_worst) >= sort.Sort(tab_av));
        assertTrue(sort.Sort(tab_av) >= sort.Sort(tab_best));
    }

    @Test
    void testSelection() {
        Random generator = new Random();
        int n = 10000;
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
        SortingStrategy sort = new Selection_Sort();
        assertTrue(sort.Sort(tab_worst) >= sort.Sort(tab_av));
        assertTrue(sort.Sort(tab_av) >= sort.Sort(tab_best));
    }

    @Test
    void testMerge() {
        Random generator = new Random();
        int n = 100000;
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
        SortingStrategy sort = new Merge_Sort();
        assertTrue(sort.Sort(tab_worst) >= sort.Sort(tab_av));
        assertTrue(sort.Sort(tab_av) >= sort.Sort(tab_best));
    }

    @Test
    void testQuick() {
        Random generator = new Random();
        int n = 10000;
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
        SortingStrategy sort = new Quick_Sort();
        assertTrue(sort.Sort(tab_worst) >= sort.Sort(tab_av));
        assertTrue(sort.Sort(tab_av) >= sort.Sort(tab_best));
    }
}
