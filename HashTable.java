import java.lang.Math;
import java.util.Vector;
import java.io.File;
import java.util.Scanner;
import java.lang.Math;

public class HashTable{
	private int num_ele;
	String[] table;
	private int[] powers;
	private long size;
	public HashTable()
	{
		this.size=1000;
		constructor();
	}

	public HashTable(int n) throws Exception
	{
		File tempFile = new File("primes.txt");
		Scanner in = new Scanner(tempFile);
		int temp_n= in.nextInt();
		while (3*n > temp_n && in.hasNext())
			temp_n = in.nextInt();
		size=temp_n;
		in.close();
		System.out.println("chose prime number " +size);
		constructor();
	}

	private void constructor() //does the actual constructing
	{
		table = new String[(int)size];
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
		//System.out.print(toHash+"=");
		if (!contains(toHash, false))
		{
			num_ele++;
			table[hashValue(toHash, false)] = toHash;
		}
	}

	private int hashValue(String toHash, boolean print)
	{
		double result=0;
		//toHash=toHash.toLowerCase();
		for (int i=0; i < toHash.length(); ++i)
		{
			result+=(toHash.charAt(i)+99)*Math.pow(10, i+1);
		}
		result-=3*(toHash.length());
		if (print) System.out.println(result);
		return Math.abs((int)(result%(size)));
	}

	public boolean contains(String toFind, boolean print)
	{
		if (print) System.out.printf("%s (%d)%n",table[hashValue(toFind, false)],hashValue(toFind, false));
		return !(table[hashValue(toFind, print)].equals(""));
	}

	public int Count()
	{
		return num_ele;
	}

}