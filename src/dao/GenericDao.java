package dao;

import java.util.Map;

import model.User;

public interface GenericDao<E> 
{

 void add(int key,E entity) throws DaoException;
 
 void update(int key,E entity) throws DaoException;
 
 void remove(int key) throws DaoException;
 
 E find(int key);
 
 Map<Integer, E> all();
 
}
