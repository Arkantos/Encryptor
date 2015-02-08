package mhwd.encryptor.affine;
import mhwd.encryptor.primes.PrimeGenerator;
public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrimeGenerator pG=new PrimeGenerator();
		Affine affine=new Affine();
		//String message="This is a message from 1991";
		String message="Radio Island Tower this is India Golf Niner Niner Requesting landing vectors, over and out";
		String cipher=affine.encode(message, 6, 4, false);
		System.out.println(message);
		System.out.println(cipher);
		System.out.println(affine.decode(cipher, 6, 4, false));
	}

}
