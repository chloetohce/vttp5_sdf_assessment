package vttp.batch5.sdf.task01;

public class Values {
    public static final String[] POSITION = {"highest", "second highest", "third highest",
         "fourth highest", "fifth highest", "sixth highest", "seventh highest", "eighth highest",
        "ninth highest", "tenth highest"
    };
    public static final String[] SEASON = { "Spring", "Summer", "Fall", "Winter" };
    public static final String[] DAY = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    public static final String[] MONTH = { "January", "Febuary", "March", "April", "May",
        "June", "July", "August", "September", "October", "November", "December"
    };
    public static final String[] WEATHER = {
        "Clear, Few clouds, Partly cloudy, Partly cloudy", 
        "Mist + Cloudy, Mist + Broken clouds, Mist + Few clouds, Mist",
        "Light Snow, Light Rain + Thunderstorm + Scattered clouds, Light Rain + Scattered clouds",
        "Heavy Rain + Ice Pallets + Thunderstorm + Mist, Snow + Fog"
    };
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

    public static String toWeekday(int weekday) {
        return switch (weekday) {
            case 0, 1, 2, 3, 4, 5, 6 -> DAY[weekday];
            default -> "invalid weekday";
        };
    }

    public static String toSeason(int season) {
        return switch (season) {
            case 1, 2, 3, 4 -> SEASON[season - 1];
            default -> "invalid season";
        };
    }

    public static String toMonth(int month) {
        return switch (month) {
            case 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 -> MONTH[month - 1];
            default -> "invalid month";
        };
    }
}
