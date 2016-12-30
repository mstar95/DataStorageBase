package userView;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;

import javax.swing.JPanel;

import controller.Controller;
import exceptions.DaoException;
import model.DataStorage;
import model.User;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class DataStorageInfoPanel extends JPanel
{	
	private Controller controller;
	private DataStorage dataStorage;
	
	private JLabel lblIdVar, lblTitleVar, lblMemoryCapacityVar, lblMemoryUsedVar, lblTypeVar;
	JButton btnModify, btnDelete, btnBack;
	private JLabel lblInfo;
	public DataStorageInfoPanel(int width,int height,Controller c)
	{
		controller = c;
		
		setPreferredSize(new Dimension(400, 400));
		setFocusable(true);
		setLayout(null);
		setName("DataStorageInfo");
		
		JLabel lblInformationAboutUser = new JLabel("Information About Data Storage");
		lblInformationAboutUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformationAboutUser.setBounds(96, 11, 173, 14);
		add(lblInformationAboutUser);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(27, 56, 66, 14);
		add(lblId);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(27, 94, 66, 14);
		add(lblTitle);
		
		JLabel lblMemoryCapacity = new JLabel("Memory Capacity");
		lblMemoryCapacity.setBounds(27, 138, 89, 14);
		add(lblMemoryCapacity);
		
		JLabel lblMemoryUsed = new JLabel("Memory Used");
		lblMemoryUsed.setBounds(27, 185, 66, 14);
		add(lblMemoryUsed);
		
		lblIdVar = new JLabel("");
		lblIdVar.setBounds(159, 56, 110, 14);
		add(lblIdVar);
		
		lblTitleVar = new JLabel("");
		lblTitleVar.setBounds(159, 94, 105, 14);
		add(lblTitleVar);
		
		lblMemoryCapacityVar = new JLabel("");
		lblMemoryCapacityVar.setBounds(159, 138, 110, 14);
		add(lblMemoryCapacityVar);
		
		lblMemoryUsedVar = new JLabel("");
		lblMemoryUsedVar.setBounds(159, 185, 110, 14);
		add(lblMemoryUsedVar);
		
		btnModify = new JButton("Modify");
		btnModify.setBounds(4, 299, 79, 23);
		add(btnModify);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(96, 299, 79, 23);
		add(btnDelete);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(274, 299, 79, 23);
		add(btnBack);
		
		lblInfo = new JLabel("");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(4, 210, 307, 14);
		add(lblInfo);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(27, 225, 46, 14);
		add(lblType);
		
		lblTypeVar = new JLabel("");
		lblTypeVar.setBounds(159, 225, 105, 14);
		add(lblTypeVar);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(185, 299, 79, 23);
		add(btnClear);
	}
	
	public void setupListeners()
	{	
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					controller.deleteDataStorage(dataStorage);
					controller.getView().showAdminPanel();
				} catch (DaoException e1) {
					lblInfo.setText(e1.getMessage());
					}
				}
				
		});
				
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			controller.getView().showDataStorageInfoPanel(dataStorage);
				
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.getView().showAdminPanel();
			}
		});
		
	};
	

	public void setDataStorageInfo(DataStorage dataStorage)
	{
		this.dataStorage = dataStorage;
		lblIdVar.setText(dataStorage.getId()+"");
		lblTitleVar.setText(dataStorage.getTitle());
		lblMemoryCapacityVar.setText(dataStorage.getMemoryCapacity().toString());
		lblMemoryUsedVar.setText(dataStorage.getMemoryUsed().toString());
		lblTypeVar.setText(dataStorage.getType().toString());
	}
}
