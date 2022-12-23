package day09;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;

public class RopeBridge {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        String[] line;
        char direction;
        int magnitude;

        HashSet<Coordinate> tailPositions = new HashSet<>();
        Coordinate head = new Coordinate(0, 0);
        Coordinate tail = new Coordinate(0, 0);

        while (in.hasNextLine()) {
            line = in.nextLine().split(" ");
            direction = line[0].charAt(0);
            magnitude = Integer.parseInt(line[1]);

            for (int i = 0; i < magnitude; i++) {
                switch (direction) {
                    case 'U' -> head.y++;
                    case 'D' -> head.y--;
                    case 'L' -> head.x--;
                    case 'R' -> head.x++;
                    default -> throw new IllegalArgumentException();
                }
                tail.follow(head);
                tailPositions.add(new Coordinate(tail));
            }
        }

        System.out.println(tailPositions.size());
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
