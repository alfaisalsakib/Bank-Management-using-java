package bankSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class openAccount implements ActionListener 
{
	
	JFrame window = new JFrame();
	JButton create = new JButton("create");
	
	JTextField ACNumber = new JTextField();
	JTextField Name = new JTextField();
	JTextField BirthDate = new JTextField();
	JTextField Address = new JTextField();
	JTextField Mobile = new JTextField();
	JTextField DepositAm = new JTextField();
	
	public openAccount(){}
	
	public void initial()
	{
		JLabel acnumber = new JLabel("Account Number");
		JLabel name = new JLabel("Name");
		JLabel birthdate = new JLabel("Birth Date");
		JLabel address = new JLabel("Address");
		JLabel mobile = new JLabel("Mobile");
		JLabel depositam = new JLabel("Deposit Amount");
		
		GridLayout gl = new GridLayout(7,2,5,5);
		window.setLayout(gl);
		
		window.add(acnumber);
		window.add(ACNumber);
		window.add(name);
		window.add(Name);
		window.add(birthdate);
		window.add(BirthDate);
		window.add(address);
		window.add(Address);
		window.add(mobile);
		window.add(Mobile);
		window.add(depositam);
		window.add(DepositAm);
		
		window.setTitle("New Account");
		window.setSize(500,300);
		window.setLocation(280,280);
		//window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
		
		window.add(create);
		create.addActionListener(this);
		
		window.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == create)
		{
			try
			{
				FileWriter fw = new FileWriter("clientInfo.txt",true);
				
				fw.write(ACNumber.getText());
				fw.write("\r\n");
				fw.write(Name.getText());
				fw.write("\r\n");
				fw.write(BirthDate.getText());
				fw.write("\r\n");
				fw.write(Address.getText());
				fw.write("\r\n");
				fw.write(Mobile.getText());
				fw.write("\r\n");
				fw.write(DepositAm.getText());
				fw.write("\r\n");
				
				JOptionPane.showMessageDialog(null, ACNumber.getText() + "\nSuccesfully created");
				
				fw.close();
				
			}
			catch(Exception a){
				
			}
			
			try
			{
				FileWriter fw1 = new FileWriter("userInfo.txt",true);
				fw1.write(ACNumber.getText());
				fw1.write("\r\n");
				fw1.write(Name.getText());
				fw1.write("\r\n");
				fw1.close();
			}
			catch(Exception a){
				
			}
		}
	}
	
}
