package view;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import controller.Controller;

public class View
{
	private final int WIDTH = 400;
	private final int HEIGHT = 400;
	private Controller controller;
	
	private JFrame frame;
	JPanel contentPanel;
	LoginPanel loginPanel;
	AdminPanel adminPanel;
	UserModifyPanel userModifyPanel;
	UserInfoPanel userInfoPanel;
	
	CardLayout cardLayout;
	
	public View(Controller c) 
	{
		controller = c;

		createGUI();
		//setupObservers();
		setupListeners();
	}
	
	private void createGUI()
	{
		frame = new JFrame("DataStorage");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		contentPanel = new JPanel();
		frame.add(contentPanel);
		
		cardLayout = new CardLayout();
		contentPanel.setLayout(cardLayout);
		
		loginPanel = new LoginPanel(WIDTH,HEIGHT,controller);
		adminPanel = new AdminPanel(WIDTH,HEIGHT,controller);
		userModifyPanel = new UserModifyPanel(WIDTH,HEIGHT,controller);
		userInfoPanel = new UserInfoPanel(WIDTH,HEIGHT,controller);
		
		contentPanel.add(loginPanel,loginPanel.getName());
		contentPanel.add(adminPanel,adminPanel.getName());
		contentPanel.add(userModifyPanel,userModifyPanel.getName());
		contentPanel.add(userInfoPanel,userInfoPanel.getName());
		
		cardLayout.show(contentPanel, "Admin");
		frame.pack();
	}
	
	private void setupListeners()
	{
		
		loginPanel.setupListeners();
		adminPanel.setupListeners();
	}
	
	public void showLoginPanel()
	{
		cardLayout.show(contentPanel, loginPanel.getName());
	}
	
	public void successfulAdminLogin()
	{
		showAdminPanel();
	}
	
	public void showAdminPanel()
	{
		adminPanel.updateList();
		cardLayout.show(contentPanel, adminPanel.getName());
	}
	
	public void unsuccessfulLogin(String information)
	{
		loginPanel.unsuccessfulLogin(information);
		showLoginPanel();
	}
	public void successfulUserLogin()
	{
		//cardLayout.show(contentPanel, userModifyPanel.getName());
	}
	
	public void showUserPanel()
	{
		//cardLayout.show(contentPanel, adminPanel.getName());
	}
	
	public void showUserModifyPanel()
	{
		cardLayout.show(contentPanel, userModifyPanel.getName());
	}
	
	public void showUserInfoPanel()
	{
		cardLayout.show(contentPanel, userInfoPanel.getName());
	}
}
