package day08;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class TreetopTreeHouse {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        ArrayList<ArrayList<Character>> map = new ArrayList<>();

        while (in.hasNext()) {
            char[] line = in.nextLine().toCharArray();
            ArrayList<Character> row = new ArrayList<>(line.length);
            for (char c : line) {
                row.add(c);
            }
            map.add(row);
        }

        int width = map.get(0).size();
        int height = map.size();

        HashSet<Pair> visibleTrees = new HashSet<>();

        int maxHeight, tree, scenicScore, maxScenicScore, distance;
        maxScenicScore = 0;

        for (int r = 0; r < height; r++) {
            maxHeight = -1;
            for (int c = 0; c < width; c++) {
                tree = map.get(r).get(c) - '0';
                if (tree > maxHeight) {
                    maxHeight = tree;
                    visibleTrees.add(new Pair(r, c));
                }

                // calculate scenic score
                scenicScore = 1;
                // left
                distance = 0;
                for (int i = c-1; i >= 0; i--) {
                    distance++;
                    if (map.get(r).get(i) >= map.get(r).get(c)) {
                        break;
                    }
                }
                scenicScore *= distance;
                // right
                distance = 0;
                for (int i = c+1; i < width; i++) {
                    distance++;
                    if (map.get(r).get(i) >= map.get(r).get(c)) {
                        break;
                    }
                }
                scenicScore *= distance;
                // up
                distance = 0;
                for (int i = r-1; i >= 0; i--) {
                    distance++;
                    if (map.get(i).get(c) >= map.get(r).get(c)) {
                        break;
                    }
                }
                scenicScore *= distance;
                // down
                distance = 0;
                for (int i = r+1; i < height; i++) {
                    distance++;
                    if (map.get(i).get(c) >= map.get(r).get(c)) {
                        break;
                    }
                }
                scenicScore *= distance;

                if (scenicScore > maxScenicScore) {
                    maxScenicScore = scenicScore;
                }
            }
            maxHeight = -1;
            for (int c = width-1; c >= 0; c--) {
                tree = map.get(r).get(c) - '0';
                if (tree > maxHeight) {
                    maxHeight = tree;
                    visibleTrees.add(new Pair(r, c));
                }
            }
        }

        for (int c = 0; c < width; c++) {
            maxHeight = -1;
            for (int r = 0; r < height; r++) {
                tree = map.get(r).get(c) - '0';
                if (tree > maxHeight) {
                    maxHeight = tree;
                    visibleTrees.add(new Pair(r, c));
                }
            }
            maxHeight = -1;
            for (int r = height-1; r >= 0; r--) {
                tree = map.get(r).get(c) - '0';
                if (tree > maxHeight) {
                    maxHeight = tree;
                    visibleTrees.add(new Pair(r, c));
                }
            }
        }

        System.out.println(visibleTrees.size());
        System.out.println(maxScenicScore);
    }

    private static class Pair {
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (r != pair.r) return false;
            return c == pair.c;
        }

        @Override
        public int hashCode() {
            int result = r;
            result = 31 * result + c;
            return result;
        }
    }
}
