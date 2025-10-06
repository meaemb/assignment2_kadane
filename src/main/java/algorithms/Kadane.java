package algorithms;

import metrics.PerformanceTracker;

public final class Kadane {
    public static final class Subarray {
        public final int sum, left, right;
        public Subarray(int sum, int left, int right){ this.sum=sum; this.left=left; this.right=right; }
        @Override public String toString(){ return "Subarray(sum=" + sum + ", left=" + left + ", right=" + right + ")"; }
    }
    public static Subarray maxSubarray(int[] a){ return maxSubarray(a, null); }

    public static Subarray maxSubarray(int[] a, PerformanceTracker pt){
        if (a == null || a.length == 0) return new Subarray(0, -1, -1);
        int bestSum = Integer.MIN_VALUE, curSum = 0, bestL = 0, bestR = 0, curL = 0;
        for (int i = 0; i < a.length; i++) {
            if (pt != null) pt.access(1);
            if (curSum <= 0) { curSum = a[i]; curL = i; if (pt != null){ pt.branch(); pt.assign(2);} }
            else { curSum += a[i]; if (pt != null){ pt.branch(); pt.assign(1);} }
            if (curSum > bestSum) { bestSum = curSum; bestL = curL; bestR = i; if (pt != null){ pt.compare(); pt.assign(3);} }
            else { if (pt != null) pt.compare(); }
        }
        return new Subarray(bestSum, bestL, bestR);
    }
}
