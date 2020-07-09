package CitiesForecast.ClientSide;

import CitiesForecast.City;
import CitiesForecast.Forecast;

import java.io.IOException;
import java.net.*;

/**
 * A client that can request forecast from server on UDP
 * and get forecasts. only returns the forecasts, doesn't know
 * how to use it.
 */
public class Client {
    public static final String SERVER_NAME = "localhost";
    public static final int SERVER_PORT = 7777;
    private static final int DATA_SIZE = 1024;

    private final DatagramSocket socket;
    private String serverName;
    private int serverPort;

    public Client() throws SocketException {
        socket = new DatagramSocket();
        serverName = SERVER_NAME;
        serverPort = SERVER_PORT;
    }

    public Forecast getForecast(City city){
        if(city == null) return null;
        try {
            sendToServer(city);
            return getFromServer(city);
        }catch (Exception ignored){}
        return null;
    }

    private void sendToServer(City city) throws UnknownHostException, IOException {
        //sending request to server
        InetAddress address = InetAddress.getByName(serverName);
        byte[] data;
        data = city.toString().getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, address, SERVER_PORT);
        socket.send(packet);
    }

    private Forecast getFromServer(City city) throws UnknownHostException, IOException{
        //reading "encrypted" forecast
        byte[] data = new byte[DATA_SIZE];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        socket.receive(packet);
        //receiving the joined forecast yesterday~today~tomorrow
        String joinedForecast = new String(packet.getData()).substring(0, packet.getLength());
        return new Forecast(city, joinedForecast);
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }
}
