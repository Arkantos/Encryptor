package mhwd.encryptor.shift;

import mhwd.encryptor.alphabet.Alphabet;
import mhwd.encryptor.alphabet.BasicAlphabet;
import mhwd.encryptor.alphabet.ExtendedAlphabet;

public class Shift {

	private static final BasicAlphabet alpha=new BasicAlphabet();
	private static final ExtendedAlphabet beta=new ExtendedAlphabet();
	public String encode(String s, int shift, boolean extended)
	{
		Alphabet alphabet=choose(extended);
		Integer[] array=alphabet.encode(s);
		String out="";
		for(int i=0; i<array.length; i++)
		{
			array[i]=(array[i]+shift)%alphabet.getSize();	
		}
		
		
		return alphabet.decode(array);
		
	}
	
	
	public String decode(String s, int shift, boolean extended)
	{
		Alphabet alphabet=choose(extended);
		int deshift=alphabet.getSize()-shift;
		
		return encode(s,deshift, extended);
		
	}
	public Alphabet choose(boolean extended)
	{
		if(extended)
			return beta;
		else return alpha;
	}
}
