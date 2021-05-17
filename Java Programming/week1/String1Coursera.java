package string;

public class String1Coursera {
	
	public static void main(String args[])
	{
//		System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!",15));
//		System.out.println(encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?",15));
//		System.out.println(encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?",21,8));
		
//		System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
		
//		for(int i = 0; i < 26; i++)
//		{
//			for(int j = 0; j < 26; j++)
//			{
//				System.out.println(encrypt("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!",i,j));
//			}
//			System.out.println(i + 1 + " " + encrypt("Akag tjw Xibhr awoa aoee xakex znxag xwko", i));
//		}
		System.out.println(encrypt("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!",26 - 7,26 - (26 - 7)));
		System.out.println(encrypt("Akag tjw Xibhr awoa aoee xakex znxag xwko", 26 - 22, 26 - 19));
//		System.out.println(encrypt("Io iwjv jz dv bcm kjvammmikz mwju edbc twpz pvb wi awm v ncmxmqnm xvzog. TMGT TJCY!", 18, 5));		//At 26-key1 & 26-key2
//		System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
	}
	
	public static String encrypt(String str, int key)
	{
		StringBuilder sb = new StringBuilder(str);            //Encryption key = 19
		String s2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String s3 = s2.substring(key) + s2.substring(0,key);
		for(int i = 0; i < sb.length(); i++)
		{
			int k = s2.indexOf(Character.toUpperCase(sb.charAt(i)));
			if(k != -1)
			{
				if(Character.isUpperCase(sb.charAt(i)))
					sb.setCharAt(i, s3.charAt(k));
				else	sb.setCharAt(i, Character.toLowerCase(s3.charAt(k)));
			}
		}
		
		return sb.toString();
	}
	
	public static String encrypt(String str, int key1, int key2)
	{
		StringBuilder sb = new StringBuilder(str);
		String s2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String s3 = s2.substring(key1) + s2.substring(0,key1);
		String s4 = s2.substring(key2) + s2.substring(0,key2);
		boolean flag = true;							//Flag is an even odd counter
		for(int i = 0; i < sb.length(); i++)
		{
			int k = s2.indexOf(Character.toUpperCase(sb.charAt(i)));
			if(k != -1)
			{
				if(flag == true)							//use condition ((i & 1) == 0) and remove all flags 
				{
					if(Character.isUpperCase(sb.charAt(i)))
						sb.setCharAt(i, s3.charAt(k));
					else	sb.setCharAt(i, Character.toLowerCase(s3.charAt(k)));
					flag = false;
				}
				else
				{
					if(Character.isUpperCase(sb.charAt(i)))
						sb.setCharAt(i, s4.charAt(k));
					else	sb.setCharAt(i, Character.toLowerCase(s4.charAt(k)));
					flag = true;
				}
			}
			else flag = !flag;				// used so that even if space comma or other special character come even odd counter shouldn't get affected
		}
//		System.out.println("Io iwjv jz dv bcm kjvammmikz mwju edbc twpz pvb wi awm v ncmxmqnm xvzog. TMGT TJCY!".equals("Io iwjv jz dv bcm kjvammmikz mwju edbc twpz pvb wi awm v ncmxmqnm xvzog. TMGT TJCY!"));
		return sb.toString();
	}
}
