package vttp.batch5.sdf.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import vttp.batch5.sdf.task01.models.BikeEntry;

public class CSVReader {
    
    public static List<BikeEntry> readFile(File f) throws IOException {
        BufferedReader r = new BufferedReader(new FileReader(f)); // TO DO: Error handling here
        String line = r.readLine();
        List<BikeEntry> data = new ArrayList<>();
        while ((line = r.readLine()) != null) {
            line = line.trim();
            data.add(BikeEntry.toBikeEntry(line.split(",")));
        }
        r.close();
        return data;
    }

}
