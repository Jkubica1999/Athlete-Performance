package MyCode;

/*
 * Program by Jedrzej Kubica
 * 28/02/2026
 * This is an AthletePerformance class with private variables for:
 * 	-name, rawScores, points, totalPoints and tier
 * Getters for:
 * 	-name and totalPoints
 * Methods:
 * 	-convertScoresToPoints - Converts rawScores into points for each event
 * 	-scoreToPoints - Converts a single event score into a points value (50-100 range depending on the event)
 * 	-calculateTotalPoints - Sorts the points array and sums the top 4 values into totalPoints
 * 	-assignTier - Assigns a performance tier (Elite / Intermediate / Beginner) based on totalPoints
 * 	-displayResults - Prints the athlete name, total points and tier
 * */

public class AthletePerformance {
	private String name;//athletes name
	private double[] rawScores;//scores from the .csv file
	private int[] points;//points that are assigned based on scores
	private int totalPoints;//sum of top 4 points values
	private String tier;//tier assigned based on totalPoints
	
	//full constructor
	public AthletePerformance(String name, double[] rawScores) {
		this.name = name;
		this.rawScores = rawScores;
		this.points = new int[6];//create an empty array
		this.convertScoresToPoints(this.rawScores);//calculate points based on raw scores
		this.calculateTotalPoints();
		this.assignTier();
	}//end constructor
	
	//GETTERS

	public String getName() {
		return name;
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	//loops 6 times through scores to calculate and pass values to points array
	private void convertScoresToPoints(double[] scores) {
		for (int i = 0; i <= 5 ; i++) {
			this.points[i] = scoreToPoints(i, scores[i]);
		}//end for
	}//end convertScoresToPoints
	
	//method takes a single score, determines what competition it is based on event index and uses a switch to score it appropriately 
	private static int scoreToPoints(int eventIndex, double score) {
		double points;
		double best;
		double worst;
		/*switch to determine the competition that is being considered when checking the score. best and worst values hard coded from the .csv
		 * best performer gets 100 points and worst gets 50
		 * athletes the fall in the middle get 50 + a fraction of 50 that represents their place between the best and worst score
		 * if for some reason the athlete doesn't fall in any case, they get 60 points as a fallback
		*/
		switch (eventIndex) {
			case 0:
				best = 13.5;
				worst = 18.5;
				if (score <= best) {
					points = 100;
				} else if (score >= worst) {
					points = 50;					
				} else {
					points = 100 - (50 * (score - best) / (worst - best));
				}//end if
				break;
			case 1:
				best = 50;
				worst = 12;
				if (score >= best) {
					points = 100;
				} else if (score <= worst) {
					points = 50;	
				}else {
					points = 50 + (50 * (score - worst) / (best - worst));
				}//end if
				break;
			case 2:
				best = 55;
				worst = 18;
				if (score >= best) {
					points = 100;
				} else if (score <= worst) {
					points = 50;	
				}else {
					points = 50 + (50 * (score - worst) / (best - worst));
				}//end if
				break;
			case 3:
				best = 60;
				worst = 25;
				if (score >= best) {
					points = 100;
				} else if (score <= worst) {
					points = 50;	
				}else {
					points = 50 + (50 * (score - worst) / (best - worst));
				}//end if
				break;
			case 4:
				best = 9.5;
				worst = 13.5;
				if (score <= best) {
					points = 100;
				} else if (score >= worst) {
					points = 50;					
				} else {
					points = 100 - (50 * (score - best) / (worst - best));
				}//end if
				break;
			case 5:
				best = 110;
				worst = 40;
				if (score >= best) {
					points = 100;
				} else if (score <= worst) {
					points = 50;	
				}else {
					points = 50 + (50 * (score - worst) / (best - worst));
				}//end if
				break;
			default:
				points = 60;//fallback value
				break;
		}//end switch
		
		return (int) points;//return points casted to an int
	}//end scoreToPoints
	
	private void calculateTotalPoints() {
		//use bubble sort to sort the array in ascending order
		for (int i = 0; i < this.points.length - 1; i++) {

		    for (int j = 0; j < this.points.length - 1 - i; j++) {

		        if (this.points[j] > this.points[j + 1]) {

		            int temp = this.points[j];//temp value to hold while switching places
		            this.points[j] = this.points[j + 1];
		            this.points[j + 1] = temp;
		        }//end if
		    }//end embedded for
		}//end for
		this.totalPoints = 0;//set totalPoints to 0 in case method is called multiple times
		//loop 4 times with i equal to 5,4,3,2
		for (int i = this.points.length - 1; i >= (this.points.length - 4); i--) {
			this.totalPoints += this.points[i];//add points to totalPoints
		}
	}//end calculateTotalPoints
	
	//assign tier based on the number of total points
	private void assignTier() {
		if (this.totalPoints >= 360) {
			this.tier = "Elite";
		} else if (this.totalPoints >= 260) {
			this.tier = "Intermediate";
		} else {
			this.tier = "Beginner";
		}//end if
	}//end assignTier
	
	//print out the athletes details
	public void displayResults() {
		System.out.println("Athlete: " + this.name);
		System.out.println("Total Points: " + this.totalPoints);
		System.out.println("Performance tier: " + this.tier);
		System.out.println("------------------------------");
	}//end displayResults
}//end class
