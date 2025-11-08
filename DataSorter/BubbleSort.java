package DataSorter;

// BubbleSort.java
// Implemented by Member 1

import java.util.Arrays;

public class BubbleSort {

    public static SortResult sort(int[] arr) {
        // Create a copy so the original array stays unchanged
        int[] a = Arrays.copyOf(arr, arr.length);

        // Counters for performance tracking
        long comps = 0;   // number of comparisons
        long swaps = 0;   // number of swaps performed

        // Start measuring execution time
        long start = System.nanoTime();

        int n = a.length;

        // Outer loop controls how many passes we make
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false; // Track if any swap happens in this pass

            // Inner loop compares adjacent pairs
            for (int j = 0; j < n - 1 - i; j++) {
                comps++; // Counting comparison between a[j] and a[j+1]

                // Swap if elements are out of order
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;

                    swaps++;      // Count swap
                    swapped = true; // Mark that at least one swap occurred
                }
            }

            // Optimization: if no swaps, array is already sorted
            if (!swapped) break;
        }

        // Stop timer
        long end = System.nanoTime();

        // Return results in a SortResult object
        return new SortResult(a, end - start, comps, swaps);
    }
}
