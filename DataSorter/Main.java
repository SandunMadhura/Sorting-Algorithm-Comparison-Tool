//CIT300 Assignment 02

package DataSorter;

// Main.java
// Implemented by Member 4
// Integrates all sorting classes and provides the console menu

import java.util.*;

public class Main {

    private static int[] currentData = new int[0];
    private static boolean dataLoaded = false;
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMenu();
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> enterManualData();
                case "2" -> generateRandomData();
                case "3" -> runSort("Bubble", BubbleSort.sort(currentData));
                case "4" -> runSort("Merge", MergeSort.sort(currentData));
                case "5" -> runSort("Quick", QuickSort.sort(currentData));
                case "6" -> compareAll();
                case "7" -> showDataset();
                case "8" -> { System.out.println("Exiting..."); return; }
                default -> System.out.println("Invalid choice! Please enter 1-8.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n--- Data Sorter: Sorting Algorithm Comparison Tool ---");
        System.out.println("1. Enter numbers manually");
        System.out.println("2. Generate random numbers");
        System.out.println("3. Perform Bubble Sort");
        System.out.println("4. Perform Merge Sort");
        System.out.println("5. Perform Quick Sort");
        System.out.println("6. Compare all algorithms (show performance table)");
        System.out.println("7. Show current dataset");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void enterManualData() {
        System.out.println("Enter integers separated by spaces or commas:");
        String input = sc.nextLine();
        try {
            String[] parts = input.trim().split("[,\\s]+");
            currentData = Arrays.stream(parts).mapToInt(Integer::parseInt).toArray();
            dataLoaded = true;
            System.out.println("Data loaded (" + currentData.length + " elements).");
        } catch (Exception e) {
            System.out.println("Invalid input! Please enter only integers.");
        }
    }

    private static void generateRandomData() {
        try {
            System.out.print("Enter number of elements: ");
            int n = Integer.parseInt(sc.nextLine());
            System.out.print("Enter min value: ");
            int min = Integer.parseInt(sc.nextLine());
            System.out.print("Enter max value: ");
            int max = Integer.parseInt(sc.nextLine());

            Random r = new Random();
            currentData = r.ints(n, min, max + 1).toArray();
            dataLoaded = true;
            System.out.println("Random data generated (" + n + " elements).");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter valid integers.");
        }
    }

    private static void runSort(String name, SortResult result) {
        if (!dataLoaded) {
            System.out.println("No dataset loaded. Use option 1 or 2 first.");
            return;
        }
        System.out.println("\n" + name + " Sort result:");
        System.out.println(Arrays.toString(result.sorted));
        System.out.println("Time: " + SortResult.nsToMsStr(result.timeNs));
        System.out.println("Comparisons: " + result.comparisons + " | Swaps/Assignments: "
                + result.swapsOrAssignments + " | Total Steps: " + result.totalSteps());
    }

    private static void compareAll() {
        if (!dataLoaded) {
            System.out.println("No dataset loaded. Use option 1 or 2 first.");
            return;
        }

        SortResult bubble = BubbleSort.sort(currentData);
        SortResult merge = MergeSort.sort(currentData);
        SortResult quick = QuickSort.sort(currentData);

        System.out.println("\n--- Performance Comparison Table ---");
        System.out.printf("%-10s | %-20s | %-14s | %-14s | %-14s%n",
                "Algorithm", "Time", "Comparisons", "Swaps/Assign", "Total Steps");
        System.out.println("--------------------------------------------------------------------------");
        printRow("Bubble", bubble);
        printRow("Merge", merge);
        printRow("Quick", quick);
        System.out.println("--------------------------------------------------------------------------");
    }

    private static void printRow(String name, SortResult r) {
        System.out.printf("%-10s | %-20s | %-14d | %-14d | %-14d%n",
                name, SortResult.nsToMsStr(r.timeNs),
                r.comparisons, r.swapsOrAssignments, r.totalSteps());
    }

    private static void showDataset() {
        if (!dataLoaded) {
            System.out.println("No dataset loaded.");
            return;
        }
        System.out.println("Current dataset (" + currentData.length + " elements):");
        System.out.println(Arrays.toString(
                currentData.length <= 200 ? currentData :
                        Arrays.copyOf(currentData, 200)
        ));
        if (currentData.length > 200)
            System.out.println("... (truncated)");
    }
}
