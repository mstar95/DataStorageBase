package dao;

import exceptions.DaoException;

public interface Dao<E> extends GenericDao<E>
{
	void add(E entity) throws DaoException;
	 
	void update(E entity) throws DaoException;
}
