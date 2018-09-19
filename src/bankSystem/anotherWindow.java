package bankSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class anotherWindow implements ActionListener
{

	JButton search = new JButton("Search");
	JButton CreateAcc = new JButton("Create Account");
	JButton Delete = new JButton("Delete Account");
	JButton LoanReq = new JButton("Loan Request");
	JButton LogOut = new JButton("Log out");
	
	JTextField accountNo = new JTextField();
	
	JFrame window = new JFrame();
	
	public anotherWindow(){}
	
	public void initWindow()
	{	
		JLabel userLabel = new JLabel("Enter Account No.");
		JLabel empty1 = new JLabel();
		JLabel empty2 = new JLabel();
		
		GridLayout gl = new GridLayout(5,1,5,5);
		window.setLayout(gl);
		
		window.add(userLabel);
		window.add(accountNo);
		
		window.add(empty1);
		window.add(empty2);
		
		window.add(search);
		window.add(CreateAcc);
		window.add(Delete);
		window.add(LoanReq);
		window.add(LogOut);
		
		window.setTitle("search Window");
		window.setSize(500 ,200);
		window.setLocation(280, 280);
		//window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		search.addActionListener(this);
		CreateAcc.addActionListener(this);
		Delete.addActionListener(this);
		LoanReq.addActionListener(this);
		LogOut.addActionListener(this);
		
		window.setVisible(true);		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getSource() == search)
		{
			manageAccount mga = new manageAccount();
			accountsInfo[] obj = mga.accountInitialization();	
			String key = accountNo.getText();
			
			boolean found = false;
			try
			{
				for(int i =0;i<obj.length;i++)
				{
					
					if(key.equals(obj[i].getAccountNo()))
					{
						JOptionPane.showMessageDialog(null, accountNo.getText() + " is Found \n");
						JOptionPane.showMessageDialog(null, "Account No : " + accountNo.getText() + ""
								+ "\nName  : " + obj[i].getName() + "\nAddress : "+ obj[i].getAddress()
								+ "\nMobile : " + obj[i].getMobileNo() + "\nBalance : " + obj[i].getDeposit());
						clientInfo ci = new clientInfo(obj[i],i);
						ci.initial();
						found = true;
						break;
					}					
				}
				
			}
			
			catch(Exception s){}
			
			if(found ==false)
				JOptionPane.showMessageDialog(null, key + " is INVALID Account\n");
		}			
		
		else if(e.getSource() == LogOut)
		{
			window.dispose();
		}
		
		else if(e.getSource() == CreateAcc)
		{	
			openAccount op = new openAccount();
			op.initial();
		}
		
		else if(e.getSource() == LoanReq)
		{
			LoanApprove lp = new LoanApprove();
			lp.iniWindow();
		}
		
		else if(e.getSource() == Delete)
		{
			manageAccount mga = new manageAccount();
			accountsInfo[] obj = mga.accountInitialization();	
			String key = accountNo.getText();
			
			boolean found = false;
			
			try
			{
				for(int i =0;i<obj.length;i++)
				{
					
					if(key.equals(obj[i].getAccountNo()))
					{
						JOptionPane.showMessageDialog(null, accountNo.getText() + " will be deleted \n");
						obj[i].setAccountNo(null);
						obj[i].setName(null);
						obj[i].setDOB(null);
						obj[i].setAddress(null);
						obj[i].setMobileNo(null);
						obj[i].setDeposit(null);
						modifyFile(i);
						modifyFileUser(i);
						JOptionPane.showMessageDialog(null, accountNo.getText() + " is deleted \n");
						found = true;
						break;
					}					
				}				
			}  catch(Exception s){}	
			
			if(found ==false)
				JOptionPane.showMessageDialog(null, key + " is INVALID Account"
						+ "\nCan not be Deleted");
			
		}
	}
	
	public void modifyFile(int index)
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
			int startIndex = index*6;
			int endIndex = startIndex+6;
			
			for(int i=startIndex;i<endIndex;i++)
			{
				info[i] = "--------";
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
	
	public void modifyFileUser(int index)
	{
		String[] info = new String[1000];
		int count=0;
		try
		{
			File f = new File("userInfo.txt");
			Scanner input = new Scanner(f);
			
			for(int i=0;input.hasNextLine();i++)
			{
				info[i] = input.nextLine();
				count++;
			}
			int startIndex = index*2;
			int endIndex = startIndex+1;
			
			for(int i=startIndex;i<endIndex;i++)
			{
				info[i] = "--------";
			}
							
		}
		catch(Exception f){
			System.out.println(f.toString());
		}
		
		
		try
		{
			FileWriter fw = new FileWriter("userInfo.txt",false);
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
