package mhwd.encryptor.enigma;

public class Enigma {
	
	private Rotor[] rotors;
	public int rotorCount;
	private Plugboard plugboard;
	private Plugboard reflector;
	
	
	public Enigma()
	{
		rotors=new Rotor[3];
	}
	public Enigma(int[] rotorChoices)
	{
		rotors=new Rotor[rotorChoices.length];
		for(int i=0; i<rotorChoices.length; i++)
		{
			rotors[i]=new Rotor(rotorChoices[i]);
			
		}
	}
	
	public void addPlug(String seed)
	{
		plugboard.addPlug((int)(seed.charAt(0))-65, (int)(seed.charAt(1))-65);
		
	}
}
