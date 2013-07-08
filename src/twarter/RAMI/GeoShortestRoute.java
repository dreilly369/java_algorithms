package twarter.RAMI;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import twarter.derived.AddressList;
import twarter.stats.CoordinateFileParser;

import acm.program.*;

public class GeoShortestRoute extends ConsoleProgram {
	/*This class takes a latitude and 
	 *longitude list and creates an AddressList Object that it sends to the ShortRoute analyzer
	 *
	 *Each pair-wise group is sent for a distance using the LonLatDistance class.
	 *then the route is calculated using a pruned decision tree method. Each branch is chosen based on
	 *the shortest distance to the next point.
	 *
	 *This is the interface class that excepts user input and displays the output
	 */

	AddressList al = new AddressList();
	private String home = null;
	ShortRouteTree srt;
	public void run(){
		this.setSize(400,200);
		println("Welcome to the GeoPath optimization sub-routine");
		println("To begin please choose an option:\n");
		println("Load a Location File = 1");
		println("Enter Latitude and Longitude (degrees) = 2");
		println("Exit = 0");
		print("You choose: ");
		int in = readInt();
		println("");
		switch(in){
		case 0:
			System.exit(0);
			break;
		case 1:
			boolean doc = false;
			File nf = new File("");
			String fn = "";
			while(!doc){
				println("Files must be Formatted Coordinate Files, located in the Programs \bin directory");
				print("Enter File Name: ");	
				fn = readLine();
				nf = new File(fn);
				if(nf.exists()||fn=="0"){
					doc = true;
				}
				if(fn=="0"){
					break;
				}
			}
			loadLocations(fn);
			break;
		case 2:
			manualLocationAL();
			if(home.equals(null)){
				String[] tmp = (String[]) al.getAddressList().keySet().toArray();
				home = tmp[0];
			}
			srt = new ShortRouteTree(al, home);
			break;
		case 3:
			//For testing only. Adds points and tries to calc distance
			//Currently Depreciated. Scheduled for removal.
			Point npt1 = new Point();
			npt1.setLocation(-155.05, 22.46);
			al.newAddress("a", npt1);
			home = "a";
			Point npt2 = new Point();
			npt2.setLocation(-175.05, 26.13);
			al.newAddress("b", npt2);
			Point npt3 = new Point();
			npt1.setLocation(155.15, -122.46);
			al.newAddress("c", npt3);
			Point npt4 = new Point();
			npt4.setLocation(-19.05, 105.04);
			al.newAddress("d", npt4);
			srt = new ShortRouteTree(al, home);
			ArrayList<String> rt = srt.route;
			for(int i = 0; i < rt.size()-1; i++){
				println(rt.get(i)+" to "+rt.get(i+1) );
			}
			break;

		default:
			run();
			break;
		}


	}

	//Method for entering in points manually. 
	private void manualLocationAL() {
		boolean cont = true;

		while (cont) {
			println("Enter 0 to quit!");
			println("");
			print("Name: ");
			String nm = readLine();
			print("Enter Latitude in Decimal Degrees: ");
			double lat = readDouble();
			print("Enter Longitude in Decimal Degrees: ");
			double lon = readDouble();
			if(home==null){
				print("is this the Start Location?: ");
				boolean h = readBoolean();
				if(h){
					home = nm;
				}
			}
			al.newAddress(nm, lat, lon);
			print("Again?: ");
			cont = readBoolean();
		}

	}

	//method for loading locations from a formatted Coordinate text file
	private void loadLocations(String fName) {
		try {
			CoordinateFileParser cfp = new CoordinateFileParser(fName);
			HashMap<String, Point> in = cfp.getList();
			if(in.isEmpty()){
				println("Failed to parse file");
			}else{
				Set<String> keyset = in.keySet();
				Iterator<String> itt = keyset.iterator();
				while(itt.hasNext()){
					String k = itt.next();
					Point pt = in.get(k);
					al.newAddress(k, pt);
				}
				srt = new ShortRouteTree(al,cfp.getHome());
				ArrayList<String> rt = srt.route;
				for(int i = 0; i < rt.size()-1; i++){
					println(rt.get(i)+" to "+rt.get(i+1) );
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
