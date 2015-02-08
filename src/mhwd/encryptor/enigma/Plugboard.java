package mhwd.encryptor.enigma;

public class Plugboard {
	private Plug[] board=new Plug[26];
	
	public Plugboard()
	{
		for(int i=0; i<26; i++)
		{
			board[i]=new Plug(i);
		}
	}
	
	public void addPlug(int plug1, int plug2)
	{
		if(plug1>25)
		{
			plug1=plug1%26;
		}
		if(plug2>25)
		{
			plug2=plug2%26;
		}
		
		while(plug1<0)
			plug1=plug1+26;
		
		while(plug2<0)
			plug2=plug2+26;
		
		Plug temp=board[plug1];
		if(!temp.isDefault())
		{
			int tempP1=temp.source;
			int tempP2=temp.dest;
			board[tempP1]=new Plug(tempP1);
			board[tempP2]=new Plug(tempP2);
		}
		temp=board[plug2];
		if(!temp.isDefault())
		{
			int tempP1=temp.source;
			int tempP2=temp.dest;
			board[tempP1]=new Plug(tempP1);
			board[tempP2]=new Plug(tempP2);
		}
		
		board[plug1]=new Plug(plug1, plug2);
		board[plug2]=new Plug(plug2, plug1);
	}
	
	public int getOutput(int input)
	{
		if(input>25)
			input=input%26;
		while(input<0)
			input=input+26;
		return board[input].dest;
		
	}
	
}
