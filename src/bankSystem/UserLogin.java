package bankSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class UserLogin implements ActionListener
{
	JFrame window = new JFrame();
	
	JButton login = new JButton("Login");
	JButton cancel = new JButton("cancel");
	
	JTextField id = new JTextField();
	JPasswordField password = new JPasswordField();
	
	public void iniWindow()
	{
		JLabel idLabel = new JLabel("Enter ID");
		JLabel passLabel = new JLabel("Password");
		
		GridLayout gl = new GridLayout(3,2,5,5);
		window.setLayout(gl);
		
		window.add(idLabel);
		window.add(id);
		
		window.add(passLabel);
		window.add(password);
		
		window.add(login);
		window.add(cancel);
		
		
		window.setTitle("User Login");
		window.setSize(500,200);
		window.setLocation(300, 300);
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		login.addActionListener(this);
		cancel.addActionListener(this);
		
		window.setVisible(true);
	}



	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == login)
		{
			boolean isFound = authentication();
			
			if(isFound == true)
			{
				JOptionPane.showMessageDialog(null, id.getText() + " successfully Login");
				window.dispose();
				
				
				manageAccount mga = new manageAccount();
				accountsInfo[] obj = mga.accountInitialization();
				
				String key = id.getText();
				
				try
				{
					for(int i =0;i<obj.length;i++)
					{	
						if(key.equals(obj[i].getAccountNo()))
						{
							JOptionPane.showMessageDialog(null, "Account No : " + id.getText() + ""
									+ "\nName  : " + obj[i].getName() + "\nAddress : "+ obj[i].getAddress()
									+ "\nMobile : " + obj[i].getMobileNo() + "\nBalance : " + obj[i].getDeposit());
							selfModify sm = new selfModify(obj[i],i);
							sm.initial();
							
							break;
						}					
					}		
				}
				
				catch(Exception s){
					System.out.println(s.toString());
				}
				
			}
			
			
			else
				JOptionPane.showMessageDialog(null, "Invalid Id or password.");			
			
		}
	
		else if(e.getSource() == cancel)
		{	
			window.dispose();
		}		
		
	}
	
	
	public boolean authentication()
	{
		
		try
		{
			
			File f = new File("userInfo.txt");
			
			Scanner input = new Scanner(f);
			
			String i = id.getText();
			String p = password.getText();
			
			while(input.hasNextLine() == true)
			{
				if(i.equals(input.nextLine()) == true && p.equals(input.nextLine()) == true)
					return true;			
			}
			
			input.close();
		}
		
		
		catch(Exception e)
		{
			System.out.println("File error");
		}
		
		
		return false;
				
	}
	
	
}
