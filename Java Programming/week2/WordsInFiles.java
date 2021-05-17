package week2;

import java.io.*;
import java.util.*;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class WordsInFiles {

	private static HashMap<String, ArrayList<String>> map;
	
	static
	{
		map = new HashMap<>();
	}
	
	public static void main(String[] args) 
	{
		buildWordFileMap();
		int k = maxNumber();
		System.out.println("Max Files a Word is in : " + k);
//		System.out.println(wordsInNumFiles(k).size());
		printFilesIn("sea");
		System.out.println();
		printFilesIn("tree");
//		printFilesIn("red");
//		System.out.println();
//		printFilesIn("sad");
//		for(String s: map.keySet())
//		{
//			if(k == map.get(s).size())
//			{
//				printFilesIn(s);
//			}
//		}
//		for(String s : map.keySet())
//		{
//			System.out.println(s + "\t" + map.get(s));
//		}
	}
	
	private static void addWordsFromFiles(File f)
	{
		FileResource resource = new FileResource(f);
		for(String s: resource.words())
		{
			if(!map.containsKey(s))
				map.put(s, new ArrayList<>());
			ArrayList<String> arrL = map.get(s);
			if(!arrL.contains(f.getName()))
				arrL.add(f.getName());
		}
	}
	
	private static void buildWordFileMap()
	{
		map.clear();
		DirectoryResource dResource = new DirectoryResource();
		for(File f: dResource.selectedFiles())
		{
			addWordsFromFiles(f);
		}
	}
	
	private static int maxNumber()
	{
		int max = 0;
		for(String s : map.keySet())
		{
			int k = map.get(s).size();
			if(k > max)
				max = k;
		}
		return max;
	}
	
	private static ArrayList wordsInNumFiles(int number)
	{
		ArrayList<String> arrL = new ArrayList<>();
		for(String s : map.keySet())
		{
			int k = map.get(s).size();
			if(k == number)
				arrL.add(s);
		}
		return arrL;
	}
	
	private static void printFilesIn(String word)
	{
		if(map.containsKey(word))
		{
			for(String s : map.get(word))
			{
				System.out.println(s);
			}
		}
	}
}
