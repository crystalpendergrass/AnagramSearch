/**
 * Program prints out all anagrams of a specified string that are contained
 * in a given dictionary (aka list of words).
 * 
 * @version Assignment 5, 23 April 2014
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JFileChooser;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class AnagramRunner {
	static boolean TESTING = false;
	static ArrayList<String> wordList;

	public static void main(String[] args) throws FileNotFoundException{
		JOptionPane.showMessageDialog(null,	"Locate a word list to load into the dictionary.", "LOCATE FILE", JOptionPane.PLAIN_MESSAGE);
		String fileName = chooseFile();

		try{
			wordList = new ArrayList<>();
			// Creating regular expression for all non-letter characters
			String regex = "(?:^|\\s)[a-zA-Z]+(?=\\s|$)";

			// File wordFile = new File("words.txt");
			File wordFile = new File(fileName);
			Scanner in = new Scanner(wordFile);
			while (in.hasNext()) {
				String wordToAdd = in.next().toLowerCase();
				
				// if word contains only letter characters it will be added to the sorted word list
				// if a word contains a non-letter character, a message will indicate
				// that it has not been added to the word list
				if (wordToAdd.matches(regex)){
					addToDict(wordToAdd);
					if(TESTING){
						System.out.println(wordToAdd + " added to word list.");
					}
				}

				else{
					System.out.println(wordToAdd + " NOT added to word list.");
				}
			}

			in.close();
		}catch (FileNotFoundException e) {
			System.out.println("ERROR: File containing word list not found.");
		}
		
		System.out.println("*** Completed loading word file into the list of words. ***\n");
		
		// Create the dictionary
		DictionaryHashTable wordTable = new DictionaryHashTable(String.class, wordList.size());

		if(TESTING){
			System.out.println("The size of the hash table is " + wordTable.getArraySize());
			System.out.println();
			System.out.println();
			System.out.println("Dictionary Hash Table");
			wordTable.displayTable();
		}

		// Now going to load the word list into the dictionary
		Iterator<String> iter = wordList.iterator();
		while(iter.hasNext()){
			String value = iter.next();
			if(value != null){ // there should be no null value, but just in case
				if(TESTING){
					System.out.println("Trying to add " + value + " to hash table.");
				}

				wordTable.hashFunction(value);
			}
		}
		
		System.out.println("*** Completed loading word list in dictionary. ***\n");

		if(TESTING){
			System.out.println();
			System.out.println("Now building the Anagram Hash Table");
		}

		// Creating the hash table that will hold the anagram pair (the word and its key)
		AnagramHashTable aTable = new AnagramHashTable(AnagramList.class, wordList.size());

		for(String s: wordList){
			if(s != null){
				AnagramPair newPair = new AnagramPair(s);
				aTable.hashFunction(newPair);
			}
		}

		if(TESTING){
			System.out.println();
			System.out.println();
			System.out.println("Dictionary Hash Table");
			wordTable.displayTable();
			System.out.println();
			System.out.println();
			System.out.println("Anagram Hash Table");
			aTable.displayTable();
			System.out.println();
			System.out.println();
		}

		// open the input file
		JOptionPane.showMessageDialog(null,	"Locate file containing words to search for anagrams.", "LOCATE FILE", JOptionPane.PLAIN_MESSAGE);
		fileName = chooseFile();
		// File inputFile = new File("input.txt");
		try{
			File inputFile = new File(fileName);
			Scanner inFile = new Scanner(inputFile);
			while (inFile.hasNext()) {
				String find = inFile.next();
				try{
					// intentionally not giving user the option to choose a file
					fileName = "output.txt";
					PrintWriter outFile = new PrintWriter(new FileWriter(fileName, true));

					String listOfAnagrams = aTable.anagramsOf(find);
					outFile.println(listOfAnagrams);
					System.out.println("ADDED TO OUTPUT FILE: " + listOfAnagrams);
					
					outFile.close();

				}catch(FileNotFoundException e){
					System.out.println("ERROR: Output file not located!");
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			inFile.close();
		}catch (FileNotFoundException e) {
			System.out.println("ERROR: File containing query data not found.");
		}
		
		System.out.println("*** Completed output file. ***\n");
	} 
	
	/**
	 * Adds a word to the dictionary
	 * 
	 * @param word a word
	 */
	public static void addToDict(String word) {
		// Word is placed in correct alphabetic order
		if (!isAlphabetized(word)) {
			alphabetize(word);            
		}   
		// If placing word at the end of the list or if the list is empty
		// is placing it in its correct alphabetic position
		else {
			wordList.add(word);
		}
	}

	/**
	 * Determines the correct alphabetic position for a word
	 *
	 * @param word a word
	 * @return true if the end of the list is the word's correct alphabetic
	 * position
	 */
	private static boolean isAlphabetized(String word) {
		if (wordList.isEmpty()) {
			return true;
		}

		Iterator<String> iter = wordList.iterator();
		try {
			while (iter.hasNext()) {
				if (word.compareToIgnoreCase((iter.next())) < 0) {
					return false;
				}
			}
		} catch (NoSuchElementException e) {
			System.out.println("ERROR: AnagramRunner.isAlphabetized() method");
		}

		return true;
	}

	/**
	 * Places a word in its correct alphabetic position
	 *
	 * @param word a word
	 */
	private static void alphabetize(String word) {
		int tempIndex = -1;
		String current;
		boolean alphaLocCorrect = false;

		Iterator<String> iter = wordList.iterator();

		try {
			while (iter.hasNext() && !alphaLocCorrect) {
				current = iter.next();
				if (word.compareToIgnoreCase(current) < 0) {
					tempIndex = wordList.indexOf(current);
					alphaLocCorrect = true;
				}
			}
		} catch (NoSuchElementException e) {
			System.out.println("ERROR: AnagramRunner.alphabetize() method");
		}

		wordList.add(tempIndex, word);
	}

	/**
	 * Allows the user to select a file and then returns the absolute path to
	 * that file.
	 *
	 * @return the path to a selected file
	 */
	public static String chooseFile() {
		try {
			JFileChooser chooser = new JFileChooser();
			// User can only select files
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooser.showOpenDialog(null);

			String path = chooser.getSelectedFile().getAbsolutePath();

			return path;

		} catch (NullPointerException e) {
			System.out.println("ERROR: No file selected! Operation canceled by user?");
		}

		return "ERROR: File not selected!";
	}
}
