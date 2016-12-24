package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import controller.Controller;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;


public class LoginPanel  extends JPanel
{
	
	private Controller controller;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblLogin;
	private JLabel lblPassword;
	private JButton btnConfirm;
	private JLabel lblLogInPlease;
	
	public LoginPanel(int width,int height,Controller c)
	{
		
		setPreferredSize(new Dimension(400, 400));
		setFocusable(true);
		setLayout(null);
		this.setName("Login");
		
		lblLogin = new JLabel("Login");
		lblLogin.setBounds(56, 99, 75, 14);
		add(lblLogin);
		
		lblPassword = new JLabel("Password");
		lblPassword.setBounds(56, 160, 75, 14);
		add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(141, 96, 127, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(141, 157, 127, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(141, 207, 89, 23);
		add(btnConfirm);
		
		lblLogInPlease = new JLabel("Sign in, please");
		lblLogInPlease.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogInPlease.setBounds(56, 294, 212, 14);
		add(lblLogInPlease);
		controller = c;
	}
	
	public void setupListeners()
	{
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login = textField.getText();
				String password = textField_1.getText();
				controller.checkLogin(login,password);
			}
		});
	};
	
	public void unsuccessfulLogin(String information)
	{
		lblLogInPlease.setText(information+", try once again");
	}
}
