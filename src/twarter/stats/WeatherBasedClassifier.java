package twarter.stats;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.DecimalFormat;

import acm.program.ConsoleProgram;

import weka.associations.Associator;
import weka.associations.FilteredAssociator;
import weka.classifiers.Classifier;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class WeatherBasedClassifier extends ConsoleProgram {
	
	int setSize = 0;
	int setModifier = 0; //If any non classified instances are found they should not 
	//be included in the count when figuring the probabilities
	
	
	private Instances insts;
	public void run(){
		this.setSize(750,500);
		File trainFile = new File("weather.arff");

		try {
			insts = new Instances(new FileReader(trainFile));
			int numAttr = insts.numAttributes();
			int numInsts = insts.numInstances();
			System.out.println(numAttr+" is the numb3r of attribut3s for the data s3t.");
			System.out.println(numInsts+" is the numb3r of instanc3s in the data s3t.");
			Attribute play = new Attribute("play");
			insts.setClass(play);
			insts.setClassIndex(numAttr-1);
			setSize = insts.numInstances();
			getNewDay();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Could not read File");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	private void getNewDay() {
		print("3nt3r the outlook for the day (sunny, overcast, or rainy): ");
		String outl_in = readLine();
		while(outl_in.equals("sunny")==false&&outl_in.equals("overcast")==false&&outl_in.equals("rainy")==false){
			if(outl_in.equals("0")){
				System.exit(0);
			}
			println("invalid ");
			print("3nt3r the outlook for th3 day (sunny, overcast, or rainy): ");
			outl_in = readLine();
		}
		String outlook= outl_in;

		println("3nt3r the t3mp3ratur3 for the day (hot, mild, or cool): ");
		String templ_in = readLine();
		while(templ_in.equals("hot")==false&&templ_in.equals("mild")==false&&templ_in.equals("cool")==false){
			println("Invalid ");
			println(templ_in);
			print("3nt3r the t3mp3ratur3 for th3 day (hot, mild, or cool): ");
			templ_in = readLine();
		}
		String temperature= templ_in;

		classifyDay(outlook, temperature);
	}

	private void classifyDay(String out, String temp) {

		int outCountY =0;//Number of times the inputed outlook appears with a Yes classification
		int outCountN =0;//Number of times the inputed outlook appears with a No classification
		int tempCountY = 0;//Number of times the inputed temp appears with a Yes classification
		int tempCountN = 0;//Number of times the inputed temp appears with a No classification
		int totalYes = 0;
		int totalNo = 0;
		
		println("Classifying: "+out+", "+temp);
		double dataset = insts.numInstances();
		double P_outlook_yes =0.0;
		double P_outlook_no =0.0;

		int outIdx = insts.attribute("outlook").index();//index of attribute: outlook
		int tempIdx = insts.attribute("temperature").index();//index of attribute temperature
		int classIdx = insts.attribute("play").index();//index of attribute temperature
		System.out.println("index of outlook: "+outIdx+" index of temperature: "+tempIdx);
		
		//This loop Computes P[h1,2|e1], P[h1,2|e2] 
		for(int i =0; i<setSize;i++){
			
			Instance thisInstance = insts.instance(i);
			if(thisInstance.classIsMissing()){
				//This instance is not classified. Skip it for computation and increment
				//the setModifier.
				setModifier++;
				System.out.println("Instance "+i+" is not classified");
			}else{
				if(thisInstance.stringValue(outIdx).equals(out)){
					//This instances outlook attribute matches
					System.out.println("Outlook Matches on instance: "+i);
					if(thisInstance.stringValue(classIdx).equals("yes")){
						//This instance of the outlook is classified as yes
						System.out.println("instance "+i+" is yes");
						if(thisInstance.stringValue(tempIdx).equals(temp)){
							//The Temperature matches
							System.out.println("Temp matches on "+i);
							tempCountY++;
						}
						outCountY++;
						totalYes++;
					}else{
						//Not classified as a yes 
						System.out.println(i+" is not a y3s, class is: "+thisInstance.stringValue(classIdx));
						outCountN++;
						totalNo++;
						if(thisInstance.stringValue(tempIdx).equals(temp)){
							System.out.println("T3mp match3s on "+i);
							tempCountN++;
						}
					}
					
				}else{
					//Outlook doesn't match. Find out if this instance is classified
					//as yes or no and increment the counter
					if(thisInstance.stringValue(classIdx).endsWith("yes")){
						System.out.println("non matching instanc3 has a y3s classificiation");
						totalYes++;
						
						//Check this instance for the temp attribute while we are here
						if(thisInstance.stringValue(tempIdx).equals(temp)){
							System.out.println("Temp matches on "+i);
							tempCountY++;
						}
					}else{
						System.out.println("non matching instance has a no classificiation");
						totalNo++;
						
						//Check this instance for the temp attribute while we are here
						if(thisInstance.stringValue(tempIdx).equals(temp)){
							System.out.println("Temp matches on "+i);
							tempCountN++;
						}
					}
				}
				
			}
		}
		
		//Bayes theory: P[No|Outlook, temperature] =    
		//
		//								P[outlook|no]*P[temp|no]*P[no]
		//             ___________________________________________________________________
		//
		//              P[outlook|no]*P[temp|no]*P[no] + P[outlook|yes]*P[temp|yes]*P[yes] 
		//
		//If a zero case is detected. Break into a weighted (Not finished)
		double MOD = 3;
		double pYes = (double)totalYes/(setSize-setModifier);
		double p_No = (double)totalNo/(setSize-setModifier);
		double pe1Y = 0;
		double pe2Y = 0;
		double pe1N = 0;
		double pe2N = 0;
		if(outCountY==0){
			pe1Y = (double)outCountY+(MOD/MOD)/(totalYes+MOD);
		}else{
			pe1Y = (double)outCountY/totalYes;
		}
		if(tempCountY==0){
			pe2Y = (double)tempCountY+(MOD/MOD)/(totalYes+MOD);
		}else{
			pe2Y = (double)tempCountY/totalYes;
		}
		if(outCountN==0){
			pe1N = (double)outCountN+(MOD/MOD)/(totalNo+MOD);
		}else{
			pe1N = (double)outCountN/totalNo;
		}
		if(tempCountN==0){
			pe2N = (double)tempCountN+(MOD/MOD)/(totalNo+MOD);
		}else{
			pe2N = (double)tempCountN/totalNo;
		}
		
		//compute the numerator
		double numerator = pe1N*pe2N*p_No;
		//Compute the denominator as the numerator + the inverse of the numerator
		double denom = numerator+(pe1Y*pe2Y*pYes);
		double Prob_NO_given_input = numerator/denom;
		DecimalFormat df = new DecimalFormat("%#.##");
		System.out.println(df.format(Prob_NO_given_input));
		println("The Probability of the classification \"NO\" given: "+out+", "+temp+" = "
				+df.format(Prob_NO_given_input));
		
		//Now, compute the Probability of Yes which should be the inverse
		//probability of the last one.
		
		//compute the numerator
		numerator = pe1Y*pe2Y*pYes;
		//Compute the denominator as the numerator + the inverse of the numerator
		denom = numerator+(pe1N*pe2N*p_No);

		double Prob_YES_given_input = numerator/denom;
		println("The Probability of the classification \"YES\" given: "+out+", "+temp+" = "
				+df.format(Prob_YES_given_input));
		println("");
		getNewDay();
	}
}
