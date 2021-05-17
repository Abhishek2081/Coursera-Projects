package week4;

import java.io.*;
import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
	
	private static int[] printKey;

	public static void main(String[] args) 
	{	
//		FileResource resource = new FileResource("data/titus-small.txt");
//		CaesarCipher cc = new CaesarCipher(4);
//		
//		String str = resource.asString();		
//		System.out.println(cc.encrypt(str));
//		System.out.println(cc.decrypt(str));
		
//		FileResource resource = new FileResource("data/titus-small_key5.txt");
//		CaesarCracker cc = new CaesarCracker();					//new CaesarCracker('e');
//		
//		System.out.println(cc.getKey(resource.asString()));
//		System.out.println(cc.decrypt(resource.asString()));
		
//		FileResource resource = new FileResource("data/oslusiadas_key17.txt");		//Portuguese Validation on google Translate
//		CaesarCracker cc = new CaesarCracker('a');
//		
//		System.out.println(cc.getKey(resource.asString()));
//		System.out.println(cc.decrypt(resource.asString()));
		
//		FileResource resource = new FileResource("data/titus-small.txt");
//		VigenereCipher vc = new VigenereCipher(new int[] {17,14,12,4});
//		
//		String str = vc.encrypt(resource.asString());
//		System.out.println(str);
//		System.out.println(vc.decrypt(str));
		
//		System.out.println(sliceString("abcdefghijklm", 3, 4));
		
//		FileResource resource = new FileResource("data/athens_keyflute.txt");
//		FileResource resource = new FileResource("data/secretmessage1.txt");
//		
//		int[] key = tryKeyLength(resource.asString(), 4, 'e');
//		System.out.println(Arrays.toString(key));
		
//		breakVigenere();
		
//		FileResource resource = new FileResource();
//		FileResource fr = new FileResource("data/English.txt");
//		HashSet<String> dictionary = readDictionary(fr);
//		System.out.println(breakForLanguage(resource.asString(), dictionary));
		
		FileResource resource = new FileResource();
		HashMap<String, HashSet<String>> map = new HashMap<>();
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles())
		{
			FileResource fr = new FileResource(f);
			HashSet<String> dictionary = readDictionary(fr);
			map.put(f.getName(), dictionary);
		}
		System.out.println(breakForAllLangs(resource.asString(), map));
	}
	
	public static String sliceString(String message, int whichSlice, int totalSlices) 
	{
		StringBuilder sb = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i += totalSlices)
        {
        	sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public static int[] tryKeyLength(String encrypted, int klength, char mostCommon) 
    {
        int[] key = new int[klength];
        for(int i = 0; i < klength; i++)
        {
        	String str = sliceString(encrypted ,i , klength);
        	CaesarCracker cb = new CaesarCracker(mostCommon);
        	key[i] = cb.getKey(str);
        }
        return key;
    }

    public static void breakVigenere () 
    {
        FileResource resource = new FileResource();
        String encrypted = resource.asString();
//      int key[] = tryKeyLength(encrypted,5, 'e');
        int key[] = tryKeyLength(encrypted,4, 'e');
        VigenereCipher vc = new VigenereCipher(key);
        System.out.println(vc.decrypt(encrypted));
    }
    
    public static HashSet<String> readDictionary(FileResource fr) 
    {
    	HashSet<String> hs = new HashSet<>();
    	for(String s: fr.lines())
    	{
    		s = s.toLowerCase();
    		hs.add(s);
    	}
		return hs;
	}
    
    public static int countWords(String message, HashSet<String> dictionary) 
    {
    	String[] realWords = message.split("\\W+");
//    	System.out.println(realWords.length);
    	int count = 0;
    	for(String s: realWords)
    	{
    		if(dictionary.contains(s))
    			count++;
    	}
    	return count;
    }
    
    public static String breakForLanguage(String encrypted, HashSet<String> dictionary)
    {
    	int max = -1;
    	String decrypted = "";
    	
    	for(int i = 1; i < 101; i++)
    	{
    		int[] key = tryKeyLength(encrypted, i, mostCommonCharIn(dictionary));
    		VigenereCipher vc = new VigenereCipher(key);
    		int count = countWords(vc.decrypt(encrypted), dictionary);
    		if(count > max)
    		{
    			max = count;
    			printKey = key;
    			decrypted = vc.decrypt(encrypted);
    		}
    	}
		
    	return decrypted;
   	}
    
    public static char mostCommonCharIn(HashSet<String> dictionary)
    {
    	HashMap<Character, Integer> map = new HashMap<>();
    	for(String s: dictionary)
    	{
    		for(int i = 0; i < s.length(); i++)
    		{
    			char c = s.charAt(i);
    			if(!map.containsKey(c))
        			map.put(c, 1);
        		else
        			map.put(c, map.get(c) + 1);
    		}
    	}
    	
    	int max = -1;
    	char ch = '\0';
    	for(char c: map.keySet())
    	{
    		if(max < map.get(c))
    		{
    			max = map.get(c);
    			ch = c;
    		}
    	}
    	
    	return ch;
    }
    
    public static String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages)
    {
    	int max = -1;
    	String decrypted = "", str = "", breakString;
    	int[] thisKey = null;
    	for(String s : languages.keySet())
    	{
    		breakString = breakForLanguage(encrypted, languages.get(s));
    		int count = countWords(breakString, languages.get(s));
    		if(count > max)
    		{
    			max = count;
    			decrypted = breakString;
    			str = s;
    			thisKey = printKey;
    		}
   		}
    	System.out.println("Valid Words for " + str + " Dictionary is : " + countWords(decrypted.toLowerCase(), languages.get(str)));
    	System.out.println(Arrays.toString(thisKey));
    	System.out.println("Key Length : " + thisKey.length);
    	return decrypted;
    }
}
