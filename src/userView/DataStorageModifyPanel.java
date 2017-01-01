
package userView;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.JPanel;

import controller.Controller;
import exceptions.ControllerException;
import exceptions.DataStorageValuesException;

import model.DataStorage;
import model.Memory;
import model.User;
import model.DataStorage.Type;
import model.Memory.Unit;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

public class DataStorageModifyPanel extends JPanel
{	
	private Controller controller;
	private DataStorage dataStorage;
	private boolean modify;
	
	private JTextField txtTitle,txtMemoryCapacity;
	private JButton btnAdd,btnBack;
	private JLabel lblInfo;
	private ButtonGroup groupMem,groupType;
	private JRadioButton rdbtnB, rdbtnKb, rdbtnMb, rdbtnGb, rdbtnTb;
	private JRadioButton rdbtnCd, rdbtnDvd, rdbtnFloppy, rdbtnPenDrive;
	public DataStorageModifyPanel(int width,int height,Controller c)
	{
		controller = c;
		
		setPreferredSize(new Dimension(400, 400));
		setFocusable(true);
		setLayout(null);
		setName("DataStorageModify");
		
		JLabel lblInformationAboutUser = new JLabel("Add Data Storage, please");
		lblInformationAboutUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformationAboutUser.setBounds(96, 11, 173, 14);
		add(lblInformationAboutUser);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setBounds(27, 50, 100, 14);
		add(lblTitle);
		
		JLabel lblMemoryCapacity = new JLabel("Memory Capacity");
		lblMemoryCapacity.setBounds(27, 94, 100, 14);
		add(lblMemoryCapacity);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(224, 246, 89, 23);
		add(btnBack);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(51, 246, 89, 23);
		add(btnAdd);
		
		txtTitle = new JTextField();
		txtTitle.setBounds(137, 47, 86, 20);
		add(txtTitle);
		txtTitle.setColumns(10);
		
		txtMemoryCapacity = new JTextField();
		txtMemoryCapacity.setBounds(137, 91, 86, 20);
		add(txtMemoryCapacity);
		txtMemoryCapacity.setColumns(10);
		
		lblInfo = new JLabel("");
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setBounds(51, 307, 262, 14);
		add(lblInfo);
		
		rdbtnB = new JRadioButton("B");
		rdbtnB.setBounds(27, 115, 36, 23);
		rdbtnB.setActionCommand("B");
		rdbtnB.setSelected(true);
		add(rdbtnB);
		
		rdbtnKb = new JRadioButton("KB");
		rdbtnKb.setBounds(68, 115, 44, 23);
		rdbtnKb.setActionCommand("KB");
		add(rdbtnKb);
		
		rdbtnMb = new JRadioButton("MB");
		rdbtnMb.setBounds(113, 115, 44, 23);
		rdbtnMb.setActionCommand("MB");
		add(rdbtnMb);
		
		rdbtnGb = new JRadioButton("GB");
		rdbtnGb.setBounds(158, 115, 44, 23);
		rdbtnGb.setActionCommand("GB");
		add(rdbtnGb);
		
		rdbtnTb = new JRadioButton("TB");
		rdbtnTb.setBounds(201, 115, 44, 23);
		rdbtnTb.setActionCommand("TB");
		add(rdbtnTb);
		
		groupMem = new ButtonGroup();
		groupMem.add(rdbtnB);
		groupMem.add(rdbtnKb);
		groupMem.add(rdbtnMb);
		groupMem.add(rdbtnGb);
		groupMem.add(rdbtnTb);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(27, 145, 46, 14);
		add(lblType);
		
		rdbtnCd = new JRadioButton("CD");
		rdbtnCd.setBounds(27, 173, 44, 23);
		rdbtnCd.setActionCommand("CD");
		rdbtnCd.setSelected(true);
		add(rdbtnCd);
		
		rdbtnDvd = new JRadioButton("DVD");
		rdbtnDvd.setBounds(83, 173, 57, 23);
		rdbtnDvd.setActionCommand("DVD");
		add(rdbtnDvd);
		
		rdbtnFloppy = new JRadioButton("FLOPPY");
		rdbtnFloppy.setBounds(145, 173, 57, 23);
		rdbtnFloppy.setActionCommand("FLOPPY");
		add(rdbtnFloppy);
		
		rdbtnPenDrive = new JRadioButton("PENDRIVE");
		rdbtnPenDrive.setBounds(212, 173, 77, 23);
		rdbtnPenDrive.setActionCommand("PENDRIVE");
		add(rdbtnPenDrive);
		
		groupType = new ButtonGroup();
		groupType.add(rdbtnCd);
		groupType.add(rdbtnDvd);
		groupType.add(rdbtnFloppy);
		groupType.add(rdbtnPenDrive);
		
	}
	
	public void setupListeners()
	{
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = txtTitle.getText().trim();
				String memoryCapacitystr = txtMemoryCapacity.getText().trim();
				if(title.equals(""))
				{
					lblInfo.setText("Title is blank");
					return;
				}
				if(memoryCapacitystr.equals(""))
				{
					lblInfo.setText("Memory Capacity is blank");
					return;
				}
				try {
					float memoryCapacityAmount = new Float(memoryCapacitystr);
					Optional<Memory.Unit> unitOptional = Memory.StringToUnit(groupMem.getSelection().getActionCommand());
					Memory memory = new Memory(memoryCapacityAmount,unitOptional
							.orElseThrow(() -> new DataStorageValuesException("Wrong unit type")));
					Optional<DataStorage.Type> typeOptional = DataStorage.StringToType(groupType.getSelection().getActionCommand());
					DataStorage.Type type = typeOptional
							.orElseThrow(() -> new DataStorageValuesException("Wrong Data Storage type"));
					if(modify)
					{
						dataStorage.setTitle(title);
						dataStorage.setMemoryCapacity(memory);
						dataStorage.setType(type);
						dataStorage.setMemoryUsed(new Memory(0,Memory.Unit.B));
						controller.clearDataStorage(dataStorage);
						controller.updateDataStorage(dataStorage);
					}
					else
					{
						dataStorage =  new DataStorage(memory,type,title);	
						controller.addDataStorage(dataStorage);
					}
				} catch (ControllerException | DataStorageValuesException e1) {
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
	
	public void setVariables(DataStorage dataStorage)
	{
		modify = true;
		this.dataStorage = dataStorage;
		txtTitle.setText(dataStorage.getTitle());
		txtMemoryCapacity.setText(dataStorage.getMemoryCapacity().getAmount()+"");
		setType(dataStorage.getType());
		setMemUnit(dataStorage.getMemoryCapacity().getUnit());
	}
	
	public void resetVariables()
	{
		modify = false;
		txtTitle.setText("");
		txtMemoryCapacity.setText("");
		rdbtnB.setSelected(true);
		rdbtnCd.setSelected(true);
	}
	
	private void setType(DataStorage.Type t)
	{
		switch(t.toString())
		{
			case "FLOPPY":
				rdbtnFloppy.setSelected(true);
				break;
			case "CD":
				rdbtnCd.setSelected(true);
				break;
			case "DVD":
				rdbtnDvd.setSelected(true);
				break;
			case "PENDRIVE":
				rdbtnPenDrive.setSelected(true);
				break;
		}
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
	
}
