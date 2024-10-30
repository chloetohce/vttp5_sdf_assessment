package vttp.batch5.sdf.task01;

public class Values {
    public static final String[] WEATHER = {
        "Clear, Few clouds, Partly cloudy, Partly cloudy", 
        "Mist + Cloudy, Mist + Broken clouds, Mist + Few clouds, Mist",
        "Light Snow, Light Rain + Thunderstorm + Scattered clouds, Light Rain + Scattered clouds",
        "Heavy Rain + Ice Pallets + Thunderstorm + Mist, Snow + Fog"
    };
    public static final String[] POSITION = {"highest", "second highest", "third highest",
         "fourth highest", "fifth highest", "sixth highest", "seventh highest", "eighth highest",
        "ninth highest", "tenth highest"};
    public static final String[] HOLIDAY = {"not a holiday", "a holiday"};

    public static String toWeather(int weather) {
        return switch (weather) {
            case 1, 2, 3, 4 -> WEATHER[weather - 1];
            default -> "invalid weather";
        };
    }

    public static String toPosition(int pos) {
        return switch (pos) {
            case 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 -> POSITION[pos];
            default -> "no position";
        };
    }

    public static String toHoliday(boolean holiday) {
        return HOLIDAY[holiday ? 1 : 0];
    }
}
