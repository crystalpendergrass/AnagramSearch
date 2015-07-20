/**
 * Defines the AnagramPair class
 * 
 * @version Assignment 5, 23 April 2014
 */

import java.util.Arrays;

public class AnagramPair {
	// Declarations of the instance variables of AnagramPair class
	private String key;
	private String word;

	/**
	 * Constructor for the AnagramPair class
	 * 
	 * @param word a word
	 */
	public AnagramPair(String word){
		this.word = word;
		key = createKey(word);
	}

	/**
	 * Returns the current value of key
	 * 
	 * @return the current value of key 
	 */
	public String getKey(){
		return key;
	}

	/**
	 * Returns the current value of word
	 * 
	 * @return the current value of word
	 */
	public String getWord(){
		return word;
	}

	/**
	 * Determines the key for a word
	 * 
	 * @param word a word
	 * @return the key of a word
	 */
	public String createKey(String word){
		char[] charArray = word.toCharArray();
		Arrays.sort(charArray);

		StringBuilder sortedWord = new StringBuilder();
		for (char c: charArray){
			sortedWord.append(c);
		}

		return sortedWord.toString();
	}

	/**
	 * Returns a text description of an AnagramPair object
	 * 
	 * @return A text description of an AnagramPair object
	 */
	@Override
	public String toString(){
		return word + " is an anagram of " + key; 
	}
}
