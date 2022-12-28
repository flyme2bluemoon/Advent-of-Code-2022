package day11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class MonkeyInTheMiddle {
    private static final Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) {
        ArrayList<Monkey> monkeys1 = new ArrayList<>();
        ArrayList<Monkey> monkeys2 = new ArrayList<>();

        while (in.hasNextLine()) {
            if (in.nextLine().split(" ")[0].equals("Monkey")) {
                ArrayList<Monkey> newMonkeys = monkeyFactory();
                monkeys1.add(newMonkeys.get(0));
                monkeys2.add(newMonkeys.get(1));
            }
        }

        for (int i = 0; i < 20; i++) {
            for (Monkey monkey : monkeys1) {
                monkey.runTurn(monkeys1);
            }
        }
        calculateMonkeyBusiness(monkeys1);

        for (int i = 0; i < 10000; i++) {
            for (Monkey monkey : monkeys2) {
                monkey.runTurn(monkeys2);
            }
        }
        calculateMonkeyBusiness(monkeys2);
    }

    private static void calculateMonkeyBusiness(ArrayList<Monkey> monkeys) {
        long firstMostActive = 0;
        long secondMostActive = 0;
        for (Monkey monkey : monkeys) {
            if (monkey.getInspectionCount() > firstMostActive) {
                secondMostActive = firstMostActive;
                firstMostActive = monkey.getInspectionCount();
            } else if (monkey.getInspectionCount() > secondMostActive) {
                secondMostActive = monkey.getInspectionCount();
            }
        }
        long monkeyBusiness = firstMostActive * secondMostActive;
        System.out.println(monkeyBusiness);
    }

    private static ArrayList<Monkey> monkeyFactory() {
        ArrayList<Monkey> monkeys = new ArrayList<>();

        ArrayList<Long> startingItems;
        String[] operation;
        int test;
        int ifTrue;
        int ifFalse;

        String[] line;

        // parse starting items
        line = in.nextLine().split(": ")[1].split(", ");
        startingItems = new ArrayList<>();
        for (String item : line) {
            startingItems.add(Long.parseLong(item));
        }

        // parse operation
        operation = in.nextLine().split(" = ")[1].split(" ");

        // parse test
        test = Integer.parseInt(in.nextLine().split(" ")[5]);
        ifTrue = Integer.parseInt(in.nextLine().split(" ")[9]);
        ifFalse = Integer.parseInt(in.nextLine().split(" ")[9]);

        monkeys.add(new Monkey(new ArrayList<>(startingItems), operation, test, ifTrue, ifFalse, 1));
        monkeys.add(new Monkey(new ArrayList<>(startingItems), operation, test, ifTrue, ifFalse, 2));

        return monkeys;
    }

    private static class Monkey {
        private final ArrayList<Long> startingItems;
        private final String[] operation;
        private final int test;
        private final int ifTrue;
        private final int ifFalse;

        private long inspectionCount = 0;

        private final int part;

        public Monkey(ArrayList<Long> startingItems, String[] operation, int test, int ifTrue, int ifFalse, int part) {
            this.startingItems = startingItems;
            this.operation = operation;
            this.test = test;
            this.ifTrue = ifTrue;
            this.ifFalse = ifFalse;
            this.part = part;

            if (part != 1 && part != 2) {
                throw new IllegalArgumentException();
            }
        }

        public void runTurn(ArrayList<Monkey> otherMonkeys) {
            this.inspectionCount += this.startingItems.size();

            // NOTE: divisibility tests happen to all be prime
            int lowestCommonMultiple = 1;
            for (Monkey otherMonkey : otherMonkeys) {
                lowestCommonMultiple *= otherMonkey.getTest();
            }

            doInspections(lowestCommonMultiple);
            throwItems(otherMonkeys);
        }

        private void doInspections(int lowestCommonMultiple) {
            long firstOperand;
            long secondOperand;
            char operator;
            for (int i = 0; i < this.startingItems.size(); i++) {
                if (this.operation[0].equals("old")) {
                    firstOperand = this.startingItems.get(i);
                } else {
                    firstOperand = Integer.parseInt(this.operation[0]);
                }
                if (this.operation[2].equals("old")) {
                    secondOperand = this.startingItems.get(i);
                } else {
                    secondOperand = Integer.parseInt(this.operation[2]);
                }
                operator = this.operation[1].charAt(0);

                if (this.part == 1) {
                    switch (operator) {
                        case '+' -> this.startingItems.set(i, (firstOperand + secondOperand) / 3);
                        case '*' -> this.startingItems.set(i, (firstOperand * secondOperand) / 3);
                        default -> throw new IllegalArgumentException();
                    }
                } else {
                    switch (operator) {
                        case '+' -> this.startingItems.set(i, (firstOperand + secondOperand) % lowestCommonMultiple);
                        case '*' -> this.startingItems.set(i, (firstOperand * secondOperand) % lowestCommonMultiple);
                        default -> throw new IllegalArgumentException();
                    }
                }
            }
        }

        private void throwItems(ArrayList<Monkey> otherMonkeys) {
            for (long item : this.startingItems) {
                if (item % this.test == 0) {
                    otherMonkeys.get(this.ifTrue).catchItem(item);
                } else {
                    otherMonkeys.get(this.ifFalse).catchItem(item);
                }
            }
            this.startingItems.clear();
        }

        private void catchItem(long item) {
            this.startingItems.add(item);
        }

        public long getInspectionCount() {
            return this.inspectionCount;
        }

        public int getTest() {
            return this.test;
        }

        public String toString() {
            return "Monkey inspected items " + this.inspectionCount + " times";
        }
    }
}
