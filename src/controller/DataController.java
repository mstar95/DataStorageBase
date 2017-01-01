package controller;

import java.util.Map;

import dao.DataDao;
import dao.DataDaoImpl;
import dao.DataStorageDao;
import dao.DataStorageDaoImpl;
import exceptions.ControllerException;
import exceptions.DaoException;
import model.Data;
import model.DataStorage;
import model.Memory;
import view.View;

public class DataController 
{
	private View view;
	private DataStorageDao dataStorageDao;
	private Map<Integer,Data> datas;
	private DataDao dataDao;
	
	
	public DataController()
	{
		dataStorageDao = new DataStorageDaoImpl();
		dataDao = new DataDaoImpl();
	}
	
	public void setView(View view)
	{
		this.view = view;
	}
	
	public Map<Integer,Data> getDatas()
	{
		datas = dataDao.all();
		return datas;
	}
	
	public Map<Integer,Data> getByDataStorage(DataStorage dataStorage)
	{
		datas = dataDao.findByDataStorage(dataStorage.getId());
		return datas;
	}
	
	
	public void addData(Data data) throws ControllerException, DaoException
	{
		checkMemoryCapacity(data);
		getDatas();
		int id = 0;
		while(true)
		{
			if(datas.containsKey(id))
				id++;
			else
				break;
		}
		
		data.setId(id);
		try {
			dataDao.add(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		view.showUserPanel();
	}
	
	public void updateData(Data data) throws ControllerException, DaoException
	{
		checkMemoryCapacity(data);
		dataDao.update(data);
		eraseMemoryCapacity(data);
		view.showUserPanel();
	}
	
	public void deleteData(Data data) throws DaoException
	{
		eraseMemoryCapacity(data);
		dataDao.remove(data.getId());
	}
	
	private void checkMemoryCapacity(Data data) throws DaoException, ControllerException
	{
		DataStorage dataStorage = dataStorageDao.find(data.getDataStorageId());
		float memoryUsed = dataStorage.getMemoryUsed().returnInBytes();
		float memoryCapacity = dataStorage.getMemoryCapacity().returnInBytes();
		memoryUsed+= data.getSize().returnInBytes();
		if(memoryUsed>memoryCapacity)
			throw new ControllerException("Memory Full");
		dataStorage.setMemoryUsed(new Memory(memoryUsed,Memory.Unit.B));
		dataStorageDao.update(dataStorage);
	}
	
	private void eraseMemoryCapacity(Data data) throws DaoException
	{
		DataStorage dataStorage = dataStorageDao.find(data.getDataStorageId());
		float memoryUsed = dataStorage.getMemoryUsed().returnInBytes();
		memoryUsed-= data.getSize().returnInBytes();
		dataStorage.setMemoryUsed(new Memory(memoryUsed,Memory.Unit.B));
		dataStorageDao.update(dataStorage);
	}
	
	public void clearDataStorage(DataStorage dataStorage)
	{
		int id = dataStorage.getId();
		Map<Integer,Data> datasToerase  = dataDao.findByDataStorage(id);
		datasToerase.entrySet().forEach(s->{
			try {
				dataDao.remove(s.getKey());
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}
	
}


