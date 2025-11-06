package DataSorter;

// QuickSort.java
// Implemented by Member 3

import java.util.Arrays;

public class QuickSort {

    public static SortResult sort(int[] arr) {
        int[] a = Arrays.copyOf(arr, arr.length);
        long[] counters = new long[2]; // [0]=comparisons, [1]=swaps
        long start = System.nanoTime();

        quickSortRecursive(a, 0, a.length - 1, counters);

        long end = System.nanoTime();
        return new SortResult(a, end - start, counters[0], counters[1]);
    }

    private static void quickSortRecursive(int[] a, int low, int high, long[] counters) {
        if (low < high) {
            int p = partition(a, low, high, counters);
            quickSortRecursive(a, low, p - 1, counters);
            quickSortRecursive(a, p + 1, high, counters);
        }
    }

    private static int partition(int[] a, int low, int high, long[] counters) {
        int pivot = a[high];
        int i = low;
        for (int j = low; j < high; j++) {
            counters[0]++;
            if (a[j] <= pivot) {
                swap(a, i, j);
                counters[1]++;
                i++;
            }
        }
        swap(a, i, high);
        counters[1]++;
        return i;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
