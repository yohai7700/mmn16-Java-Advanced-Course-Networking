package CitiesForecast.ClientSide;

import CitiesForecast.City;
import CitiesForecast.Forecast;

import java.io.IOException;
import java.net.*;

public class Client {
    public static final String SERVER_NAME = "localhost";
    public static final int SERVER_PORT = 7777;
    private static final int DATA_SIZE = 1024;

    private final DatagramSocket socket;

    public Client() throws SocketException {
        socket = new DatagramSocket();
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
        InetAddress address = InetAddress.getByName(SERVER_NAME);
        byte[] data = new byte[DATA_SIZE];
        data = city.toString().getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length, address, SERVER_PORT);
        socket.send(packet);
    }

    private Forecast getFromServer(City city) throws UnknownHostException, IOException{
        byte[] data = new byte[DATA_SIZE];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        socket.receive(packet);
        //receiving the joined forecast yesterday~today~tomorrow
        String joinedForecast = new String(packet.getData()).substring(0, packet.getLength());
        return new Forecast(city, joinedForecast);
    }
}
