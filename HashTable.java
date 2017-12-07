import java.lang.Math;
import java.util.Vector;
import java.io.File;
import java.util.Scanner;
import java.lang.Math;

public class HashTable{
	private int num_ele;
	String[] table;
	private int size;
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
		for (int i=0; i < size; ++i)
		{
			table[i]="";
		}
	}
	public void insert(String toHash) //insert a string to the array
	{
		//System.out.print(toHash+"=");
		if (isEmpty(toHash))
		{
			num_ele++;
			table[hashValue(toHash, false)] = toHash;
		}
	}

	private int hashValue(String toHash, boolean print) //Calculate the hash value
	{
		double result=0, tempSum=0, value=0;
		result+=7*toHash.length();
		toHash=toHash.toLowerCase();
		for (int i=0; i < toHash.length(); ++i) //calculates the hash value
		{
			if (!Character.isLetter(toHash.charAt(i)))
				continue;
			value+=toHash.charAt(i);
			if (i%2==0)	tempSum=(toHash.charAt(i)+29)*Math.pow(10, i+1);
			else tempSum=(toHash.charAt(i)*7)*Math.pow(10, i);
			result+=isVowel(toHash.charAt(i),tempSum);
		}
		result=(result%size)+value;
		if (table[(int)Math.abs(result%size)].equals(""))  //Check if it's blank
			return (int)Math.abs(result%size);
		while (!table[(int)Math.abs(++result%size)].equals("")) //otherwise probe the list linearly
		{
			if (table[(int)Math.abs(result%size)].equals(toHash))
				break;
		}
		return (int)Math.abs(result%size); //return the next blank number
	}

	public int isVowel(char c, double toGet) //checks if the letter is a vowel. does things if so.
	{										 //part of the hashing function
		if (c=='a') return (int)toGet;
		if (c=='e') return (int)toGet*3;
		if (c=='i') return (int)toGet*5;
		if (c=='o') return (int)toGet*7;
		if (c=='u') return (int)toGet*11;
		return (int)toGet+1027;
	}

	public boolean contains(String toFind, boolean print) //check if the array contains an element
	{
		int index=hashValue(toFind, false);
		if (print) System.out.printf("%s (%d)%n",table[hashValue(toFind, false)],hashValue(toFind, false));
		return (table[index].equals(toFind));
	}

	public boolean isEmpty(String toFind) //check if the array is blank at some point
	{
		return (table[hashValue(toFind, false)].equals(""));
	}

	public int Count()
	{
		return num_ele;
	}

}
