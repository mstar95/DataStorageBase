package userView;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.Controller;
import exceptions.ControllerException;
import exceptions.DaoException;
import exceptions.DataStorageValuesException;
import model.Data;
import model.DataStorage;
import model.Memory;

import javax.swing.JTextField;

public class DataModifyPanel extends JPanel implements ListSelectionListener
{
	private Controller controller;
	
	private Map<Integer,DataStorage> dataStorages;
	private DefaultListModel<DataStorage> listModel;
	private Data data;
	
	private boolean modify;
	
	private JLabel lblNewLabel;
	private JButton btnAdd, btnBack;
	private JList list;
	private JLabel lblInfo;
	private JLabel lblName;
	private JTextField txtName;
	private JRadioButton rdbtnB, rdbtnKb, rdbtnMb, rdbtnGb, rdbtnTb;
	private ButtonGroup groupMem;
	private JLabel lblExtension;
	private JTextField txtExtension;
	private JLabel lblSize;
	private JTextField txtSize;
	public DataModifyPanel(int width,int height,Controller c)
	{
		controller = c;
		
		setPreferredSize(new Dimension(400, 400));
		setFocusable(true);
		setLayout(null);
		setName("DataModify");
		
		lblNewLabel = new JLabel("Data Storages");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(150, 25, 100, 14);
		add(lblNewLabel);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAdd.setBounds(10, 316, 101, 37);
		add(btnAdd);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(179, 316, 106, 37);
		add(btnBack);
		
		listModel = new DefaultListModel<DataStorage>();
		list = new JList<DataStorage>(listModel);
		list.setBounds(231, 82, 159, 223);
		updateList();
		add(list);
		
		lblInfo = new JLabel("");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(10, 241, 192, 14);
		add(lblInfo);
		
		JLabel lblDataStorage = new JLabel("Data Storage ");
		lblDataStorage.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataStorage.setBounds(253, 57, 126, 14);
		add(lblDataStorage);
		
		lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(31, 83, 62, 14);
		add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(103, 80, 86, 20);
		add(txtName);
		txtName.setColumns(10);
		
		rdbtnB = new JRadioButton("B");
		rdbtnB.setBounds(10, 173, 36, 23);
		rdbtnB.setActionCommand("B");
		rdbtnB.setSelected(true);
		add(rdbtnB);
		
		rdbtnKb = new JRadioButton("KB");
		rdbtnKb.setBounds(43, 173, 44, 23);
		rdbtnKb.setActionCommand("KB");
		add(rdbtnKb);
		
		rdbtnMb = new JRadioButton("MB");
		rdbtnMb.setBounds(89, 173, 44, 23);
		rdbtnMb.setActionCommand("MB");
		add(rdbtnMb);
		
		rdbtnGb = new JRadioButton("GB");
		rdbtnGb.setBounds(135, 173, 44, 23);
		rdbtnGb.setActionCommand("GB");
		add(rdbtnGb);
		
		rdbtnTb = new JRadioButton("TB");
		rdbtnTb.setBounds(181, 173, 44, 23);
		rdbtnTb.setActionCommand("TB");
		add(rdbtnTb);
		
		groupMem = new ButtonGroup();
		groupMem.add(rdbtnB);
		groupMem.add(rdbtnKb);
		groupMem.add(rdbtnMb);
		groupMem.add(rdbtnGb);
		groupMem.add(rdbtnTb);
		
		lblExtension = new JLabel("Extension:");
		lblExtension.setHorizontalAlignment(SwingConstants.CENTER);
		lblExtension.setBounds(31, 115, 68, 14);
		add(lblExtension);
		
		txtExtension = new JTextField();
		txtExtension.setColumns(10);
		txtExtension.setBounds(103, 111, 86, 20);
		add(txtExtension);
		
		lblSize = new JLabel("Size:");
		lblSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblSize.setBounds(31, 149, 68, 14);
		add(lblSize);
		
		txtSize = new JTextField();
		txtSize.setColumns(10);
		txtSize.setBounds(103, 146, 86, 20);
		add(txtSize);
	}
	
	public void setupListeners()
	{
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText().trim();
				String extension = txtExtension.getText().trim();
				String size = txtSize.getText().trim();
				if(name.equals(""))
				{
					lblInfo.setText("Name is blank");
					return;
				}
				if(extension.equals(""))
				{
					lblInfo.setText("Extension is blank");
					return;
				}
				if(size.equals(""))
				{
					lblInfo.setText("Size is blank");
					return;
				}
				try {
					float memoryCapacityAmount = new Float(size);
					Optional<Memory.Unit> unitOptional = Memory.StringToUnit(groupMem.getSelection().getActionCommand());
					Memory memory = new Memory(memoryCapacityAmount,unitOptional
							.orElseThrow(() -> new DataStorageValuesException("Wrong unit type")));
					
					Optional<DataStorage> optionalDataStorage = Optional.ofNullable((DataStorage)list.getSelectedValue());
					
					int dataStorageId = (optionalDataStorage
							.orElseThrow(() -> new DataStorageValuesException("Select Data Storage,please"))).getId();
							
					if(modify)
					{
						data.setName(name);
						data.setExtension(extension);
						data.setSize(memory);
						data.setDataStorageId(dataStorageId);
						controller.updateData(data);
					}
					else
					{
						data =  new Data(dataStorageId,name,extension,memory);	
						controller.addData(data);
					}
				} catch (ControllerException | DataStorageValuesException|DaoException e1) {
					lblInfo.setText(e1.getMessage());
				} catch (NumberFormatException  e1) {
					lblInfo.setText("wrong Memory Capacity format");
				}
				}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.getView().showUserPanel();
			}
		});
	}
	
	private void updateList()
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
	public void update()
	{
		updateList();
		lblInfo.setText(" ");
	}
	
	public void setVariables(DataStorage dataStorage)
	{
		update();
		modify = false;
		//list.setSelectedValue(dataStorage, true);
		//list.addSelectionInterval(0, 1);
		
	}
	
	public void setVariables(Data data)
	{
		update();
		modify = true;
		this.data = data;
		txtName.setText(data.getName());
		txtSize.setText(data.getSize().getAmount()+"");
		txtExtension.setText(data.getExtension());
		setMemUnit(data.getSize().getUnit());
	}
	
	public void resetVariables()
	{
		update();
		modify = false;
		txtName.setText("");
		txtSize.setText("");
		txtExtension.setText("");
		rdbtnB.setSelected(true);
	}
	
	private void setMemUnit(Memory.Unit u)
	{
		switch(u.toString())
		{
			case "B":
				rdbtnB.setSelected(true);
				break;
			case "KB":
				rdbtnKb.setSelected(true);
				break;
			case "MB":
				rdbtnMb.setSelected(true);
				break;
			case "GB":
				rdbtnGb.setSelected(true);
				break;
			case "TB":
				rdbtnTb.setSelected(true);
				break;
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

