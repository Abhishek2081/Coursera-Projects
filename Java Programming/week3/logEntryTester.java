package week3;

import java.util.*;

public class logEntryTester {

	public static void main(String[] args) 
	{
//		LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
//		System.out.println(le);																		//sysout(methodCall()) if not toString()
//		LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
//		System.out.println(le2);
		testLogAnalyzer();
	}
	
	public static void testLogAnalyzer() {
        
		LogAnalyzer logA = new LogAnalyzer();
//		logA.readFile("data/weblog-short_log.txt");
//		logA.readFile("data/weblog2-short_log.txt");
//		logA.readFile("data/weblog3-short_log.txt");
//		logA.readFile("data/weblog1_log.txt");
		logA.readFile("data/weblog2_log.txt");
//		logA.readFile("data/short-test_log.txt");
		int k = logA.countUniqueIPs();
		System.out.println("There are " + k + " Unique IP's");
		int num = 400;
		System.out.println("These are IP's with status code greater than " + num);
		logA.printAllHigherThanNum(num);
		System.out.println("---------------------------------------------------------------------------------------------------------");
		String str = "Sep 24";			//"Sep 14" //"Sep 30";
		System.out.println("These are IP's which visited on " + str);
		ArrayList<String> arrL = logA.uniqueIPVisitsOnDay(str);
		System.out.println(arrL.size());
		System.out.println("---------------------------------------------------------------------------------------------------------");
		int low = 200, high = 299;
		System.out.println("The No. IP's with status code between " + low + " and " + high + " are " + logA.countUniqueIPsInRange(low, high));
		System.out.println("---------------------------------------------------------------------------------------------------------");
		HashMap<String, Integer> counts = logA.countVisitsperIP();
		System.out.println("Total Visits per IP : \n" + counts);
		System.out.println("---------------------------------------------------------------------------------------------------------");
		System.out.println("Maximum number of visits to this website by a single IP address : " + logA.mostNumberVisitsByIP(counts));
		System.out.println("---------------------------------------------------------------------------------------------------------");
		ArrayList<String> maxVisitedIPs = logA.iPsMostVisits(counts);
		System.out.println("Maximum number of visits to this website by IP's : " + maxVisitedIPs);
		System.out.println("---------------------------------------------------------------------------------------------------------");
		HashMap<String, ArrayList<String>> iPForDays = logA.iPsForDays();
		System.out.println("IP's for Days is : " + iPForDays);
		System.out.println("---------------------------------------------------------------------------------------------------------");
		System.out.println("Days with most visited IP's : " + logA.dayWithMostIPVisits(iPForDays));
		System.out.println("---------------------------------------------------------------------------------------------------------");
		System.out.println("Ip's With Most Visit On A Day : " + logA.iPsWithMostVisitsOnDay(iPForDays, "Sep 29"));
//		logA.printAll();
    }
	
	static class LogEntry
	{
		private String ipAddress;
	    private Date accessTime;
	    private String request;
	    private int statusCode;
	    private int bytesReturned;
	     
	   public LogEntry(String ip, Date time, String req, int status, int bytes) {
	       ipAddress = ip;
	       accessTime = time;
	       request = req;
	       statusCode = status;
	       bytesReturned = bytes;
	       
	   }
	   
	   public String getIpAddress() {
	         return ipAddress;
	    }
	    public Date getAccessTime() {
	         return accessTime;
	   }   
	   public String getRequest() {
	         return request;
	   }
	   public int getStatusCode() {
	         return statusCode;
	   }
	   public int getBytesReturned() {
	         return bytesReturned;
	   }
	   
	   public String toString() 				// Doesn't Work for tostring() as well
	   {
	       return ipAddress + " " + accessTime + " " + request 
	           + " " + statusCode + " " + bytesReturned;
	    }
	}

}
