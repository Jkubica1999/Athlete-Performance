package MyCode;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Program by Jedrzej Kubica
 * 28/02/2026
 * A use-case for the AthletePerformance class.
 * Actions:
 *  -Reads a .csv file using BufferedReader and InputStreamReader.
 *  -Skips the header row and loops through each remaining line.
 *  -Splits each row by commas and validates the expected amount of values.
 *  -Parses 6 score values into doubles, creates AthletePerformance objects and stores them in an ArrayList.
 *  -Loops through the ArrayList to display each athlete's results.
 *  -Determines the highest and lowest performers based on total points.
 *  -Calculates the average points scored and prints a final summary report.
 * */

public class UseAthletePerformance {

	public static void main(String[] args) {
		String csvFile = "src/MyCode/athletes.csv";//csv file path
		String line;
		String csvSplitBy = ",";
		ArrayList<AthletePerformance> athletes = new ArrayList<>();
		//try reading the csv file
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), "UTF-8"))) {
			br.readLine();//skip first row
			
			while((line = br.readLine()) != null) {//while the line isn't empty
				String[] values = line.split(csvSplitBy);//split the line by commas
				if(values.length == 7) {//if the amount of values is 7 try creating an AthletePerformance object
					try {//try parsing values into doubles and create an AthletePerformance object if the data matches
						String name = values[0];
						//parse strings to double variables
						double val1 = Double.parseDouble(values[1]);
						double val2 = Double.parseDouble(values[2]);
						double val3 = Double.parseDouble(values[3]);
						double val4 = Double.parseDouble(values[4]);
						double val5 = Double.parseDouble(values[5]);
						double val6 = Double.parseDouble(values[6]);
						//put values in an array to pass it to the constructor
						double[] rawScores = {val1,val2,val3,val4,val5,val6};
						athletes.add(new AthletePerformance(name,rawScores));
					}catch (Exception f) {
						//if exception occurs print stack trace
						f.printStackTrace();
					}//end try catch
				}//end if
			}//end while
		} catch (IOException e) {
			//if exception occurs print stack trace
			e.printStackTrace();
		}//end try catch
		//variables to store the lowest and highest performer's names and points
		String lowPerformName = "";
		String highPerformName = "";
		int lowPerformPoints = Integer.MAX_VALUE;
		int highPerformPoints = Integer.MIN_VALUE;
		//variable to store the total number of points scored by all athletes
		double total = 0.0;
		
		for (AthletePerformance athlete : athletes) {//loop through every object in athletes ArrayList
			athlete.displayResults();//print the athletes results
			total += (double) athlete.getTotalPoints();//add the athletes points to the total
			//if the current athletes has more points than the current top performer, update them
			if (athlete.getTotalPoints() > highPerformPoints) {
				highPerformPoints = athlete.getTotalPoints();
				highPerformName = athlete.getName();
			}//end if
			//if the current athletes has less points than the current bottom performer, update them
			if (athlete.getTotalPoints() < lowPerformPoints) {
				lowPerformPoints = athlete.getTotalPoints();
				lowPerformName = athlete.getName();
			}//end if
		}//end for
		double average = total / athletes.size();//calculate the average amount of points scored by all athletes
		System.out.println("==============================\n"
						+ "Top Performer: " + highPerformName + "(" + highPerformPoints +")\n"
						+ "Lowest Performer: " + lowPerformName + "(" + lowPerformPoints +")\n"
						+ "Average points: " + String.format("%.2f", average) +
						"\n==============================");
	}//end main

}//end class
