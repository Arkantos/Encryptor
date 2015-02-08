package mhwd.encryptor.shift;

public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String message="hello this is a message from 1991!";
		Shift shift=new Shift();
		String cipher=shift.encode(message, 35, false);
		System.out.println(message);
		System.out.println(cipher);
		System.out.println(shift.decode(cipher, 35,false));
	}

}
