package bankSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class ChooseOptions implements ActionListener 
{
	JFrame window = new JFrame();
	
	JButton Admin = new JButton("Admin Login");
	JButton User = new JButton("User Login");
	
	public ChooseOptions(){}
	
	public void iniWindow()
	{
		JLabel info = new JLabel("                      Welcome to online Banking System");
		
		
		GridLayout ly = new GridLayout(3,2,50,50);
		window.setLayout(ly);
		
		window.add(info);
		window.add(Admin);
		window.add(User);
		
		window.setTitle("Login Options");
		window.setSize(400, 400);
		window.setLocation(280, 280);
		//window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		Admin.addActionListener(this);
		User.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getSource() == Admin)
		{
			LoginWindow lw = new LoginWindow();
			lw.initGUI();
		}
		else if(e.getSource() == User)
		{
			UserLogin ul = new UserLogin();
			ul.iniWindow();
		}
		
	}
}
