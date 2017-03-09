package model;

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
	private final int todaOriginal;
	private final int toraOriginal;
	private final int asdaOriginal;
	private final int ldaOriginal;
	private int todaCurrent;
	private int toraCurrent;
	private int asdaCurrent;
	private int ldaCurrent;
	private Obstacle obstacle;
	private final int displacedThreshold;


	public Runway(int orientation, char location, int toda, int tora, int asda, int lda, int displacedThreshold) {
		this.orientation = orientation;
		this.location = location;
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
		recalculate();
	}

	public void recalculate(Aircraft aircraft) {
		boolean closeToThreshold = ldaOriginal / 2 > obstacle.distanceFromThreshold;
		recalculateTora(aircraft, closeToThreshold);
		recalculateToda(aircraft, closeToThreshold);
		recalculateLda(aircraft, closeToThreshold);
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
			toraworking += "Tora = " + toraOriginal + "(Old Tora) - " + obstacle.distanceFromThreshold  + "(Distance From Threshold) - "  + subtraction + "(Subtraction) - "  + displacedThreshold + "(Displaced Threshold)" + System.lineSeparator();
			int newToraAway = toraOriginal - obstacle.distanceFromThreshold - subtraction - displacedThreshold;
			toraworking += "= " + newToraAway + System.lineSeparator();

			toraCurrent = newToraAway;
		} else {
			int subtraction = RESA; //RESA
			int height = obstacle.height * slopeFactor;
			toraworking += "Subtraction: Max (" + RESA + "(RESA)" + "," + obstacle.height + "(model.Obstacle Height) + " + slopeFactor + "(Slope Factor))" + System.lineSeparator();
			if (height > RESA) {
				subtraction = height;
			}
			toraworking += "Toda = " + obstacle.distanceFromThreshold + "(Distance From Threshold) - " + stripEnd + "(Strip End) - "  + subtraction + "(Subtraction) + "  + displacedThreshold + "(Displaced Threshold)" + System.lineSeparator();
			int newToraTowards = obstacle.distanceFromThreshold - stripEnd - subtraction + displacedThreshold;
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
			todaworking += "Toda = " + todaOriginal + "(Old Toda) - " + obstacle.distanceFromThreshold + "(Distance From Threshold) - "  + subtraction + "(Subtraction) - "  + displacedThreshold + "(Displaced Threshold)" + System.lineSeparator();
			int newTodaAway = todaOriginal - obstacle.distanceFromThreshold - subtraction - displacedThreshold;
			todaworking += "= " + newTodaAway + System.lineSeparator();
			todaCurrent = newTodaAway;
		} else {

			todaworking += "Toda = Tora";
			todaCurrent = toraCurrent;

		}
	}

	private void recalculateLda(Aircraft aircraft, boolean closeToThreshold) {
		if (closeToThreshold) {
						ldaworking += "New Resa: Max (" + RESA + "(RESA)" + "," + obstacle.height + "(model.Obstacle Height) + " + slopeFactor + "(Slope Factor))" + System.lineSeparator();
			int h = obstacle.height; //height of the obstacle
			int newRESA = Math.max(h * slopeFactor, RESA);
						ldaworking += "Subtraction: Max (" + newRESA + "(newRESA) + " + stripEnd + "(Strip End)"+ "," + aircraft.getBlastDistance() + "(Blast Distance))" + System.lineSeparator();
			int subtraction = stripEnd + newRESA; //strip end value and slope
			int blast = aircraft.getBlastDistance(); //blast from the plane's engines
			if (subtraction < blast) { // if blast bigger the height calculations, use blast
				subtraction = blast;

			}
					ldaworking += "Tora = " + ldaOriginal + "(Old Lda) - " + obstacle.distanceFromThreshold + "(Distance From Threshold) - "  + subtraction + "(Subtraction) - " + System.lineSeparator();
			int newLdaOver = ldaOriginal - obstacle.distanceFromThreshold - subtraction;
			//new LDA = original LDA - obstacle distance from the threshold - calculated 'obstruction'
						ldaworking += "= " + newLdaOver + System.lineSeparator();
			ldaCurrent = newLdaOver;
		} else {
						ldaworking += "LDA = " + obstacle.distanceFromThreshold + "(Distance From Threshold) - " + obstacle.distanceFromThreshold + "(Distance From Threshold) - "  + RESA + "(RESA) - "  + stripEnd + "(Strip End)" + System.lineSeparator();
			int newLdaTowards = obstacle.distanceFromThreshold - RESA - stripEnd;
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
						asdaworking += "Tora = " + asdaOriginal + "(Old Asda) - " + obstacle.distanceFromThreshold + "(Distance From Threshold) - "  + subtraction + "(Subtraction) - "  + displacedThreshold + "(Displaced Threshold)" + System.lineSeparator();
			int newAsdaAway = asdaOriginal - obstacle.distanceFromThreshold - subtraction - displacedThreshold;
						asdaworking += "= " + newAsdaAway + System.lineSeparator();
			asdaCurrent = newAsdaAway;
		} else {
						asdaworking += "Asda = Tora";
			asdaCurrent = toraCurrent;

		}
	}


	@Override
	public String toString() {
		return String.valueOf(orientation) + location;
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


}
