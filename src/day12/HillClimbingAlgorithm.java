package day12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class HillClimbingAlgorithm {
    private static int width, height;

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        ArrayList<ArrayList<Character>> map = new ArrayList<>();

        Coordinate start = null;
        Coordinate end = null;

        while (in.hasNext()) {
            char[] line = in.nextLine().toCharArray();
            ArrayList<Character> row = new ArrayList<>(line.length);
            for (int i = 0; i < line.length; i++) {
                row.add(line[i]);
                if (line[i] == 'S') {
                    start = new Coordinate(i, map.size());
                }
                if (line[i] == 'E') {
                    end = new Coordinate(i, map.size());
                }
            }
            map.add(row);
        }

        if (start == null || end == null) {
            throw new RuntimeException("Start and/or end not found in map.");
        }

        height = map.size();
        width = map.get(0).size();

        map.get(start.y).set(start.x, 'a');
        map.get(end.y).set(end.x, 'z');

        System.out.println(getPathLength(map, start, end));

        int minimumPathLength = Integer.MAX_VALUE;
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                if (map.get(r).get(c) == 'a') {
                    int pathLength = getPathLength(map, new Coordinate(c, r), end);
                    if (pathLength < minimumPathLength) {
                        minimumPathLength = pathLength;
                    }
                }
            }
        }
        System.out.println(minimumPathLength);
    }

    private static int getPathLength(ArrayList<ArrayList<Character>> map, Coordinate start, Coordinate end) {
        Queue<Node> queue = new LinkedList<>();
        HashSet<Coordinate> explored = new HashSet<>();

        queue.add(new Node(start));
        explored.add(start);

        while (!queue.isEmpty()) {
            Node node = queue.remove();
            Coordinate coordinate = node.coordinate;

            if (coordinate.equals(end)) {
                int pathLength = 1;
                while (!node.parent.coordinate.equals(start)) {
                    pathLength++;
                    node = node.parent;
                }
                return pathLength;
            }

            ArrayList<Coordinate> neighbors = new ArrayList<>();

            neighbors.add(new Coordinate(coordinate.x - 1, coordinate.y));
            neighbors.add(new Coordinate(coordinate.x + 1, coordinate.y));
            neighbors.add(new Coordinate(coordinate.x, coordinate.y - 1));
            neighbors.add(new Coordinate(coordinate.x, coordinate.y + 1));

            for (Coordinate neighbor : neighbors) {
                if (neighbor.isInBounds(width, height) && map.get(neighbor.y).get(neighbor.x) <= map.get(coordinate.y).get(coordinate.x)+1 && !explored.contains(neighbor)) {
                    explored.add(neighbor);
                    queue.add(new Node(neighbor, node));
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    private static class Node {
        public Coordinate coordinate;
        public Node parent = null;

        public Node(Coordinate coordinate) {
            this.coordinate = coordinate;
        }

        public Node(Coordinate coordinate, Node parent) {
            this.coordinate = coordinate;
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "coordinate=" + coordinate +
                    ", parent=" + (parent != null ? parent.coordinate : "null") +
                    '}';
        }
    }

    private static class Coordinate {
        public int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isInBounds(int width, int height) {
            return this.x >= 0 && this.x < width && this.y >= 0 && this.y < height;
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
            return "(" + this.x + ", " + this.y + ")";
        }
    }
}
