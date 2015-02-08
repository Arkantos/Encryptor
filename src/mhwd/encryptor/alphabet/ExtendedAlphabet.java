package mhwd.encryptor.alphabet;

import java.util.HashMap;

public class ExtendedAlphabet implements Alphabet{

	private HashMap<Character, Integer> encode;
	private HashMap<Integer, Character> decode;
	public static final int size=94;
	public ExtendedAlphabet()
	{
		encode=new HashMap<Character, Integer>();
		decode=new HashMap<Integer, Character>();
		createMaps();
	}
	
	private void createMaps()
	{
		for(int i=33; i<127; i++)
		{
			encode.put((char)i, i-33);
			decode.put(i-33, (char)i);
		}
		
	}
	public int getSize()
	{
		return size;
	}
	public Integer[] encode(String message)
	{
		String out="";
		
		for(int i=0; i<message.length(); i++)
		{
			char c=message.charAt(i);
			if(encode.containsKey(c))
				out+=c;
		}
		Integer[] array=new Integer[out.length()];
		for(int i=0; i<out.length(); i++)
		{
			array[i]=encode.get(out.charAt(i));
		}
		return array;
	}
	
	public String decode(Integer[] array)
	{
		String out="";
		for(int i=0; i<array.length; i++)
		{
			out+=decode.get(array[i]);
			if((i+1)%5==0)
				out+=" ";
		}
		
		return out;
	}
	public String toString()
	{
		Object[] arr1= encode.values().toArray();
		Object[] arr2= encode.keySet().toArray();
		String out="";
		for(int i=0; i<arr1.length; i++)
		{
			out=out+arr1[i]+" -> "+arr2[i]+" : ";
			if((i+1) % 6==0)
				out+="\n";
		}
		return out;
	}

	@Override
	public Double[] frequency(String message) {
		Double[] freq=new Double[size];
		Integer[] temp=encode(message);
		for(int i=0; i<temp.length; i++)
		{
			freq[temp[i]]++;
		}
		for(int i=0; i<freq.length; i++)
			freq[i]/=temp.length;
		return freq;
	}
}
