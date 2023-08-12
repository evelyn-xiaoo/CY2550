package project3;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class xkcdpwgen {
	
	public static void main(String[] args) throws IOException {
		
		
		File fr = new File("/Users/evelynxiao/Desktop/northeastern/cy2550/xkcdpwgen/project3/src/words.txt");
        List<String> loWords = new ArrayList<>();
        int numWords = 4;
        
        // creates initial password (default)
        for (int j = 0; j < 4; j++) {
        	
			//uses the choose method to choose a random word from words.txt
	        String newWord = choose(fr);
	        newWord = newWord.toLowerCase();
	        System.out.println(newWord);
	        //adds new word to loWords
        	loWords.add(newWord);
		}
        
        

		for (int i = 0; i < args.length; i++) {
			
			
			// if -h is called
			if (args[i].contains("--help") || args[i].contains("-h")) {
				System.out.println("usage: xkcdpwgen [-h] [-w WORDS] [-c CAPS] [-n NUMBERS] [-s SYMBOLS]");
				System.out.println("Generate a secure, memorable password using the XKCD method");
				System.out.println("optional arguments:");
				System.out.println("-h, --help            show this help message and exit\n"
						+ "-w WORDS, --words WORDS\n"
						+ "          include WORDS words in the password (default=4)\n"
						+ "	-c CAPS, --caps CAPS  capitalize the first letter of CAPS random words\n"
						+ "			 (default=0)\n"
						+ "-n NUMBERS, --numbers NUMBERS\n"
						+ "			insert NUMBERS random numbers in the password\n"
						+ "			(default=0)\n"
						+ "-s SYMBOLS, --symbols SYMBOLS\n"
						+ "			insert SYMBOLS random symbols in the password\n"
						+ "			(default=0)");
			}
			
			
			// if -w is called, number of words is updated
			if (args[i].contains("--words") || args[i].contains("-w")) {
				// checks if number of words is less than original
				for (int j = numWords; j > Integer.parseInt(args[i+1]); j--) {
					loWords.remove(0);
					numWords = loWords.size();
				}
				
				//makes a password with the number of words indicated
				for (int j = loWords.size(); j < Integer.parseInt(args[i+1]); j++) {
					//uses the choose method to choose a random word from words.txt
		        	String newWord = choose(fr);
		        	//adds new word to loWords
		        	loWords.add(newWord);
				}
				numWords = Integer.parseInt(args[i+1]);
			}
			
			
			
			// if -c is called 
			// error if number of wanted caps is larger than the number of words
			if ((args[i].contains("--caps") || args[i].contains("-c")) &&
					(Integer.parseInt(args[i+1]) > numWords)) {
				throw new Error();
			}
			if (args[i].contains("--caps") || args[i].contains("-c")) {
				for (int j = 0; j < Integer.parseInt(args[i+1]); j++) {
					//chooses a random word
					Random rand = new Random();
					int index = rand.nextInt(loWords.size()-1);
					String w = loWords.get(index);
					if (! isCaps(w.charAt(0))) {
						String caps = w.substring(0, 1).toUpperCase();
						String rest = w.substring(1);
						w = caps.concat(rest);
						loWords.remove(index);
						loWords.add(index, w);
					}
					else {
						j--;
					}
					
				}
			} 
			
			
			// if -n is called
			if (args[i].contains("--numbers") || args[i].contains("-n")) {
				for (int j = 0; j < Integer.parseInt(args[i+1]); j++) {
					// random number between 0-9
					Random randNum = new Random();
					int num = randNum.nextInt(9);
					String stringNum = ("" + num);
					
					// helps decide if the number will go infront or behind a word
					Random randPlace = new Random();
					int place = randPlace.nextInt(1);
					
					// chooses a random word
					Random randWord = new Random();
					int wordIndex = randWord.nextInt(loWords.size()-1);
					
					// number in before word in list
					if (place % 2 == 0) {
						loWords.add(wordIndex, stringNum);
					}
					
					// number after word
					else {
						loWords.add(wordIndex + 1, stringNum);
					}
				}
			}
			
			
			// if -s is called
			if (args[i].contains("--symbols") || args[i].contains("-s")) {
				String syms = "~!@#$%^&*.:;";
				for (int j = 0; j < Integer.parseInt(args[i+1]); j++) {
					// chooses random symbol
					Random randNum = new Random();
					int num = randNum.nextInt(syms.length()-2);
					String symbol = syms.substring(num, num + 1);
					
					// helps decide if the symbol will go infront or behind a word
					Random randPlace = new Random();
					int place = randPlace.nextInt(1);
					
					// chooses a random word
					Random randWord = new Random();
					int wordIndex = randWord.nextInt(loWords.size()-1);
					
					// number in beginning of word
					if (place % 2 == 0) {
						loWords.add(wordIndex, symbol);
					}
					
					// number after word
					else {
						loWords.add(wordIndex + 1, symbol);
					}
				}
			}
			
		}
		
		// creates password
		String pw = "";
		for (int i = 0; i < loWords.size(); i++) {
			String word = loWords.get(i);
			pw = pw.concat(word);
			pw = pw.concat(" ");
		}
		System.out.println(pw);
	}
	
	// randomly chooses a line from the given file
	public static String choose(File f) throws IOException {
		
		     String result = null;
		     Random rand = new Random();
		     int n = 1;
		     for(Scanner sc = new Scanner(f); sc.hasNext(); )
		     {
		        ++n;
		        String line = sc.nextLine();
		        if(rand.nextInt(n) == 0)
		           result = line;         
		     }

		     return result;      
		  
	}

	
	// checks if the given character is capitalized
	public static boolean isCaps(char c) {
		if (c>= 'A'  && c <= 'Z') {
			return true;
		}
	 
	    else {
	        return false;
	    }
		
	}
	

}
	




