package CitiesForecast;

public enum City {
    PARIS,
    JERUSALEM,
    SAN_DIEGO,
    SEATTLE,
    BERLIN,
    TOKYO,
    BEIJING,
    MADRID,
    BARCELONA,
    SAN_FRANCISCO;

    @Override
    public String toString() {
        switch (this){
            case PARIS: return "Paris";
            case TOKYO: return "Tokyo";
            case BERLIN: return "Berlin";
            case MADRID: return "Madrid";
            case BARCELONA: return "Barcelona";
            case BEIJING: return "Beijing";
            case SAN_DIEGO: return "San Diego";
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
