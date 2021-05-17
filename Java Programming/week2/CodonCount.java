package week2;

import java.util.HashMap;

import edu.duke.FileResource;

public class CodonCount {

	private static HashMap<String, Integer> map;
	
	static
	{
		map = new HashMap<>();
	}
	
	public static void main(String[] args) 
	{
		FileResource resource = new FileResource();
		for(int i = 0; i < 3; i++)
		{
			buildCodonMap(i, resource.asString());
			System.out.println("Most Common Word : " + getMostCommonCodon() + "\t" + map.get(getMostCommonCodon()));
			printCodon(0, 20);
		}
	}
	
	private static void buildCodonMap(int start, String dna)
	{
		map.clear();
		for(int i = start; i < dna.length(); i += 3)
		{
			try
			{
				String s = dna.substring(i, i + 3);
				int j = 0;
				for(; j < 3; j++)
				{
					if(!Character.isAlphabetic(s.charAt(j)))
						break;
				}
				if(j < 3) continue;
				if(!map.containsKey(s))
					map.put(s, 1);
				else
					map.put(s, map.get(s) + 1);
			}
			catch (IndexOutOfBoundsException e) {
			}
		}
	}
	
	private static String getMostCommonCodon()
	{
		String str = "";
		int max = 0;
		for(String s: map.keySet())
		{
			if(map.get(s) > max)
			{
				max = map.get(s);
				str = s;
			}
		}
		return str;
	}
	
	private static void printCodon(int start, int end)
	{
		int count = 0;
		for(String s: map.keySet())
		{
			if(map.get(s) >= start && map.get(s) <= end)
			{
				System.out.println(s + "\t" + map.get(s));
				count++;
			}
		}
		System.out.println("TotalCount : " + count);
	}

}
