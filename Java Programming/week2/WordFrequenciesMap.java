package week2;

import java.util.HashMap;

import edu.duke.FileResource;

public class WordFrequenciesMap {

	public static void main(String[] args) 
	{
		FileResource resource = new FileResource();
		int total = 0;
		HashMap<String, Integer> map = new HashMap<>();
		for(String s: resource.words())
		{
			s = s.toLowerCase();
			total++;
			if(map.containsKey(s))
				map.put(s, map.get(s) + 1);
			else
				map.put(s, 1);
		}
		for(String w : map.keySet())
		{
			int occurrences = map.get(w);
			if(occurrences > 300)
				System.out.println(w + "\t" + map.get(w));
		}
		System.out.println("Total : " + total);
		System.out.println("Unique : " + map.size());
	}

}
