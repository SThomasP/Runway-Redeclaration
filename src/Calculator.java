import java.util.ArrayList;

/**
 * Created by steff on 15/02/2017.
 */
public final class Calculator {
	
	static int RESA = 240;
	static int stripEnd = 60;
	
	public static ArrayList<Integer> calculate(ObstacleOnRunway or,Aircraft a)
	{
		ArrayList<Integer> recalculatedDistances = new ArrayList<Integer>();
		recalculatedDistances.add(calculateToda(or,a));
		recalculatedDistances.add(calculateTora(or,a));
		recalculatedDistances.add(calculateAsda(or,a));
		recalculatedDistances.add(calculateLda(or,a));
		return recalculatedDistances;
		
		
	}
	
	static int calculateToda(ObstacleOnRunway or,Aircraft a)
	{
		if(or.closeToThreshold){
			int subtraction = a.getBlastDistance();
			
			if (a.getBlastDistance() < RESA + stripEnd){
				subtraction = RESA + stripEnd;
			}
			else
			{
				subtraction = a.getBlastDistance();
			}
			int newTodaAway = or.getRunway().getToda() - or.getDistanceFromThreshold() - subtraction - or.getRunway().getDisplacedThreshold();
			return newTodaAway;
		} else {
			int subtraction = RESA; //RESA
			int height = or.getObstacle().getHeight()*50;
			if (height > RESA){
				subtraction = height;
			}
			int newTodaTowards = or.getDistanceFromThreshold() - stripEnd - subtraction + or.getRunway().getDisplacedThreshold(); 
			return newTodaTowards;
		}
	}
	
	static int calculateTora(ObstacleOnRunway or,Aircraft a)
	{
		if(or.closeToThreshold){
			return calculateToda(or,a) + or.getRunway().getClearway();
		}
		else
		{
			return calculateToda(or,a); //the obstacle blocks clearway 
		}
	}
	
	static int calculateAsda(ObstacleOnRunway or,Aircraft a)
	{
		if(or.closeToThreshold){
			return calculateToda(or,a) + or.getRunway().getStopway();
		}
		else
		{
			return calculateToda(or,a); //the obstacle blocks stopway 
		}
	}
	
	static int calculateLda(ObstacleOnRunway or,Aircraft a)
	{
		if(or.closeToThreshold)
		{
			int h = or.getObstacle().getHeight(); //height of the obstacle
			int subtraction  = stripEnd + h*50; //strip end value and slope
			int blast = a.getBlastDistance(); //blast from the plane's engines
			if (subtraction < blast){ // if blast bigger the height calculations, use blast
				subtraction = blast;
			
			}
			
			int newLdaOver = or.getRunway().getLda() - or.getDistanceFromThreshold() - subtraction;
			//new LDA = original LDA - obstacle distance from the threshold - calculated 'obstruction'
			return newLdaOver;
		}
		else
		{
			int newLdaTowards = or.getDistanceFromThreshold() - RESA - stripEnd;
			//new LDA = distance from threshold - RESA - stripend
			return newLdaTowards;
		}
		
	}
	
	
}