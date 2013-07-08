package twarter.derived;

import java.util.*;
import java.awt.Point;
public class AddressList {
/*
 * This returns an object that is an associated ArrayList
 * of the Key, and its latitude and longitude.
 * A key can be retrieved by latitude and longitude or vice verse
 */

	/*ArrayList<String> key = new ArrayList<String>();
	ArrayList<Double> lat = new ArrayList<Double>();
	ArrayList<Double> lon = new ArrayList<Double>();*/
	HashMap<String,Point> places = new HashMap<String, Point>();
	
	public HashMap<String,Point> getAddressList(){
		return places;
	}
	
	public void newAddress(String nKey, double nLat, double nLon){
		Point loc = new Point();
		loc.setLocation(nLon, nLat);
		places.put(nKey, loc);
	}

	public void newAddress(String nKey,Point loc){
		places.put(nKey, loc);
	}
	
	public Point getAddressPoint(String index){
		Point addr = new Point();
		if(places.containsKey(index)){
			return places.get(index);
		}else{
			return null;
		}
	}
	
}



