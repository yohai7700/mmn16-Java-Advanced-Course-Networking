package CitiesForecast.ServerSide;

import CitiesForecast.Forecast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Collection;

public class Server extends Thread{
    public static final int PORT = 7777;
    private static final int REQUEST_SIZE = 1024/*bytes*/;

    private DatagramSocket socket = null;
    private ForecastFileReader fileReader;

    public Server() throws SocketException{
        fileReader = new ForecastFileReader();
        socket= new DatagramSocket(PORT);
    }

    @Override
    public void run() {
        super.run();
        while(true)
            handleRequest();
    }

    private void handleRequest(){
        try {
            //get request
            byte[] bytes = new byte[REQUEST_SIZE];
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
            socket.receive(packet);
            String city = new String(packet.getData()).substring(0, packet.getLength());
            int clientPort = packet.getPort();
            InetAddress inetAddress = packet.getAddress();
            String joined = getForecast(city).joinToString();
            packet = new DatagramPacket(joined.getBytes(), joined.getBytes().length, inetAddress, clientPort);
            socket.send(packet);
        } catch (IOException exception) {
            socket.close();
        }
    }

    private Forecast getForecast(String city){
        Collection<Forecast> forecasts = fileReader.readForecasts();
        if(forecasts == null) return null;
        for(Forecast forecast: forecasts)
            if(forecast.getCity().toString().equals(city))
                return forecast;
        return null;
    }

    public void setFile(String fileName) {
        fileReader = new ForecastFileReader(fileName);
    }
}
