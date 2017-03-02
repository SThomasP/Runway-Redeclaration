package model;


import static org.junit.Assert.assertEquals;


/**
 * Created by steff on 02/03/2017.
 * JUnit Test of the Model Calculations.
 */
public class RunwayTest {

    @org.junit.Test
    public void landingOver() throws Exception {
        Runway testRunway = new Runway(27, 'R',3962, 3884,3884,3884,0);
        Obstacle testObstacle = new Obstacle(25,10,500);
        testRunway.addObstacle(testObstacle);
        assertEquals(testRunway.getLda(),2074);
    }

    @org.junit.Test
    public void landingTowards() throws Exception {
        Runway testRunway = new Runway(9, 'L',3902, 3902,3902,3595,306);
        Obstacle testObstacle = new Obstacle(25,10,2600);
        testRunway.addObstacle(testObstacle);
        assertEquals(testRunway.getLda(),2300);
    }

    @org.junit.Test
    public void takeOffTowards() throws Exception {
        Runway testRunway = new Runway(9, 'L',3902, 3902,3902,3595,306);
        Obstacle testObstacle = new Obstacle(25,10,2500);
        testRunway.addObstacle(testObstacle);
        assertEquals(testRunway.getTora(),1496);
        assertEquals(testRunway.getToda(),1496);
        assertEquals(testRunway.getAsda(),1496);

    }

    @org.junit.Test
    public void takeOffAway() throws Exception {
        Runway testRunway = new Runway(27, 'R',3962, 3884,3884,3884,0);
        Obstacle testObstacle = new Obstacle(25,10,500);
        testRunway.addObstacle(testObstacle);
        assertEquals(testRunway.getTora(),3084);
        assertEquals(testRunway.getToda(),3162);
        assertEquals(testRunway.getAsda(),3084);
    }

}