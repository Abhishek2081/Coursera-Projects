package string;

import edu.duke.FileResource;

public class ArrayCountingCoursera {

	public static void main(String[] args) 
	{
		String[] plays = {"caesar.txt","hamlet.txt","romeo.txt","errors.txt"};
		
		String common[] = getCommon();
		int counts[] = new int[common.length];
		
		for(int i = 0; i < plays.length; i++)
		{
			FileResource resource = new FileResource("data/" + plays[i]);
			countWords(resource,common,counts);
			System.out.println("done with " + plays[i]);
		}
		
		for(int i = 0; i < common.length; i++)
		{
			System.out.println(common[i] + "\t" + counts[i]);
		}
	}
	
	public static String[] getCommon()
	{
		FileResource resource = new FileResource("data/common.txt");
		String common[] = new String[20];
		int index = 0;
		for(String s: resource.words())
		{
			common[index] = s;
			index++;
			if(index == 20)	break;
		}
		return common;
	}
	
	public static void countWords(FileResource resource, String[] common, int[] counts)
	{
		for(String word: resource.words())
		{
			int index = indexOf(common,word.toLowerCase());
			if(index != -1)
			{
				counts[index]++;
			}
		}
	}
	
	public static int indexOf(String [] common, String s)
	{
		for(int i = 0 ; i < common.length; i++)
		{
			if(common[i].equals(s))
			{
				return i;
			}
		}
		
		return -1;
	}
}
