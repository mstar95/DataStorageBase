package model;
import java.util.List;

import controller.Controller;

public class Model {

	private Controller controller;
	private List<User> users;
	public void setController(Controller c)
	{
		 controller = c;
	}
}
