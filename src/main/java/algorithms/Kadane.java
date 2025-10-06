package algorithms;

import metrics.PerformanceTracker;

public final class Kadane {

    public static final class Subarray {
        public final int sum, left, right;
        public Subarray(int sum, int left, int right) {
            this.sum = sum; this.left = left; this.right = right;
        }
        @Override public String toString() {
            return "Subarray(sum=" + sum + ", left=" + left + ", right=" + right + ")";
        }
    }

    // Convenience overload without metrics
    public static Subarray maxSubarray(int[] a) {
        return maxSubarray(a, null);
    }

    // Optimized Kadane with optional metrics (pt may be null)
    public static Subarray maxSubarray(int[] a, PerformanceTracker pt) {
        if (a == null || a.length == 0) return new Subarray(0, -1, -1);

        // 1) Initialize from the first element (handles all-negative arrays)
        int bestSum = a[0], curSum = a[0];
        int bestL = 0, bestR = 0, curL = 0;

        // 2) Cache length; start from i = 1
        for (int i = 1, n = a.length; i < n; i++) {
            // 3) Cache a[i] so we read the array only once per iteration
            final int v = a[i];
            if (pt != null) pt.access(1);

            // If current sum is positive, extend; otherwise start a new segment
            if (curSum > 0) {
                curSum += v;
                if (pt != null) { pt.branch(); pt.assign(1); }
            } else {
                curSum = v;        // start a new segment
                curL = i;
                if (pt != null) { pt.branch(); pt.assign(2); }
            }

            // Update the best answer only when we truly improve it
            if (curSum > bestSum) {
                bestSum = curSum;
                bestL = curL;
                bestR = i;
                if (pt != null) { pt.compare(); pt.assign(3); }
            } else {
                if (pt != null) pt.compare();
            }
        }
        return new Subarray(bestSum, bestL, bestR);
    }
}
