package Server;

import Collection.CollectionManager;
import Commands.Command;
import Commands.CommandManager;
import Requests.Request;
import Responses.NoCommandResponse;
import Responses.Response;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;


public class TCPServer {

    ServerSocketChannel ss;
    private final int port;
    private final String fileName;
    private final CollectionManager collectionManager;
    BufferedInputStream bf = new BufferedInputStream(System.in);
    BufferedReader scanner = new BufferedReader(new InputStreamReader(bf));


    public TCPServer(int port, CollectionManager collectionManager, String fileName) throws IOException {
        this.port = port;
        this.collectionManager = collectionManager;
        this.fileName = fileName;
    }


    public void run() {
        try {
            openServerSocket();
            while (true){
                if (scanner.ready()){
                    String line = scanner.readLine();
                    if (line.equals("save")){
                        collectionManager.save();
                        System.out.println("The collection was successfully saved.");
                    } else if (line.equals("exit")){
                        collectionManager.exit();
                        System.out.println("The collection was successfully saved before exit. Now – bye!");
                        System.exit(0);
                    } else System.out.println("There are only 'exit' or 'save' commands.");
                }
                SocketChannel clientSocket = ss.accept();
                if (clientSocket != null){
                    processClientRequest(clientSocket);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void openServerSocket() throws IOException {
        try {
            ss = ServerSocketChannel.open();
            ss.bind(new InetSocketAddress(InetAddress.getLocalHost(), 4242));
            ss.configureBlocking(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void processClientRequest(SocketChannel clientSocket) throws IOException {
        Request userRequest;
        Response response;

        ByteBuffer buffer = ByteBuffer.allocate(4096);
        buffer.clear();

        int bytesRead = clientSocket.read(buffer);
        System.out.println("eeee");
        if (bytesRead == -1) {
            System.out.println("Client closed connection.");
            clientSocket.close();
            return;
        }

        buffer.flip(); // preparing buffer for reading

        try (ByteArrayInputStream bais = new ByteArrayInputStream(buffer.array(), 0, bytesRead);
             ObjectInputStream ois = new ObjectInputStream(bais)) {

            userRequest = (Request) ois.readObject();

            CommandManager commandManager = new CommandManager(collectionManager);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {

                if (commandManager.getCommands().containsKey(userRequest.getName())) {
                    try {
                        Response responseToUser = commandManager.getCommands().get(userRequest.getName()).apply(userRequest);
                        oos.writeObject(responseToUser);
                    } catch (ClassNotFoundException | InterruptedException e) {
                        response = new Response("Client interaction error: ", e.toString());
                        oos.writeObject(response);
                    }
                } else {
                    response = new NoCommandResponse(userRequest.getName());
                    oos.writeObject(response);
                }
                oos.flush();
                byte[] responseBytes = baos.toByteArray();
                ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);

                while (responseBuffer.hasRemaining()) {
                    clientSocket.write(responseBuffer);
                }

            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}