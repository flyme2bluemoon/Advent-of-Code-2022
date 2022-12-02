package day01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CalorieCounting {
    private static final Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static final ArrayList<Integer> elves = new ArrayList<>();

    private static int partOne() {
        return elves.get(0);
    }

    private static int partTwo() {
        return elves.get(0) + elves.get(1) + elves.get(2);
    }

    public static void main(String[] args) {
        int sum = 0;
        while (in.hasNext()) {
            String line = in.nextLine();
            if (line.isEmpty()) {
                elves.add(sum);
                sum = 0;
            } else {
                sum += Integer.parseInt(line);
            }
        }
        elves.sort(Collections.reverseOrder());
        System.out.println(partOne());
        System.out.println(partTwo());
    }
}
