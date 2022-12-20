package day07;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class NoSpaceLeftOnDevice {
    private static final Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) {
        Node root = new Node("/");
        Node cur = root;

        String[] line;
        while (in.hasNext()) {
            line = in.nextLine().split(" ");
            if (line[0].equals("$")) {
                // parse command
                if (line[1].equals("cd")) {
                    if (line[2].equals("..")) {
                        cur = cur.getParent();
                    } else if (line[2].equals("/")) {
                        cur = root;
                    } else {
                        cur = cur.getChild(line[2]);
                    }
                } else if (!line[1].equals("ls")) {
                    throw new RuntimeException("Command not found");
                }
            } else {
                // parse directory listing
                if (line[0].equals("dir")) {
                    cur.add(new Node(line[1]));
                } else {
                    cur.add(new Node(line[1], Integer.parseInt(line[0])));
                }
            }
        }

        // TODO: maybe just implement DFS using stack instead of recursion to get away with the global variable issue.

        Int partOneAnswer = new Int();
        int usedSpace = root.getDirectorySizePartOne(partOneAnswer);
        System.out.println(partOneAnswer.val);

        int freeSpace = 70000000 - usedSpace;
        int spaceNeeded = 30000000 - freeSpace;
        Int partTwoAnswer = new Int(Integer.MAX_VALUE);
        root.getDirectorySizePartTwo(partTwoAnswer, spaceNeeded);
        System.out.println(partTwoAnswer.val);
    }

    private static class Node {
        String name;
        int size;
        Node parent;
        ArrayList<Node> contents;

        public Node(String name) {
            this.name = name;
            this.size = 0;
            this.contents = new ArrayList<>();
        }

        public Node(String name, int size) {
            this(name);
            this.size = size;
        }

        public void add(Node node) {
            node.parent = this;
            contents.add(node);
        }

        public Node getParent() {
            if (this.parent == null) throw new RuntimeException("Cannot get parent of root directory");
            return this.parent;
        }

        public Node getChild(String key) {
            for (Node node : contents) {
                if (node.name.equals(key)) {
                    return node;
                }
            }
            throw new RuntimeException("Child directory " + key + " not found");
        }

        public int getDirectorySizePartOne(Int answer) {
            int directorySize = this.size;
            for (Node node : contents) {
                directorySize += node.getDirectorySizePartOne(answer);
            }
            if (directorySize <= 100000 && !this.contents.isEmpty()) {
                answer.val += directorySize;
            }
            return directorySize;
        }

        public int getDirectorySizePartTwo(Int answer, int spaceNeeded) {
            int directorySize = this.size;
            for (Node node : contents) {
                directorySize += node.getDirectorySizePartTwo(answer, spaceNeeded);
            }
            if (directorySize >= spaceNeeded && directorySize < answer.val) {
                answer.val = directorySize;
            }
            return directorySize;
        }
    }

    private static class Int {
        // TODO: is there a better way to do pass by reference in Java?
        public int val = 0;

        public Int() {}

        public Int(int val) {
            this.val = val;
        }
    }
}
