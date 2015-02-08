package mhwd.encryptor.vigenere;

import mhwd.encryptor.primes.PrimeGenerator;

public class Tester {

	public static void main(String[]args)
	{
		VigenereShift vshift=new VigenereShift();
		String message="Radio Island Tower this is India Golf Niner Niner Requesting landing vectors, over and out";
		String key="jkm345";
		String cipher=vshift.encode(message, key, false);
		System.out.println(message+" -> "+key);
		System.out.println(cipher);
		System.out.println(vshift.decode(cipher, key, false));
	
		VigenereAffine vaffine=new VigenereAffine();
		String bkey="decode";
		String cipher2=vaffine.encode(message, key, bkey, false);
		System.out.println(message+" -> "+key+" , "+bkey);
		System.out.println(cipher2);
		System.out.println(vaffine.decode(cipher, key,bkey, false));
		
	/*	PrimeGenerator pG=new PrimeGenerator();
		Integer[] relprime=pG.getRelPrime(36);
		for(int i=0; i<relprime.length; i++)
		{
			System.out.print(relprime[i]+", ");
		}
		*/
	}
}
