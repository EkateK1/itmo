package org.example.utility;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class HostStorage {
    private static final ArrayList<InetSocketAddress> hosts = new ArrayList<>();

    public static void addInetSocketAddress(InetAddress host, int port) {
        InetSocketAddress address = new InetSocketAddress(host, port);
        hosts.add(address);
    }

    public static InetAddress createInetAddress(String host) throws UnknownHostException {
        return InetAddress.getByName(host);
    }

    public static InetSocketAddress getByName(String host) throws RuntimeException {
        for (InetSocketAddress address : hosts) {
            if (address.getHostName().equals(host) || address.getAddress().equals(host))
                return address;
        }
        throw new RuntimeException("Cannot find host((");
    }

    public static String printAvailableAddresses() {
        StringBuilder result = new StringBuilder();
        for (InetSocketAddress address : hosts) {
            result.append(address.getHostName()).append("\n");
        }
        return result.deleteCharAt(result.length() - 1).toString();
    }

    public static boolean contains(String host){
        for (InetSocketAddress address : hosts){
            if (address.getHostName().equals(host)) return true;
        }
        return false;
    }
}

