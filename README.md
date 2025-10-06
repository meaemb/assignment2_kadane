# Kadane’s Algorithm — Maximum Subarray (Java + Maven)

A compact Java implementation of **Kadane’s algorithm** for the maximum subarray problem, with an optional lightweight **performance tracker** to count operations for analysis/comparison.

## Overview

Given an integer array, Kadane’s algorithm finds the contiguous subarray with the maximum possible sum in linear time **O(n)** and constant extra space **O(1)**.  
This project provides:

- A production-ready `Kadane` class with a simple API.
- An inner `Subarray` DTO (sum, left, right) for results.
- An optional `PerformanceTracker` to measure elementary operations during runs.

## Project Structure

- pom.xml
- src/
    - main/
        - java/
            - algorithms/
                - Kadane.java
            - metrics/
                - PerformanceTracker.java


> The repository also includes standard files like `.gitignore` and a README placeholder in the root. The primary language is Java. :contentReference[oaicite:0]{index=0}

## Requirements

- Java 17+ (or your course’s required JDK)
- Maven 3.8+ (or the version configured in your environment)

## Build


mvn -q -e -DskipTests package
This produces a JAR under target/. If you add a main class later, you can run it with:

java -jar target/<your-jar-name>.jar

### Usage (as a Library)
Import the classes and call Kadane.maxSubarray(...). You can pass a PerformanceTracker to record operation counts (optional).

import algorithms.Kadane;
import metrics.PerformanceTracker;

public class Demo {
    public static void main(String[] args) {
        int[] a = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };

        // Without metrics:
        Kadane.Subarray ans = Kadane.maxSubarray(a);
        System.out.println(ans); // e.g., Subarray(sum=6, left=3, right=6)

        // With metrics:
        PerformanceTracker pt = new PerformanceTracker();
        Kadane.Subarray ans2 = Kadane.maxSubarray(a, pt);
        System.out.println(ans2);
        System.out.println("ops=" + pt.getOps() + ", compares=" + pt.getCompares());
    }
}
## API
- Kadane.maxSubarray(int[] a) -> Kadane.Subarray
Runs Kadane with default settings.

- Kadane.maxSubarray(int[] a, PerformanceTracker pt) -> Kadane.Subarray
Same as above, additionally recording operations into pt (if non-null).

- Kadane.Subarray 
  - int sum — maximum subarray sum
  - int left — start index (inclusive), -1 if array is null/empty
  - int right — end index (inclusive), -1 if array is null/empty
  - toString() — formatted summary

### Notes
- For null or empty arrays, the implementation returns a neutral Subarray(0, -1, -1).
- Indices are 0-based and inclusive.

## Algorithm Summary
Kadane maintains a running sum of the best subarray ending at position i:
  - bestEndingHere = max(a[i], bestEndingHere + a[i])

  - bestSoFar = max(bestSoFar, bestEndingHere)

It also tracks the start/end indices when bestSoFar improves.
  - Time complexity: O(n)

  - Space complexity: O(1)

## Tests
If your course requires tests, add them under:

src/test/java/...
with your preferred framework (e.g., JUnit 5), then run:

mvn test

## Extending the Project
- Negative-only arrays: Current behavior returns (0, -1, -1).
If your assignment requires choosing the largest negative element, adjust the initialization to start from a[0] and propagate strictly.

- Indices vs. sums: If you need all optimal segments (ties), keep a list of candidates during traversal.

- Metrics: Extend PerformanceTracker with custom counters (e.g., assignments, array reads/writes).


## Performance Results

After running `cli.BenchmarkRunner`, the algorithm produced the following performance data.

| Metric | Behavior |
|--------|-----------|
| Time (μs) | Grows roughly linearly with n |
| Comparisons | Also linear with n |

### Time vs n
![Time vs n](docs/performance-plots/time_vs_n.png)

### Comparisons vs n
![Comparisons vs n](docs/performance-plots/comparisons_vs_n.png)


## Troubleshooting
- NullPointerException — Ensure you pass a non-null array; or handle (-1, -1) result.

- Maven errors — Verify your JDK (java -version) matches the Maven compiler target in pom.xml.


## Acknowledgments
Classic Kadane’s algorithm (maximum subarray) taught in most Algorithms courses; this implementation is aligned for coursework and performance tracking.