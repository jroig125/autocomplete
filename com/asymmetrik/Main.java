package com.asymmetrik;

import java.util.Scanner;
import java.util.StringJoiner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        boolean isOn = true;
        Scanner scanner = new Scanner(System.in);
        //Initial directions
        System.out.println("------------------------\n" +
                "Welcome to Autocomplete.\n" +
                "------------------------\n" +
                "Use Training Mode [t] to enter training text.\n" +
                "Use Autocomplete Mode [a] to receive predictive text suggestions.\n" +
                "-----------------------------------------------------------------\n");

        //Mode selection instructions
        while (isOn) {
            System.out.println("----------\n" +
                    "Main Menu\n" +
                    "----------\n\n" +
                    "Enter 't' to enter Training Mode.\n" +
                    "Enter 'a' to enter Autocomplete Mode.\n" +
                    "Enter '0' to quit the program.");
            String input = scanner.nextLine();

            System.out.println("");
            isOn = modeSelector(input);
        }

        scanner.close();
    }

    //redirects program to the proper mode, or prepares for quitting
    private static boolean modeSelector(String input) {
        boolean isOn = true;
        if (input.equals("t"))
            trainingMode();
        else if (input.equals("a")) {
            autocompleteMode();
        } else if (input.equals("0")) {
            System.out.println(" -----------------------------------\n" +
                    "| Thank you for using Autocomplete. |\n" +
                    " -----------------------------------");
            isOn = false;
        } else {
            System.out.println("\nPlease enter 't' or 'a' to choose your mode, or '0' to quit.\n");
        }

        return isOn;
    }

    //adds input lines to database
    private static void trainingMode() {
        System.out.println("-------------\n" +
                "Training Mode\n" +
                "-------------\n");
        System.out.println("Enter training text below and press enter to train the algorithm. " +
                "When you are done, enter '0' to return to the main menu.");

        Scanner scanner = new Scanner(System.in);
        boolean isOn = true;
        while (isOn) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                isOn = false;
                System.out.println();
            } else if (input.trim().isEmpty()) {
                System.out.println("Blank lines do not register in the system.");
            } else {
                AutocompleteProvider.train(input);
                System.out.println("\nYour training text has been processed. " +
                        "You may enter more, or enter '0' to return to the main menu.");
            }
        }
    }

    //uses database to give all possible predictions sorted by confidence,
    //then length (shortest first), then alphabet
    private static void autocompleteMode() {
        System.out.println("------------------\n" +
                "Autocomplete Mode\n" +
                "------------------\n");
        System.out.println("Enter the beginning of a word to get completion suggestions. " +
                "When you are done, enter '0' to return to the main menu.");

        Scanner scanner = new Scanner(System.in);
        boolean isOn = true;
        while (isOn) {
            String input = scanner.nextLine();
            //lower case input except for solo capital I
            if(!input.equals("I")) {
                input = input.toLowerCase();
            }

            if (input.equals("0")) {
                isOn = false;
                System.out.println();
            } else if (input.trim().isEmpty()) {
                System.out.println("Blank lines do not register in the system.\n");
            } else {
                TreeSet<Candidate> finalList = AutocompleteProvider.getWords(input);

                //formats output of suggestions, including comma-separated word list with confidence
                //for each word
                StringJoiner joiner = new StringJoiner(", ");
                for (Candidate candidate : finalList) {
                    String item = candidate.getWord() + " " + "(" + candidate.getConfidence() + ")";
                    joiner.add(item);
                }
                String output = joiner.toString();
                if (output.isEmpty()) {
                    System.out.println("No suggestions found.\n");
                } else {
                    System.out.println(output.toString() + "\n");
                }
            }
        }
    }
}