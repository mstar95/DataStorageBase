package main;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.SwingUtilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import controller.Controller;
import model.DataStorage;
import model.Memory;
import model.User;
import view.View;

public class Main 
{
	static FileWriter writer;
	static FileReader reader;
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() 
			{
				//test();
				test2();
				Controller controller = new Controller();
				View  view = new View(controller);
				controller.setView(view);	
			}
		});
	}
	public static void test()
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		User user = new User(0,"Admin","Haslo",true);
		Map<Integer,User> users = new HashMap<Integer,User>();
		users.put(user.getId(), user);
		
		String json = gson.toJson(users);
		System.out.println(users);
        System.out.println(json);
        openFile();
		try {
			
			//gson.toJson(user, writer);
			writer.write(json);
			writer.flush();
			writer.close();
			Map<Integer,User> users1 = (Map<Integer, User>) gson.fromJson(reader, new TypeToken<HashMap<Integer, User>>(){}.getType());
			System.out.println(users1.toString());
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    public static void openFile()
    {
    	try {
			writer = new FileWriter("users.json");
			reader = new FileReader("users.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void test2()
    {
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	Memory memory = new Memory(1000000,Memory.Unit.B);
    	System.out.println(memory);
    	System.out.println(memory.returnInBytes());
    	DataStorage dataStorage = new DataStorage(memory,DataStorage.Type.CD,"XD");
    	System.out.println(dataStorage);
    	Map<Integer,DataStorage> dataStorages = new HashMap<Integer,DataStorage>();
    	dataStorages.put(dataStorage.getId(), dataStorage);
    	String json = gson.toJson(dataStorages);
    	System.out.println(json);
    	//DataStorage dataStorage2 =  gson.fromJson(json,new TypeToken<DataStorage>(){}.getType());
    	//System.out.println(dataStorage2);
    /*	try {
		//	writer = new FileWriter("dataStorages.json");
		//	writer.write(json);
		//	writer.flush();
		//	writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
    }
}
