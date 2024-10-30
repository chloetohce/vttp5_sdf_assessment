package vttp.batch5.sdf.task01;

import java.util.Arrays;
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

    private boolean isValidArg(String position, String season, String day, String month, String weather) {
        List<String> positionList = Arrays.asList(Values.POSITION);
        List<String> seasonList = Arrays.asList(Values.SEASON);
        List<String> dayList = Arrays.asList(Values.DAY);
        List<String> monthList = Arrays.asList(Values.MONTH);
        List<String> weatherList = Arrays.asList(Values.WEATHER);

        return positionList.contains(position)
                && seasonList.contains(season)
                && dayList.contains(day)
                && monthList.contains(month)
                && weatherList.contains(weather);
    }

    public void print(int num) {
        for (int pos = 0; pos < num; pos++) {
            BikeEntry e = data.get(pos);

            String position = Values.toPosition(pos);
            String season = Values.toSeason(e.getSeason());
            String day = Values.toWeekday(e.getWeekday()); // TODO: Utilities.toWeekday() mapping int -> weekday is wrong. 6 -> Saturday, not Friday
            String month = Values.toMonth(e.getMonth());
            int total = e.getCasual() + e.getRegistered();
            String weather = Values.toWeather(e.getWeather());
            String holiday = Values.toHoliday(e.isHoliday());

            if (!isValidArg(position, season, day, month, weather)) {
                System.out.println("Invalid arguments found in dataset");
                System.out.println("Data entry with issue is: ");
                System.out.printf(
                    """
                        Position: %s
                        Season: %s
                        Day: %s
                        Month: %s
                        Total: %s
                        Weather: %s
                        Holiday: %s
                        """, 
                    position, season, day, holiday, month, total, weather, holiday);
                break;
            }

            System.out.printf(
                    """
                            The %s (position) recorded number of cyclists was in %s (season), on a %s (day) in the month of %s (month).
                            There were a total of %d (total) cyclists. The weather was %s (weather).
                            %s (day) was %s.\n""",
                    position, season, day, month, total, weather, day, holiday);
            System.out.println();
        }
    }
}
