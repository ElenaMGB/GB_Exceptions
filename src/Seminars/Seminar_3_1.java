package Seminars;

import java.io.IOException;

public class Seminar_3_1 {
    public static void main(String[] args) {
        try {
            doSomething();
        }
        catch (IOException e) {
            System.out.println("Исключение ввода-вывода");
        }

    }

    public static void doSomething() throws IOException {
        throw new IOException();
    }
}
