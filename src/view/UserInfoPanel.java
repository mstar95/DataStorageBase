package view;

import java.awt.Dimension;

import javax.swing.JPanel;

import controller.Controller;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class UserInfoPanel extends JPanel
{	
	private Controller controller;
	public UserInfoPanel(int width,int height,Controller c)
	{
		controller = c;
		
		setPreferredSize(new Dimension(400, 400));
		setFocusable(true);
		setLayout(null);
		setName("UserInfo");
		
		JLabel lblInformationAboutUser = new JLabel("Information About User");
		lblInformationAboutUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformationAboutUser.setBounds(96, 11, 173, 14);
		add(lblInformationAboutUser);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(27, 56, 66, 14);
		add(lblId);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(27, 94, 66, 14);
		add(lblLogin);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(27, 138, 66, 14);
		add(lblPassword);
		
		JLabel lblAdminRights = new JLabel("Admin Rights");
		lblAdminRights.setBounds(27, 185, 66, 14);
		add(lblAdminRights);
		
		JLabel lblIdVar = new JLabel("New label");
		lblIdVar.setBounds(108, 56, 46, 14);
		add(lblIdVar);
		
		JLabel lblLoginVar = new JLabel("New label");
		lblLoginVar.setBounds(108, 94, 46, 14);
		add(lblLoginVar);
		
		JLabel lblPasswordVar = new JLabel("New label");
		lblPasswordVar.setBounds(108, 138, 46, 14);
		add(lblPasswordVar);
		
		JLabel lblAdminRightsVar = new JLabel("New label");
		lblAdminRightsVar.setBounds(108, 185, 46, 14);
		add(lblAdminRightsVar);
		
		JButton btnModify = new JButton("Modify");
		btnModify.setBounds(4, 246, 89, 23);
		add(btnModify);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(116, 246, 89, 23);
		add(btnDelete);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(224, 246, 89, 23);
		add(btnBack);
	}
}
