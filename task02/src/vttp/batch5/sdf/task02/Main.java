package vttp.batch5.sdf.task02;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.err.println("No file input. ");
			System.err.println("Usage: java -cp classes vttp.batch5.sdf.task02.Main <file path>");
			System.exit(-1);
		}

		File f = new File(args[0]);
		
		try {
			Board b = Board.of(f);
			System.out.println();
			System.out.println("Processing: " + f.getPath());
			System.out.println();
			System.out.println("Board: ");
			b.printBoard();
			
			System.out.println();
			System.out.println("------------------------------");
			UtilityEval utility = new UtilityEval(b);
			utility.printUtility();

		} catch (IOException e) {
			System.err.println("File not found. Try again.");
			System.exit(-1);
		}


	}
}
