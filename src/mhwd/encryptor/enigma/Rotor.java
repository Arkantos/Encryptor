package mhwd.encryptor.enigma;

public class Rotor {
	/**
	 * rotor is the circular linked list that consists of a map of letters to other letters.
	 */
	private Node rotor;
	/**
	 * rotateOn determines whether the rotor sends a signal to the next rotor to rotate.
	 */
	private int[] rotateOn=new int[2];
	/**
	 * location tells the machine where the rotor is placed.
	 */
	public int location;

	
	/**
	 * Constructor for Rotor
	 * Creates an invalid mapping that maps each letter to itself, and tells it to rotate only on the first letter, 'A'.
	 */
	public Rotor()
	{
		createRotor("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		rotateOn[0]=0;
		rotateOn[1]=-1;
	}
	
	/**
	 * Constructor for Rotor
	 * @param seed=the number of the rotor.
	 */
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
	
	/**
	 * 
	 * @param seed=the string used to set the linked list's mapping.
	 */
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
	
	/**
	 * 
	 * @param setting = contains the position on the rotor that the previous rotor maps to, whether the rotor needs to turn, and the direction the information is going in.
	 * @return an integer array, where the first integer is the mapping, and the second tells the next rotor if it needs to rotate.
	 */
	public int[] encode(int[] setting)
	{
		int[] output=new int[3];
		output[1]=0;
		
		if(setting[1]==1)
		{
			setting[1]=0;
			rotor=rotor.next;
			if(rotor.identity==rotateOn[0]||rotor.identity==rotateOn[1])
				setting[1]=1;
			
		}
		if(setting[2]==0)
		{
			Node temp=rotor;
			for(int i=0; i<setting[0]; i++)
				temp=temp.next;
			setting[0]=temp.map;
		}
		else
		{
			Node temp=rotor;
			for(int i=0; i<(26-setting[0]); i++)
				temp=temp.next;
			setting[0]=temp.identity;
		}
		
		
		return setting;
	}

	public int secondInput(int setting)
	{
		Node temp=rotor;
		int output=0;
		for(int i=0; i<setting; i++)
			temp=temp.next;
		output=temp.identity;
		return output;
	}

	/**
	 * sets the rotor back to the first position.
	 */
	private void reset()
	{
		while(rotor.identity!=0)
			rotor=rotor.next;
	}
	
	/**
	 * Sets the rotor to a particular top letter
	 * @param setting=the letter to be on top.
	 */
	public void set(int setting)
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

	/**
	 * Creates a node where the identity points to itself
	 * @param identity=the initial letter for the node
	 */
	public Node(int identity)
	{
		this.identity=identity;
		this.map=identity;
		this.next=null;

	}
	
	/**
	 * Creates a node where the identity points to the map.
	 * @param identity=the initial letter for the node
	 * @param map=the letter the identity maps to
	 */
	public Node(int identity, int map)
	{
		this.identity=identity;
		this.map=map;
		this.next=null;
		
	}
	
	/**
	 * Adds another node to the linked list
	 * @param node = the node to be added
	 */
	public void addNode(Node node)
	{
		this.next=node;
	}
}

