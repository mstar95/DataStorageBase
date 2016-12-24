package dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import dao.GenericDaoImpl;

public abstract class GenericDaoImpl<E > implements GenericDao<E> 
{

   // protected Class<? extends E> daoType;
	protected Type daoType;
    protected Gson gson;
    protected FileWriter writer;
    protected FileReader reader;
    protected String filename;
    boolean dataLoaded;
    protected Map<Integer,E> map ;
    public GenericDaoImpl(String filename) {
        //\daoType = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    	//daoType = new TypeToken<HashMap<Integer,E>>(){}.getType();
        gson = new GsonBuilder().setPrettyPrinting().create();
        this.filename = filename;
        dataLoaded = false;
    }
    
  
    @Override
    public void add(int key,E entity) throws DaoException  
    {
	
		loadData();
		if(map.containsKey(key))
			throw new DaoException("Key is used now");
		else
		{
			map.put(key, entity);
		}
		saveData();
		
	  
    }

    @Override
    public void update(int key,E entity) throws DaoException 
    {
	
		loadData();
		if(map.containsKey(key))
			map.put(key, entity);
		else
		{
			throw new DaoException("It is no object to update");
		}
		saveData();
		
    }

    @Override
    public void remove(int key) throws DaoException
    {
	
		loadData();
		if(map.containsKey(key))
			map.remove(key);
		else
		{
			throw new DaoException("It is no object to remove");
		}
		saveData();
		
    }

    @Override
    public E find(int key) 
    {
    	E entity = null;
    	
		loadData();
		if(map.containsKey(key))
			entity = map.get(key);
		closeFile();
	
		return entity;
    }

    @Override
    public Map<Integer,E> all() 
    {
	
		loadData();
		closeFile();
	
    	return map;
    }
    
    private void loadData() 
    {
    	openFiletoRead();
		map =(HashMap<Integer,E>)gson.fromJson(reader, daoType);
		dataLoaded = true;
    }
    
    private void saveData() 
    {
    	if(dataLoaded)
    	{
    		openFiletoWrite();
    		closeFile();
    	}
    }
    
    private void openFiletoRead() 
    {
		try {
			reader = new FileReader(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void openFiletoWrite() 
    {
		try {
			writer = new FileWriter(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void closeFile() 
    {
    	dataLoaded = false;
    	try {
	    	if(writer != null)
	    	{
	    		writer.flush();
	    		writer.close();
	    	}
	    	if(writer != null)
	    		reader.close();
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
