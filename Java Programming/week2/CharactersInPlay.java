package week2;

import java.util.ArrayList;

import edu.duke.FileResource;

public class CharactersInPlay {

	private static ArrayList<String> Words;
	private static ArrayList<Integer> myFreqs;
	
	static
	{
		Words = new ArrayList<>();
		myFreqs = new ArrayList<>();
	}
	
	public static void main(String[] args) 
	{	
		findUnique();
		System.out.println("Most Occurred Word : " + Words.get(indexOfMax()) + "\t" + myFreqs.get(indexOfMax()));
		System.out.println("Word Occurred between 10-15 times: " + Words.get(indexOfWord()) + "\t" + myFreqs.get(indexOfWord()));
//		for(int k = 0; k < Words.size(); k++)
//		{
//			System.out.println(myFreqs.get(k) + "\t" + Words.get(k));
//		}
	}
	
	public static int indexOfWord()
	{
		int WordInd = 0, count = 0;
		for (int i = 0; i < myFreqs.size(); i++)
		{
			if(myFreqs.get(i) > 50 & myFreqs.get(i) < 101)
			{
				WordInd = i;
				count++;
				System.out.println("Word : " + Words.get(i) + "\t" + myFreqs.get(i));
			}
		}
//		System.out.println("Words : " + count);
		return WordInd;
	}
	
	public static int indexOfMax()
	{
		int maxInd = 0;
		for(int i = 1; i < myFreqs.size(); i++)
		{
			if(myFreqs.get(i) > myFreqs.get(maxInd))
			{
				maxInd = i;
			}
		}
		return maxInd;
	}
	
	public static void findUnique() {
		
//		FileResource resource = new FileResource("data/test.txt");
//		FileResource resource = new FileResource("data/test1.txt");
		FileResource resource = new FileResource();
		
		for(String s: resource.lines())
		{
			StringBuilder sb = new StringBuilder();
			for(int i = 0 ; i < s.length(); i++)
			{
				if(s.charAt(i) == '.')
				{
					s = sb.toString();
					if(!Words.contains(s))
					{
						Words.add(s);
						int k = Words.indexOf(s);
						myFreqs.add(k,1);
					}
					else
					{
						int k = Words.indexOf(s);
						myFreqs.set(k,myFreqs.get(k) + 1);
					}
				}
				else sb.append(s.charAt(i));
			}
		}
	}

}
