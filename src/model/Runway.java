package model;

public class Runway {

    static final int RESA = 240;
    static final int stripEnd = 60;
    static final int slopeFactor = 50;


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

    private void recalculateToda(Aircraft aircraft, boolean closeToThreshold) {
//		working += "Toda Calculation" + System.lineSeparator();
        if (closeToThreshold) {
            int subtraction;
//			working += "Subtraction: Max (" + RESA + "(RESA) + " + stripEnd + "(Strip End)"+ "," + aircraft.getBlastDistance() + "(Blast Distance))" + System.lineSeparator();
            if (aircraft.getBlastDistance() < RESA + stripEnd) {
                subtraction = RESA + stripEnd;
            } else {
                subtraction = aircraft.getBlastDistance();
            }
//			working += "Toda = " + or.getRunway().getToda() + "(Old Toda) - " + or.getDistanceFromThreshold() + "(Distance From Threshold) - "  + subtraction + "(Subtraction) - "  + or.getRunway().getDisplacedThreshold() + "(Displaced Threshold)" + System.lineSeparator();
            int newTodaAway = todaOriginal - obstacle.distanceFromThreshold - subtraction - displacedThreshold;
//			working += "= " + newTodaAway + System.lineSeparator();
            todaCurrent = newTodaAway;
        } else {

//			working += "Toda = Tora";
            todaCurrent = toraCurrent;

        }
    }

    private void recalculateTora(Aircraft aircraft, boolean closeToThreshold) {
//		working += System.lineSeparator() + "Tora Calculation" + System.lineSeparator();
        if (closeToThreshold) {
            int subtraction;
//			working += "Subtraction: Max (" + RESA + "(RESA) + " + stripEnd + "(Strip End)"+ "," + aircraft.getBlastDistance() + "(Blast Distance))" + System.lineSeparator();
            if (aircraft.getBlastDistance() < RESA + stripEnd) {
                subtraction = RESA + stripEnd;
            } else {
                subtraction = aircraft.getBlastDistance();
            }
//			working += "Tora = " + or.getRunway().getTora() + "(Old Tora) - " + or.getDistanceFromThreshold() + "(Distance From Threshold) - "  + subtraction + "(Subtraction) - "  + or.getRunway().getDisplacedThreshold() + "(Displaced Threshold)" + System.lineSeparator();
            int newToraAway = toraCurrent - obstacle.distanceFromThreshold - subtraction - displacedThreshold;
//			working += "= " + newToraAway + System.lineSeparator();

            toraCurrent = newToraAway;
        } else {
            int subtraction = RESA; //RESA
            int height = obstacle.height * slopeFactor;
//			working += "Subtraction: Max (" + RESA + "(RESA)" + "," + obstacle.height + "(model.Obstacle Height) + " + slopeFactor + "(Slope Factor))" + System.lineSeparator();
            if (height > RESA) {
                subtraction = height;
            }
//			working += "Toda = " + or.getDistanceFromThreshold() + "(Distance From Threshold) - " + stripEnd + "(Strip End) - "  + subtraction + "(Subtraction) + "  + or.getRunway().getDisplacedThreshold() + "(Displaced Threshold)" + System.lineSeparator();
            int newToraTowards = obstacle.distanceFromThreshold - stripEnd - subtraction + displacedThreshold;
//			working += "= " + newToraTowards + System.lineSeparator();
            toraCurrent = newToraTowards;
        }
    }

    private void recalculateLda(Aircraft aircraft, boolean closeToThreshold) {
//		working += System.lineSeparator() + "Asda Calculation" + System.lineSeparator();
        if (closeToThreshold) {
//			working += "New Resa: Max (" + RESA + "(RESA)" + "," + obstacle.height + "(model.Obstacle Height) + " + slopeFactor + "(Slope Factor))" + System.lineSeparator();
            int h = obstacle.height; //height of the obstacle
            int newRESA = Math.max(h * slopeFactor, RESA);
//			working += "Subtraction: Max (" + newRESA + "(newRESA) + " + stripEnd + "(Strip End)"+ "," + aircraft.getBlastDistance() + "(Blast Distance))" + System.lineSeparator();
            int subtraction = stripEnd + newRESA; //strip end value and slope
            int blast = aircraft.getBlastDistance(); //blast from the plane's engines
            if (subtraction < blast) { // if blast bigger the height calculations, use blast
                subtraction = blast;

            }
//			working += "Tora = " + or.getRunway().getLda() + "(Old Lda) - " + or.getDistanceFromThreshold() + "(Distance From Threshold) - "  + subtraction + "(Subtraction) - " + System.lineSeparator();
            int newLdaOver = ldaOriginal - obstacle.distanceFromThreshold - subtraction;
            //new LDA = original LDA - obstacle distance from the threshold - calculated 'obstruction'
//			working += "= " + newLdaOver + System.lineSeparator();
            ldaCurrent = newLdaOver;
        } else {
//			working += "LDA = " + or.getDistanceFromThreshold() + "(Distance From Threshold) - " + or.getDistanceFromThreshold() + "(Distance From Threshold) - "  + RESA + "(RESA) - "  + stripEnd + "(Strip End)" + System.lineSeparator();
            int newLdaTowards = obstacle.distanceFromThreshold - RESA - stripEnd;
            //new LDA = distance from threshold - RESA - stripend
//			working += "= " + newLdaTowards + System.lineSeparator();
            ldaCurrent = newLdaTowards;
        }
    }

    private void recalculateAsda(Aircraft aircraft, boolean closeToThreshold) {
//		working += System.lineSeparator() + "Asda Calculation" + System.lineSeparator();
        if (closeToThreshold) {
            int subtraction;
//			working += "Subtraction: Max (" + RESA + "(RESA) + " + stripEnd + "(Strip End)"+ "," + aircraft.getBlastDistance() + "(Blast Distance))" + System.lineSeparator();
            if (aircraft.getBlastDistance() < RESA + stripEnd) {
                subtraction = RESA + stripEnd;
            } else {
                subtraction = aircraft.getBlastDistance();
            }
//			working += "Tora = " + or.getRunway().getAsda() + "(Old Asda) - " + or.getDistanceFromThreshold() + "(Distance From Threshold) - "  + subtraction + "(Subtraction) - "  + or.getRunway().getDisplacedThreshold() + "(Displaced Threshold)" + System.lineSeparator();
            int newAsdaAway = asdaOriginal - obstacle.distanceFromThreshold - subtraction - displacedThreshold;
//			working += "= " + newAsdaAway + System.lineSeparator();
            asdaCurrent = newAsdaAway;
        } else {
//			working += "Asda = Tora";
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
