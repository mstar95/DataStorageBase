package view;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import controller.Controller;
import model.DataStorage;
import model.User;
import userView.DataStorageInfoPanel;
import userView.DataStorageModifyPanel;
import userView.UserPanel;
import view.adminView.AdminPanel;
import view.adminView.UserInfoPanel;
import view.adminView.UserModifyPanel;

public class View
{
	private final int WIDTH = 400;
	private final int HEIGHT = 400;
	private Controller controller;
	
	private JFrame frame;
	private JPanel contentPanel;
	private LoginPanel loginPanel;
	private AdminPanel adminPanel;
	private UserModifyPanel userModifyPanel;
	private UserInfoPanel userInfoPanel;
	private UserPanel userPanel;
	private DataStorageInfoPanel dataStorageInfoPanel;
	private DataStorageModifyPanel dataStorageModifyPanel;
	
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
		userPanel = new UserPanel(WIDTH,HEIGHT,controller);
		dataStorageInfoPanel = new DataStorageInfoPanel(WIDTH,HEIGHT,controller);
		dataStorageModifyPanel = new DataStorageModifyPanel(WIDTH,HEIGHT,controller);
		
		contentPanel.add(loginPanel,loginPanel.getName());
		contentPanel.add(adminPanel,adminPanel.getName());
		contentPanel.add(userModifyPanel,userModifyPanel.getName());
		contentPanel.add(userInfoPanel,userInfoPanel.getName());
		contentPanel.add(userPanel,userPanel.getName());
		contentPanel.add(dataStorageInfoPanel,dataStorageInfoPanel.getName());
		contentPanel.add(dataStorageModifyPanel,dataStorageModifyPanel.getName());
	
		//cardLayout.show(contentPanel, "Admin");
		cardLayout.show(contentPanel, "User");
		frame.pack();
	}
	
	private void setupListeners()
	{
		
		loginPanel.setupListeners();
		adminPanel.setupListeners();
		userModifyPanel.setupListeners();
		userInfoPanel.setupListeners();
		userPanel.setupListeners();
		dataStorageInfoPanel.setupListeners();
		dataStorageModifyPanel.setupListeners();
	}
	
	public void showLoginPanel()
	{
		loginPanel.reset();
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
	
	
	public void successfulUserLogin()
	{
		showUserPanel();
	}
	
	public void showUserPanel()
	{
		cardLayout.show(contentPanel, userPanel.getName());
	}
	
	public void showUserModifyPanel()
	{
		userModifyPanel.resetVariables();
		cardLayout.show(contentPanel, userModifyPanel.getName());
	}
	
	public void showUserModifyPanel(User user)
	{
		userModifyPanel.setVariables(user);
		cardLayout.show(contentPanel, userModifyPanel.getName());
	}
	
	public void showUserInfoPanel(User user)
	{
		userInfoPanel.setUserInfo(user);
		cardLayout.show(contentPanel, userInfoPanel.getName());
	}
	
	public void showDataStorageInfoPanel(DataStorage dataStorage)
	{
		dataStorageInfoPanel.setDataStorageInfo(dataStorage);
		cardLayout.show(contentPanel, dataStorageInfoPanel.getName());
	}
	
	public void showDataStorageModifyPanel()
	{
		dataStorageModifyPanel.resetVariables();
		cardLayout.show(contentPanel, dataStorageModifyPanel.getName());
	}
	
	public void showDataStorageModifyPanel(DataStorage dataStorage)
	{
		dataStorageModifyPanel.setVariables(dataStorage);
		cardLayout.show(contentPanel, dataStorageModifyPanel.getName());
	}
}
