package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import controller.Controller;
import exceptions.ControllerException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;


public class LoginPanel  extends JPanel
{
	
	private Controller controller;
	private JTextField txtLogin,txtPassword;
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
		
		txtLogin = new JTextField();
		txtLogin.setBounds(141, 96, 127, 20);
		add(txtLogin);
		txtLogin.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(141, 157, 127, 20);
		add(txtPassword);
		txtPassword.setColumns(10);
		
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
				String login = txtLogin.getText();
				String password = txtPassword.getText();
				try {
					controller.checkLogin(login,password);
				} catch (ControllerException e1) {
					lblLogInPlease.setText(e1.getMessage()+", try once again");
				}
			}
		});
	};
	
	public void reset()
	{
		txtLogin.setText("");
		txtPassword.setText("");
	}
	
}
