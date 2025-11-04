package com.adventcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static boolean safety(int[] levels) {
        boolean safety = false;
        if (levels.length > 0) {
            if (levels.length == 1) {
                safety = true;
            } else if (levels.length >= 2) {
                float result = levels[0] - levels[1];

                if (result < 0 && Math.abs(result) <= 3 || result > 0 && Math.abs(result) <= 3) {
                    safety = true;
                }

                if (levels.length > 2) {
                    int[] arr = {1, 2, 3};
                    boolean found = true;

                    int i = 1;
                    while (i < levels.length - 1 && found) {
                        float newResult = levels[i] - levels[i + 1];

                        found = Arrays.stream(arr).anyMatch(x -> x == Math.abs(result))
                                && Arrays.stream(arr).anyMatch(x -> x == Math.abs(newResult))
                                && (Math.signum(result) == Math.signum(newResult));
                        if (!found) {
                            safety = false;
                            break;
                        }
                        i++;
                    }
                    if (found) {
                        safety = true;
                    }
                }
            }
        }
        return safety;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the levels for each report (one per line).");
        System.out.println("When finished, enter an empty line to stop:\n");

        List<int[]> allReports = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) break;

            int[] levels = Arrays.stream(input.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            allReports.add(levels);
        }
        System.out.println("\n---------Results ------------------------------------------");

        int safeCount = 0;
        int unsafeCount = 0;

        for (int[] levels : allReports) {
            boolean isSafe = safety(levels);
            if (isSafe) safeCount++;
            else unsafeCount++;
        }
        System.out.println("Total safe reports: " + safeCount);
        System.out.println("Total unsafe reports: " + unsafeCount);
    }
}
