package mhwd.encryptor.affine;

import mhwd.encryptor.alphabet.Alphabet;
import mhwd.encryptor.alphabet.BasicAlphabet;
import mhwd.encryptor.alphabet.ExtendedAlphabet;
import mhwd.encryptor.primes.PrimeGenerator;

public class Affine {
	private static final BasicAlphabet alpha=new BasicAlphabet();
	private static final ExtendedAlphabet beta=new ExtendedAlphabet();
	private static final PrimeGenerator pG=new PrimeGenerator();
	public String encode(String s, int a, int b, boolean extended)
	{
		Alphabet alphabet=choose(extended);
		Integer[] array=alphabet.encode(s);
		String out="";
		System.out.println(alphabet.getSize());
		a=pG.makeRelPrime(a, alphabet.getSize());
		for(int i=0; i<array.length; i++)
		{
			array[i]=(array[i]*a+b)%alphabet.getSize();	
			
		}
		
		
		return alphabet.decode(array);
		
	}
	
	
	public String decode(String s, int a, int b, boolean extended)
	{
		Alphabet alphabet=choose(extended);
		a=pG.makeRelPrime(a, alphabet.getSize());
		int inverseA=(int)pG.inverse(a, alphabet.getSize());
		//System.out.println("inverse of "+a+" mod "+alphab.getSize()+" is "+inverseA);
		int inverseB=(inverseA*(alphabet.getSize()-b)) % alphabet.getSize();
		
		
		return encode(s,inverseA, inverseB, extended);
		
	}
	public Alphabet choose(boolean extended)
	{
		if(extended)
			return beta;
		else return alpha;
	}
	
}
