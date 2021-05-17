package test;

import java.util.*;

public class Random1Coursera {

	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Number of Times Dice to be Rolled");
//		simpleSimulate(sc.nextInt());
		simulate(sc.nextInt());
		sc.close();
	}
	
	public static void simulate(int rolls)
	{
		Random rd = new Random();
		
		int counter[] = new int [13];
		
		for(int i = 0; i < rolls; i++)
		{
			int d1 = rd.nextInt(6) + 1;
			int d2 = rd.nextInt(6) + 1;
			System.out.println("Roll is " + d1 + "+" + d2 + "=" + (d1 + d2));
			counter[d1 + d2]++;
		}
		
		for(int j = 2; j <= 12; j++)
		{
			System.out.println("No. of " + j + "'s\t" + counter[j] + "\t" + 100.0 * counter[j]/rolls);
		}
	}
	
	public static void simpleSimulate(int rolls)
	{
		Random rd = new Random();
		
		int two = 0;
		int twelve = 0;
		
		for(int i = 0; i < rolls; i++)
		{
			int d1 = rd.nextInt(6) + 1;
			int d2 = rd.nextInt(6) + 1;
			
			if(d1 + d2 == 2)
			{
				two++;
			}
			if(d1 + d2 == 12)
				twelve++;
		}
		
		System.out.println("two's \t\t--> " + two + "   " + 100.0 * two/rolls + "\n" + "twelve's \t--> " + twelve + "   " + 100.0 * twelve/rolls);
	}
}
