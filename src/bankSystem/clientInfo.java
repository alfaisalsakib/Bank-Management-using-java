package bankSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class clientInfo implements ActionListener  
{
	JFrame window = new JFrame();
	
	JButton Withdraw = new JButton("Withdraw");
	JButton Deposit = new JButton("Diposit"); 
	JButton LogOut = new JButton("Log Out");
	
	JTextField deposit = new JTextField();
	JTextField withdraw = new JTextField();
	
	private accountsInfo ob;
	private int index;
	
	public clientInfo(accountsInfo ob, int index)
	{
		this.ob = ob;
		this.index = index;
	}
	
	public void initial()
	{
		
		JLabel depositL = new JLabel("Diposit Money");
		JLabel withdrawL = new JLabel("Withdraw Money");
		JLabel em1 = new JLabel("                    "
				+ "                                 Account NO.");
		JLabel em2 = new JLabel(ob.getAccountNo());
		
		
		GridLayout gl = new GridLayout(5,2,5,5);
		window.setLayout(gl);
		
		window.add(em1);
		window.add(em2);
		window.add(depositL);
		window.add(deposit);
		window.add(withdrawL);
		window.add(withdraw);
		
		window.add(Withdraw);
		window.add(Deposit);
		window.add(LogOut);
		
		window.setTitle("Client Info");
		window.setSize(500, 200);
		window.setLocation(280, 280);
		//window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Deposit.addActionListener(this);
		Withdraw.addActionListener(this);
		LogOut.addActionListener(this);
		
		window.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == Deposit)
		{
			try
			{
				String dep = ob.getDeposit();
				float DepBalance = Float.parseFloat(dep);
				float b = Float.parseFloat(deposit.getText());
				
				if(b<0)
				{
					JOptionPane.showMessageDialog(null,"Account No "
							+ ob.getAccountNo()+"\nPlease Enter Valid Amount");
				}
				else
				{
					
					float newBalance = DepBalance + b;
					String NewBalance = Float.toString(newBalance);
					ob.setDeposit(NewBalance);
					fileModify(NewBalance);
					JOptionPane.showMessageDialog(null,"Account No "
					+ ob.getAccountNo()+"\nYour new balance is " + ob.getDeposit());
				}
				
				JOptionPane.showMessageDialog(null,"Account No "
						+ ob.getAccountNo()+"\nTrasaction Completed.");
			}
			catch(Exception a){
				JOptionPane.showMessageDialog(null, "Balance is not being modified"
						+ " for Unknown digits\n");
			}
			
		}
		
		else if(e.getSource() == LogOut)
		{
			window.dispose();
		}
		else if(e.getSource() == Withdraw)
		{
			try
			{
				String wit = ob.getDeposit();
				float oldBalance = Float.parseFloat(wit);
				float witBalance = Float.parseFloat(withdraw.getText());
				
				if(witBalance > oldBalance)
				{
					JOptionPane.showMessageDialog(null,"Account No "
							+ ob.getAccountNo()+"\nYou have not enough balance");
				}
				else if(witBalance < 0)
				{
					JOptionPane.showMessageDialog(null,"Account No "
							+ ob.getAccountNo()+"\nPlease Enter Valid Amount");
				}
				else
				{
					float newBalance =oldBalance - witBalance;
					String NewBalance = Float.toString(newBalance);
					ob.setDeposit(NewBalance);
					fileModify(NewBalance);
					JOptionPane.showMessageDialog(null,"Account No "
							+ ob.getAccountNo()+"\nYour new balance is " + ob.getDeposit());
				}
				
				JOptionPane.showMessageDialog(null,"Account No "
						+ ob.getAccountNo()+"\nTrasaction Completed.");
			}
			catch(Exception w){
				JOptionPane.showMessageDialog(null, "Balance is not being modified"
						+ " for Unknown digits\n");
			}
		}		
	}
	
	public void fileModify(String newBalance)
	{
		String[] info = new String[1000];
		int count=0;
		try
		{
			File f = new File("clientInfo.txt");
			Scanner input = new Scanner(f);
			
			for(int i=0;input.hasNextLine();i++)
			{
				info[i] = input.nextLine();
				count++;
			}
			for(int i=5;i<=count;i=i+6)
			{
				if((i-index)%5==0)
				{
					info[i] = newBalance;
				}
			}
							
		}
		catch(Exception f){
			System.out.println(f.toString());
		}
		
		
		try
		{
			FileWriter fw = new FileWriter("clientInfo.txt",false);
			for(int i=0;i<count;i++)
			{
				fw.write(info[i]);
				fw.write("\r\n");
			}
			fw.close();
		}
		catch(Exception s) {
			System.out.println(s.toString());
		}
	}
}
