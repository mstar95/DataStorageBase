package controller;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import dao.DataStorageDao;
import dao.UserDao;
import dao.UserDaoImpl;
import exceptions.DaoException;
import exceptions.ControllerException;
import lombok.Getter;
import model.DataStorage;
import model.User;
import view.View;

public class Controller 
{
	@Getter
	private View view;
	
	private UserController userController;
	private DataStorageController dataStorageController;

	public Controller()
	{
		userController = new UserController();
		dataStorageController = new DataStorageController();
	}
	
	public void setView(View view)
	{
		this.view = view;
		userController.setView(view);
		userController.setView(view);
	}
	
	public void checkLogin(String Login,String Password) throws ControllerException
	{
		userController.checkLogin(Login, Password);
	}
		
	public Map<Integer,User> getUsers()
	{
		return userController.getUsers();
	}
	
	public void addUser(User user) throws ControllerException
	{
		userController.addUser(user);
	}
	
	public void updateUser(User user) throws ControllerException
	{
		userController.updateUser(user);
	}
	
	public void deleteUser(User user) throws DaoException
	{
		userController.deleteUser(user);
	}

	public Map<Integer,DataStorage> getDataStorages()
	{
		return dataStorageController.getDataStorages();
	}
	
	public void addDataStorage(DataStorage dataStorage) throws ControllerException
	{
		dataStorageController.addDataStorage(dataStorage);
	}
	
	public void updateDataStorage(DataStorage dataStorage) throws ControllerException
	{
		dataStorageController.updateDataStorage(dataStorage);
	}
	
	public void deleteDataStorage(DataStorage dataStorage) throws DaoException
	{
		dataStorageController.deleteDataStorage(dataStorage);
	}
	
}