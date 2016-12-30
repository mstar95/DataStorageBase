package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class User 
{
	private int id;
	private String login;
	private String password;
	boolean admin;
	public User(String login,String password,boolean admin)
	{
		this.login = login;
		this.password = password;
		this.admin = admin;
	}
	public String toString()
	{
		return id +" "+ login;
		
	}
}
