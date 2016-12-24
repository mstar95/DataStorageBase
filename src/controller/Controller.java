package controller;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import dao.UserDao;
import dao.UserDaoImpl;
import lombok.Getter;
import model.Model;
import model.User;
import view.View;

public class Controller 
{
	private Model model;
	@Getter
	private View view;
	private Map<Integer,User> users;
	
	UserDao userDao;
	
	public Controller(Model model)
	{
		this.model = model; 
		userDao = new UserDaoImpl();
	}
	
	public void setView(View view)
	{
		this.view = view;
	}
	
	public void checkLogin(String Login,String Password)
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
				view.unsuccessfulLogin("Invalid Password");
		}
		else
			view.unsuccessfulLogin("Invalid Login");
	}
	
	public Map<Integer,User> getUsers()
	{
		users = userDao.all();
		return users;
	}
}