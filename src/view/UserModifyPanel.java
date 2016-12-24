
package view;

import java.awt.Dimension;

import javax.swing.JPanel;

import controller.Controller;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class UserModifyPanel extends JPanel
{	
	private Controller controller;
	private JTextField txtLogin;
	private JTextField txtPassword;
	public UserModifyPanel(int width,int height,Controller c)
	{
		controller = c;
		
		setPreferredSize(new Dimension(400, 400));
		setFocusable(true);
		setLayout(null);
		setName("UserModify");
		
		JLabel lblInformationAboutUser = new JLabel("Information About User");
		lblInformationAboutUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformationAboutUser.setBounds(96, 11, 173, 14);
		add(lblInformationAboutUser);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(27, 50, 66, 14);
		add(lblLogin);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(27, 94, 66, 14);
		add(lblPassword);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(224, 246, 89, 23);
		add(btnBack);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(51, 246, 89, 23);
		add(btnAdd);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(137, 47, 86, 20);
		add(txtLogin);
		txtLogin.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(137, 91, 86, 20);
		add(txtPassword);
		txtPassword.setColumns(10);
	}
}
