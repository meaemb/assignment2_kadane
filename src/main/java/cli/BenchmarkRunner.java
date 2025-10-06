package cli;

import algorithms.Kadane;
import algorithms.Kadane.Subarray;
import metrics.PerformanceTracker;
import java.io.PrintWriter;
import java.io.File;
import java.util.Random;

public final class BenchmarkRunner {
    private static final Random RNG = new Random(42);

    public static void main(String[] args) throws Exception {
        int[] sizes = {100, 1000, 10000, 100000};
        int runs = 5;
        String out = "docs/perf_kadane.csv";
        try (PrintWriter pw = new PrintWriter(new File(out))) {
            PerformanceTracker pt = new PerformanceTracker();
            pw.println(pt.headerCSV());
            for (int n : sizes) {
                for (int r = 1; r <= runs; r++) {
                    int[] a = genRandom(n);
                    long t0 = System.nanoTime();
                    pt = new PerformanceTracker();
                    Subarray res = Kadane.maxSubarray(a, pt);
                    long t1 = System.nanoTime();
                    long us = (t1 - t0) / 1000;
                    pw.println(pt.lineCSV(n, r, res.sum, res.left, res.right, us));
                }
            }
        }
        System.out.println("CSV written to docs/perf_kadane.csv");
    }

    private static int[] genRandom(int n){
        int[] a = new int[n];
        for (int i=0;i<n;i++) a[i] = RNG.nextInt(21) - 10; // -10..10
        return a;
    }
}
