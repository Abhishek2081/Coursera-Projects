package week2;

import java.util.*;
import edu.duke.*;

public class GladLibs {
	
	private static ArrayList<String> adjectiveList;
	private static ArrayList<String> nounList;
	private static ArrayList<String> colorList;
	private static ArrayList<String> countryList;
	private static ArrayList<String> nameList;
	private static ArrayList<String> animalList;
	private static ArrayList<String> timeList;
	private static ArrayList<String> verbList;
	private static ArrayList<String> fruitList;
	private static ArrayList<String> trackList;
	
	private static int count;
	private static Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com//course3//data";
	/*This is a <title>302 Found</title> story about how a 
	<h1>Found</h1> <hr> became a <title>302 Found</title> 
	<address>Apache/2.4.29 (Ubuntu) Server at 
	www.dukelearntoprogram.com Port 80</address>. Once upon a 
	time, about 305 <!DOCTYPE HTML PUBLIC "-//IETF//DTD HTML 
	2.0//EN"> ago, <h1>Found</h1>, <p>The document has moved <a 
	href="https://www.dukelearntoprogram.com//course3/data/adjective.txt">here</a>.</p> 
	<p>The document has moved <a 
	href="https://www.dukelearntoprogram.com//course3/data/animal.txt">here</a>.</p>s 
	roamed the earth. One of them was named <!DOCTYPE HTML 
	PUBLIC "-//IETF//DTD HTML 2.0//EN">. This <!DOCTYPE HTML 
	PUBLIC "-//IETF//DTD HTML 2.0//EN"> was alone in the world. 
	Then it became a </head><body> </head><body> living in 
	<h1>Found</h1>.*/ 
	private static String dataSourceDirectory = "data";
	
	static
	{
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
		count = 0;
		trackList = new ArrayList<>();
	}
	
//	public GladLibs(String source)
//	{
//		initializeFromSource(source);
//		myRandom = new Random();
//	}
	
	public static void main(String [] args)
	{
		FileResource resource = new FileResource("data/madtemplate.txt");
		for(String s: resource.lines()) 
			System.out.println(s);
		System.out.println();
		String story = fromTemplate("data/madtemplate.txt");
		printOut(story, 60);
		System.out.println("\n\nNo. of Words Changes : " + count);
		trackList.clear();
	}
	
	private static void initializeFromSource(String source) 
	{
		adjectiveList = readIt(source + "/adjective.txt");
		nounList      = readIt(source + "/noun.txt");
		colorList     = readIt(source + "/color.txt");
		countryList   = readIt(source + "/country.txt");
		nameList	  = readIt(source + "/name.txt");
		animalList	  = readIt(source + "/animal.txt");
		timeList 	  = readIt(source + "/timeframe.txt");
		verbList      = readIt(source + "/verb.txt");
		fruitList	  = readIt(source + "/fruit.txt");
	}
	
	private static String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private static String getSubstitute(String label) {
		if (label.equals("country")) {
			return randomFrom(countryList);
		}
		if (label.equals("color")){
			return randomFrom(colorList);
		}
		if (label.equals("noun")){
			return randomFrom(nounList);
		}
		if (label.equals("name")){
			return randomFrom(nameList);
		}
		if (label.equals("adjective")){
			return randomFrom(adjectiveList);
		}
		if (label.equals("animal")){
			return randomFrom(animalList);
		}
		if (label.equals("timeframe")){
			return randomFrom(timeList);
		}
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		if (label.equals("verb")){
			return randomFrom(verbList);
		}
		if (label.equals("fruit")){
			return randomFrom(fruitList);
		}
		return "**UNKNOWN**";
	}
	
	private static String processWord(String w){
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String sub = getSubstitute(w.substring(first+1,last));
		if(!trackList.contains(sub))
		{
			trackList.add(sub);
		}
		while(trackList.contains(sub))			
		{
			sub = getSubstitute(w.substring(first+1,last));
			count++;
		}
		return prefix+sub+suffix;
	}
	
	private static void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private static String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private static ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
}
