package dao;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import exceptions.DaoException;
import model.User;

public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao
{

	public UserDaoImpl() 
	{
		super("users.json");
		daoType = new TypeToken<HashMap<Integer,User>>(){}.getType();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void add(User entity) throws DaoException 
	{
		add(entity.getId(),entity);
		
	}

	@Override
	public void update(User entity) throws DaoException {
		update(entity.getId(),entity);	
	}
	
}
