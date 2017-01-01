package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Data 
{
	private int id;
	private int dataStorageId;
	private String name;
	private String extension;
	private Memory size;
	
	public Data(int dataStorageId,String name,String extension,Memory size)
	{
		this.dataStorageId = dataStorageId;
		this.name = name;
		this.extension = extension;
		this.size = size;
	}
	
	public String toString()
	{
		return id+" "+name+"."+extension +" "+size+" dataStorage: " + dataStorageId;
	}
}
