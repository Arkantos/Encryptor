package mhwd.encryptor.enigma;

public class Rotor {
	private Node rotor;
	private int[] rotateOn=new int[2];
	public int location;
	public Rotor()
	{
		createRotor("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		rotateOn[0]=0;
		rotateOn[1]=-1;
	}
	public Rotor(int seed)
	{
		
		switch(seed)
		{
			case 0:
				createRotor("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
				rotateOn[0]=0;
				rotateOn[1]=-1;
				break;
			case 1:
				createRotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ");
				rotateOn[0]=(int)'R'-65;
				rotateOn[1]=-1;
				break;
			case 2:
				createRotor("AJDKSIRUXBLHWTMCQGZNPYFVOE");
				rotateOn[0]=(int)'F'-65;
				rotateOn[1]=-1;
				break;
			case 3:
				createRotor("BDFHJLCPRTXVZNYEIWGAKMUSQO");
				rotateOn[0]=(int)'W'-65;
				rotateOn[1]=-1;
				break;
			case 4:
				createRotor("ESOVPZJAYQUIRHXLNFTGKDCMWB");
				rotateOn[0]=(int)'K'-65;
				rotateOn[1]=-1;
				break;
			case 5:
				createRotor("VZBRGITYUPSDNHLXAWMJQOFECK");
				rotateOn[0]=(int)'A'-65;
				rotateOn[1]=-1;
				break;
			case 6:
				createRotor("JPGVOUMFYQBENHZRDKASXLICTW");
				rotateOn[0]=(int)'A'-65;
				rotateOn[1]=(int)'N'-65;
				break;
			case 7:
				createRotor("NZJHGRCXMYSWBOUFAIVLPEKQDT");
				rotateOn[0]=(int)'A'-65;
				rotateOn[1]=(int)'N'-65;
				break;
			case 8:
				createRotor("FKQHTLXOCBJSPDZRAMEWNIUYGV");
				rotateOn[0]=(int)'A'-65;
				rotateOn[1]=(int)'N'-65;
				break;
			default:
				createRotor("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
				rotateOn[0]=0;
				rotateOn[1]=-1;
				break;
		}
	}
	private void createRotor(String seed)
	{
		rotor=new Node(0, ((int)seed.charAt(0))-65);
		Node temp=rotor;
		for(int i=1; i<26; i++)
		{
			temp.next=new Node(i, ((int)seed.charAt(i))-65);
			temp=temp.next;
		}
		temp.next=rotor;
	}
	
	
	private int[] firstInput(int[] setting)
	{
		int[] output=new int[2];
		output[1]=0;
		
		if(setting[1]==1)
		{
			rotor=rotor.next;
			if(rotor.identity==rotateOn[0]||rotor.identity==rotateOn[1])
				output[1]=1;
			
		}
		Node temp=rotor;
		for(int i=0; i<setting[0]; i++)
			temp=temp.next;
		output[0]=temp.map;
		
		return output;
	}

	private int secondInput(int setting)
	{
		Node temp=rotor;
		int output=0;
		for(int i=0; i<setting; i++)
			temp=temp.next;
		output=temp.identity;
		return output;
	}
	
	private void reset()
	{
		while(rotor.identity!=0)
			rotor=rotor.next;
	}
	private void set(int setting)
	{
		while(rotor.identity!=setting)
			rotor=rotor.next;
	}
	
	
}

class Node
{
	public int identity;
	public int map;
	public Node next;

	public Node(int identity)
	{
		this.identity=identity;
		this.map=identity;
		this.next=null;

	}
	
	public Node(int identity, int map)
	{
		this.identity=identity;
		this.map=map;
		this.next=null;
		
	}
	
	public void addNode(Node node)
	{
		this.next=node;
	}
}

