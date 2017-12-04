import java.io.File;
import java.util.Scanner;
import java.util.Vector;

public class p7main{
	static HashTable ht;
	static File dict;
	static File testFile;
	static Vector<String> wordList;

	public static void main(String[] args) throws Exception{
		ht = new HashTable();
		wordList = new Vector<String>();
		Scanner stdin = new Scanner(System.in);
		System.out.println("What's the dictionary file?");
		loadNewDictionaryFile("dict.txt");
		System.out.println("What's the file you want to test?");
		loadTestFile("file.txt");

		stdin.close();
	}
	public static void loadTestFile(String name) throws Exception
	{
		int wrong=0;
		testFile = new File(name);
		Scanner in= new Scanner(testFile);
		while (in.hasNext())
		{
			wordList.add(in.next());
		}
		System.out.printf("%d entries read%n", wordList.size());
		for (int i=0; i < wordList.size(); ++i)
		{
			System.out.printf("%s=",wordList.get(i));
			if (!ht.contains(wordList.get(i), true))
			{
				System.out.println(wordList.get(i)+" was not found in the dictionary");
				wrong++;
			}
			//else System.out.println(wordList.get(i)+" was found");
		}
		System.out.println("There were "+wrong+" errors found");
		in.close();
		
	}
	public static void loadNewDictionaryFile(String fname) throws Exception
	{
		dict= new File(fname);
		if (dict.canRead())
			ReadDictionaryFile();
	}
	public static void ReadDictionaryFile() throws Exception
	{
		Vector<String> temp = new Vector<String>();
		Scanner in = new Scanner(dict);
		while (in.hasNext())
			temp.add(in.next());
		in.close();
		ht = new HashTable(temp.size());
		for (int i=0; i < temp.size(); ++i)
		{
			ht.insert(temp.get(i));
		}
		System.out.printf("%d entries%n", temp.size());
		System.out.printf("%d went through%n", ht.Count());
	}

}