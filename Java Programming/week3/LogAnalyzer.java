package week3;

import java.util.*;
import edu.duke.*;
import week3.logEntryTester.LogEntry;

public class LogAnalyzer {

	private ArrayList<LogEntry> records;
    
    public LogAnalyzer() {
        records = new ArrayList<>();
    }
       
    public void readFile(String filename) {
        FileResource resource = new FileResource(filename);
        
        for(String s: resource.lines())
        {
        	LogEntry le = WebLogParser.parseEntry(s);
        	records.add(le);
        }
    }
    
    public int countUniqueIPs()
    {
    	ArrayList<String> uniqueIPs = new ArrayList<>();
    	
    	for(LogEntry le: records)
    	{
    		String ipAddr = le.getIpAddress();
    		if(!uniqueIPs.contains(ipAddr))
    			uniqueIPs.add(ipAddr);
    	}
    	return uniqueIPs.size();
    }
    
    public HashMap<String, Integer> countVisitsperIP()
    {
    	HashMap<String, Integer> counts = new HashMap<>();
    	
    	for(LogEntry le: records)
    	{
    		String ip = le.getIpAddress();
    		
    		if(!counts.containsKey(ip))
    			counts.put(ip, 1);
    		else
    			counts.put(ip, counts.get(ip) + 1);
    	}
    	return counts;
    }
    
    public int mostNumberVisitsByIP(HashMap<String, Integer> map)
    {
    	int max = -1;
    	for(String ip: map.keySet())
    	{
    		if(max < map.get(ip))
    			max = map.get(ip);
    	}
    	return max;
    }
    
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map)
    {
    	int max = mostNumberVisitsByIP(map);;
    	ArrayList<String> arrL = new ArrayList<>();
    	for(String ip: map.keySet())
    	{
    		if(max == map.get(ip))
    			arrL.add(ip);
    	}
    	return arrL;
    }
    
    public HashMap<String,ArrayList<String>> iPsForDays()
    {
    	HashMap<String,ArrayList<String>> map = new HashMap<>();
    	for(LogEntry le: records)
    	{
    		String s = le.getAccessTime().toString();
    		s = s.substring(4, 10);
    		if(!map.containsKey(s))
    		{
    			map.put(s, new ArrayList<String>());
    			map.get(s).add(le.getIpAddress());
    		}
    		else
    			map.get(s).add(le.getIpAddress());
    	}
    	return map;
    }
    
    public String dayWithMostIPVisits(HashMap<String,ArrayList<String>> map)
    {
    	String str = "";
    	int max = -1;
    	for(String s: map.keySet())
    	{
    		if(map.get(s).size() > max)
    		{
    			max = map.get(s).size();
    			str = s;
    		}
    	}
    	return str;
    }
    
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> map, String day)
    {
    	ArrayList<String> arrL = map.get(day);
    	HashMap<String, Integer> hMap = new HashMap<>();
    	ArrayList<String> Ips = new ArrayList<>();
    	for(String s : arrL)
    	{
    		if(!hMap.containsKey(s))
    			hMap.put(s, 1);
    		else
    			hMap.put(s, hMap.get(s) + 1);
    	}
    	int max = mostNumberVisitsByIP(hMap);
    	for(String s : hMap.keySet())
    	{
    		if(hMap.get(s) == max)
    			Ips.add(s);
    	}
    	return Ips;
    }
    
    public void printAllHigherThanNum(int num)
    {
    	for(LogEntry le: records)
    	{
    		int k = le.getStatusCode();
    		if(k > num)
    			System.out.println(le);
    	}
    }
    
    public ArrayList<String> uniqueIPVisitsOnDay(String someday)
    {
    	ArrayList<String> arrL = new ArrayList<>();
    	for(LogEntry le: records)
    	{
    		String s = le.getAccessTime().toString();
    		s = s.substring(4, 10);
    		if(!arrL.contains(le.getIpAddress()))
    			if(someday.equals(s))
    				arrL.add(le.getIpAddress());
    	}
    	
    	return arrL;
    }
    
    public int countUniqueIPsInRange(int low, int high)
    {
    	ArrayList<String> UniqueIPs = new ArrayList<>();
    	for(LogEntry le: records)
    	{
    		int k = le.getStatusCode();
    		if(k >= low && k <= high)
    		{
    			if(!UniqueIPs.contains(le.getIpAddress()))
    				UniqueIPs.add(le.getIpAddress());
    		}
    	}
    	
    	return UniqueIPs.size();
    }
       
    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }
}
