package day02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class RockPaperScissors {
    private static final Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    private static final ArrayList<char[]> strategy = new ArrayList<>();

    private static int getScore(char[] gamePair) {
        assert gamePair.length == 2;

        int score = 0;

        if (gamePair[1] == 'X') {
            score += 1;
            if (gamePair[0] == 'A') {
                score += 3;
            } else if (gamePair[0] == 'C') {
                score += 6;
            }
        } else if (gamePair[1] == 'Y') {
            score += 2;
            if (gamePair[0] == 'B') {
                score += 3;
            } else if (gamePair[0] == 'A') {
                score += 6;
            }
        } else if (gamePair[1] == 'Z') {
            score += 3;
            if (gamePair[0] == 'C') {
                score += 3;
            } else if (gamePair[0] == 'B') {
                score += 6;
            }
        }

        return score;
    }

    private static int getScore2(char[] gamePair) {
        assert gamePair.length == 2;

        int score = 0;

        if (gamePair[1] == 'X') {
            if (gamePair[0] == 'A') {
                score += 3;
            } else if (gamePair[0] == 'B') {
                score += 1;
            } else if (gamePair[0] == 'C') {
                score += 2;
            }
        } else if (gamePair[1] == 'Y') {
            score += 3;
            if (gamePair[0] == 'A') {
                score += 1;
            } else if (gamePair[0] == 'B') {
                score += 2;
            } else if (gamePair[0] == 'C') {
                score += 3;
            }
        } else if (gamePair[1] == 'Z') {
            score += 6;
            if (gamePair[0] == 'A') {
                score += 2;
            } else if (gamePair[0] == 'B') {
                score += 3;
            } else if (gamePair[0] == 'C') {
                score += 1;
            }
        }

        return score;
    }

    private static void partOne() {
        int sum = 0;
        for (char[] round : strategy) {
            sum += getScore(round);
        }
        System.out.println(sum);
    }

    private static void partTwo() {
        int sum = 0;
        for (char[] round : strategy) {
            sum += getScore2(round);
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 2500; i++) {
            strategy.add(new char[]{in.next().charAt(0), in.next().charAt(0)});
        }

        partOne();
        partTwo();
    }
}
