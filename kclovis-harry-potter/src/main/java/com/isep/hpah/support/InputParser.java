package com.isep.hpah.support;

import java.util.Scanner;

public class InputParser {
    public static int questionWithIntOutput(String question, int lowerBound, int upperBound) {
        String input;

        // repeat until a valid input is entered
        do {
            Scanner scanner = new Scanner(System.in);

            System.out.print(question);
            input = scanner.nextLine();

        } while (tryParse(input) < lowerBound || tryParse(input) > upperBound);

        return tryParse(input);
    }
    public static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return -99; // we rarely go beyond this case so it is safe
        }
    }
}
