import java.lang.Math;

public class HashTable{
	private int num_ele;
	String[] table;
	private int[] powers;
	private int size;

	public HashTable()
	{
		this.size=1000;
		constructor();
	}

	public HashTable(int n)
	{
		this.size=n;
		constructor();
	}

	private void constructor() //does the actual constructing
	{
		table = new String[size];
		num_ele=0;
		powers= new int[4];
		powers[0]=29791;
		powers[1]=961;
		powers[2]=31;
		powers[3]=1;
		for (int i=0; i < size; ++i)
		{
			table[i]="";
		}
	}
	public void insert(String toHash)
	{
		if (!contains(toHash))
		{
			num_ele++;
			table[hashValue(toHash)] = toHash;
		}
	}

	private int hashValue(String toHash)
	{
		double result=0;
		for (int j=0; j < toHash.length(); j+=4)
		for (int i=0; i < 4; ++i)
		{
			if (i+j >= toHash.length())
				return Math.abs((int)(result%size));
			else result+=(Character.getNumericValue(toHash.charAt(i+j))-10)*powers[i];
		}
		return Math.abs((int)(result%size));
	}

	public boolean contains(String toFind)
	{
		return table[hashValue(toFind)].isEmpty();
	}

	public int Count()
	{
		return num_ele;
	}
}