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
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;


public class TCPServer {

    ServerSocketChannel ss;
    private final int port;
    private final String fileName;
    private final CollectionManager collectionManager;
    BufferedInputStream bf = new BufferedInputStream(System.in);
    BufferedReader scanner = new BufferedReader(new InputStreamReader(bf));

    private final ExecutorService readPool;

    private final ExecutorService proccesPool;

    private final ExecutorService answerPool;

    public TCPServer(int port, CollectionManager collectionManager, String fileName) throws IOException {
        this.port = port;
        this.collectionManager = collectionManager;
        this.fileName = fileName;
        this.readPool = new ForkJoinPool();
        this.proccesPool = Executors.newCachedThreadPool();
        this.answerPool = Executors.newFixedThreadPool(5);
    }


    public void run() {
        try {
            openServerSocket();
            while (true){
                if (scanner.ready()){
                    String line = scanner.readLine();
                    if (line.equals("save")){
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
        readPool.submit(() -> {
            Request userRequest;

            ByteBuffer buffer = ByteBuffer.allocate(4096);
            buffer.clear();

            int bytesRead = 0;
            try {
                bytesRead = clientSocket.read(buffer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("eeee");
            if (bytesRead == -1) {
                System.out.println("Client closed connection.");
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return;
            }

            buffer.flip(); // preparing buffer for reading


            try (ByteArrayInputStream bais = new ByteArrayInputStream(buffer.array(), 0, bytesRead);
                 ObjectInputStream ois = new ObjectInputStream(bais)) {

                userRequest = (Request) ois.readObject();

                proccesPool.submit(() -> {
                    Response response;

                    CommandManager commandManager = new CommandManager(collectionManager);

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {

                        if (commandManager.getCommands().containsKey(userRequest.getName())) {
                            try {
                                Response responseToUser = commandManager.getCommands().get(userRequest.getName()).apply(userRequest);
                                oos.writeObject(responseToUser);
                            } catch (ClassNotFoundException | InterruptedException | IOException e) {
                                response = new Response("Client interaction error: ", e.toString());
                                oos.writeObject(response);
                            }
                        } else {
                            response = new NoCommandResponse(userRequest.getName());
                            oos.writeObject(response);
                        }

                        answerPool.submit(() -> {
                            try {
                                oos.flush();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            byte[] responseBytes = baos.toByteArray();
                            ByteBuffer responseBuffer = ByteBuffer.wrap(responseBytes);

                            while (responseBuffer.hasRemaining()) {
                                try {
                                    clientSocket.write(responseBuffer);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });

                    }catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            } catch (ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}