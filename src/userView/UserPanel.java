package userView;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.Controller;
import exceptions.DaoException;
import model.DataStorage;
import model.User;

public class UserPanel extends JPanel implements ListSelectionListener
{
private Controller controller;
	
	private Map<Integer,DataStorage> dataStorages;
	private DefaultListModel<DataStorage> listModel;
	
	private JLabel lblNewLabel;
	private JButton btnNewDataStorage, btnDeleteDataStorage,btnDataStorageInfo,btnLogout,btnModifyDataStorage;
	private JList list;
	private JLabel lblInfo;

	public UserPanel(int width,int height,Controller c)
	{
		controller = c;
		
		setPreferredSize(new Dimension(400, 400));
		setFocusable(true);
		setLayout(null);
		setName("User");
		
		lblNewLabel = new JLabel("Data Storages");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(150, 25, 100, 14);
		add(lblNewLabel);
		
		btnNewDataStorage = new JButton("<html>New<br /><span>DataStorage</span></html>");
		btnNewDataStorage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewDataStorage.setBounds(10, 303, 101, 37);
		add(btnNewDataStorage);
		
		btnDeleteDataStorage = new JButton("<html>Delete<br/><span>DataStorage</span></html>");
		btnDeleteDataStorage.setBounds(121, 303, 106, 37);
		add(btnDeleteDataStorage);
		
		btnDataStorageInfo = new JButton("<html>DataStorage<br /><span>Info</span></html>");
		btnDataStorageInfo.setBounds(10, 351, 101, 41);
		add(btnDataStorageInfo);
		
		listModel = new DefaultListModel<DataStorage>();
		list = new JList<DataStorage>(listModel);
		list.setBounds(43, 50, 260, 209);
		updateList();
		add(list);
		
		btnLogout = new JButton("Logout");
		btnLogout.setBounds(121, 351, 106, 41);
		add(btnLogout);
		
		btnModifyDataStorage = new JButton("<html>Modify<br /><span>DataStorage</span></html>");
		btnModifyDataStorage.setBounds(237, 303, 121, 37);
		add(btnModifyDataStorage);
		
		lblInfo = new JLabel("");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(43, 270, 260, 14);
		add(lblInfo);
	}
	
	public void setupListeners()
	{
		btnNewDataStorage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.getView().showDataStorageModifyPanel();
			}
		});
		
		btnDeleteDataStorage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkIfValueIsSelected() == false)
					return;
				DataStorage dataStorage = (DataStorage)list.getSelectedValue();
				
				try {
					controller.deleteDataStorage(dataStorage);
					updateList();
				} catch (DaoException e1) {
					lblInfo.setText(e1.getMessage());
				}
				
					
			}
		});
		
		btnDataStorageInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkIfValueIsSelected() == false)
					return;
				DataStorage dataStorage = (DataStorage)list.getSelectedValue();
				controller.getView().showDataStorageInfoPanel(dataStorage);
			}
		});
		
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.getView().showLoginPanel();
			}
		});
		
		btnModifyDataStorage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkIfValueIsSelected() == false)
					return;
				DataStorage dataStorage = (DataStorage)list.getSelectedValue();
				
				//controller.getView().showUserModifyPanel(user);
			}
		});
		
	};
	
	public void updateList()
	{
		dataStorages = controller.getDataStorages();
		listModel.clear();
		for(Entry<Integer, DataStorage> entry : dataStorages.entrySet())
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
