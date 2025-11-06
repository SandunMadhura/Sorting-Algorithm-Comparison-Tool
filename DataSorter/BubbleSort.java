package DataSorter;

// BubbleSort.java
// Implemented by Member 1

import java.util.Arrays;

public class BubbleSort {

    public static SortResult sort(int[] arr) {
        int[] a = Arrays.copyOf(arr, arr.length);
        long comps = 0;
        long swaps = 0;
        long start = System.nanoTime();

        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                comps++;
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }

        long end = System.nanoTime();
        return new SortResult(a, end - start, comps, swaps);
    }
}
