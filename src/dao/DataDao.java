package dao;

import java.util.Map;

import model.Data;

public interface DataDao extends Dao<Data>
{
	Map<Integer, Data> findByDataStorage(int key);
}
