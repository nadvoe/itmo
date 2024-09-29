package Console;

import java.util.Scanner;

public class ConsoleManager implements ReadWrite {

    public ConsoleManager(){}

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
        System.err.println("Error(err): " + obj);
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
    public void write(String text){
        System.out.print(text);
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

    public void println(Object obj) {
        System.out.println(obj);
    }
}