package day03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class RucksackReorganization {
    private static final Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static final ArrayList<String> rucksacks = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < 300; i++) {
            rucksacks.add(in.nextLine());
        }

        part1();
        part2();
    }

    private static void part1() {
        int priorities = 0;

        for (String rucksack : rucksacks) {
            priorities += getPriority(rucksack);
        }

        System.out.println(priorities);
    }

    private static void part2() {
        int badgeSum = 0;

        int n = rucksacks.size();
        for (int i = 2; i < n; i += 3) {
            badgeSum += getBadgeValue(rucksacks.get(i-2), rucksacks.get(i-1), rucksacks.get(i));
        }

        System.out.println(badgeSum);
    }

    private static int getPriority(String s) {
        int mid = s.length() / 2;
        for (int i = 0; i < mid; i++) {
            for (int j = mid; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    return char2int(s.charAt(i));
                }
            }
        }
        throw new RuntimeException();
    }

    private static int getBadgeValue(String s1, String s2, String s3) {
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    for (int k = 0; k < s3.length(); k++) {
                        if (s1.charAt(i) == s3.charAt(k)) {
                            return char2int(s1.charAt(i));
                        }
                    }
                }
            }
        }
        throw new RuntimeException();
    }

    private static int char2int(char c) {
        if (Character.isLowerCase(c)) {
            return c - 'a' + 1;
        } else {
            return c - 'A' + 27;
        }
    }
}
