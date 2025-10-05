package metrics;

public final class PerformanceTracker {
    private long comparisons;
    private long arrayAccesses;
    private long assignments;
    private long branches;
    private long bytesAllocated; // при желании можно учитывать новые массивы

    public void compare()   { comparisons++; }
    public void access(int k){ arrayAccesses += k; }
    public void assign(int k){ assignments += k; }
    public void branch()    { branches++; }

    public void addAllocatedBytes(long b) { bytesAllocated += b; }

    public long getComparisons(){ return comparisons; }
    public long getArrayAccesses(){ return arrayAccesses; }
    public long getAssignments(){ return assignments; }
    public long getBranches(){ return branches; }
    public long getBytesAllocated(){ return bytesAllocated; }

    public String headerCSV(){
        return "n,run,sum,left,right,comparisons,accesses,assignments,branches,bytes";
    }
    public String lineCSV(int n, int run, int sum, int l, int r){
        return n + "," + run + "," + sum + "," + l + "," + r + ","
                + comparisons + "," + arrayAccesses + "," + assignments + ","
                + branches + "," + bytesAllocated;
    }
}
