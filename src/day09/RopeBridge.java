package day09;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class RopeBridge {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        String[] line;
        char direction;
        int magnitude;

        HashSet<Coordinate> partOneTailPositions = new HashSet<>();
        HashSet<Coordinate> partTwoTailPositions = new HashSet<>();

        int numOfKnots = 10;
        ArrayList<Coordinate> rope = new ArrayList<>(numOfKnots);
        for (int i = 0; i < numOfKnots; i++) {
            rope.add(new Coordinate(0, 0));
        }

        while (in.hasNextLine()) {
            line = in.nextLine().split(" ");
            direction = line[0].charAt(0);
            magnitude = Integer.parseInt(line[1]);

            for (int i = 0; i < magnitude; i++) {
                switch (direction) {
                    case 'U' -> rope.get(0).y++;
                    case 'D' -> rope.get(0).y--;
                    case 'L' -> rope.get(0).x--;
                    case 'R' -> rope.get(0).x++;
                    default -> throw new IllegalArgumentException();
                }
                for (int j = 1; j < numOfKnots; j++) {
                    rope.get(j).follow(rope.get(j-1));
                }
                partOneTailPositions.add(new Coordinate(rope.get(1)));
                partTwoTailPositions.add(new Coordinate(rope.get(numOfKnots-1)));
            }
        }

        System.out.println(partOneTailPositions.size());
        System.out.println(partTwoTailPositions.size());
    }

    private static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Coordinate(Coordinate original) {
            this.x = original.x;
            this.y = original.y;
        }

        public void follow(Coordinate head) {
            if (this.x == head.x) {
                if (this.y > head.y + 1) {
                    this.y--;
                } else if (this.y < head.y - 1) {
                    this.y++;
                }
            } else if (this.y == head.y) {
                if (this.x > head.x + 1) {
                    this.x--;
                } else if (this.x < head.x - 1) {
                    this.x++;
                }
            } else {
                int manhattanDistance = Math.abs(this.x - head.x) + Math.abs(this.y - head.y);
                if (manhattanDistance > 2) {
                    if (this.x > head.x) {
                        this.x--;
                    } else {
                        this.x++;
                    }
                    if (this.y > head.y) {
                        this.y--;
                    } else {
                        this.y++;
                    }
                }
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Coordinate that = (Coordinate) o;

            if (x != that.x) return false;
            return y == that.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        @Override
        public String toString() {
            return "Coordinate{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
