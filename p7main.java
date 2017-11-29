import java.io.File;
import java.util.Scanner;

public class p7main{
	static HashTable ht;
	static File dict;

	public static void main(String[] args) {
	}
	public static void loadNewDictionaryFile(String fname)
	{
		ht= new HashTable();
		dict= new File(fname);
		if (dict.canRead())
			ReadDictionaryFile();
	}
	public static void ReadDictionaryFile()
	{
		Scanner in = new Scanner(dict);
		while (in.hasNext())
			ht.insert(in.next());
		in.close();
	}
}