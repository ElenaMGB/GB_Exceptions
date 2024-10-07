//Задача 0

//    Исходно:
//    public void rwLine(Path pathRead, Path pathWrite) throws IOException {
//        BufferedReader in = null;
//        BufferedReader out = null;
//        try {
//            in = Files.newBufferedReader (pathRead);
//            out = Files.newBufferedWriter (pathWrite);
//            out.write(in.readLine());
//        } finally {
//            try {
//                if (in != null) in.close();
//            } catch (IOException e) {
//            }
//            try {
//                if (out != null) out.close();
//            } catch (IOException e) {
//            }
//        }
//    }
//    Необходимо преобразовать в код с использованием ресурсного try

package Seminars;

import java.io.*;
import java.io.IOException;
import java.nio.file.*;


public class Seminar_3_0 {
    public static void main(String[] args) {
    }

    public void rwLine(Path pathRead, Path pathWrite) throws IOException {
        try(BufferedReader in = Files.newBufferedReader (pathRead);
            BufferedWriter out = Files.newBufferedWriter (pathWrite)) {
            out.write(in.readLine());
        }
    }
}

