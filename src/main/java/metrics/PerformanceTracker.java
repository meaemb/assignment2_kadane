package metrics;

public final class PerformanceTracker {
    private long comparisons, arrayAccesses, assignments, branches, bytesAllocated;

    public void compare(){ comparisons++; }
    public void access(int k){ arrayAccesses += k; }
    public void assign(int k){ assignments += k; }
    public void branch(){ branches++; }
    public void addAllocatedBytes(long b){ bytesAllocated += b; }

    public long comparisons(){ return comparisons; }
    public long accesses(){ return arrayAccesses; }
    public long assignments(){ return assignments; }
    public long branches(){ return branches; }
    public long bytes(){ return bytesAllocated; }

    public String headerCSV(){ return "n,run,sum,left,right,comparisons,accesses,assignments,branches,bytes,time_us"; }
    public String lineCSV(int n, int run, int sum, int l, int r, long timeUs){
        return n + "," + run + "," + sum + "," + l + "," + r + ","
                + comparisons + "," + arrayAccesses + "," + assignments + "," + branches + "," + bytesAllocated + "," + timeUs;
    }

    // === Additional getters for CLI ===
    public long getOps() {
        // Общее количество операций (пример: сложим все типы)
        return comparisons + arrayAccesses + assignments + branches;
    }

    public long getCompares() {
        // Только количество сравнений
        return comparisons;
    }

}
