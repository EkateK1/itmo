package org.example;

import org.example.managers.Runner;
import org.example.network.UDPClient;
import org.example.utility.ConsoleManager;
import org.example.utility.HostStorage;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
    public static int PORT;
    public static String HOST;

    public static void main(String[] args) throws UnknownHostException{

        ConsoleManager console = new ConsoleManager();

        while (true){
            console.println("Выберите порт");
            String port = console.readln().trim();
            if (port.matches("\\d+")) {
                PORT = Integer.parseInt(port);
                break;
            } else{
                console.printError("Порт должен быть числом");
            }
        }

        HostStorage.addInetSocketAddress(HostStorage.createInetAddress("localhost"), PORT);
        HostStorage.addInetSocketAddress(HostStorage.createInetAddress("helios.cs.ifmo.ru"), PORT);
        //HostStorage.addInetSocketAddress(HostStorage.createInetAddress("LAPTOP-LKGEIB5V"), PORT);

        while (true){
            console.println("Выберите хост из предложенных: \n" + HostStorage.printAvailableAddresses());
            String host = console.readln().trim();
            if (HostStorage.contains(host)) {
                HOST = host;
                break;
            }
        }


        try{
            DatagramSocket socket = new DatagramSocket();
            UDPClient client = new UDPClient(HostStorage.getByName(HOST), socket);

            Runner runner = new Runner(console, client);
            runner.commandGetting();
        } catch (SocketException e) {
            console.printError("Ошибка при подключении к серверу");
        }
    }
}