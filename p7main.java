import java.io.File;
import java.util.Scanner;

public class p7main{
	static HashTable ht;
	static File dict;

	public static void main(String[] args) throws Exception{
		ht = new HashTable();
		Scanner stdin = new Scanner(System.in);
		System.out.println("What's the dictionary file?");
		loadNewDictionaryFile(stdin.next());
		System.out.println("Load finished");
		stdin.close();
	}
	public static void loadNewDictionaryFile(String fname) throws Exception
	{
		ht= new HashTable();
		dict= new File(fname);
		if (dict.canRead())
			ReadDictionaryFile();
	}
	public static void ReadDictionaryFile() throws Exception
	{
		Scanner in = new Scanner(dict);
		while (in.hasNext())
			ht.insert(in.next());
		in.close();
	}
}