package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import controller.Controller;
import model.User;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;

public class AdminPanel extends JPanel implements ListSelectionListener
{
	private Controller controller;
	
	private Map<Integer,User> users;
	private DefaultListModel<User> listModel;
	
	private JLabel lblNewLabel;
	private JButton btnNewUser, btnDeleteUser,btnUserInfo;
	private JList list;
	private JButton btnNewButton;
	private JButton btnModifyUser;
	public AdminPanel(int width,int height,Controller c)
	{
		controller = c;
		
		setPreferredSize(new Dimension(400, 400));
		setFocusable(true);
		setLayout(null);
		setName("Admin");
		
		lblNewLabel = new JLabel("Users");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(150, 25, 100, 14);
		add(lblNewLabel);
		
		btnNewUser = new JButton("New User");
		btnNewUser.setBounds(22, 335, 89, 23);
		add(btnNewUser);
		
		btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.setBounds(121, 335, 106, 23);
		add(btnDeleteUser);
		
		btnUserInfo = new JButton("User Info");
		btnUserInfo.setBounds(22, 369, 89, 23);
		add(btnUserInfo);
		
		listModel = new DefaultListModel<User>();
		list = new JList<User>(listModel);
		list.setBounds(43, 83, 260, 209);
		updateList();
		add(list);
		
		btnNewButton = new JButton("Logout");
		btnNewButton.setBounds(121, 369, 106, 23);
		add(btnNewButton);
		
		btnModifyUser = new JButton("Modify User");
		btnModifyUser.setBounds(237, 335, 121, 23);
		add(btnModifyUser);
	}
	
	public void setupListeners()
	{
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.getView().showUserModifyPanel();
			}
		});
		
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if()
			}
		});
	};
	
	public void updateList()
	{
		users = controller.getUsers();
		for(Entry<Integer, User> entry : users.entrySet())
		{
			int index = list.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

			listModel.addElement(entry.getValue());
			list.ensureIndexIsVisible(index);
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
