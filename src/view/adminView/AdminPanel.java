package view.adminView;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import controller.Controller;
import exceptions.DaoException;
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
	private JButton btnNewUser, btnDeleteUser,btnUserInfo,btnLogout,btnModifyUser;
	private JList list;
	private JLabel lblInfo;

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
		
		btnLogout = new JButton("Logout");
		btnLogout.setBounds(121, 369, 106, 23);
		add(btnLogout);
		
		btnModifyUser = new JButton("Modify User");
		btnModifyUser.setBounds(237, 335, 121, 23);
		add(btnModifyUser);
		
		lblInfo = new JLabel("");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(43, 310, 260, 14);
		add(lblInfo);
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
				if(checkIfValueIsSelected() == false)
					return;
				User user = (User)list.getSelectedValue();
				if(user.isAdmin())
					lblInfo.setText("Admin is protected");
				else
				{
					try {
						controller.deleteUser(user);
						updateList();
					} catch (DaoException e1) {
						lblInfo.setText(e1.getMessage());
					}
				}
					
			}
		});
		
		btnUserInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkIfValueIsSelected() == false)
					return;
				User user = (User)list.getSelectedValue();
				controller.getView().showUserInfoPanel(user);
			}
		});
		
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.getView().showLoginPanel();
			}
		});
		
		btnModifyUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkIfValueIsSelected() == false)
					return;
				User user = (User)list.getSelectedValue();
				if(user.isAdmin())
					lblInfo.setText("Admin is protected");
				else
				{
				controller.getView().showUserModifyPanel(user);
				}
			}
		});
		
	};
	
	public void updateList()
	{
		users = controller.getUsers();
		listModel.clear();
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
	
	public boolean checkIfValueIsSelected()
	{
		if( list.getSelectedValue() == null)
		{
			lblInfo.setText("Select Object, please");
			return false;
		}
		return true;
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
	    if (e.getValueIsAdjusting() == false) {

	        if (list.getSelectedIndex() == -1) {
	        //No selection, disable fire button.
	           // fireButton.setEnabled(false);

	        } else {
	        //Selection, enable the fire button.
	           // fireButton.setEnabled(true);
	        }
	    }
	}
	
	
}
