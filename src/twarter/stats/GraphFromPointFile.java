package twarter.stats;

import java.awt.*;
import java.io.*;
import java.util.*;

import acm.graphics.*;
import acm.program.GraphicsProgram;

public class GraphFromPointFile extends GraphicsProgram{

	int thisW = 1000;
	int thisH = 650;
	private static final double BLOCK_SIZE = 12;
	ArrayList<Double> x_arr = new ArrayList<Double>();
	ArrayList<Double> y_arr = new ArrayList<Double>();
	double minX;
	double maxX;
	double maxY;
	double minY;
	String fn = "graphpoints.txt";

	GCanvas gc = new GCanvas();
	public void run(){

		try {
			BufferedReader br = new BufferedReader(new FileReader(fn));
			try {
				String line = br.readLine();
				while(line!=null){
					System.out.println(line);
					char delim = ',';
					int div = line.indexOf(delim);
					Double newX = Double.valueOf(line.substring(0, div));
					Double newY = Double.valueOf(line.substring(div+1));
					x_arr.add(newX);
					y_arr.add(newY);
					line = br.readLine();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Could not read from file.");
			}

		} catch (FileNotFoundException e) {

		}
		findMinMax();
		drawCanvas();
		addPoints();
		connectTheDots();
	}

	private void findMinMax() {
		for(int i = 0; i< x_arr.size(); i++){
			double newX = x_arr.get(i);
			double newY = y_arr.get(i);
			if(newX<minX){
				minX=newX;
			}
			if(newX>maxX){
				maxX=newX;
			}
			if(newY<minY){
				minY=newY;
			}
			if(newY>maxY){
				maxY=newY;
			}
		}
		System.out.println(minX+"--"+maxX+" is the min and max X");
		System.out.println(minY+"--"+maxY+" is the min and max X");
	}

	private void drawCanvas(){
		//Draw the canvas and add lines at 20px intervals (1 unit)

		int bufferX = (int)(BLOCK_SIZE*3);
		int bufferY = (int) (BLOCK_SIZE*2);
		thisW = (int) ((maxX-minX)*BLOCK_SIZE)+(bufferX*2);
		thisH = (int) ((maxY-minY)*BLOCK_SIZE)+(bufferY*2);
		this.setSize(thisW, thisH);

		int i = 0;

		while(i<maxX+1){
			//Vertical Lines
			GLine l = new GLine(bufferX+(i*BLOCK_SIZE),bufferY,bufferX+(i*BLOCK_SIZE) ,thisH-bufferY);
			l.setColor(Color.blue);
			this.add(l);
			if (x_arr.contains(Double.valueOf(Integer.toString(i)))) {
				double label_x = l.getStartPoint().getX();
				double label_y = (maxY * BLOCK_SIZE) + (bufferY * 1.5);
				GLabel label = new GLabel(Integer.toString(i), label_x, label_y);
				label.setFont("times new roman");
				this.add(label);
			}
			i++;
			
		}
		i=0;
		while (i<maxY+1) {
			//Horizontal Lines
			GLine l2 = new GLine(bufferX, bufferY + (i * BLOCK_SIZE), thisW-bufferX, bufferY + (i * BLOCK_SIZE));
			l2.setColor(Color.blue);
			this.add(l2);
			double label_x = bufferX/2;
			double label_y = l2.getStartPoint().getY();
			if(y_arr.contains(Double.valueOf(Integer.toString(i)))){
				GLabel label = new GLabel(Integer.toString((int) (maxY-i)), label_x, label_y);
				label.setFont("times new roman");
				
				this.add(label);
			}
			i++;
		}
		System.out.println("added lines to gc");	
		//Add the axis counts

	}

	private void addPoints(){
		int bufferX = (int)(BLOCK_SIZE*3);
		int bufferY = (int) (BLOCK_SIZE*2);
		double base_y = thisH-bufferY;
		System.out.println("Adding Points");
		for(int i =0;i<y_arr.size();i++){
			GOval t = new GOval(10, 10);
			System.out.println(y_arr.get(i));
			double ov_x = (bufferX+(x_arr.get(i)*BLOCK_SIZE))-(t.getWidth()/2);
			double ov_y = base_y -(y_arr.get(i)*BLOCK_SIZE)-(t.getHeight()/2);
			t.setLocation(ov_x,ov_y );
			System.out.println("x= "+ov_x+" y= "+ov_y);
			t.setFilled(true);
			t.setFillColor(Color.red);
			this.add(t);
		}
	}

	private void connectTheDots() {
		int o_w = 10;
		int o_h = 10;
		int bufferX = (int)(BLOCK_SIZE*3);
		int bufferY = (int) (BLOCK_SIZE*2);
		double base_y = thisH-bufferY;
		System.out.println("Adding Points");
		for(int i =0;i<y_arr.size()-1;i++){
			GLine t;

			double ov_x = (bufferX+(x_arr.get(i)*BLOCK_SIZE));
			double ov_y = base_y -(y_arr.get(i)*BLOCK_SIZE);
			double nov_x = (bufferX+(x_arr.get(i+1)*BLOCK_SIZE));
			double nov_y = base_y -(y_arr.get(i+1)*BLOCK_SIZE);
			t = new GLine(ov_x, ov_y,nov_x,nov_y);
			t.setColor(Color.red);
			this.add(t);
		}
	}
}
