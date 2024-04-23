package org.example;

import org.example.commands.available.SaveCommand;
import org.example.managers.CommandExecute;
import org.example.managers.CommandManager;
import org.example.network.Request;
import org.example.network.Response;
import org.example.network.Serialisation;
import org.example.utility.ConsoleManager;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPServer {

    private final int PACKET_SIZE = 1024*1024;
    private final DatagramChannel channel;
    private InetAddress host;
    private final SocketAddress address;
    private final Logger logger;
    private final ConsoleManager console;
    private final CommandManager commandManager;
    private Selector selector;
    private SocketAddress addr;
    public UDPServer(InetAddress host, int port, Logger logger, ConsoleManager console, CommandManager commandManager) throws IOException {
        this.host = host;
        this.logger = logger;
        this.address = new InetSocketAddress(host, port);
        this.channel = DatagramChannel.open().bind(this.address);
        this.channel.configureBlocking(false);
        this.console = console;
        this.commandManager = commandManager;
        this.logger.log(Level.INFO, "DatagramChannel подключен к " + address);
    }

    public void sendData (Response response) throws IOException {
        byte[] serializedMessage = Serialisation.serializeObject(response);
        ByteBuffer buf = ByteBuffer.wrap(serializedMessage);
        channel.send(buf, addr);
        buf.clear();
        this.logger.log(Level.INFO, "Отправлены данные на " + addr);
    }

    public Request receiveData() throws IOException {
        ByteBuffer receiveBuf = ByteBuffer.allocate(65000);
        SocketAddress address = null;
        while (address == null) {
            address = channel.receive(receiveBuf);
        }

        receiveBuf.flip();
        byte[] bytes = new byte[receiveBuf.remaining()];
        receiveBuf.get(bytes);

        byte[] toDeserialize = receiveBuf.array();
        Request request = null;

        try {
            request = (Request) Serialisation.deserializeObject(toDeserialize);
        } catch (ClassNotFoundException ex) {
            console.printError(String.valueOf(ex));
        }
        addr = address;
        this.logger.log(Level.INFO, "Получен запрос от " + address);
        return request;
    }

    public void run() throws IOException {
        logger.log(Level.INFO, "Сервер начал работу");
        boolean running = true;
        while (running) {
            Request request = null;
            try {
                request = receiveData();
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Ошибка получения данных");
            }

            Response response = null;
            try {
                if (request != null){
                    CommandExecute commandExecute = new CommandExecute(request, commandManager);
                    response = commandExecute.executeCommand(request);
                    sendData(response);}
            } catch (IOException e){
                logger.log(Level.SEVERE, "Ошибка отправки данных");
            }

            if (request.getCommand().equals("exit")){
                running = false;
                logger.log(Level.INFO, "Сервер завершает работу");
            }

        }
    }

}