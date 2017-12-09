import java.lang.Math;
import java.util.LinkedList;
import java.util.HashMap;

public class HashTable{
	private int num_ele;
	private HashMap<Integer, HashTable.Bucket> map;
	private int[] powers;
	private int size;
	private Bucket newBucket;


	private class Bucket{
		private LinkedList list;

		public Bucket()
		{
			list = new LinkedList<String>();
		}
		public void add(String toAdd)
		{
			list.add(toAdd);
		}
		public boolean exists(String toMatch)
		{
			return list.contains(toMatch);
		}
	}

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
		map = new HashMap<Integer,HashTable.Bucket>();
		num_ele=0;
		powers= new int[4];
		powers[0]=29791;
		powers[1]=961;
		powers[2]=31;
		powers[3]=1;
	}
	public void insert(String toHash)
	{
		toHash=preprocess(toHash);
		int key = hashValue(toHash);
		if (!map.containsKey(key))
		{
			newBucket = new Bucket();
			newBucket.add(toHash);
			map.put(key, newBucket);
		}
		else map.get(key).add(toHash);
		num_ele++;
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

		toFind=preprocess(toFind);
		int key = hashValue(toFind);
		if (map.containsKey(key))
		{
			if (map.get(key).exists(toFind))
				return true;
		}
		return false;

	}

	public int Count()
	{
		return num_ele;
	}
	public String preprocess(String go)
	{
		go=go.toLowerCase();
		String result="";
		for (int i=0; i < go.length(); ++i)
			if (Character.isAlphabetic(go.charAt(i)))
				result+=go.charAt(i);
		return result;
	}

}