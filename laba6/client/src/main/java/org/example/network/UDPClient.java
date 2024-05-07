package org.example.network;

import org.example.network.Request;
import org.example.network.Response;
import org.example.network.Serialisation;
import org.example.utility.Status;

import java.io.IOException;
import java.net.*;

public class UDPClient {
    private final int PACKET_SIZE = 1024*1024;
    private final int TIMEOUT = 100000;
    private final DatagramSocket socket;
    private final SocketAddress address;

    public UDPClient(SocketAddress address, DatagramSocket socket) throws SocketException {
        this.socket = socket;
        this.address = address;
        this.socket.setSoTimeout(TIMEOUT);
        System.out.println("Клиент подключен к " + address);
    }

    public void sendData(Request request) throws IOException {
        byte[] data = Serialisation.serializeObject(request);
        DatagramPacket packet = new DatagramPacket(data, data.length, address);
        socket.send(packet);
    }

    public Response readData() throws IOException, ClassNotFoundException {
        try {
        byte[] buffer = new byte[PACKET_SIZE];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        Response response = (Response) Serialisation.deserializeObject(packet.getData());
        return response;
        } catch (SocketTimeoutException e){
            return new Response(Status.EXIT, "Истекло время ожидания ответа от сервера.");
        }
    }

}
