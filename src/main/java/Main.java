package main.java;

import main.java.pages.LoginPage;
import main.java.lifecycle.Application;


public class Main {

    public static void main(String[] args) {
        Application.start(LoginPage.class);

    }
}
