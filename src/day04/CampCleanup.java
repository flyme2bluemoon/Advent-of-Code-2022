package day04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class CampCleanup {
    private static final Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static final ArrayList<int[][]> elfPairs = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            String[] assignmentPair = in.nextLine().split(",");
            String[] elfOne = assignmentPair[0].split("-");
            String[] elfTwo = assignmentPair[1].split("-");
            elfPairs.add(new int[][]{
                    new int[]{Integer.parseInt(elfOne[0]), Integer.parseInt(elfOne[1])},
                    new int[]{Integer.parseInt(elfTwo[0]), Integer.parseInt(elfTwo[1])}
            });
        }

        int subsetCount = 0;
        int overlapCount = 0;
        for (int[][] elfPair : elfPairs) {
            if ((elfPair[0][0] >= elfPair[1][0] && elfPair[0][1] <= elfPair[1][1]) || (elfPair[0][0] <= elfPair[1][0] && elfPair[0][1] >= elfPair[1][1])) {
                subsetCount++;
            }
            if ((elfPair[0][0] <= elfPair[1][1] && elfPair[0][0] >= elfPair[1][0]) || (elfPair[1][0] <= elfPair[0][1] && elfPair[1][0] >= elfPair[0][0])) {
                overlapCount++;
            }
        }
        System.out.println(subsetCount);
        System.out.println(overlapCount);
    }
}
