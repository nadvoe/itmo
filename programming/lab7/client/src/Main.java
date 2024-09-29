import Client.ClientRunner;
import Client.TCPClient;
import Console.Console;

import java.io.IOException;
import java.net.InetAddress;

public class Main {
    private  static final int port = 4242;

    public static void main(String[] args) {
        var console = new Console();
        try{
            System.out.println("Hello mir!");
            var client = new TCPClient(InetAddress.getLocalHost(), port);
            var runner = new ClientRunner(client, console);
            runner.interactiveMode();
        } catch (IOException e){
            System.out.println("Impossible to connect to the server :( ....");
        }
    }
}