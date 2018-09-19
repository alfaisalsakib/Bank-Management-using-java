package bankSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class selfModify implements ActionListener
{

	JFrame window = new JFrame();
	
	JButton Loan = new JButton("Loan Request");
	JButton Deposit = new JButton("Diposit"); 
	JButton LogOut = new JButton("Log out");
	
	JTextField deposit = new JTextField();
	JTextField loan = new JTextField();
	
	private accountsInfo ob;
	private int index;
	private accountsInfo[] obj;
	
	public selfModify(accountsInfo ob, int index)
	{
		this.ob = ob;
		this.index = index;
	}
	
	public void initial()
	{
		
		JLabel depositL = new JLabel("Diposit Money");
		JLabel loanL = new JLabel("Amount Loan");
		JLabel em1 = new JLabel("                    "
				+ "                                 Account NO.");
		JLabel em2 = new JLabel(ob.getAccountNo());
		
		
		GridLayout gl = new GridLayout(5,2,5,5);
		window.setLayout(gl);
		
		window.add(em1);
		window.add(em2);
		window.add(depositL);
		window.add(deposit);
		window.add(loanL);
		window.add(loan);
		window.add(Deposit);
		window.add(Loan);
		window.add(LogOut);
		
		
		window.setTitle("Client Info");
		window.setSize(500, 200);
		window.setLocation(280, 280);
		//window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Deposit.addActionListener(this);
		Loan.addActionListener(this);
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
		else if(e.getSource() == Loan)
		{
			try
			{
				FileWriter fw = new FileWriter("loanReq.txt",true);
				
				fw.write(ob.getAccountNo());
				fw.write("\r\n");
				fw.write(loan.getText());
				fw.write("\r\n");
				JOptionPane.showMessageDialog(null,"Account No "+ob.getAccountNo());
				JOptionPane.showMessageDialog(null,"Request for loan "+loan.getText()+
						" has been sent.\n");
				
				fw.close();
			}
			catch(Exception o){
				System.out.println(o.toString());
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