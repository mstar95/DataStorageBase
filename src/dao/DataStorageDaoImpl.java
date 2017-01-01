package dao;

import java.util.HashMap;

import com.google.gson.reflect.TypeToken;

import exceptions.DaoException;
import model.DataStorage;

public class DataStorageDaoImpl extends GenericDaoImpl<DataStorage> implements DataStorageDao
{

	public DataStorageDaoImpl() 
	{
		super("dataStorages.json");
		daoType = new TypeToken<HashMap<Integer,DataStorage>>(){}.getType();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void add(DataStorage entity) throws DaoException 
	{
		add(entity.getId(),entity);
		
	}

	@Override
	public void update(DataStorage entity) throws DaoException {
		update(entity.getId(),entity);	
	}
	
}
