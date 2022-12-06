package day06;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TuningTrouble {
    static private final Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    static String input;

    public static void main(String[] args) {
        input = in.nextLine();

        solve(4);
        solve(14);
    }

    private static void solve(int markerLength) {
        markerLength--;
        int i = markerLength;
        boolean foundMarker = false;
        while (!foundMarker) {
            foundMarker = true;
            for (int j = i - markerLength; j <= i; j++) {
                for (int k = j + 1; k <= i; k++) {
                    if (input.charAt(j) == input.charAt(k)) {
                        foundMarker = false;
                        break;
                    }
                }
            }
            i++;
        }

        System.out.println(i);
    }
}
