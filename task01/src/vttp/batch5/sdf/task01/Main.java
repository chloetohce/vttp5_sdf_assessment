package vttp.batch5.sdf.task01;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.List;
import vttp.batch5.sdf.task01.models.BikeEntry;


public class Main {

	public static void main(String[] args) throws IOException {

		if (args.length == 0) {
			System.err.println("No file name provided. Please provide a file name");
			System.err.println("Usage: java -cp classes vttp.batch5.sdf.task01.Main day.csv");
			System.exit(-1);
		}

		File f = new File(args[0]);
		Console cons = System.console();
		while (!f.exists()) {
			System.out.println("File does not exist. ");
			String input = cons.readLine("Enter a file: ");
			f = new File(input.trim());
		}

		List<BikeEntry> list = CSVReader.readFile(f);
		Printer p = new Printer(list);
		p.print(5);
	}
}
