package day13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DistressSignal {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        String firstLine, secondLine;
        NestedInteger first, second;

        int index = 1;
        int sumOfIndicies = 0;
        ArrayList<NestedInteger> packets = new ArrayList<>();
        NestedInteger decoderKey2 = new NestedInteger("[[2]]");
        NestedInteger decoderKey6 = new NestedInteger("[[6]]");
        packets.add(decoderKey2);
        packets.add(decoderKey6);

        boolean eof = false;
        while (!eof) {
            firstLine = in.nextLine();
            secondLine = in.nextLine();

            first = new NestedInteger(firstLine);
            second = new NestedInteger(secondLine);

            System.out.printf("== Pair %d ==\n", index);
            System.out.printf("- Compare %s vs %s\n\n", first, second);

            if (first.compareTo(second) <= 0) {
                sumOfIndicies += index;
            }

            packets.add(first);
            packets.add(second);

            index++;

            if (in.hasNextLine()) {
                in.nextLine();
            } else {
                eof = true;
            }
        }
        System.out.println(sumOfIndicies);

        Collections.sort(packets);
        System.out.println((packets.indexOf(decoderKey2) + 1) * (packets.indexOf(decoderKey6) + 1));
    }

    private static class NestedInteger implements Comparable<NestedInteger> {
        private int value;
        private ArrayList<NestedInteger> list = null;

        private static final String NOT_A_LIST = "NestedInteger is not a list, it is contains only an integer. Did you mean to use the convertToList() method?";

        public NestedInteger() {
            list = new ArrayList<>();
        }

        public NestedInteger(int value) {
            this.value = value;
        }

        public NestedInteger(String s) {
            this();
            char[] contents = s.substring(1, s.length()-1).toCharArray();
            int nextInt = 0;
            int depth = 0;
            int start = 0;
            for (int i = 0; i < contents.length; i++) {
                char c = contents[i];
                if (c == '[') {
                    if (depth == 0) {
                        start = i;
                    }
                    depth++;
                } else if (c == ']') {
                    depth--;
                    if (depth == 0) {
                        list.add(new NestedInteger(s.substring(start + 1, i + 2)));
                    }
                } else if (depth == 0) {
                    if (c == ',') {
                        if (i >= 1 && isDigit(contents[i-1])) {
                            list.add(new NestedInteger(nextInt));
                            nextInt = 0;
                        }
                    } else if (isDigit(c)) {
                        nextInt *= 10;
                        nextInt += (c-'0');
                    }
                }
            }
            if (contents.length >= 1 && isDigit(contents[contents.length-1])) {
                list.add(new NestedInteger(nextInt));
            }
        }

        private static boolean isDigit(char c) {
            return c >= '0' && c <= '9';
        }

        public boolean isInteger() {
            return this.list == null;
        }

        private void guaranteeList() {
            if (this.isInteger())
                throw new RuntimeException(NOT_A_LIST);
        }

        public NestedInteger asList() {
            if (this.isInteger()) {
                NestedInteger thisAsList = new NestedInteger();
                thisAsList.add(this.value);
                return thisAsList;
            } else {
                return this;
            }
        }

        public void add(NestedInteger nestedInteger) {
            guaranteeList();
            list.add(nestedInteger);
        }

        public void add(int i) {
            this.add(new NestedInteger(i));
        }

        public void add() {
            this.add(new NestedInteger());
        }

        public NestedInteger get(int index) {
            guaranteeList();
            return this.list.get(index);
        }

        public int size() {
            guaranteeList();
            return this.list.size();
        }

        @Override
        public String toString() {
            if (this.isInteger()) {
                return Integer.toString(this.value);
            } else {
                return list.toString();
            }
        }

        @Override
        public int compareTo(NestedInteger o) {
            if (this.isInteger() && o.isInteger()) {
                return this.value - o.value;
            }
            NestedInteger self = this.asList();
            NestedInteger other = o.asList();

            int result;
            for (int i = 0; i < Math.min(self.size(), other.size()); i++) {
                result = self.get(i).compareTo(other.get(i));
                if (result != 0) {
                    return result;
                }
            }

            return self.size() - other.size();
        }
    }
}
