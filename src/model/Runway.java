package model;

import java.text.DecimalFormat;

public class Runway {

	static final int RESA = 240;
	static final int stripEnd = 60;
	static final int slopeFactor = 50;
	public static String toraworking ="";
	public static String todaworking="";
	public static String asdaworking="";
	public static String ldaworking="";



	private final int orientation;
	private final char location;

	private final int inverseOrientation;
	private final char inverseLocation;

	private final int todaOriginal;
	private final int toraOriginal;
	private final int asdaOriginal;
	private final int ldaOriginal;
	private int todaCurrent;
	private int toraCurrent;
	private int asdaCurrent;
	private int ldaCurrent;
	private final int width;
	private Obstacle obstacle;
	private final int displacedThreshold;


	public Runway(int orientation, char location, int toda, int tora, int asda, int lda, int displacedThreshold, int width) {
		this.width = width;
		this.orientation = orientation;
		inverseOrientation = 36 - orientation;
		this.location = location;
		if (location == 'L'){
			inverseLocation = 'R';
		}
		else if (location == 'R') {
			inverseLocation = 'L';
		}
		else
		{
			inverseLocation = 'C';
		}
		this.todaCurrent = toda;
		this.toraCurrent = tora;
		this.asdaCurrent = asda;
		this.ldaCurrent = lda;
		ldaOriginal = lda;
		toraOriginal = tora;
		todaOriginal = toda;
		asdaOriginal = asda;
		this.displacedThreshold = displacedThreshold;
	}

	public void addObstacle(Obstacle obstacle) {
		this.obstacle = obstacle;
		if (obstacle.getDistanceFromCentreLine() < 75 && obstacle.getDistanceFromThreshold() > -60)
		{
			recalculate();
		}
		
		
	}

	
	public void recalculate(Aircraft aircraft) {
		boolean closeToThreshold = ldaOriginal / 2 > obstacle.getDistanceFromThreshold();
		toraworking = "";
		recalculateTora(aircraft, closeToThreshold);
		todaworking = "";
		recalculateToda(aircraft, closeToThreshold);
		ldaworking = "";
		recalculateLda(aircraft, closeToThreshold);
		asdaworking = "";
		recalculateAsda(aircraft, closeToThreshold);
	}

	public void recalculate() {
		recalculate(new Aircraft(300));
	}


	private void recalculateTora(Aircraft aircraft, boolean closeToThreshold) {
		if (closeToThreshold) {
			int subtraction;
			toraworking += "Subtraction: Max (" + RESA + "(RESA) + " + stripEnd + "(Strip End)"+ "," + aircraft.getBlastDistance() + "(Blast Distance))" + System.lineSeparator();
			if (aircraft.getBlastDistance() < RESA + stripEnd) {
				subtraction = RESA + stripEnd;
			} else {
				subtraction = aircraft.getBlastDistance();
			}
			toraworking += "Tora = " + toraOriginal + "(Old Tora) - " + obstacle.getDistanceFromThreshold()  + "(Distance From Threshold) - "  + subtraction + "(Subtraction) - "  + displacedThreshold + "(Displaced Threshold)" + System.lineSeparator();
			int newToraAway = toraOriginal - obstacle.getDistanceFromThreshold() - subtraction - displacedThreshold;
			toraworking += "= " + newToraAway + System.lineSeparator();

			toraCurrent = newToraAway;
		} else {
			int subtraction = RESA; //RESA
			int height = obstacle.getObstacleHeight() * slopeFactor;
			toraworking += "Subtraction: Max (" + RESA + "(RESA)" + "," + obstacle.getObstacleHeight() + "(model.Obstacle Height) + " + slopeFactor + "(Slope Factor))" + System.lineSeparator();
			if (height > RESA) {
				subtraction = height;
			}
			toraworking += "Toda = " + obstacle.getDistanceFromThreshold() + "(Distance From Threshold) - " + stripEnd + "(Strip End) - "  + subtraction + "(Subtraction) + "  + displacedThreshold + "(Displaced Threshold)" + System.lineSeparator();
			int newToraTowards = obstacle.getDistanceFromThreshold() - stripEnd - subtraction + displacedThreshold;
			toraworking += "= " + newToraTowards + System.lineSeparator();
			toraCurrent = newToraTowards;
		}
	}

	private void recalculateToda(Aircraft aircraft, boolean closeToThreshold) {
		if (closeToThreshold) {
			int subtraction;
			todaworking += "Subtraction: Max (" + RESA + "(RESA) + " + stripEnd + "(Strip End)"+ "," + aircraft.getBlastDistance() + "(Blast Distance))" + System.lineSeparator();
			if (aircraft.getBlastDistance() < RESA + stripEnd) {
				subtraction = RESA + stripEnd;
			} else {
				subtraction = aircraft.getBlastDistance();
			}
			todaworking += "Toda = " + todaOriginal + "(Old Toda) - " + obstacle.getDistanceFromThreshold() + "(Distance From Threshold) - "  + subtraction + "(Subtraction) - "  + displacedThreshold + "(Displaced Threshold)" + System.lineSeparator();
			int newTodaAway = todaOriginal - obstacle.getDistanceFromThreshold() - subtraction - displacedThreshold;
			todaworking += "= " + newTodaAway + System.lineSeparator();
			todaCurrent = newTodaAway;
		} else {

			todaworking += "Toda = Tora";
			todaCurrent = toraCurrent;

		}
	}

	private void recalculateLda(Aircraft aircraft, boolean closeToThreshold) {
		if (closeToThreshold) {
						ldaworking += "New Resa: Max (" + RESA + "(RESA)" + "," + obstacle.getObstacleHeight() + "(model.Obstacle Height) + " + slopeFactor + "(Slope Factor))" + System.lineSeparator();
			int h = obstacle.getObstacleHeight(); //height of the obstacle
			int newRESA = Math.max(h * slopeFactor, RESA);
						ldaworking += "Subtraction: Max (" + newRESA + "(newRESA) + " + stripEnd + "(Strip End)"+ "," + aircraft.getBlastDistance() + "(Blast Distance))" + System.lineSeparator();
			int subtraction = stripEnd + newRESA; //strip end value and slope
			int blast = aircraft.getBlastDistance(); //blast from the plane's engines
			if (subtraction < blast) { // if blast bigger the height calculations, use blast
				subtraction = blast;

			}
					ldaworking += "Tora = " + ldaOriginal + "(Old Lda) - " + obstacle.getDistanceFromThreshold() + "(Distance From Threshold) - "  + subtraction + "(Subtraction) - " + System.lineSeparator();
			int newLdaOver = ldaOriginal - obstacle.getDistanceFromThreshold() - subtraction;
			//new LDA = original LDA - obstacle distance from the threshold - calculated 'obstruction'
						ldaworking += "= " + newLdaOver + System.lineSeparator();
			ldaCurrent = newLdaOver;
		} else {
						ldaworking += "LDA = " + obstacle.getDistanceFromThreshold() + "(Distance From Threshold) - " + obstacle.getDistanceFromThreshold() + "(Distance From Threshold) - "  + RESA + "(RESA) - "  + stripEnd + "(Strip End)" + System.lineSeparator();
			int newLdaTowards = obstacle.getDistanceFromThreshold() - RESA - stripEnd;
			//new LDA = distance from threshold - RESA - stripend
						ldaworking += "= " + newLdaTowards + System.lineSeparator();
			ldaCurrent = newLdaTowards;
		}
	}

	private void recalculateAsda(Aircraft aircraft, boolean closeToThreshold) {
		if (closeToThreshold) {
			int subtraction;
						asdaworking += "Subtraction: Max (" + RESA + "(RESA) + " + stripEnd + "(Strip End)"+ "," + aircraft.getBlastDistance() + "(Blast Distance))" + System.lineSeparator();
			if (aircraft.getBlastDistance() < RESA + stripEnd) {
				subtraction = RESA + stripEnd;
			} else {
				subtraction = aircraft.getBlastDistance();
			}
						asdaworking += "Tora = " + asdaOriginal + "(Old Asda) - " + obstacle.getDistanceFromThreshold() + "(Distance From Threshold) - "  + subtraction + "(Subtraction) - "  + displacedThreshold + "(Displaced Threshold)" + System.lineSeparator();
			int newAsdaAway = asdaOriginal - obstacle.getDistanceFromThreshold() - subtraction - displacedThreshold;
						asdaworking += "= " + newAsdaAway + System.lineSeparator();
			asdaCurrent = newAsdaAway;
		} else {
						asdaworking += "Asda = Tora";
			asdaCurrent = toraCurrent;

		}
	}

	public String toString(){
		DecimalFormat format = new DecimalFormat("00");
		return format.format(orientation)+location;
	}

	public String[] getName() {
		DecimalFormat format  = new DecimalFormat("00");
		return new String[] {format.format(orientation),Character.toString(location)};
	}



	public int getTodaOriginal() {
		return todaOriginal;
	}

	public int getToraOriginal() {
		return toraOriginal;
	}

	public int getAsdaOriginal() {
		return asdaOriginal;
	}

	public int getLdaOriginal() {
		return ldaOriginal;
	}

	public int getToda() {
		return todaCurrent;
	}

	public int getTora() {
		return toraCurrent;
	}

	public int getAsda() {
		return asdaCurrent;
	}

	public int getLda() {
		return ldaCurrent;
	}

	public char getLocation() {
		return location;
	}

	public int getDisplacedThreshold() {
		return displacedThreshold;
	}
	public int getOrientation() {
		return orientation;
	}

	public Obstacle getObstacle() {
		return obstacle;
	}


	public boolean isObstaclePresent() {
		return obstacle != null;
	}

	public String[] inverse() {
		DecimalFormat format  = new DecimalFormat("00");
		return new String[] {format.format(inverseOrientation),Character.toString(inverseLocation)};
	}


	public int getWidth() {
		return width;
	}

    public String inverseToString() {
		DecimalFormat format = new DecimalFormat("00");
		return format.format(inverseOrientation)+inverseLocation;
    }
}
