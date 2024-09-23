package Console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.util.Scanner;

public class Console implements ReadWrite {

    private static PushbackReader fileReader = null;

    public Console(){}

    @Override
    public int readInt() {
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine().trim());
    }

    @Override
    public long readLong() {
        Scanner scanner = new Scanner(System.in);
        return Long.parseLong(scanner.nextLine().trim());
    }

    public void printError(Object obj) {
        System.out.println("Error(out): " + obj);
    }
    @Override
    public double readDouble(){
        Scanner scanner = new Scanner(System.in);
        return Double.parseDouble(scanner.nextLine().trim());
    }

    @Override
    public String readLine(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }


    @Override
    public void write(String text){ System.out.print(text);
    }


    public String toValidatedMessage(){
        while (true){
            String userPrint = readLine();
            if(userPrint.isEmpty()){
                return null;
            }
            if (!userPrint.isBlank()){
                return userPrint;
            }
        }
    }

    public void println(Object obj) { System.out.println(obj);
    }

    public static PushbackReader getFileReader(PushbackReader reader){
        return fileReader;
    }


    public String readln() {
        StringBuilder input = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            int c;
            while ((c = reader.read()) != -1) {
                if (c == '\n') {
                    break; // to stop reading when new line symbol was founded
                }
                input.append((char) c);
            }
        } catch (IOException e) {
            System.out.println("Input reading error: " + e.getMessage());
        }
        return input.toString();
    }

    public String filereadln() {
        StringBuilder input = new StringBuilder();

        try {
            int c;
            while ((c = fileReader.read()) != -1) {
                if (c == '\n') {
                    break; // to stop reading when new line symbol was founded
                }
                input.append((char) c);
            }
        } catch (IOException e) {
            System.out.println("Input reading error: " + e.getMessage());
        }
        return input.toString();
    }

    public boolean isCanReadln(PushbackReader reader) {
        int nextChar = 0;
        try {
            nextChar = reader.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if ((nextChar != -1)&&(nextChar != 65535)) {
            try {
                reader.unread(nextChar);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        } else {
            return false;
        }
    }

    public void selectConsoleReader() {
        fileReader = null;
    }

    @Override
    public void selectFileReader(PushbackReader reader) {
        fileReader = reader;
    }

}