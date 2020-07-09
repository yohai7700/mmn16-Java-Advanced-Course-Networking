package CitiesForecast;

/**
 * A given list of cities.
 */
public enum City {
    PARIS,
    JERUSALEM,
    SEATTLE,
    BERLIN,
    TOKYO,
    BARCELONA,
    SAN_FRANCISCO;

    @Override
    public String toString() {
        switch (this){
            case PARIS: return "Paris";
            case TOKYO: return "Tokyo";
            case BERLIN: return "Berlin";
            case BARCELONA: return "Barcelona";
            case SEATTLE: return "Seattle";
            case JERUSALEM: return "Jerusalem";
            case SAN_FRANCISCO: return "San Francisco";
        }
        return null;
    }

    public static City get(String cityName){
        if(cityName == null) return null;
        for(City city: values())
            if(cityName.equals(city.toString()))
                return city;
        return null;
    }
}
