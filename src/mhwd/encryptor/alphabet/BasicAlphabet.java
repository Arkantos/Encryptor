package mhwd.encryptor.alphabet;

import java.util.HashMap;

public class BasicAlphabet implements Alphabet{

	private HashMap<Character, Integer> encode;
	private HashMap<Integer, Character> decode;
	public static final int size=36;
	public BasicAlphabet()
	{
		encode=new HashMap<Character, Integer>();
		decode=new HashMap<Integer, Character>();
		createMaps();
	}
	
	private void createMaps()
	{
		for(int i=65; i<91; i++)
		{
			encode.put((char)i, i-65);
			decode.put(i-65, (char)i);
		}
		for(int i=48; i<58; i++)
		{
			encode.put((char)i, i-22);
			decode.put(i-22, (char)i);
		}
	}
	
	public int getSize()
	{
		return size;
	}
	public Integer[] encode(String s)
	{
		String out="";
		s=s.toUpperCase();
		for(int i=0; i<s.length(); i++)
		{
			char c=s.charAt(i);
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
