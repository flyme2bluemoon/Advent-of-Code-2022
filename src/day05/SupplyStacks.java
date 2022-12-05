package day05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class SupplyStacks {
    private static final Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    private static final int STACK_COUNT = 9;

    public static void main(String[] args) {
        ArrayList<ArrayList<Character>> input = new ArrayList<>();
        for (int i = 0; i < STACK_COUNT; i++) {
            input.add(new ArrayList<>());
        }

        for (int i = 0; i < 8; i++) {
            String level = in.nextLine();
            for (int j = 0; j < STACK_COUNT; j++) {
                char c = level.charAt(j*4+1);
                if (c != ' ') {
                    input.get(j).add(c);
                }
            }
        }

        ArrayList<Stack<Character>> stacksForPartOne = getArrayListOfStacks(input);
        ArrayList<Stack<Character>> stacksForPartTwo = getArrayListOfStacks(input);

        in.nextLine();
        in.nextLine();

        for (int i = 0; i <= 500; i++) {
            String[] instruction = in.nextLine().split(" ");
            int count = Integer.parseInt(instruction[1]);
            int from = Integer.parseInt(instruction[3])-1;
            int to = Integer.parseInt(instruction[5])-1;
            for (int j = 0; j < count; j++) {
                stacksForPartOne.get(to).push(stacksForPartOne.get(from).pop());
            }
            Stack<Character> tmp = new Stack<>();
            for (int j = 0; j < count; j++) {
                tmp.push(stacksForPartTwo.get(from).pop());
            }
            for (int j = 0; j < count; j++) {
                stacksForPartTwo.get(to).push(tmp.pop());
            }
        }

        for (int i = 0; i < STACK_COUNT; i++) {
            System.out.print(stacksForPartOne.get(i).peek());
        }
        System.out.println();
        for (int i = 0; i < STACK_COUNT; i++) {
            System.out.print(stacksForPartTwo.get(i).peek());
        }
        System.out.println();
    }

    private static ArrayList<Stack<Character>> getArrayListOfStacks(ArrayList<ArrayList<Character>> input) {
        ArrayList<Stack<Character>> stacks = new ArrayList<>();
        for (int i = 0; i < STACK_COUNT; i++) {
            stacks.add(new Stack<>());
            for (int j = input.get(i).size()-1; j >= 0; j--) {
                stacks.get(i).push(input.get(i).get(j));
            }
        }
        return stacks;
    }
}
