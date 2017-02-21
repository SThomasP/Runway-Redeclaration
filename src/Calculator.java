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
		return 1;
	}
	
	
}
