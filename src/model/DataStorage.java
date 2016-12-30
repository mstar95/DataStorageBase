package model;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import model.Memory.Unit;

@Setter
@Getter
@AllArgsConstructor
public class DataStorage 
{
	private int id;
	private String title;
	private Memory memoryCapacity;
	private Memory memoryUsed;
	private Type type;
	public static enum Type {
    FLOPPY, CD, DVD, PENDRIVE,
	}
	
	public DataStorage(Memory memoryCapacity,Memory memoryUsed,Type type,String title)
	{
		this(memoryCapacity,type,title);
		this.memoryUsed = memoryUsed;
	}
	public DataStorage(Memory memoryCapacity,Type type,String title)
	{
		this.memoryCapacity = memoryCapacity;
		this.type = type;
		this.memoryUsed = new Memory(0,Memory.Unit.B);
		this.title = title;
	}
	
	public String toString()
	{
		return id + " "+ type +" "+ title +" " + memoryUsed + "/" + memoryCapacity;
	}
	
	public static Optional<Type> StringToType(String string)
	{
		Optional<Type> newType = Optional.empty();
		switch(string)
		{
			case "FLOPPY":
				newType = Optional.of(Type.FLOPPY);
				break;
			case "CD":
				newType = Optional.of(Type.CD);
				break;
			case "DVD":
				newType = Optional.of(Type.DVD);
				break;
			case "PENDRIVE":
				newType = Optional.of(Type.PENDRIVE);
				break;
		}
		
		return newType;
		
	}
}
