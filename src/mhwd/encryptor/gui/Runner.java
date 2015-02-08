package mhwd.encryptor.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Choice;
import javax.swing.JTextField;


import mhwd.encryptor.alphabet.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.ButtonGroup;

import mhwd.encryptor.affine.Affine;
import mhwd.encryptor.alphabet.BasicAlphabet;
import mhwd.encryptor.alphabet.ExtendedAlphabet;
import mhwd.encryptor.primes.PrimeGenerator;
import mhwd.encryptor.shift.Shift;
import mhwd.encryptor.vigenere.VigenereShift;

public class Runner extends JFrame {

	private JPanel contentPane, shiftPanel, affinePanel, vigenerePanel, alphabetPanel, outPanel;
	private JTextField vigenereKeyField;
	private JButton btnEncode, btnDecode;
	private Choice affineAKeyChoice, shiftKeyChoice, affineBKeyChoice;
	private JTextPane messageTextPane;
	private static final BasicAlphabet alpha=new BasicAlphabet();
	private static final ExtendedAlphabet beta=new ExtendedAlphabet();
	private static final PrimeGenerator pG=new PrimeGenerator();
	private int flag=-1;
	private boolean extended=false;
	private Shift shift=new Shift();
	private Affine affine=new Affine();
	private VigenereShift vShift=new VigenereShift();
	private JToggleButton tglbtnBasic;
	private final ButtonGroup alphabetButtonGroup = new ButtonGroup();
	private JToggleButton tglbtnExtended;
	private JToggleButton tglbtnShift;
	private final ButtonGroup encodeButtonGroup = new ButtonGroup();
	private JToggleButton tglbtnAffine;
	private JToggleButton tglbtnVigenere;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Runner frame = new Runner();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Runner() {
		setTitle("Encryptor v.1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 559);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		affinePanel = new JPanel();
		affinePanel.setBackground(Color.GRAY);
		affinePanel.setBounds(10, 153, 414, 131);
		affinePanel.setVisible(false);
		
		shiftPanel = new JPanel();
		shiftPanel.setBackground(Color.GRAY);
		shiftPanel.setBounds(10, 153, 414, 131);
		shiftPanel.setVisible(false);
		contentPane.add(shiftPanel);
		shiftPanel.setLayout(null);
		
		shiftKeyChoice = new Choice();
		shiftKeyChoice.setBounds(187, 12, 61, 20);
		shiftPanel.add(shiftKeyChoice);
		
		JLabel lblKey = new JLabel("Key:");
		lblKey.setBounds(7, 12, 91, 14);
		shiftPanel.add(lblKey);
		contentPane.add(affinePanel);
		affinePanel.setLayout(null);
		
		JLabel lblPrimaryKey = new JLabel("Primary Key:");
		lblPrimaryKey.setBounds(10, 6, 109, 14);
		affinePanel.add(lblPrimaryKey);
		
		affineAKeyChoice = new Choice();
		affineAKeyChoice.setBounds(187, 10, 61, 20);
		affinePanel.add(affineAKeyChoice);
		
		JLabel lblSecondaryKey = new JLabel("Secondary Key:");
		lblSecondaryKey.setBounds(10, 35, 109, 14);
		affinePanel.add(lblSecondaryKey);
		
		affineBKeyChoice = new Choice();
		affineBKeyChoice.setBounds(187, 36, 61, 20);
		affinePanel.add(affineBKeyChoice);
		
		outPanel = new JPanel();
		outPanel.setBounds(10, 315, 414, 194);
		contentPane.add(outPanel);
		outPanel.setLayout(null);
		
		JLabel lblEnterMessageHere = new JLabel("Enter Message Here:");
		lblEnterMessageHere.setBounds(10, 5, 137, 14);
		outPanel.add(lblEnterMessageHere);
		
		messageTextPane = new JTextPane();
		messageTextPane.setBounds(10, 30, 394, 100);
		outPanel.add(messageTextPane);
		
		btnEncode = new JButton("Encode");
		btnEncode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switch(flag)
				{
				case 0:
					encodeShift();
					break;
				case 1:
					encodeAffine();
					break;
				case 2:
					encodeVigenereShift();
					break;
				default:
					break;
				
				
				
				
				
				}
					
			}

			
		});
		btnEncode.setBounds(10, 141, 89, 23);
		outPanel.add(btnEncode);
		
		btnDecode = new JButton("Decode");
		btnDecode.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switch(flag)
				{
				case 0:
					decodeShift();
					break;
				case 1:
					decodeAffine();
					break;
				case 2:
					decodeVigenereShift();
					break;
				default:
					break;
				
				
				
				
				
				}
			}
		});
		btnDecode.setBounds(109, 141, 89, 23);
		outPanel.add(btnDecode);
		
		JPanel encodingPanel = new JPanel();
		encodingPanel.setBounds(10, 11, 414, 74);
		contentPane.add(encodingPanel);
		encodingPanel.setLayout(null);
		
		JLabel lblSelectEncodingMethod = new JLabel("Select Encoding Method");
		lblSelectEncodingMethod.setBounds(10, 11, 222, 14);
		encodingPanel.add(lblSelectEncodingMethod);
		
		tglbtnShift = new JToggleButton("Shift");
		tglbtnShift.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				shiftPanel.setVisible(true);
				affinePanel.setVisible(false);
				vigenerePanel.setVisible(false);
				flag=0;
			}
		});
		encodeButtonGroup.add(tglbtnShift);
		tglbtnShift.setBounds(10, 32, 121, 23);
		encodingPanel.add(tglbtnShift);
		
		tglbtnAffine = new JToggleButton("Affine");
		tglbtnAffine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				shiftPanel.setVisible(false);
				affinePanel.setVisible(true);
				vigenerePanel.setVisible(false);
				flag=1;
			}
		});
		encodeButtonGroup.add(tglbtnAffine);
		tglbtnAffine.setBounds(141, 32, 121, 23);
		encodingPanel.add(tglbtnAffine);
		
		tglbtnVigenere = new JToggleButton("Vigenere");
		tglbtnVigenere.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				shiftPanel.setVisible(false);
				affinePanel.setVisible(false);
				vigenerePanel.setVisible(true);
				flag=2;
			}
		});
		encodeButtonGroup.add(tglbtnVigenere);
		tglbtnVigenere.setBounds(283, 32, 121, 23);
		encodingPanel.add(tglbtnVigenere);
		
		alphabetPanel = new JPanel();
		alphabetPanel.setBounds(10, 96, 414, 46);
		contentPane.add(alphabetPanel);
		alphabetPanel.setLayout(null);
		
		JLabel lblSelectAlphabet = new JLabel("Select Alphabet");
		lblSelectAlphabet.setBounds(10, 11, 124, 14);
		alphabetPanel.add(lblSelectAlphabet);
		
		tglbtnBasic = new JToggleButton("Basic");
		tglbtnBasic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				shiftKeyChoice.removeAll();
				affineAKeyChoice.removeAll();
				affineBKeyChoice.removeAll();
				extended=false;
				for(int i=0; i<alpha.getSize(); i++)
				{
					shiftKeyChoice.add(i+"");
					affineBKeyChoice.add(i+"");
				}
				Integer[] rprime=pG.getRelPrime(alpha.getSize());
				for(int i=0; i<rprime.length; i++)
				{
					affineAKeyChoice.add(rprime[i]+"");
				}
				
			}
		});
		alphabetButtonGroup.add(tglbtnBasic);
		tglbtnBasic.setBounds(125, 7, 121, 23);
		alphabetPanel.add(tglbtnBasic);
		
		tglbtnExtended = new JToggleButton("Extended");
		tglbtnExtended.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				shiftKeyChoice.removeAll();
				affineAKeyChoice.removeAll();
				affineBKeyChoice.removeAll();
				extended=true;
				for(int i=0; i<beta.getSize(); i++)
				{
					shiftKeyChoice.add(i+"");
					affineBKeyChoice.add(i+"");
				}
				Integer[] rprime=pG.getRelPrime(beta.getSize());
				for(int i=0; i<rprime.length; i++)
				{
					affineAKeyChoice.add(rprime[i]+"");
				}
			}
		});
		alphabetButtonGroup.add(tglbtnExtended);
		tglbtnExtended.setBounds(256, 7, 121, 23);
		alphabetPanel.add(tglbtnExtended);
		
		vigenerePanel = new JPanel();
		vigenerePanel.setBackground(Color.GRAY);
		vigenerePanel.setBounds(10, 153, 414, 131);
		vigenerePanel.setVisible(false);
		contentPane.add(vigenerePanel);
		
		JLabel lblKey_1 = new JLabel("Key:");
		vigenerePanel.add(lblKey_1);
		
		vigenereKeyField = new JTextField();
		vigenereKeyField.setToolTipText("Enter a String to use as a key");
		vigenerePanel.add(vigenereKeyField);
		vigenereKeyField.setColumns(10);
	}

	protected void decodeVigenereShift() {
		String message=messageTextPane.getText();
		String key=vigenereKeyField.getText();
		if(message !=null && key!=null && !message.equals("") && !key.equals(""))
		{
			String cipher=vShift.decode(message, key, extended);
			messageTextPane.setText(cipher);
			
		}
		else JOptionPane.showMessageDialog(null,"You need to add a message and key!");
		
	}

	protected void decodeAffine() {
		String message=messageTextPane.getText();
		String aKey=affineAKeyChoice.getSelectedItem();
		String bKey=affineBKeyChoice.getSelectedItem();
		if(message!=null && !message.equals("") && aKey!=null && bKey!=null)
		{
			String cipher=affine.decode(message, Integer.parseInt(aKey), Integer.parseInt(bKey), extended);
			messageTextPane.setText(cipher);
		}
		else JOptionPane.showMessageDialog(null,"You need to add a message and key!");
		
	}

	protected void decodeShift() {
		String message=messageTextPane.getText();
		String key=shiftKeyChoice.getSelectedItem();
		if(message!=null && !message.equals("") && key != null)
		{
			String cipher=shift.decode(message, Integer.parseInt(key), extended);
			messageTextPane.setText(cipher);
		}
		else JOptionPane.showMessageDialog(null,"You need to add a message and key!");
		
	}

	protected void encodeVigenereShift() {
		String message=messageTextPane.getText();
		String key=vigenereKeyField.getText();
		if(message !=null && key!=null && !message.equals("") && !key.equals(""))
		{
			String cipher=vShift.encode(message, key, extended);
			messageTextPane.setText(cipher);
			
		}
		else JOptionPane.showMessageDialog(null,"You need to add a message and key!");
	}

	protected void encodeAffine() {
		String message=messageTextPane.getText();
		String aKey=affineAKeyChoice.getSelectedItem();
		String bKey=affineBKeyChoice.getSelectedItem();
		if(message!=null && !message.equals("") && aKey!=null && bKey!=null)
		{
			String cipher=affine.encode(message, Integer.parseInt(aKey), Integer.parseInt(bKey), extended);
			messageTextPane.setText(cipher);
		}
		else JOptionPane.showMessageDialog(null,"You need to add a message and key!");
	}

	protected void encodeShift() {
		
		String message=messageTextPane.getText();
		String key=shiftKeyChoice.getSelectedItem();
		if(message!=null && !message.equals("") && key != null)
		{
			String cipher=shift.encode(message, Integer.parseInt(key), extended);
			messageTextPane.setText(cipher);
		}
		else JOptionPane.showMessageDialog(null,"You need to add a message and key!");
			
	}
}
