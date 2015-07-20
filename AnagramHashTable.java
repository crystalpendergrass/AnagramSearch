/**
 * Defines the AnagramHashTable class
 * 
 * @version Assignment 5, 23 April 2014
 */

import java.util.Arrays;
import java.util.Iterator;

public class AnagramHashTable extends GenericHashTable<AnagramList, AnagramPair>  {
	/**
	 * Constructor for the AnagramHashTable class
	 * 
	 * @param size a minimum size of the hash table
	 */
	public AnagramHashTable(Class<AnagramList> AnagramList, int size){
		super(AnagramList, size);
	}

	/**
	 * Inserts an object into the anagram hash table
	 * 
	 * @param o an object that is an instance of type AnagramPair
	 */
	public void hashFunction(AnagramPair aPair){
		AnagramList[] anagramList = getArray();
		int hashNum = aPair.getKey().hashCode();
		int aHashIndex = Math.abs(hashNum % anagramList.length);

		// System.out.println("The hashcode " + hashNum + " for value " + aPair.getWord() 
		// + " whose key is " + aPair.getKey());
		// System.out.println("Trying to place anagram pair in index " + aHashIndex);

		while (anagramList[aHashIndex] != null && !anagramList[aHashIndex].getKey().equals(aPair.getKey())) {
			aHashIndex++;
			aHashIndex %= anagramList.length;
			if(aHashIndex == hashNum % anagramList.length)
				System.out.println("This shouldn't be happening, but the hash table is full!");

			// System.out.println("Collision occured.  Trying  to insert " 
			// + aPair + " in index " + aHashIndex + " instead.");
		}

		if(anagramList[aHashIndex] == null){
			anagramList[aHashIndex] = new AnagramList();
			anagramList[aHashIndex].add(aPair);
		}

		else if(anagramList[aHashIndex].getKey().equals(aPair.getKey())){
			anagramList[aHashIndex].add(aPair);
		}
	}

	/**
	 * Finds a list of anagrams for a word
	 * 
	 * @param word a word
	 * @return a list of anagrams for a word
	 */
	public AnagramList findAnagram(String word){
		AnagramList[] anagramList = getArray();
		int arraySize = getArraySize();
		String key = createKey(word);
		int hashNum = key.hashCode();
		int aHashIndex = Math.abs(hashNum % anagramList.length);

		// System.out.println("Start looking for " + word + " in index " + aHashIndex);
		// System.out.println("Key: " + key + " Key Hashcode: " + hashNum);

		while(anagramList[aHashIndex] != null && !anagramList[aHashIndex].getKey().equals(key)){
			// System.out.println("The AnagramList at index " + aHashIndex + " is " + anagramList[aHashIndex]);
			aHashIndex++; 
			// System.out.println("aHashIndex increased by one: " + aHashIndex);

			if(aHashIndex ==  Math.abs(hashNum % arraySize)){
				// System.out.println("Cycled through the whole array");
				return null;
			}

			// System.out.println("Searching for " + word + "'s key [" + key +"] in index " + aHashIndex);
			aHashIndex %= arraySize;
		}

		if(anagramList[aHashIndex].getKey() == null){
			// System.out.println("This shouldn't be happening");
			return null;
		}

		// System.out.println(word + " found in list " + anagramList[aHashIndex]);
		return anagramList[aHashIndex];
	}

	public String anagramsOf(String word){
		int aCounter = 0;
		String list = "";

		try{
			AnagramList aList = findAnagram(word);
			Iterator<AnagramPair> iter = aList.getAList().iterator();
			while(iter.hasNext()){
				String current = iter.next().getWord();
				if(!current.equals(word)){
					list = list + current + " ";
					aCounter++;
				}
			}

			return word + " " + aCounter + " " + list;
		}catch (NullPointerException e) {
			return word + " " + aCounter;
		}
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
	 * Returns a text description of an AnagramHashTable object
	 * 
	 * @return A text description of an AnagramHashTable object
	 */
	@Override
	public String toString(){
		return "The anagram table can hold " + getArraySize() + " anagram pairs.";
	}

}
