package main.java.ui;

import java.util.Scanner;

/**
 * A helper class to get inputs from {@link Scanner}
 * this class use {@link Printer} to print formatted messages for inputs
 */
public class Input {
    private final static String TITLE = "Your Choice";
    private static Input input;
    private Scanner scanner;

    private Input() {
        scanner = new Scanner(System.in);
    }

    public static Input getInstance() {
        if (input == null)
            input = new Input();

        return input;
    }


    public int intIn() {
        return intIn(TITLE);
    }

    public int intIn(String title) {
        Printer.print(title.concat(": "),Printer.COLOR_GREEN);
        return Integer.parseInt(scanner.nextLine().trim());
    }

    public String lineIn() {
        return lineIn(TITLE);
    }

    public String lineIn(String title) {
        Printer.print(title+": ",Printer.COLOR_GREEN);
        return scanner.nextLine().trim();
    }

    public String strIn() {
        return strIn(TITLE);
    }

    // TODO: 7/14/2020 fix this shit
    public String strIn(String title) {
        Printer.print(title + ": " , Printer.COLOR_GREEN);
        String val = scanner.nextLine().trim();
        return val;
    }

    public void pressEnterToContinue() {
        Printer.print("Press Enter To Continue...");
        scanner.nextLine();
    }
}
