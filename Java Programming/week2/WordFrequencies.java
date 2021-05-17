package week2;

import java.util.ArrayList;

import edu.duke.FileResource;

public class WordFrequencies {

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
		System.out.println("No Of Unique Words : " + getSize());
		System.out.println("Most Occurred Word : " + Words.get(indexOfMax()) + "\t" + myFreqs.get(indexOfMax()));
		for(int k = 0; k < Words.size(); k++)
		{
			System.out.println(myFreqs.get(k) + "\t" + Words.get(k));
		}
	}
	
	public static int getSize()
	{
		return Words.size();
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
		
		FileResource resource = new FileResource("data/test.txt");
		
		for(String s: resource.words())							//NULL CHARACTER IS ALSO CONSIDERED IF FILE IS NULL BUT ONLY ONCE	
		{
			s = s.toLowerCase();
//			int index = Words.indexOf(s);
//			if(index == -1)
//			{
//				Words.add(s);
//				myFreqs.add(1);
//			}
//			else
//			{
//				int value = myFreqs.get(index);
//				myFreqs.add(index,value + 1);
//			}
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
	}

}

