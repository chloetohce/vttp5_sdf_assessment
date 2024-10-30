package vttp.batch5.sdf.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import vttp.batch5.sdf.task01.models.BikeEntry;

public class CSVReader {
    
    public static List<BikeEntry> readFile(File f) {
        List<BikeEntry> data = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new FileReader(f))) {
            String line = r.readLine();
            while ((line = r.readLine()) != null) {
                line = line.trim();
                data.add(BikeEntry.toBikeEntry(line.split(",")));
            }

        } catch (IOException e) {
            System.err.println("Error reading file.");
            System.exit(-1);
        }
        return data;
    }

}
