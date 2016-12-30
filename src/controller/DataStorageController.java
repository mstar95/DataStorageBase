package controller;

import java.util.Map;
import java.util.Optional;

import dao.DataStorageDao;
import dao.DataStorageDaoImpl;
import dao.UserDaoImpl;
import exceptions.DaoException;
import exceptions.ControllerException;
import model.DataStorage;
import model.User;
import view.View;

public class DataStorageController 
{
	private View view;
	private Map<Integer,DataStorage> dataStorages;
	private DataStorageDao dataStorageDao;
	
	public DataStorageController()
	{
		dataStorageDao = new DataStorageDaoImpl();
		
	}
	
	public void setView(View view)
	{
		this.view = view;
	}
	
	public Map<Integer,DataStorage> getDataStorages()
	{
		dataStorages = dataStorageDao.all();
		return dataStorages;
	}
	
	public void addDataStorage(DataStorage dataStorage) throws ControllerException
	{
		getDataStorages();
		int id = 0;
		while(true)
		{
			if(dataStorages.containsKey(id))
				id++;
			else
				break;
		}
		
		dataStorage.setId(id);
		try {
			dataStorageDao.add(dataStorage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		view.showUserPanel();
	}
	
	public void updateDataStorage(DataStorage dataStorage) throws ControllerException
	{
		getDataStorages();
		try {
			dataStorageDao.update(dataStorage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		view.showUserPanel();
	}
	
	public void deleteDataStorage(DataStorage dataStorage) throws DaoException
	{
		dataStorageDao.remove(dataStorage.getId());
	}
	
}
