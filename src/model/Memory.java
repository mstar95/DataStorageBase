package model;

import java.util.Optional;

import lombok.Getter;

@Getter
public class Memory 
{
	private static int MULTIPLIER  = 1024;
	public enum Unit{
		TB,GB,MB,KB,B;
	}
	
	public float amount;
	public Unit unit;
	
	public Memory(float amount,Unit unit)
	{
		this.amount = amount;
		this.unit = unit;
		normalize();
	}
	
	public void normalize()
	{
		while(unit!= Unit.TB && amount>=1024)
		{
			amount/=MULTIPLIER;
			switch(unit)
			{
				case B:
					unit = Unit.KB;
					break;
				case KB:
					unit = Unit.MB;
					break;
				case MB:
					unit = Unit.GB;
					break;
				case GB:
					unit = Unit.TB;
					break;
			}
		}
	}
	
	public  float returnInBytes()
	{
		Unit newUnit = unit;
		Float newAmount = amount;
		while(newUnit!= Unit.B)
		{
			newAmount*=MULTIPLIER;
			switch(newUnit)
			{
				case KB:
					newUnit = Unit.B;
					break;
				case MB:
					newUnit = Unit.KB;
					break;
				case GB:
					newUnit = Unit.MB;
					break;
				case TB:
					newUnit = Unit.GB;
					break;
			}
		}
		return newAmount;
		
	}
	
	public void toB()
	{
		while(unit!= Unit.B)
		{
			amount*=MULTIPLIER;
			switch(unit)
			{
				case KB:
					unit = Unit.B;
					break;
				case MB:
					unit = Unit.KB;
					break;
				case GB:
					unit = Unit.MB;
					break;
				case TB:
					unit = Unit.GB;
					break;
			}
		}
	}
	
	public String toString()
	{
		return amount +" "+ unit;
	}
	
	public static Optional<Unit> StringToUnit(String string)
	{
		Optional<Unit> newUnit = Optional.empty();
		switch(string)
		{
			case "B":
				newUnit = Optional.of(Unit.B);
				break;
			case "KB":
				newUnit = Optional.of(Unit.KB);
				break;
			case "MB":
				newUnit = Optional.of(Unit.MB);
				break;
			case "GB":
				newUnit = Optional.of(Unit.GB);
				break;
			case "TB":
				newUnit = Optional.of(Unit.TB);
				break;
		}
		
		return newUnit;
		
	}
}
