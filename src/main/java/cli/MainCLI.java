package cli;

import java.util.Scanner;
import algorithms.Kadane;
import metrics.PerformanceTracker;

public class MainCLI {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the size of the array: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter " + n + " integers:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        PerformanceTracker tracker = new PerformanceTracker();
        Kadane.Subarray result = Kadane.maxSubarray(arr, tracker);

        System.out.println("\n===== Kadane’s Algorithm Result =====");
        System.out.println("Maximum subarray sum: " + result.sum);
        System.out.println("Start index: " + result.left);
        System.out.println("End index: " + result.right);
        System.out.println("Operations: " + tracker.getOps());
        System.out.println("Comparisons: " + tracker.getCompares());
    }
}
