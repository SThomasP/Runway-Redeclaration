package model;


import static org.junit.Assert.assertEquals;


/**
 * Created by steff on 02/03/2017.
 * JUnit Test of the Model Calculations.
 */
public class RunwayTest {

	//normal running methods
	
    @org.junit.Test
    public void landingOver() throws Exception {
        Runway testRunway = new Runway(27, 'R',3962, 3884,3884,3884,0,50);
        Obstacle testObstacle = new Obstacle(25,10,500);
        testRunway.addObstacle(testObstacle);
        assertEquals(testRunway.getLda(),2074);
    }

    @org.junit.Test
    public void landingTowards() throws Exception {
        Runway testRunway = new Runway(9, 'L',3902, 3902,3902,3595,306,50);
        Obstacle testObstacle = new Obstacle(25,10,2600);
        testRunway.addObstacle(testObstacle);
        assertEquals(testRunway.getLda(),2300);
    }

    @org.junit.Test
    public void takeOffTowards() throws Exception {
        Runway testRunway = new Runway(9, 'L',3902, 3902,3902,3595,306,50);
        Obstacle testObstacle = new Obstacle(25,10,2500);
        testRunway.addObstacle(testObstacle);
        assertEquals(testRunway.getTora(),1496);
        assertEquals(testRunway.getToda(),1496);
        assertEquals(testRunway.getAsda(),1496);

    }

    @org.junit.Test
    public void takeOffAway() throws Exception {
        Runway testRunway = new Runway(27, 'R',3962, 3884,3884,3884,0,50);
        Obstacle testObstacle = new Obstacle(25,10,500);
        testRunway.addObstacle(testObstacle);
        assertEquals(testRunway.getTora(),3084);
        assertEquals(testRunway.getToda(),3162);
        assertEquals(testRunway.getAsda(),3084);
    }
    
    
    //boundry testing
    
    @org.junit.Test
    public void resaBoundryTowards() throws Exception {
        Runway testRunway = new Runway(16, 'C',3900, 3902,3902,3595,306,50);
        for(int i = 1; i <= 4;i++)
        {
        	Obstacle testObstacle = new Obstacle(i,10,2000);
            testRunway.addObstacle(testObstacle);
            assertEquals(testRunway.getTora(),2006);
            assertEquals(testRunway.getToda(),2006);
            assertEquals(testRunway.getAsda(),2006);
        }
        
    }
    @org.junit.Test
    public void heighSlopeFactorBoundryTowards() throws Exception {
        Runway testRunway = new Runway(16, 'C',3900, 3902,3902,3595,306,50);
        Obstacle testObstacle = new Obstacle(5,10,2000);
        testRunway.addObstacle(testObstacle);
        assertEquals(testRunway.getTora(),1996);
        assertEquals(testRunway.getToda(),1996);
        assertEquals(testRunway.getAsda(),1996);
    }
    
    @org.junit.Test
    public void BlastDistanceBoundryAway() throws Exception {
        Runway testRunway = new Runway(16, 'C',3900, 3902,3902,3595,306,50);
        for(int i = 1; i <= 4;i++)
        {
        	Obstacle testObstacle = new Obstacle(i,10,50);
            testRunway.addObstacle(testObstacle);
            assertEquals(testRunway.getLda(),3245);
 
        }
        
    }
    @org.junit.Test
    public void heighSlopeFactorBoundryAway() throws Exception {
        Runway testRunway = new Runway(16, 'C',3900, 3902,3902,3595,306,50);
        Obstacle testObstacle = new Obstacle(5,10,50);
        testRunway.addObstacle(testObstacle);
        assertEquals(testRunway.getLda(),3235);
    }
    
    
    
    
    
    

}