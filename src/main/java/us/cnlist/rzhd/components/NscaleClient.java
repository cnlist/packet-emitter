package us.cnlist.rzhd.components;

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
        try {
            this.socket = new Socket(host, port);

        } catch (ConnectException e) {
            System.err.println("Connection error to " + host + ":" + port);
        } catch (UnknownHostException nhe) {
            System.err.println("Unknown host " + host);
        } catch (IOException ioe) {
            System.err.println("IO Error");
        }
    }

}
