package vttp.batch5.sdf.task01;

import java.util.List;
import vttp.batch5.sdf.task01.models.BikeEntry;

public class Printer {
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
        String position = Values.POSITION[pos];
        String season = Utilities.toSeason(e.getSeason());
        String day = Utilities.toWeekday(e.getWeekday());
        String month = Utilities.toMonth(e.getMonth());
        int total = e.getCasual() + e.getRegistered();
        String weather = Values.WEATHER[e.getWeather() - 1];
        String holiday = Values.HOLIDAY[e.isHoliday() ? 1 : 0];

        return String.format("""
                             The %s (position) recorded number of cyclists was in %s (season), on a %s (day) in the month of %s (month).
                             There were a total of %d (total) cyclists. The weather was %s (weather).
                             %s (day) was %s.""", 
            position, season, day, month, total, weather, day, holiday);
    }

    public void print(int num) {
        for (int i = 0; i < num; i++) {
            System.out.println(template(i, data.get(i)));
            System.out.println();
        }
    }
}
