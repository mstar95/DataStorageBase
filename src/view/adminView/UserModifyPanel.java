
package view.adminView;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import controller.Controller;
import exceptions.ControllerException;
import model.User;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class UserModifyPanel extends JPanel
{	
	private Controller controller;
	private User user;
	private boolean modify;
	
	private JTextField txtLogin,txtPassword;
	private JButton btnAdd,btnBack;
	private JLabel lblInfo;
	public UserModifyPanel(int width,int height,Controller c)
	{
		controller = c;
		
		setPreferredSize(new Dimension(400, 400));
		setFocusable(true);
		setLayout(null);
		setName("UserModify");
		
		JLabel lblInformationAboutUser = new JLabel("Add user, please");
		lblInformationAboutUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformationAboutUser.setBounds(96, 11, 173, 14);
		add(lblInformationAboutUser);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setBounds(27, 50, 66, 14);
		add(lblLogin);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(27, 94, 66, 14);
		add(lblPassword);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(224, 246, 89, 23);
		add(btnBack);
		
		btnAdd = new JButton("Add");
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
		
		lblInfo = new JLabel("");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(51, 307, 262, 14);
		add(lblInfo);
	}
	
	public void setupListeners()
	{
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login = txtLogin.getText().trim();
				String password = txtPassword.getText().trim();
				if(login.equals(""))
				{
					lblInfo.setText("Login is blank");
					return;
				}
				if(password.equals(""))
				{
					lblInfo.setText("Password is blank");
					return;
				}
				try {
					if(modify)
					{
						user.setLogin(login);
						user.setPassword(password);
						controller.updateUser(user);
					}
					else
					{
						user = new User(login,password,false);	
						controller.addUser(user);
					}
				} catch (ControllerException e1) {
					lblInfo.setText(e1.getMessage());
				}
				}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.getView().showAdminPanel();
			}
		});
	}
	
	public void setVariables(User user)
	{
		modify = true;
		this.user = user;
		txtLogin.setText(user.getLogin());
		txtPassword.setText(user.getPassword());
	}
	
	public void resetVariables()
	{
		modify = false;
		txtLogin.setText("");
		txtPassword.setText("");
	}
}
