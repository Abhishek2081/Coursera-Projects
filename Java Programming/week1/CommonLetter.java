package string;

public class CommonLetter {

	public static void main(String[] args) {
		
		countLet("Hi, do you want a lollipop today? I own many good flavors, but banana is outstanding.");

	}
	
	public static void countLet(String str)
	{
		int count[] = new int[26];
		for(int i = 0; i < str.length(); i++)
		{
			if(Character.isAlphabetic(str.charAt(i)))
			{
				count[Character.toUpperCase(str.charAt(i)) - 'A']++;
			}
		}
		
		for(int i = 0; i < count.length; i++)
		{
			System.out.println((char)(i + 'A') + " -> " + count[i]);
		}
	}

}
