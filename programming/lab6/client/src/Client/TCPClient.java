package Client;

import Requests.Request;
import Responses.Response;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Set;

public class TCPClient {
    private SocketChannel channel;

    private final InetSocketAddress addr;

    public TCPClient(InetAddress address, int port) throws IOException {
        this.addr = new InetSocketAddress(address, port);
        this.channel = SocketChannel.open();
        this.channel.configureBlocking(false);
        channel.configureBlocking(false);
    }

    public Response requestProcess(Request request) throws IOException, ClassNotFoundException {
        channel = SocketChannel.open();
        if(!channel.isConnected()) {
            channel.connect(addr);
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(request);
        oos.flush();
        byte[] serializedRequest = baos.toByteArray();
        ByteBuffer requestBuffer = ByteBuffer.wrap(serializedRequest);
        while (requestBuffer.hasRemaining()) {
            channel.write(requestBuffer);
        }
        return responseProcess();
    }


    public Response responseProcess() throws IOException, ClassNotFoundException {
        ByteBuffer buffer = ByteBuffer.allocate(8192);
        while (true) {

            int bytesRead = channel.read(buffer);

            if (bytesRead == -1) {
                throw new IOException("Server closed the connection");
            }
            else if(bytesRead > 0){
                buffer.flip();
                channel.close();
                break;
            }
        }

        // Десериализация ответа
        try (ByteArrayInputStream bi = new ByteArrayInputStream(buffer.array());
             ObjectInputStream oi = new ObjectInputStream(bi)) {
            return (Response) oi.readObject();
        }
    }
}