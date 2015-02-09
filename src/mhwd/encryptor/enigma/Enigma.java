package mhwd.encryptor.enigma;

public class Enigma {
	
	private Rotor[] rotors;
	public int rotorCount;
	private Plugboard plugboard;
	private Plugboard reflector;
	private int plugCount;
	
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
		plugboard=new Plugboard();
		reflector=new Plugboard();
	}
	
	public void addPlug(String seed)
	{
		plugboard.addPlug((int)(seed.charAt(0))-65, (int)(seed.charAt(1))-65);
		
	}
	
	public String encode(String message)
	{
		message=message.toUpperCase();
		message=message.replaceAll(" ", "X");
		String out="";
		for(int i=0; i<message.length(); i++)
		{
			int character=(int)(message.charAt(i))-65;
			character=plugboard.encode(character);
			int[] inOut=new int[2];
			inOut[0]=character;
			inOut[1]=1;
			for(int j=0; j<rotors.length; j++)
			{
				inOut=rotors[j].encode(inOut);
				
			}
			inOut[0]=reflector.encode(inOut[0]);
			for(int j=rotors.length-1; j>=0; j--)
			{
				inOut[1]=0;
				inOut=rotors[j].encode(inOut);
			}
			out+=(char)(inOut[0]+65);
		}
		
		return out;
	}
}
