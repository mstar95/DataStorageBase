package controller;

import java.util.Map;
import java.util.Optional;

import dao.DataStorageDao;
import dao.UserDao;
import dao.UserDaoImpl;
import exceptions.DaoException;
import exceptions.ControllerException;
import model.DataStorage;
import model.User;
import view.View;

public class UserController 
{
	private View view;
	private Map<Integer,User> users;
	private UserDao userDao;
	
	public UserController()
	{
		userDao = new UserDaoImpl();
		
	}
	
	public void setView(View view)
	{
		this.view = view;
	}
	
	public void checkLogin(String Login,String Password) throws ControllerException
	{
		users = userDao.all(); 
		Optional<User> optionalUser =  users.entrySet().stream()
			.map(s->s.getValue())
			.filter(s->s.getLogin().equals(Login))
			.findFirst();
		
		if(optionalUser.isPresent())	
		{
			User user = optionalUser.get();
			if(user.getPassword().equals(Password))
			{
				if(user.isAdmin())
					view.successfulAdminLogin();
				else
					view.successfulUserLogin();
			}
			else
				throw new ControllerException("Invalid Password");
		}
		else
			throw new ControllerException("Invalid Login");
	}
	
	public Map<Integer,User> getUsers()
	{
		users = userDao.all();
		return users;
	}
	
	public void addUser(User user) throws ControllerException
	{
		getUsers();
		int id = 0;
		while(true)
		{
			if(users.containsKey(id))
				id++;
			else
				break;
		}
		Optional<User> reapeatedName = users.entrySet().stream()
						.map(s->s.getValue())
						.filter(s->s.getLogin().equals(user.getLogin()))
						.findAny();
		if(reapeatedName.isPresent())
			throw new ControllerException("Login not available");
		user.setId(id);
		try {
			userDao.add(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		view.showAdminPanel();
	}
	
	public void updateUser(User user) throws ControllerException
	{
		getUsers();
		Optional<User> reapeatedName = users.entrySet().stream()
						.map(s->s.getValue())
						.filter(s->(s.getLogin().equals(user.getLogin()) && (s.getId()!= user.getId())))
						.findAny();
		if(reapeatedName.isPresent())
			throw new ControllerException("Login not available");
		try {
			userDao.update(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		view.showAdminPanel();
	}
	
	public void deleteUser(User user) throws DaoException
	{
		userDao.remove(user.getId());
	}
	
}

