/**
 * Defines the DictionaryHashTable class
 * 
 * @version Assignment 5, 23 April 2014
 */

public class DictionaryHashTable extends GenericHashTable<String, String> {
	/**
	 * Constructor for the DictionaryHashTable class
	 * 
	 * @param size a minimum size of the hash table
	 */
	public DictionaryHashTable(Class<String> String, int size){
		
		super(String, size);
	}

	/**
	 * Inserts an object into the dictionary hash table
	 * 
	 * @param word a word
	 */
	public void hashFunction(String word){
		String[]dictionary = getArray();
		int arraySize = getArraySize();
		int hashNum = word.hashCode();
		int sHashIndex = Math.abs(hashNum % arraySize);
		// System.out.println("Initial attempt to insert " + word + " in index " + sHashIndex);

		while(dictionary[sHashIndex] != null){
			sHashIndex++;
			// System.out.println("Collision occurred. Trying to insert " + word + " in index " + sHashIndex);
			sHashIndex %= arraySize;
		}

		dictionary[sHashIndex] = word;
		// System.out.println(word + " inserted at index " + sHashIndex);
	}

	/**
	 * Determines if a word is in the dictionary hash table
	 * 
	 * @param word a word
	 * @return true if the word is in hash table
	 */
	public boolean isFound(String word){
		String[]dictionary = getArray();
		int arraySize = getArraySize();
		int hashNum = word.hashCode();
		int sHashIndex = Math.abs(hashNum % arraySize);

		// System.out.println("Dict: Start looking for " + word + " in index " + sHashIndex);
		// System.out.println("Dict: Hashcode: " + hashNum);

		while(dictionary[sHashIndex] != null && dictionary[sHashIndex].equals(word)){
			// System.out.println("Dict: The word at index " + sHashIndex + " is " + dictionary[sHashIndex]);
			sHashIndex++; 
			// System.out.println("sHashIndex increased by one: " + sHashIndex);

			if(sHashIndex ==  Math.abs(hashNum % arraySize)){
				// System.out.println("Cycled through the hash table without finding "
					// + word + ".\n");
				return false;
			}

			// System.out.println("Dict: Searching for " + word + " in index " + sHashIndex);
			sHashIndex %= arraySize;
		}

		if(dictionary[sHashIndex] == null ){
			System.out.println("Dict: The index at " + sHashIndex + " is empty.\n");
			return false;
		}

		// System.out.println("Dict. " + word + " found in index " + sHashIndex +"\n");
		return true;
	}

	/*
	 * Returns a text description of a DictionaryHashTable object
	 * 
	 * @return A text description of a DictionaryHashTable object
	 */
	@Override
	public String toString(){
		return "The dictionary can hold " + getArraySize() + " words.";
	}
}
