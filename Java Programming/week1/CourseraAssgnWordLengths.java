package string;

import edu.duke.FileResource;

public class CourseraAssgnWordLengths {

	public static void main(String[] args) {
		
		FileResource resource = new FileResource("data/smallHamlet.txt");
		int counts[] = new int[31];
		countWordLengths(resource,counts);
		for(int  i = 0; i  < counts.length; i++)
		{
			System.out.println("Words of Length " + (i + 1) + " are \t" + counts[i]);
		}
		System.out.println("Index of Max : " + indexOfMax(counts));
	}
	
	public static int indexOfMax(int [] values)
	{
		int max = values[0];
		int k = -1;
		for(int i = 1; i  < values.length; i++)
		{
			if(max < values[i])
			{
				max = values[i];
				k = i;
			}
		}
		return k + 1;
	}

	public static void countWordLengths(FileResource resource, int[] counts)
	{
		for(String word: resource.words())
		{
			int k = 0;
			boolean flag = false;
			for(int i = 0; i < word.length(); i++)
			{
				if(Character.isAlphabetic(word.charAt(i)))	k++;
				else
				{
					if(word.charAt(i) == 39)
						if(k > 0)	k++;
					if(word.charAt(i) == '"')
					{
						if(flag == false)
							flag = true;
						else
							flag = false;
					}
					if(flag == true)
						k++;
				}
			}
			if(k > 0 && k < 31)
				counts[k - 1]++;
			else
				counts[30]++;
		}
	}
}
