import java.io.*;
import java.util.*;
import java.text.*;

public class comparator {
	BufferedReader input;
	BufferedReader fr;

	public void comparator (BufferedReader input, BufferedReader file) { 
// class to compare Scratch project text summaries
		BufferedReader in = input; 
		BufferedReader f = file;
// declare readers for both the user input file and the files in the predetermined directory
	}

	public static void main (String[] args) throws java.io.FileNotFoundException, java.io.IOException { 
		comparator c = new comparator();
		String incurrentline; // string for current line of input
		String[] insprites; // hold array once input line is split
		int innumsprites; // hold number of sprites in input project
		String fcurrentline; // string for current line of file from directory
		String[] fsprites; // hold array once file line is split
		int fnumsprites; // hold number of sprites in file project
		int diff; // difference in number of sprites between input and file
		double score = 0; // score of each file
		String fname; // string for file name
		
		File myDir = new File(System.getProperty("user.dir")); // get all text files from directory and put in array
    		FilenameFilter text = new Filter("", "txt");
		File[] summaries = myDir.listFiles(text);
		for (File f : summaries) { // loop through array of files
			score = 0; // reset score for each file
			c.input =  new BufferedReader(new FileReader(args[0])); // reset file reader for each file
			do {
				incurrentline = c.input.readLine();
			} while (!incurrentline.equals("Totals: "));
		// Get to "Totals: " line to retrieve number of sprites in input project
			incurrentline = c.input.readLine();
			insprites = incurrentline.split("\\s+");
			innumsprites = Integer.parseInt(insprites[2]) + 1; // parse the number of sprites from string to int

			c.fr =  new BufferedReader(new FileReader(f));
			fname = c.fr.readLine();
			do {
				fcurrentline = c.fr.readLine();
			} while (!fcurrentline.equals("Totals: "));
		// Get to "Totals: " line to retrieve number of sprites in file project
			fcurrentline = c.fr.readLine();
			fsprites = fcurrentline.split("\\s+");
			fnumsprites = Integer.parseInt(fsprites[2]) + 1; // parse the number of sprites from string to int
			diff = Math.abs(innumsprites - fnumsprites); // get difference in number of sprites
			if (innumsprites <= fnumsprites) { 
		// check for smaller number of sprites to determine number of comparisons
				for (int i = 0; i < innumsprites; i++) {
					score = score + c.Sprites(); // calculate score for each sprites and total
				}
			}
			else {
				for (int i = 0; i < fnumsprites; i++) {
					score = score + c.Sprites(); // calculate score for each sprites and total
				}
			}
			score = score - diff; // subtract difference from total score
			if (score >= 10) {
				System.out.println(fname + " ----- Score: " + score);
			}
		}
		System.out.println("The given file is similar to the listed files.");
	}

	public double Sprites () throws java.io.IOException {
		String currentline; // string for current line of input
		String[] current; // hold array after current line is split
		String fcurrentline; // string for current line of file
		String[] fcurrent; // hold array after current line of file is split
		double score; // score of sprite
		int c, so, st; // scores for costumes, sounds, and stacks
		do {
			currentline = input.readLine();
			//System.out.println(currentline);
			current = currentline.split("\\s+");
		} while (!current[0].equals("Sprite:")); // find line "Sprite: " to begin costume scoring
		do {
			fcurrentline = fr.readLine();
			//System.out.println(fcurrentline);
			fcurrent = fcurrentline.split("\\s+");
		} while (!fcurrent[0].equals("Sprite:")); // find line "Sprite: " to begin costume scoring
		c = Costumes(); // score costumes or backgrounds
		so = Sounds(); // score sounds 
		st = Stacks(); // score stacks
		score = 0.2*c + 0.2*so + 0.6*st; // total score with weights given to each element	
		return score;	
	}

	public int Costumes () throws java.io.IOException {
		int inc; // number of costumes in input project
		int fc; // number of costumes in file project
		int diff; // difference in number of costumes
		int score = 0; // costumes score
		String currentline; // string to hold current line of input
		String[] current; // array to hold strings after current line is split
		String fcurrentline; // string to hold current line of file
		String[] fcurrent; // array to hold strings after current line of file is split
		do {
			currentline = input.readLine();
			//System.out.println(currentline);
			current = currentline.split("\\s+");
		} while (!current[1].equals("Costumes")); // find "Costumes" line to retrieve number of costumes
		do {
			fcurrentline = fr.readLine();
			//System.out.println(fcurrentline);
			fcurrent = fcurrentline.split("\\s+");
		} while (!fcurrent[1].equals("Costumes")); // find "Costumes" line to retrieve number of costumes
		StringTokenizer currentinput = new StringTokenizer(current[2], "():");
		StringTokenizer currentf = new StringTokenizer(fcurrent[2], "():");
		inc = Integer.parseInt(currentinput.nextToken()); // parse number of costumes from string to int
		fc = Integer.parseInt(currentf.nextToken()); // parse number of costumes from string to int
		diff = Math.abs(inc - fc); // difference between number of costumes
		StringTokenizer currentres; // parse input costume resolution
		StringTokenizer fcurrentres; // parse file costume resolution
		int cres1, cres2, fres1, fres2; // integers to hold costume resolutions
		if (inc <= fc) {
			for (int i = 0; i < inc; i++) {
		// find smallest number of costumes to get number of comparisons then loop through costumes getting the 		// resolutions, compare the resolutions and add to the score if they match
				currentline = input.readLine();
				fcurrentline = fr.readLine();
				current = currentline.split("\\s+");
				fcurrent = fcurrentline.split("\\s+");
				currentres = new StringTokenizer(current[2], "(x)");
				fcurrentres = new StringTokenizer(fcurrent[2], "(x)");
				cres1 = Integer.parseInt(currentres.nextToken());
				cres2 = Integer.parseInt(currentres.nextToken());
				fres1 = Integer.parseInt(fcurrentres.nextToken());
				fres2 = Integer.parseInt(fcurrentres.nextToken());
				if ((cres1 == fres1) && (cres2 == fres2))
					score = score + 1;	
			}
		}
		else {
			for (int i = 0; i < fc; i++) {
				currentline = input.readLine();
				fcurrentline = fr.readLine();
				current = currentline.split("\\s+");
				fcurrent = fcurrentline.split("\\s+");
				currentres = new StringTokenizer(current[2], "(x)");
				fcurrentres = new StringTokenizer(fcurrent[2], "(x)");
				cres1 = Integer.parseInt(currentres.nextToken());
				cres2 = Integer.parseInt(currentres.nextToken());
				fres1 = Integer.parseInt(fcurrentres.nextToken());
				fres2 = Integer.parseInt(fcurrentres.nextToken());
				if ((cres1 == fres1) && (cres2 == fres2))
					score = score + 1;	
			}
		}
		score = score - diff; // subtract the difference from the total score
		return score;
	}

	public int Sounds () throws java.io.IOException {
		int ins; // input number of sounds 
		int fs; // file number of sounds
		int diff; // difference in number of sounds
		int score = 0; // score for sounds
		String currentline; // current line of input
		String[] current; // array to hold strings when current line of input is split
		String fcurrentline; // current line of file
		String[] fcurrent; // array to hold strings when current line of file is split
		do {
			currentline = input.readLine();
			//System.out.println(currentline);
			current = currentline.split("\\s+");
		} while (!current[1].equals("Sounds")); // find "Sounds" line to retrieve number of sounds
		do {
			fcurrentline = fr.readLine();
			//System.out.println(fcurrentline);
			fcurrent = fcurrentline.split("\\s+");
		} while (!fcurrent[1].equals("Sounds")); // find "Sounds" line to retrieve number of sounds		
		StringTokenizer currentinput = new StringTokenizer(current[2], "():");
		StringTokenizer currentf = new StringTokenizer(fcurrent[2], "():");
		ins = Integer.parseInt(currentinput.nextToken()); // parse number of sounds from string to integer
		fs = Integer.parseInt(currentf.nextToken()); // parse number of sounds from string to integer
		diff = Math.abs(ins - fs); // difference between number of sounds in input and file
		StringTokenizer currents;
		StringTokenizer fcurrents;
		DateFormat time = new SimpleDateFormat("h:mm:ss"); // time format to parse times to compare sounds
		Date cst = new Date(0, 0, 0, 0, 0, 0); // initialize date variables input and file sound times
		Date fst = new Date(0, 0, 0, 0, 0, 0);
		if (ins <= fs) {
			// find smallest number of costumes to get number of comparisons then loop through costumes getting the 			// times, compare the times and add to the score if they match
			for (int i = 0; i < ins; i++) {
				currentline = input.readLine();
				fcurrentline = fr.readLine();
				current = currentline.split("\\s+");
				fcurrent = fcurrentline.split("\\s+");
				currents = new StringTokenizer(current[2], "()");
				fcurrents = new StringTokenizer(fcurrent[2], "()");
				try { 
					cst = time.parse(currents.nextToken());
					fst = time.parse(fcurrents.nextToken());
				} catch (Exception e)
				{
					e.printStackTrace();
				}
				if (cst.equals(fst))
					score = score + 1;	
			}
		}
		else {
			for (int i = 0; i < fs; i++) {
				currentline = input.readLine();
				fcurrentline = fr.readLine();
				current = currentline.split("\\s+");
				fcurrent = fcurrentline.split("\\s+");
				currents = new StringTokenizer(current[2], "()");
				fcurrents = new StringTokenizer(fcurrent[2], "()");
				try { 
					cst = time.parse(currents.nextToken());
					fst = time.parse(fcurrents.nextToken());
				} catch (Exception e)
				{
					e.printStackTrace();
				}
				if (cst.equals(fst))
					score = score + 1;
			}
		}
		score = score - diff; // subtract difference from total sound score
		return score;
	}
	
	public int Stacks () throws java.io.IOException {
		int ins; // number of stacks in input project
		int fs; // number of stacks in file project
		int diff; // difference between number of stacks 
		int score = 0; // total project score
		String currentline; // string for current line of input 
		String[] current; // array to hold strings when current line of input is split
		String fcurrentline; // string for current line of file
		String[] fcurrent; // array to hold strings when current line of file is split
		do {
			currentline = input.readLine();
			//System.out.println(currentline);
			current = currentline.split("\\s+");
			//System.out.println(current[1]);
		} while (!current[1].equals("Stacks") && !current[1].equals("No")); 
		// find "Sounds" line to retrieve number of sounds
		do {
			fcurrentline = fr.readLine();
			//System.out.println(fcurrentline);
			fcurrent = fcurrentline.split("\\s+");
		} while (!fcurrent[1].equals("Stacks") && !fcurrent[1].equals("No"));
		// find "Sounds" line to retrieve number of sounds
		if (current[1].equals("No") || fcurrent[1].equals("No")) {
		// if no stacks in either input or file, do no comparisons  
			if(current[1].equals(fcurrent[1])) // check if both are equal and add to the score if they are
				score = score + 5;
		}
		else { // otherwise retrieve number of stacks
			StringTokenizer currentinput = new StringTokenizer(current[2], "():");
			StringTokenizer currentf = new StringTokenizer(current[2], "():");
			ins = Integer.parseInt(currentinput.nextToken()); // parse number of stacks from string to integer
			fs = Integer.parseInt(currentf.nextToken()); // parse number of stacks from string to integer
			diff = Math.abs(ins - fs); // difference between number of stacks
			if (ins <= fs) { // get smallest number of stacks to determine number of comparisons
				for (int i = 0; i < ins; i++) { // loop through each stack
					do { // loop through each line
						currentline = input.readLine(); // read in input line
						fcurrentline = fr.readLine(); // read in file line
						current = currentline.split("\\s+"); // split input line
						fcurrent = fcurrentline.split("\\s+"); // split file line
						if (current.length <= fcurrent.length) { 
						// compare length of file and input lines to determine number of comparisons
							for (int n = 0; n < current.length; n++) { // loop through words in line
								if (current[n].equals(fcurrent[n])) {
									score = score + 1;} // add to score if equal
							}
						}
						else {
							for (int n = 0; n < fcurrent.length; n++) { // loop through words in line
								if (current[n].equals(fcurrent[n])) {
									score = score + 1;} // add to score if equal
							}
						}
					} while (current[0].equals("end") || fcurrent[0].equals("end")); 
					// stack ends with "end"
					
				}
			}
			else {
				for (int i = 0; i < fs; i++) { // loop through each stack
					do { // loop through each line
						currentline = input.readLine(); // read in input line
						fcurrentline = fr.readLine(); // read in file line
						current = currentline.split("\\s+"); // split input line
						fcurrent = fcurrentline.split("\\s+"); // split file line
						if (current.length <= fcurrent.length) {
						// compare length of file and input lines to determine number of comparisons 
							for (int n = 0; n < current.length; n++) { // loop through words in line
								if (current[n].equals(fcurrent[n])) {
									score = score + 1;} // add to score if equal
							}
						}
						else {
							for (int n = 0; n < fcurrent.length; n++) { // loop through words in line
								if (current[n].equals(fcurrent[n])) {
									score = score + 1;} // add to score if equal
							}
						}
					} while (current[0].equals("end") || fcurrent[0].equals("end"));
					// stack ends with "end"
				}
			}
			score = score - diff; // subtract difference from stack score
		}
		return score;
	}
}
