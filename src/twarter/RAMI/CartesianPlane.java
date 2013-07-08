package twarter.RAMI;

import java.awt.Point;
import java.util.ArrayList;

public class CartesianPlane {
	private double maxX;
	private double maxY;
	private ArrayList<Point> cellCenters;
	private boolean manhattanDist = true;//If false use Euclidean distance. This will smooth out the lines 
	
	public void test_case(){
		maxX = 1000;
		maxY = 1000;
		cellCenters.add(new Point(-42,73));
	}

	/**
	 * @param manhattanDist the manhattanDist to set
	 */
	public void setManhattanDist(boolean manhattanDist) {
		this.manhattanDist = manhattanDist;
	}

	/**
	 * @return the manhattanDist
	 */
	public boolean usesManhattanDist() {
		return manhattanDist;
	}

}
