/**
 * Defines the generic abstract GenericTableClass class
 * 
 * Ideally I would have liked for this to have been a generic superclass
 * @version Assignment 5, 23 April 2014
 */

import java.lang.reflect.Array;

public abstract class GenericHashTable<E, T> {
	// Declarations of the instance variables of GenericHashTable class
	private E[] anArray;
	private int arraySize;
	private Class<E> elementType;

	/**
	 * Constructor for the GenericHashTable class
	 * 
	 * @param size a minimum size of the hash table
	 */
	@SuppressWarnings("unchecked")
	public GenericHashTable(Class<E> elementType, int size){
		this.elementType = elementType;
		arraySize = getPrime(size * 2); 
		anArray = (E[]) Array.newInstance(elementType, arraySize);
	}

	/**
	 * Return the hash table array
	 * 
	 * @return the hash table array
	 */
	public E[] getArray(){
		return anArray;
	}

	/**
	 * Returns the size of the hash table's array
	 * 
	 * @return the size of the hash table's array
	 */
	public int getArraySize(){
		return arraySize;
	}

	/**
	 * Determines if an integer is a prime number
	 * 
	 * @param number an integer
	 * @return true if the number is a prime number
	 */
	public boolean isPrime(int number){
		if(number % 2 == 0)
			return false;

		for(int i = 3; i * i < number; i = i + 2){
			if(number % i == 0){
				return false;
			}
		}
		return true;
	}

	/**
	 * Finds a the next prime number that is greater than the entered
	 * number
	 * 
	 * @param number an integer
	 * @return a prime number
	 */
	public int getPrime(int number){
		for(int i = number; true; i++){
			if(isPrime(i)){
				return i;
			}
		}
	}

	/**
	 * Displays the hash table
	 */
	public void displayTable() {
		for(int i = 0; i < arraySize; i++){
			System.out.println(i + ": " + anArray[i]);
		}
	}
	
	/*
	 * Returns a text description of a GenericHashTable object
	 * 
	 * @return A text description of a GenericHashTable object
	 */
	@Override
	public String toString(){
		return "The hash table can hold " + getArraySize() + " objects.";
	}

	/*
	 * Abstract method adding an Object to the hash table
	 * 
	 * @param o an object of type Object
	 */	
	abstract void hashFunction(T t);
}
