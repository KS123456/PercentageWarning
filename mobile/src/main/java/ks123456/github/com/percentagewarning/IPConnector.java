package ks123456.github.com.percentagewarning;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by koens on 13-3-2016.
 */
public class IPConnector {
    private Socket socket;

    public static final int SERVERPORT = 5000;
    public String IP_ADRESS;
    public void IPConnect(String IP) {
        IP_ADRESS = IP;
        Thread cThread = new Thread(new ClientThread());
        cThread.start();
    }
    public class ClientThread implements Runnable {
        public void run(){
            try {
                InetAddress serverAddr = InetAddress.getByName(IP_ADRESS);
                Log.d("NewDeviceActivity", "Connecting...");
                Socket socket = new Socket(serverAddr, SERVERPORT);

            }
            catch (Exception e){

            }
        }

    }
}

