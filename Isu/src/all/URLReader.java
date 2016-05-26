package all;

import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.List;
import java.io.*;

public class URLReader {
    public static void main(String[] args) throws Exception {
    	System.out.println("Fair warning... This script is slow as shit. \nAlso, you might get some redundant emails...");
    	System.out.println("\nI'm writing these emails to emailRefined.txt.\n");
    	System.out.println("If you it to run faster, you can change the for loop to not go from a-z to search for initials.");
    	try {
    		Thread.sleep(5000); // Just wait 5 seconds so you can read my message is all.
    	}
    	catch(InterruptedException ex){
    		Thread.currentThread().interrupt();
    	}
    	ArrayList<String> profiles = new ArrayList<String>();
    	ArrayList<String> emails = new ArrayList<String>();
    	File file = new File("emails.txt");
    	//System.out.println(file.exists());
    	
    	Scanner s = new Scanner(file);
    	HashMap<String, Integer> ha = new HashMap<String, Integer>();
    	ArrayList<String> newL = new ArrayList<String>();
    	Integer i = 0;
    	
    	while(s.hasNext()){
    		String word = s.next();
    		if (!ha.containsKey(word)){
    			ha.put(word, i);
    			newL.add(word);
    			i++;
    		}
    		
    	}
    	//System.out.println(newL.size());
    	writeOut(newL);
    	//System.out.println(ha.size());
//    	for(int i=0;i<=100;i++){
//            prog.setValue(i); // this would set the progressbar
//           Thread.sleep(300); // this should pause the program or the loop
//         }
    	//int i = 1;
    	for(char alphabet = 'a'; alphabet <= 'z';alphabet++) {
    		for(char alphabet1 = 'a'; alphabet1 <= 'b';alphabet1++){
    			for(char alphabet2 = 'a'; alphabet2 <= 'z';alphabet2++){
    				String url = "http://info.iastate.edu/individuals/search/" + alphabet + alphabet1 + alphabet2;
    				
    				//String url = "http://info.iastate.edu/individuals/search/" + alphabet + "a" +"a";
	
    		parseS(profiles,url);
    			}
    		}
    	}
    	for ( i=0; i <profiles.size(); i ++){
    		String str = profiles.get(i);
    		doPage(emails, str);
    	}
    	writeOut(emails);
    	System.out.println("Number of items found: " + profiles.size());
    	
    }
    public static void parseS(ArrayList<String>tmp, String url) throws IOException{
    	
    	URL oracle = new URL(url);
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));

        String inputLine;
        String save = "";
        while ((inputLine = in.readLine()) != null)
           save += inputLine;
        in.close();
        String find = "href=" +"\"/individuals/info";
       // System.out.println(save.length());
        int index = 0;
        int count = 0;
        String pre = "http://info.iastate.edu";
        String save1 = save;
        ArrayList<String> finds = new ArrayList<String>();
        save = save.substring(index + 1);
        index = save.indexOf(find);
        while (index != -1) {
            //System.out.println(index);
            int index1= index;
            String temp = "";
            for(int i = 0; i < 50; i ++){
            	char ind = save.charAt(index1 + 6);
            	if (ind == '"') break;
            	index1 ++;
            	temp += ind;
            }
            String myString = pre + temp;
            String[] array = myString.split("\\/");
			System.out.println("Grabbing " + array[array.length - 1] + "'s email");
            tmp.add(pre+temp);
          //  System.out.println("String: " + temp);
            count++;
            save = save.substring(index + 1);
            index = save.indexOf(find);
            if (index == -1) break;
        }
    }
    
    public static void doPage(ArrayList<String> temp, String url) throws IOException{
    	URL oracle = new URL(url);
    	String post = "@iastate.edu";
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));

        String inputLine;
        String save = "";
        while ((inputLine = in.readLine()) != null)
           save += inputLine;
        in.close();
       // System.out.println(save);
        int index = 0;
        String find = "href=\"mailto:";
        save = save.substring(index + 1);
        index = save.indexOf(find);
        if (index == -1){
        	System.out.println("Did not find Email for: " + url);
        	return;
        }
        String temp1 = "";
        for(int i = 0; i < 50; i ++){
        	char ind = save.charAt(index + 20);
        	if (ind == '\'') break;
        	index ++;
        	temp1 += ind;
        }
        temp.add(temp1+post);
    }
    
    public static void writeOut(ArrayList<String> e) throws FileNotFoundException, UnsupportedEncodingException{
    	PrintWriter writer = new PrintWriter("emailsRefinedList.txt", "UTF-8");
    	for(int i = 0; i < e.size(); i ++){
    		writer.println(e.get(i));
    	}
    	writer.close();
    }
    public static void writeOut(HashMap <String, Integer> e) throws FileNotFoundException, UnsupportedEncodingException{
    	PrintWriter writer = new PrintWriter("emailsRefined.txt", "UTF-8");
    	for(int i = 0; i < e.size(); i ++){
    		writer.println(e.get(i));
    	}
    	writer.close();
    }
    
    
}

