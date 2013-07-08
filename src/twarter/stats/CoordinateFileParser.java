package twarter.stats;

import java.awt.Point;
import java.io.*;
import java.util.*;

import twarter.derived.AddressList;

public class CoordinateFileParser {

	private BufferedReader br ;
	HashMap<String, Point> points = new HashMap<String, Point>();
	private String DELIM = ",";
	private String SEPARATOR = "-";
	File f ;
	private String home_ky;
	public CoordinateFileParser(String file_addr) throws FileNotFoundException{
		f = new File(file_addr);
		br = new BufferedReader(new FileReader(f));
	}
	public HashMap<String, Point> getList(){
		HashMap<String, Point> list = new HashMap<String, Point> ();
		String line = "";
		try {
			line = br.readLine();
			while(line!=null){
				String key = line.substring(0, line.indexOf(SEPARATOR));
				System.out.println(key);
				
				System.out.println("Lat: "+line.substring(line.indexOf(SEPARATOR)+1,line.lastIndexOf(DELIM)));
				System.out.println("Lon: "+line.substring(line.lastIndexOf(DELIM)+1));
				Point pt = new Point();
				double newY = Double.valueOf(line.substring(line.indexOf(SEPARATOR)+1, line.lastIndexOf(DELIM)));

				Double newX = 0.0;
				if(line.endsWith("-")){
					home_ky = key;
					newX  = Double.valueOf(line.substring(line.lastIndexOf(DELIM)+1,line.lastIndexOf(SEPARATOR)));
				}else{
					newX = Double.valueOf(line.substring(line.lastIndexOf(DELIM)+1));
				}
				pt.setLocation(newX, newY);
				list.put(key, pt);
				line = br.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public String getHome(){
		return home_ky;
	}
}
