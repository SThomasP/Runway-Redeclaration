import java.util.ArrayList;

/**
 * Created by steff on 15/02/2017.
 */
public class Calculator {
	
	public ArrayList<Integer> calculate(ObstacleOnRunway or,Aircraft a)
	{
		ArrayList<Integer> recalculatedDistances = new ArrayList<Integer>();
		recalculatedDistances.add(calculateToda(or,a));
		recalculatedDistances.add(calculateTora(or,a));
		recalculatedDistances.add(calculateAsda(or,a));
		recalculatedDistances.add(calculateLda(or,a));
		return recalculatedDistances;
		
		
	}
	
	int calculateToda(ObstacleOnRunway or,Aircraft a)
	{
		return 2;
	}
	
	int calculateTora(ObstacleOnRunway or,Aircraft a)
	{
		return 1;
	}
	
	int calculateAsda(ObstacleOnRunway or,Aircraft a)
	{
		return 1;
	}
	
	int calculateLda(ObstacleOnRunway or,Aircraft a)
	{
		if(or.closeToThreshold)
		{
			int h = or.getObstacle().getHeight(); //height of the obstacle
			int decrement = 60; //strip end value
			int compare = h*50; //height calculations --> height of the obstacle * 50
			int blast = a.getBlastDistance(); //blast from the plane's engines
			if (compare < blast){ // if blast bigger the height calculations, use blast
				if (compare < 240) { //if RESA bigger then height calculations, use RESA
					decrement = decrement + 240; //RESA + strip end
				}
				decrement = blast; //blast distance value
			} else { //otherwise use height calculations
				decrement = decrement + compare; //height*50 plus strip end
			}
			
			int newLdaOver = or.getRunway().getLda() - or.getDistanceFromThreshold() - decrement;
			//new LDA = original LDA - obstacle distance from the threshold - calculated 'obstruction'
			return newLdaOver;
		}
		else
		{
			return 1;
		}
		
	}
	
	
}
