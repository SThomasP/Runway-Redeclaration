import java.util.ArrayList;

/**
 * Created by steff on 15/02/2017.
 */
public final class Calculator {

	static int RESA = 240;
	static int stripEnd = 60;
	static int slopeFactor = 50;
	//contains the working of the last calculation done
	static String working;

	public static ArrayList<Integer> calculate(ObstacleOnRunway or,Aircraft a)
	{
		working = "";
		ArrayList<Integer> recalculatedDistances = new ArrayList<Integer>();
		recalculatedDistances.add(calculateToda(or,a));
		recalculatedDistances.add(calculateTora(or,a));
		recalculatedDistances.add(calculateAsda(or,a));
		recalculatedDistances.add(calculateLda(or,a));
		return recalculatedDistances;


	}

	static int calculateTora(ObstacleOnRunway or,Aircraft a)
	{
		working += System.lineSeparator() + "Tora Calculation" + System.lineSeparator();
		if(or.closeToThreshold){
			int subtraction = a.getBlastDistance();
			working += "Subtraction: Max (" + RESA + "(RESA) + " + stripEnd + "(Strip End)"+ "," + a.getBlastDistance() + "(Blast Distance))" + System.lineSeparator();
			if (a.getBlastDistance() < RESA + stripEnd){
				subtraction = RESA + stripEnd;
			}
			else
			{
				subtraction = a.getBlastDistance();
			}
			working += "Tora = " + or.getRunway().getTora() + "(Old Tora) - " + or.getDistanceFromThreshold() + "(Distance From Threshold) - "  + subtraction + "(Subtraction) - "  + or.getRunway().getDisplacedThreshold() + "(Displaced Threshold)" + System.lineSeparator();  
			int newToraAway = or.getRunway().getTora() - or.getDistanceFromThreshold() - subtraction - or.getRunway().getDisplacedThreshold();
			working += "= " + newToraAway + System.lineSeparator();

			return newToraAway;
		} else {
			int subtraction = RESA; //RESA
			int height = or.getObstacle().getHeight()*slopeFactor;
			working += "Subtraction: Max (" + RESA + "(RESA)" + "," + or.getObstacle().getHeight() + "(Obstacle Height) + " + slopeFactor + "(Slope Factor))" + System.lineSeparator();
			if (height > RESA){
				subtraction = height;
			}
			working += "Toda = " + or.getDistanceFromThreshold() + "(Distance From Threshold) - " + stripEnd + "(Strip End) - "  + subtraction + "(Subtraction) + "  + or.getRunway().getDisplacedThreshold() + "(Displaced Threshold)" + System.lineSeparator();
			int newToraTowards = or.getDistanceFromThreshold() - stripEnd - subtraction + or.getRunway().getDisplacedThreshold(); 
			working += "= " + newToraTowards + System.lineSeparator();
			return newToraTowards;
		}
	}
	
	static int calculateToda(ObstacleOnRunway or,Aircraft a)
	{
		working += "Toda Calculation" + System.lineSeparator();
		if(or.closeToThreshold){
			int subtraction = a.getBlastDistance();
			working += "Subtraction: Max (" + RESA + "(RESA) + " + stripEnd + "(Strip End)"+ "," + a.getBlastDistance() + "(Blast Distance))" + System.lineSeparator();
			if (a.getBlastDistance() < RESA + stripEnd){
				subtraction = RESA + stripEnd;
			}
			else
			{
				subtraction = a.getBlastDistance();
			}
			working += "Toda = " + or.getRunway().getToda() + "(Old Toda) - " + or.getDistanceFromThreshold() + "(Distance From Threshold) - "  + subtraction + "(Subtraction) - "  + or.getRunway().getDisplacedThreshold() + "(Displaced Threshold)" + System.lineSeparator();  
			int newTodaAway = or.getRunway().getToda() - or.getDistanceFromThreshold() - subtraction - or.getRunway().getDisplacedThreshold();
			working += "= " + newTodaAway + System.lineSeparator();
			return newTodaAway;
		} else {
			
			working += "Toda = Tora";
			return calculateTora(or,a);
			
		}
	}

	

	static int calculateAsda(ObstacleOnRunway or,Aircraft a)
	{
		working += System.lineSeparator() + "Asda Calculation" + System.lineSeparator();
		if(or.closeToThreshold){
			int subtraction = a.getBlastDistance();
			working += "Subtraction: Max (" + RESA + "(RESA) + " + stripEnd + "(Strip End)"+ "," + a.getBlastDistance() + "(Blast Distance))" + System.lineSeparator();
			if (a.getBlastDistance() < RESA + stripEnd){
				subtraction = RESA + stripEnd;
			}
			else
			{
				subtraction = a.getBlastDistance();
			}
			working += "Tora = " + or.getRunway().getAsda() + "(Old Asda) - " + or.getDistanceFromThreshold() + "(Distance From Threshold) - "  + subtraction + "(Subtraction) - "  + or.getRunway().getDisplacedThreshold() + "(Displaced Threshold)" + System.lineSeparator();  
			int newAsdaAway = or.getRunway().getAsda() - or.getDistanceFromThreshold() - subtraction - or.getRunway().getDisplacedThreshold();
			working += "= " + newAsdaAway + System.lineSeparator();
			return newAsdaAway;
		} else {
			working += "Asda = Tora";
			return calculateTora(or,a);

		}
	}

	static int calculateLda(ObstacleOnRunway or,Aircraft a)
	{
		working += System.lineSeparator() + "Asda Calculation" + System.lineSeparator();
		if(or.closeToThreshold)
		{
			working += "New Resa: Max (" + RESA + "(RESA)" + "," + or.getObstacle().getHeight() + "(Obstacle Height) + " + slopeFactor + "(Slope Factor))" + System.lineSeparator();
			int h = or.getObstacle().getHeight(); //height of the obstacle
			int newRESA = Math.max(h*slopeFactor, Calculator.RESA);
			working += "Subtraction: Max (" + newRESA + "(newRESA) + " + stripEnd + "(Strip End)"+ "," + a.getBlastDistance() + "(Blast Distance))" + System.lineSeparator();
			int subtraction  = stripEnd + newRESA; //strip end value and slope
			int blast = a.getBlastDistance(); //blast from the plane's engines
			if (subtraction < blast){ // if blast bigger the height calculations, use blast
				subtraction = blast;

			}
			working += "Tora = " + or.getRunway().getLda() + "(Old Lda) - " + or.getDistanceFromThreshold() + "(Distance From Threshold) - "  + subtraction + "(Subtraction) - " + System.lineSeparator();  
			int newLdaOver = or.getRunway().getLda() - or.getDistanceFromThreshold() - subtraction;
			//new LDA = original LDA - obstacle distance from the threshold - calculated 'obstruction'
			working += "= " + newLdaOver + System.lineSeparator();
			return newLdaOver;
		}
		else
		{
			working += "LDA = " + or.getDistanceFromThreshold() + "(Distance From Threshold) - " + or.getDistanceFromThreshold() + "(Distance From Threshold) - "  + RESA + "(RESA) - "  + stripEnd + "(Strip End)" + System.lineSeparator();  
			int newLdaTowards = or.getDistanceFromThreshold() - RESA - stripEnd;
			//new LDA = distance from threshold - RESA - stripend
			working += "= " + newLdaTowards + System.lineSeparator();
			return newLdaTowards;
		}

	}


}