package dao;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.gson.reflect.TypeToken;

import exceptions.DaoException;
import model.Data;


public class DataDaoImpl extends GenericDaoImpl<Data> implements DataDao
{

	public DataDaoImpl()
	{

		super("data.json");
		daoType = new TypeToken<HashMap<Integer,Data>>(){}.getType();
	}

	@Override
	public void add(Data entity) throws DaoException {
		add(entity.getId(),entity);
		
	}

	@Override
	public void update(Data entity) throws DaoException {
		update(entity.getId(),entity);
	}

	@Override
	public Map<Integer, Data> findByDataStorage(int key) 
	{
		Map<Integer, Data> map = all().entrySet().stream().map(s->s.getValue())
				.filter(s->s.getDataStorageId() == key)
				.collect(Collectors.toMap(s->s.getId(), s->s));
		return map;
	}
}
