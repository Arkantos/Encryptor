package mhwd.encryptor.primes;


import java.util.ArrayList;
import java.util.Arrays;




public class PrimeGenerator {

	public PrimeGenerator()
	{
		
	}
	
	
	
	private boolean[] sieve(int n)
	{
		
		boolean array[] = new boolean[n];
		Arrays.fill(array, true);
		array[0]=false;
		array[1]=false;
		for(int i=0; i<Math.sqrt(n);i++)
		{
			if(array[i]=true)
			{
				for(int j=i^2; j<n;j+=i)
				{
					array[j]=false;
				}
			}
		}
		
		
		return array;
	}
	
	public ArrayList<Integer> getPrimes(int num)
	{
		ArrayList<Integer> primes=new ArrayList<Integer>();
		boolean[] arr=sieve(num);
		for(int i=0; i<arr.length; i++)
		{
			if(arr[i]==true)
				primes.add(i);
		}
		
		return primes;
		
	}
	public ArrayList<Integer> getSafePrimes(int num)
	{
		ArrayList<Integer> primes=getPrimes(num);
		ArrayList<Integer> safePrimes=new ArrayList<Integer>();
		for(int i=0; i<primes.size(); i++)
		{
			int temp=primes.get(i);
			if(primes.contains((temp-1)/2))
				safePrimes.add(temp);
		}
		return safePrimes;
	}
	
	public Integer[] getRelPrime(int num)
	{
		ArrayList<Integer> relPrime=new ArrayList<Integer>();
		for(int i=2; i<num; i++)
		{
			if(gcd((long)i, (long)num)[0]==1)
			{
				relPrime.add(i);
			//	System.out.print(i);
			}
				
		}
		int size=relPrime.size();
		Integer[] primes=new Integer[size];
		for(int i=0; i<size; i++)
		{
			
			primes[i]=relPrime.get(i);
		//	System.out.print(primes[i]+" ");
		}
		return primes;
	}
	public boolean isRelPrime(int num, int size)
	{
		Integer[] relprimes=getRelPrime(num);
		
		for(int i=0; i<relprimes.length; i++)
		{
			
			if(relprimes[i]==num)
				return true;
		}
		return false;
	}
	public int makeRelPrime(int num, int size)
	{
		
		Integer[] relprimes=getRelPrime(size);
		
		for(int i=0; i<relprimes.length; i++)
		{
		//	System.out.println(relprimes[i]);
			if(num==relprimes[i])
				return num;
			else if(num<relprimes[i])
				return relprimes[i];
			
		}
		
		return relprimes[0];
	}
	public long[] gcd(long p, long q) {
		 if (q == 0)
			 return new long[] { p, 1, 0 };
		 long[] vals = gcd(q, p % q);
		 long d = vals[0];
		 long a = vals[2];
		 long b = vals[1] - (p / q) * vals[2];
		 return new long[] { d, a, b };
	}
	
	public long inverse(long k, long n) {
		long[] vals = gcd(k, n);
		long d = vals[0];
		long a = vals[1];
		long b = vals[2];
		if (d > 1) { System.out.println("Inverse does not exist."); return 0; }
			if (a > 0) return a;
		    	return n + a;
	}
	
	
}
