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
import model.Data;
import model.DataStorage;

public class DataListPanel extends JPanel  implements ListSelectionListener
{	
	private Controller controller;
	
	private Map<Integer,Data> datas;
	private DefaultListModel<Data> listModel;
	DataStorage dataStorage;
	
	private JLabel lblTitle;
	private JButton btnNewData, btnDeleteData,btnDataStorageInfo,btnBack,btnModifyData;
	private JList list;
	private JLabel lblInfo;

	public DataListPanel(int width,int height,Controller c)
	{
		controller = c;
		
		setPreferredSize(new Dimension(400, 400));
		setFocusable(true);
		setLayout(null);
		setName("DataList");
		
		datas = controller.getDatas();
		
		lblTitle = new JLabel("All Datas");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(43, 25, 260, 14);
		add(lblTitle);
		
		btnNewData = new JButton("New Data");
		btnNewData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewData.setBounds(10, 270, 101, 37);
		add(btnNewData);
		
		btnDeleteData = new JButton("Delete Data");
		btnDeleteData.setBounds(121, 270, 106, 37);
		add(btnDeleteData);
		
		//btnDataStorageInfo = new JButton("Data Info");
	//	btnDataStorageInfo.setBounds(10, 318, 101, 41);
	//	add(btnDataStorageInfo);
		
		listModel = new DefaultListModel<Data>();
		list = new JList<Data>(listModel);
		list.setBounds(43, 50, 260, 185);
		updateList();
		add(list);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(121, 318, 106, 41);
		add(btnBack);
		
		btnModifyData = new JButton("Modify Data");
		btnModifyData.setBounds(237, 270, 121, 37);
		add(btnModifyData);
		
		lblInfo = new JLabel("");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(43, 246, 260, 14);
		add(lblInfo);
	}
	
	public void setupListeners()
	{
		btnNewData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.getView().showDataModifyPanel();
			}
		});
		
		btnDeleteData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkIfValueIsSelected() == false)
					return;
				Data data= (Data)list.getSelectedValue();
				
				try {
					controller.deleteData(data);
					if(dataStorage == null)
						update();
					else
						update( dataStorage);
					updateList();
				} catch (DaoException e1) {
					lblInfo.setText(e1.getMessage());
				}
				
					
			}
		});
		
	/*	btnDataStorageInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkIfValueIsSelected() == false)
					return;
				DataStorage dataStorage = (DataStorage)list.getSelectedValue();
				controller.getView().showDataStorageInfoPanel(dataStorage);
			}
		}); */
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.getView().showUserPanel();
			}
		});
		
		btnModifyData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkIfValueIsSelected() == false)
					return;
				Data data = (Data)list.getSelectedValue();
				controller.getView().showDataModifyPanel(data);
			}
		});
		
	};
	
	private void updateList()
	{
		listModel.clear();
		for(Entry<Integer, Data> entry : datas.entrySet())
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
	
	public void update()
	{
		dataStorage = null;
		datas = controller.getDatas();
		updateList();
		lblInfo.setText(" ");
		lblTitle.setText("All Datas");
	}
	
	public void update(DataStorage dataStorage)
	{
		this.dataStorage = dataStorage;
		datas = controller.getByDataStorage(dataStorage);
		updateList();
		lblTitle.setText(dataStorage.toString());
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
