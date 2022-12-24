package day10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CathodeRayTube {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        String[] line;

        int register = 1;
        int cycle = 0;
        int sum = 0;

        int height = 6;
        int width = 40;
        char[][] crtDisplay = new char[height][width];
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                crtDisplay[r][c] = ' ';
            }
        }

        String opcode;
        int operand;

        while (in.hasNextLine()) {
            line = in.nextLine().split(" ");

            opcode = line[0];
            if (opcode.equals("noop")) {
                if (Math.abs(register - (cycle % 40)) <= 1) {
                    crtDisplay[cycle / 40][cycle % 40] = '#';
                }
                cycle++;
                if ((cycle - 20) % 40 == 0) {
                    sum += register * cycle;
                }
            } else if (opcode.equals("addx")) {
                operand = Integer.parseInt(line[1]);

                for (int i = 0; i < 2; i++) {
                    if (Math.abs(register - (cycle % 40)) <= 1) {
                        crtDisplay[cycle / 40][cycle % 40] = '#';
                    }
                    cycle++;
                    if ((cycle - 20) % 40 == 0) {
                        sum += register * cycle;
                    }
                }

                register += operand;
            } else {
                throw new IllegalArgumentException();
            }
        }

        System.out.println(sum);

        for (char[] row : crtDisplay) {
            System.out.println(new String(row));
        }
    }
}
