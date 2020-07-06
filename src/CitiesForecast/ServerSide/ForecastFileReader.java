package CitiesForecast.ServerSide;

import CitiesForecast.City;
import CitiesForecast.Forecast;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class ForecastFileReader {
    public static final String DEFAULT_FILE = "forecast.txt";

    private String fileName = DEFAULT_FILE;

    public ForecastFileReader(){
        this(DEFAULT_FILE);
    }

    public ForecastFileReader(String fileName){
        setFileName(fileName);
    }

    /**
     * This function assumes the file has a format of list of forecasts
     * when each forecast consists of 4 lines: city - yesterday - today - tomorrow
     */
    public Collection<Forecast> readForecasts(){
        ArrayList<Forecast> forecasts = new ArrayList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e){ System.out.println("Couldn't open file: " + System.getProperty("user.dir")); return null;  }
        while(scanner.hasNextLine())
            forecasts.add(readForecast(scanner));
        return forecasts;
    }

    private Forecast readForecast(Scanner scanner){
        City city = City.get(scanner.nextLine());
        String yesterday = scanner.nextLine();
        String today = scanner.nextLine();
        String tomorrow = scanner.nextLine();
        return new Forecast(city, yesterday, today, tomorrow);
    }


    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
