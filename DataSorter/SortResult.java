package DataSorter;

// SortResult.java
// Shared class — holds results for all algorithms

public class SortResult {
    public int[] sorted;
    public long timeNs;
    public long comparisons;
    public long swapsOrAssignments;

    public SortResult(int[] sorted, long timeNs, long comparisons, long swapsOrAssignments) {
        this.sorted = sorted;
        this.timeNs = timeNs;
        this.comparisons = comparisons;
        this.swapsOrAssignments = swapsOrAssignments;
    }

    public long totalSteps() {
        return comparisons + swapsOrAssignments;
    }

    public static String nsToMsStr(long ns) {
        double ms = ns / 1_000_000.0;
        return String.format("%.3f ms (%d ns)", ms, ns);
    }
}
