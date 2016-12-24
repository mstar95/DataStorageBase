package dao;

public interface Dao<E> extends GenericDao<E>
{
	void add(E entity) throws Exception;
	 
	void update(E entity) throws Exception;
}
