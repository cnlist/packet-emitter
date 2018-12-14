package us.cnlist.rzhd.components;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class NscaleClient {

    private String host;
    private int port;
    private String dumpDirectory;
    private Socket socket;

    public NscaleClient(String host, int port) {
        this.host = host;
        this.port = port;
        initSocket();
    }

    private void initSocket() {
        try {
            if (socket != null) {
                this.socket.close();
            }
            this.socket = new Socket(host, port);

        } catch (ConnectException e) {
            System.err.println("Connection error to " + host + ":" + port);
        } catch (UnknownHostException nhe) {
            System.err.println("Unknown host " + host);
        } catch (IOException ioe) {
            System.err.println("IO Error");
        }
    }

    public void sendByteArray(byte[] bytes) {

        System.out.println("received " + bytes.length + " bytes");
        try  {
            socket.getOutputStream().write(bytes);
            System.out.println(bytes.length + " bytes sent.");
            initSocket();
        } catch (Exception io) {
            io.printStackTrace();
        }
    }

}
