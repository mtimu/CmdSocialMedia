package main.java.ui;

import java.io.IOException;

/**
 * a simple Message Printer to prettify message with text color and background color
 */
public class Printer {
    private static final String COLOR_RESET = "\u001B[0m";
    public static final String COLOR_BLACK = "\u001B[30m";
    public static final String COLOR_RED = "\u001B[31m";
    public static final String COLOR_GREEN = "\u001B[32m";
    public static final String COLOR_YELLOW = "\u001B[33m";
    public static final String COLOR_BLUE = "\u001B[34m";
    public static final String COLOR_PURPLE = "\u001B[35m";
    public static final String COLOR_CYAN = "\u001B[36m";
    public static final String COLOR_WHITE = "\u001B[37m";

    public static void println(String message) {
        println(message,"");
    }

    public static void print(String message) {
        print(message,"");
    }

    public static void printERR(String message) {
        println(message,COLOR_RED);
    }

    public static void print(String message , String color) {
        System.out.print(color + message+COLOR_RESET);
    }

    public static void println(String message , String color) {
        System.out.println(color + message+COLOR_RESET);
    }

    public static void printLine() {
        System.out.println(StrHelper.repeat("=",30));
    }

    public static void clearScreen() {
        try {
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
