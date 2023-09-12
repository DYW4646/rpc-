package org.example.utils.Zookeeper;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * Created with IntelliJ IDEA.
 * PackageName: org.example.utils.Zookeeper
 *
 * @author: YW
 * @description:
 * @data: 2023/9/12 11:26
 */
public class NetUtils {
    public static String findLocalIP() {
        String ipAddress = null;
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    if (!address.isLoopbackAddress() && address.isSiteLocalAddress()) {
                        ipAddress = address.getHostAddress();
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ipAddress;
    }

    public static void main(String[] args) {
        String localIP = findLocalIP();
        System.out.println("Local IP Address: " + localIP);
    }






}
