/**
 * Defines the AnagramList class
 * 
 * @version Assignment 5, 23 April 2014
 */

import java.util.ArrayList;
import java.util.Iterator;

public class AnagramList {
	// Declarations of the instance variables of AnagramList class
	private ArrayList<AnagramPair> aList;
	private String key;

	/**
	 * Constructor for the AnagramList class
	 */
	public AnagramList(){
		aList = new ArrayList<>();
		key = null;
	}

	/**
	 * Returns the current value of aList
	 * 
	 * @return the current value of aList
	 */
	public ArrayList<AnagramPair> getAList(){
		return aList;
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
	 * Adds an object of type AnagramList to a list of anagrams with 
	 * the same key
	 * 
	 * @param aPair an object of type AnagramPair
	 */
	public void add(AnagramPair aPair){
		if(key == null){
			key = aPair.getKey();}

		aList.add(aPair);
	}

	/**
	 * Returns a text description of an AnagramList object
	 * 
	 * @return A text description of an AnagramList object
	 */
	@Override
	public String toString(){
		String left = key + ":";
		StringBuilder message = new StringBuilder(left);

		Iterator<AnagramPair> iter = aList.iterator();
		while(iter.hasNext()){
			String current = iter.next().getWord();

			if(!current.equals(key)){
				String right = " " + current;
				message.append(right);
			}
		}

		return message.toString();
	}
}
