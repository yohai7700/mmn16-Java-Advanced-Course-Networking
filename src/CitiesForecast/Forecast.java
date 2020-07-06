package CitiesForecast;

/**
 * Represents a forecast for a city, for yesterday, today and tomorrow in strings.
 * The strings should not contain SPLIT_CHAR.
 */
public class Forecast {
    public static final char SPLIT_CHAR = '~';

    private final City city;
    private final String yesterday, today, tomorrow;

    public Forecast(City city, String yesterday, String today, String tomorrow) {
        this.city = city;
        this.yesterday = yesterday;
        this.today = today;
        this.tomorrow = tomorrow;
    }

    public Forecast(City city, String forecastsJoined){
        this.city = city;
        String[] forecasts =  forecastsJoined.split(SPLIT_CHAR +"");
        this.yesterday = forecasts[0];
        this.today = forecasts[1];
        this.tomorrow = forecasts[2];
    }

    public City getCity() {
        return city;
    }

    public String getYesterday() {
        return yesterday;
    }

    public String getToday() {
        return today;
    }

    public String getTomorrow() {
        return tomorrow;
    }

    //joining the forecasts to one string, for example, for transporting over UDP.
    public String joinToString(){
        return yesterday + SPLIT_CHAR + today + SPLIT_CHAR + tomorrow;
    }
}
