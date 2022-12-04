package day03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

@SuppressWarnings("OptionalGetWithoutIsPresent")
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
            HashSet<Character> firstHalf = new HashSet<>();
            for (int i = 0; i < rucksack.length() / 2; i++) {
                firstHalf.add(rucksack.charAt(i));
            }
            HashSet<Character> secondHalf = new HashSet<>();
            for (int i = rucksack.length() / 2; i < rucksack.length(); i++) {
                secondHalf.add(rucksack.charAt(i));
            }
            firstHalf.retainAll(secondHalf);
            priorities += char2int(firstHalf.stream().findAny().get());
        }

        System.out.println(priorities);
    }

    private static void part2() {
        int badgeSum = 0;

        int n = rucksacks.size();
        for (int i = 0; i < n-2; i += 3) {
            HashSet<Character> e1 = new HashSet<>();
            HashSet<Character> e2 = new HashSet<>();
            HashSet<Character> e3 = new HashSet<>();
            for (int j = 0; j < rucksacks.get(i).length(); j++) {
                e1.add(rucksacks.get(i).charAt(j));
            }
            for (int j = 0; j < rucksacks.get(i+1).length(); j++) {
                e2.add(rucksacks.get(i+1).charAt(j));
            }
            for (int j = 0; j < rucksacks.get(i+2).length(); j++) {
                e3.add(rucksacks.get(i+2).charAt(j));
            }
            e2.retainAll(e3);
            e1.retainAll(e2);
            badgeSum += char2int(e1.stream().findAny().get());
        }

        System.out.println(badgeSum);
    }

    private static int char2int(char c) {
        if (Character.isLowerCase(c)) {
            return c - 'a' + 1;
        } else {
            return c - 'A' + 27;
        }
    }
}
