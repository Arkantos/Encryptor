package mhwd.encryptor.enigma;

public class Plug {

	public int source;
	public int dest;
	
	public Plug(int number)
	{
		source=number;
		dest=number;
	}
	public Plug(int number1, int number2)
	{
		source=number1;
		dest=number2;
	}
	
	public boolean isDefault()
	{
		if(source==dest)
			return true;
		else return false;
	}
}
