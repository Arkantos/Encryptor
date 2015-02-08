package mhwd.encryptor.vigenere;

import mhwd.encryptor.alphabet.Alphabet;
import mhwd.encryptor.alphabet.BasicAlphabet;
import mhwd.encryptor.alphabet.ExtendedAlphabet;
import mhwd.encryptor.primes.PrimeGenerator;

public class VigenereAffine {

	
	private static BasicAlphabet alpha=new BasicAlphabet();
	private static ExtendedAlphabet beta=new ExtendedAlphabet();
	private static PrimeGenerator pG=new PrimeGenerator();
	
	public String encode(String message, String aKey, String bKey, boolean extended)
	{
		Alphabet alphabet=choose(extended);
		int size=alphabet.getSize();
//		String newkey="";
	//	while(newkey.length()<message.length())
	//		newkey+=key;
		Integer[] messageArray=alphabet.encode(message);
		Integer[] aKeyArray=alphabet.encode(aKey);
		Integer[] bKeyArray=alphabet.encode(bKey);
		for(int i=0; i<aKeyArray.length; i++)
		{		
		//	System.out.print(aKeyArray[i]+" -> ");
			aKeyArray[i]=pG.makeRelPrime(aKeyArray[i], size);
		//	System.out.println(aKeyArray[i]);
		}
		String out="";
		for(int i=0; i<messageArray.length; i++)
		{
		//	System.out.print(messageArray[i]+" -> ");
			messageArray[i]=(messageArray[i]*aKeyArray[i%aKeyArray.length]+bKeyArray[i%bKeyArray.length])%alphabet.getSize();
		//	System.out.println(messageArray[i]);
		}
		out=alphabet.decode(messageArray);
		
		return out;
	}
	
	public String decode(String message, String aKey, String bKey, boolean extended)
	{
		Alphabet alphabet=choose(extended);
		int size=alphabet.getSize();
		Integer[] aKeyArray=alphabet.encode(aKey);
		Integer[] bKeyArray=alphabet.encode(bKey);
		for(int i=0; i<aKeyArray.length; i++)
		{
			
		//	System.out.print(aKeyArray[i]+" -> ");
			aKeyArray[i]=pG.makeRelPrime(aKeyArray[i], size);
		//	System.out.print(aKeyArray[i]+" -> ");
			aKeyArray[i]=(int) pG.inverse(aKeyArray[i], size);
		//	System.out.println(aKeyArray[i]);
		}
		
		Integer[] messageArray=alphabet.encode(message);
		String out="";
		for(int i=0; i<messageArray.length; i++)
		{
			messageArray[i]=((messageArray[i]*aKeyArray[i%aKeyArray.length]));
			messageArray[i]+=(aKeyArray[i%aKeyArray.length]*(size-bKeyArray[i%bKeyArray.length]));
			messageArray[i] %=size;
			
			
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
