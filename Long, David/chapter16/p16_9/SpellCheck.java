package p16_9;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SpellCheck {
	private static ArrayList<String> dictionary = new ArrayList<String>();
	
	public static void main(String[] args)
	{
		try  {loadDictionary();}
		catch (FileNotFoundException e)
		{
			System.out.println("Please correct the dictionary location before running this program");
			System.exit(0);
		}
		catch (IOException e)
		{
			System.out.println("Please correct the text file to have one word every line");
			System.exit(0);
		}
		
		
		boolean fin = false;
		Scanner k = new Scanner(System.in);
		System.out.println("Please enter the file path (e.g. chapter16/p16_9/MyDiary.txt) : ");
		
		while (!fin)
		{
			String fileName = k.nextLine();
			try
			{
				ArrayList<String> unknownWords = spellCheck(fileName);
				
				System.out.println("Incorrect Words: ");
				for (String s : unknownWords) System.out.println(s);
				
				fin = true;
			}
			catch (FileNotFoundException e)
			{
				System.out.println("File not found. Please enter an existing file path: ");
			}
			catch (IOException e)
			{
				System.out.println("File formatting error. Please fix the file or enter another existing file path: ");
			}
		}
		
		k.close();
	}
	
	public static void loadDictionary() throws IOException
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(new File("chapter16/p16_9/dictionary.txt")));
			try
			{
				String line = br.readLine();
				while (line != null)
				{
					dictionary.add(line);
					line = br.readLine();
				}
			}
			finally
			{
				br.close();
			}
		}
		catch (FileNotFoundException e)
		{
			throw e;
		}
		catch (IOException e)
		{
			throw e;
		}
	}
	
	public static ArrayList<String> spellCheck(String fileName) throws IOException
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
			ArrayList<String> unknownWords = new ArrayList<String>();
			
			try
			{
				String line = br.readLine();
				while (line != null)
				{
					StringTokenizer t = new StringTokenizer(line);
					while (t.hasMoreElements())
					{
						String w = t.nextToken().toLowerCase();
						if (!dictionary.contains(w))
							unknownWords.add(w);
					}
					line = br.readLine();
				}
				
				return unknownWords;
			}
			finally
			{
				br.close();
			}
		}
		catch (FileNotFoundException e)
		{
			throw e;
		}
		catch (IOException e)
		{
			throw e;
		}
		
	}
}
