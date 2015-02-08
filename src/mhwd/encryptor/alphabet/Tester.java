package mhwd.encryptor.alphabet;

public class Tester {
	public static void main(String[] args)
	{
		ExtendedAlphabet alpha=new ExtendedAlphabet();
	//	System.out.println(alpha.toString());
		MiniAlphabet beta=new MiniAlphabet();
		System.out.println(beta.toString());
		
		String message="This is a test.";
		Integer[] array=beta.encode(message);
		String cipher=beta.decode(array);
		System.out.println(message);
		System.out.println(cipher);
	}
}
