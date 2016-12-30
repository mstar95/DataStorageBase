package view.adminView;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;

import javax.swing.JPanel;

import controller.Controller;
import exceptions.DaoException;
import model.User;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class UserInfoPanel extends JPanel
{	
	private Controller controller;
	private User user;
	
	private JLabel lblIdVar, lblLoginVar, lblPasswordVar, lblAdminRightsVar;
	JButton btnModify, btnDelete, btnBack;
	private JLabel lblInfo;
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
		
		JLabel lblAdminRights = new JLabel("Rights:");
		lblAdminRights.setBounds(27, 185, 66, 14);
		add(lblAdminRights);
		
		lblIdVar = new JLabel("New label");
		lblIdVar.setBounds(108, 56, 46, 14);
		add(lblIdVar);
		
		lblLoginVar = new JLabel("New label");
		lblLoginVar.setBounds(108, 94, 46, 14);
		add(lblLoginVar);
		
		lblPasswordVar = new JLabel("New label");
		lblPasswordVar.setBounds(108, 138, 46, 14);
		add(lblPasswordVar);
		
		lblAdminRightsVar = new JLabel("New label");
		lblAdminRightsVar.setBounds(108, 185, 46, 14);
		add(lblAdminRightsVar);
		
		btnModify = new JButton("Modify");
		btnModify.setBounds(4, 246, 89, 23);
		add(btnModify);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(116, 246, 89, 23);
		add(btnDelete);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(224, 246, 89, 23);
		add(btnBack);
		
		lblInfo = new JLabel("");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(4, 210, 307, 14);
		add(lblInfo);
	}
	
	public void setupListeners()
	{	
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user.isAdmin())
					lblInfo.setText("Admin is protected");
				else
				{
					try {
						controller.deleteUser(user);
						controller.getView().showAdminPanel();
					} catch (DaoException e1) {
						lblInfo.setText(e1.getMessage());
					}
				}
					
			}
		});
				
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user.isAdmin())
					lblInfo.setText("Admin is protected");
				else
				{
				controller.getView().showUserModifyPanel(user);
				}
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.getView().showAdminPanel();
			}
		});
		
	};
	

	public void setUserInfo(User user)
	{
		this.user = user;
		lblIdVar.setText(user.getId()+"");
		lblLoginVar.setText(user.getLogin());
		lblPasswordVar.setText(user.getPassword());
		if(user.isAdmin())
			lblAdminRightsVar.setText("Admin");
		else
			lblAdminRightsVar.setText("User");
		
	}
}
