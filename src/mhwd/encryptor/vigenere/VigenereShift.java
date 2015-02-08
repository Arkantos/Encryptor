package mhwd.encryptor.vigenere;

import mhwd.encryptor.alphabet.Alphabet;
import mhwd.encryptor.alphabet.BasicAlphabet;
import mhwd.encryptor.alphabet.ExtendedAlphabet;

public class VigenereShift {

	private static BasicAlphabet alpha=new BasicAlphabet();
	private static ExtendedAlphabet beta=new ExtendedAlphabet();
	
	
	public String encode(String message, String key, boolean extended)
	{
		Alphabet alphabet=choose(extended);
		
//		String newkey="";
	//	while(newkey.length()<message.length())
	//		newkey+=key;
		Integer[] messageArray=alphabet.encode(message);
		Integer[] keyArray=alphabet.encode(key);
		String out="";
		for(int i=0; i<messageArray.length; i++)
		{
			messageArray[i]=(messageArray[i]+keyArray[i%keyArray.length])%alphabet.getSize();
		
		}
		out=alphabet.decode(messageArray);
		
		return out;
	}
	
	public String decode(String message, String key, boolean extended)
	{
		Alphabet alphabet=choose(extended);
		int size=alphabet.getSize();
		Integer[] keyArray=alphabet.encode(key);
		for(int i=0; i<keyArray.length; i++)
		{
		
			keyArray[i]=(size-keyArray[i])%size;
		
		}
		Integer[] messageArray=alphabet.encode(message);
		String out="";
		for(int i=0; i<messageArray.length; i++)
		{
			messageArray[i]=(messageArray[i]+keyArray[i%keyArray.length])%alphabet.getSize();
		
		}
		out=alphabet.decode(messageArray);
		return out;
		
	}
	public Alphabet choose(boolean extended)
	{
		if(extended)
			return beta;
		else return alpha;
	}
	
}
