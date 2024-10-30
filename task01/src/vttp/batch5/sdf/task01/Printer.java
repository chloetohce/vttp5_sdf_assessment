package vttp.batch5.sdf.task01;

import java.util.List;
import vttp.batch5.sdf.task01.models.BikeEntry;

public class Printer {
    private static final String[] WEATHER = {
        "Clear, Few clouds, Partly cloudy, Partly cloudy", 
        "Mist + Cloudy, Mist + Broken clouds, Mist + Few clouds, Mist",
        "Light Snow, Light Rain + Thunderstorm + Scattered clouds, Light Rain + Scattered clouds",
        "Heavy Rain + Ice Pallets + Thunderstorm + Mist, Snow + Fog"
    };
    private static final String[] POSITION = {"highest", "second highest", "third highest",
         "fourth highest", "fifth highest"};
    private static final String[] HOLIDAY = {"not a holiday", "a holiday"};
    private final List<BikeEntry> data;

    public Printer(List<BikeEntry> data) {
        this.data = data.stream()
            .sorted((a,b) -> {
                int totalA = a.getCasual() + a.getRegistered();
                int totalB = b.getCasual() + b.getRegistered();
                return totalB - totalA; //In descending order
            })
            .toList();
    }

    private String template(int pos, BikeEntry e) {
        String position = POSITION[pos];
        String season = Utilities.toSeason(e.getSeason());
        String day = Utilities.toWeekday(e.getWeekday());
        String month = Utilities.toMonth(e.getMonth());
        int total = e.getCasual() + e.getRegistered();
        String weather = WEATHER[e.getWeather() - 1];
        String holiday = HOLIDAY[e.isHoliday() ? 1 : 0];

        return String.format("""
                             The %s recorded number of cyclists was in %s, on a %s in the month of %s.
                             There were a total of %d cyclists. The weather was %s.
                             %s was %s.""", 
            position, season, day, month, total, weather, day, holiday);
    }

    public void print(int num) {
        for (int i = 0; i < num; i++) {
            System.out.println(template(i, data.get(i)));
            System.out.println();
        }
    }
}
