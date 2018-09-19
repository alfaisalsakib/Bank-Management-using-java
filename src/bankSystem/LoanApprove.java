package bankSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class LoanApprove implements ActionListener
{
	JFrame[] window = new JFrame[100];
	
	JButton[] Accept = new JButton[100];
	JButton[] Decline = new JButton[100];
	
	private int req;
	private String[] id;
	private String[] amount;
	private accountsInfo[] ob;
	private int index;
	
	public LoanApprove()
	{
		id = new String[1000];
		amount = new String[1000];
		
		manageAccount mg = new manageAccount();
		ob = mg.accountInitialization();
		index = mg.TotalAccount();
		
		try
		{
			File f = new File("loanReq.txt");
			Scanner input = new Scanner(f);
			
			for(int i=0;input.hasNextLine();i++)
			{
				id[i] = input.nextLine();
				amount[i] = input.nextLine();
				req++;
			}
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public void iniWindow()
	{
		int count=0;
		GridLayout ly = new GridLayout(3,2,5,5);
		
		JOptionPane.showMessageDialog(null, "You have "+req+" loan request.");
		
		for(int i=0;i<req;i++)
		{
			window[i] = new JFrame();
			Accept[i] = new JButton("Accept");
			Decline[i] = new JButton("Decline");
			
			JLabel idLabel = new JLabel("Account No. "+id[i]);
			JLabel empty1 = new JLabel("");
			JLabel amLabel = new JLabel("Requested Amount "+amount[i]);
			JLabel empty2 = new JLabel("");
			
			window[i].setLayout(ly);
			
			window[i].add(idLabel);
			window[i].add(empty1);
			window[i].add(amLabel);
			window[i].add(empty2);
			window[i].add(Accept[i]);
			window[i].add(Decline[i]);
			
			window[i].setTitle("Loan Approve");
			window[i].setSize(500,200);
			window[i].setLocation(280, 280);
			Accept[i].addActionListener(this);
			Decline[i].addActionListener(this);
			window[i].setVisible(true);
			
			count++;	
		}
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		for(int i=0;i<req;i++)
		{
			if(e.getSource() == Accept[i])
			{
				for(int j=0;j<index;j++)
				{
					if(id[i].equals(ob[j].getAccountNo()))
					{
						String req = amount[i];
						float reqAmount = Float.parseFloat(req);
						float oldAmount = Float.parseFloat(ob[j].getDeposit());
						float currentAmount = reqAmount+oldAmount;
						String curAmount = Float.toString(currentAmount);
						ob[j].setDeposit(curAmount);
						fileModify(curAmount,j);
						JOptionPane.showMessageDialog(null,"Account No "
								+ ob[j].getAccountNo()+"\nYour new balance is " + ob[j].getDeposit());
						window[i].dispose();
						
					}	
				}
				
				try
				{
					FileWriter fw = new FileWriter("loanReq.txt",false);
					fw.close();
				}
				catch(Exception d){
					
				}
				
			}
			else if(e.getSource() == Decline[i])
			{
				JOptionPane.showMessageDialog(null, "Account : "+id[i]+"\nRequest has been canceled");
				window[i].dispose();
				try
				{
					FileWriter fw = new FileWriter("loanReq.txt",false);
					fw.close();
				}
				catch(Exception d){
					
				}
			}
		}
	}
	
	public void fileModify(String newBalance,int index)
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
