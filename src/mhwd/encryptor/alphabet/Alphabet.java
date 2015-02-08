package mhwd.encryptor.alphabet;



public interface Alphabet {
	
	/**
	 * A method to return the size of the alphabet
	 * @return size of Alphabet
	 */
	public int getSize();
	
	/**
	 * A method to convert a message into an integer array
	 * @param message
	 * @return an integer array consisting of the encoding of the characters in the message
	 */
	public Integer[] encode(String message);
	
	/**
	 * A method to convert an integer array into a message
	 * @param cipher
	 * @return a string consisting of the characters encoded in the array
	 */
	public String decode(Integer[] cipher);
	
	/**
	 * A method to find the frequency of letters in a message.
	 * @param message
	 * @return a double array of the frequencies of the characters in the message.
	 */
	public Double[] frequency(String message);
	
	/**
	 * A method to showcase the conversion between characters and integers
	 * @return a string showing the encoding of each character in the alphabet
	 */
	public String toString();
	
	
}
