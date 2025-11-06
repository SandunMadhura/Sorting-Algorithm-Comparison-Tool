package DataSorter;

// MergeSort.java
// Implemented by Member 2

import java.util.Arrays;

public class MergeSort {

    public static SortResult sort(int[] arr) {
        int[] a = Arrays.copyOf(arr, arr.length);
        long[] counters = new long[2]; // [0]=comparisons, [1]=assignments
        long start = System.nanoTime();

        mergeSortRecursive(a, 0, a.length - 1, counters);

        long end = System.nanoTime();
        return new SortResult(a, end - start, counters[0], counters[1]);
    }

    private static void mergeSortRecursive(int[] a, int left, int right, long[] counters) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        mergeSortRecursive(a, left, mid, counters);
        mergeSortRecursive(a, mid + 1, right, counters);
        merge(a, left, mid, right, counters);
    }

    private static void merge(int[] a, int left, int mid, int right, long[] counters) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) { L[i] = a[left + i]; counters[1]++; }
        for (int j = 0; j < n2; j++) { R[j] = a[mid + 1 + j]; counters[1]++; }

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            counters[0]++;
            if (L[i] <= R[j]) a[k++] = L[i++];
            else a[k++] = R[j++];
            counters[1]++;
        }
        while (i < n1) { a[k++] = L[i++]; counters[1]++; }
        while (j < n2) { a[k++] = R[j++]; counters[1]++; }
    }
}
