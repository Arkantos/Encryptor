package mhwd.encryptor.enigma;

public class Plugboard {
	private int[] board=new int[26];
	
	public Plugboard()
	{
		createBoard("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
	}
	public Plugboard(String seed)
	{
		createBoard(seed);
	}
	private void createBoard(String seed)
	{
		for(int i=0; i<26; i++)
		{
			board[i]=((int)(seed.charAt(i)))-65;
		}
	}
	public int addPlug(int plug1, int plug2)
	{
		
		if(board[plug1]==plug1&&board[plug2]==plug2)
		{
			board[plug1]=plug2;
			board[plug2]=plug1;
			return 1;
		}
		else return 0;
	}
	public int removePlug(int plug)
	{
		if(board[plug]!=plug)
		{
			int temp=board[plug];
			board[plug]=plug;
			board[temp]=temp;
			return 1;
		}
		else return 0;
	}
	public int encode(int input)
	{
		return board[input];
		
	}
	
}
