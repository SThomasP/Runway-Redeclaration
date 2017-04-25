package model;


import org.junit.Test;

import controller.ReadAirportXMLFile;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;


/**
 * Created by steff on 02/03/2017.
 * JUnit Test of the Model Calculations.
 */
public class RunwayTest {

	//normal running methods

	@Test
	public void landingOver() throws Exception {
		Runway testRunway = new Runway(27, 'R',3962, 3884,3884,3884,0,50);
		Obstacle testObstacle = new Obstacle(0,0,25,10,500);
		testRunway.addObstacle(testObstacle);
		assertEquals(testRunway.getLda(),2074);
	}

	@Test
	public void landingTowards() throws Exception {
		Runway testRunway = new Runway(9, 'L',3902, 3902,3902,3595,306,50);
		Obstacle testObstacle = new Obstacle(0,0,25,10,2600);
		testRunway.addObstacle(testObstacle);
		assertEquals(testRunway.getLda(),2300);
	}

	@Test
	public void takeOffTowards() throws Exception {
		Runway testRunway = new Runway(9, 'L',3902, 3902,3902,3595,306,50);
		Obstacle testObstacle = new Obstacle(0,0,25,10,2500);
		testRunway.addObstacle(testObstacle);
		assertEquals(testRunway.getTora(),1496);
		assertEquals(testRunway.getToda(),1496);
		assertEquals(testRunway.getAsda(),1496);

	}

	@Test
	public void takeOffAway() throws Exception {
		Runway testRunway = new Runway(27, 'R',3962, 3884,3884,3884,0,50);
		Obstacle testObstacle = new Obstacle(0,0,25,10,500);
		testRunway.addObstacle(testObstacle);
		assertEquals(testRunway.getTora(),3084);
		assertEquals(testRunway.getToda(),3162);
		assertEquals(testRunway.getAsda(),3084);
	}


	//boundry testing

	@Test
	public void resaBoundryTowards() throws Exception {
		Runway testRunway = new Runway(16, 'C',3900, 3902,3902,3595,306,50);
		for(int i = 1; i <= 4;i++)
		{
			Obstacle testObstacle = new Obstacle(0,0,i,10,2000);
			testRunway.addObstacle(testObstacle);
			assertEquals(testRunway.getTora(),2006);
			assertEquals(testRunway.getToda(),2006);
			assertEquals(testRunway.getAsda(),2006);
		}

	}
	@Test
	public void heighSlopeFactorBoundryTowards() throws Exception {
		Runway testRunway = new Runway(16, 'C',3900, 3902,3902,3595,306,50);
		Obstacle testObstacle = new Obstacle(0,0,5,10,2000);
		testRunway.addObstacle(testObstacle);
		assertEquals(testRunway.getTora(),1996);
		assertEquals(testRunway.getToda(),1996);
		assertEquals(testRunway.getAsda(),1996);
	}

	@Test
	public void BlastDistanceBoundryAway() throws Exception {
		Runway testRunway = new Runway(16, 'C',3900, 3902,3902,3595,306,50);
		for(int i = 1; i <= 4;i++)
		{
			Obstacle testObstacle = new Obstacle(0,0,i,10,50);
			testRunway.addObstacle(testObstacle);
			assertEquals(testRunway.getLda(),3245);

		}

	}
	@Test
	public void heighSlopeFactorBoundryAway() throws Exception {
		Runway testRunway = new Runway(16, 'C',3900, 3902,3902,3595,306,50);
		Obstacle testObstacle = new Obstacle(0,0,5,10,50);
		testRunway.addObstacle(testObstacle);
		assertEquals(testRunway.getLda(),3235);
	}

	//airport import test


	@Test
	public void testImportObstacle() throws Exception {

		Runway runway1 = new Runway(27, 'R',3902,3900 ,3902,3900,0,50);
		Obstacle obstacle1 = new Obstacle(0,0,12,0,500);

		runway1.addObstacle(obstacle1);
		ArrayList<Runway> r = new ArrayList<Runway>();
		r.add(runway1);

		Airport a = new Airport(r);

		ReadAirportXMLFile x = new ReadAirportXMLFile();

		x.write(a);

		a = x.read();

		r = a.getListOfRunways();


		assertEquals(r.get(0).getOrientation(),runway1.getOrientation());
		assertEquals(r.get(0).getLocation(),runway1.getLocation());
		assertEquals(r.get(0).getTora(),runway1.getTora());
		assertEquals(r.get(0).getToda(),runway1.getToda());
		assertEquals(r.get(0).getAsda(),runway1.getAsda());
		assertEquals(r.get(0).getLda(),runway1.getLda());
		assertEquals(r.get(0).getDisplacedThreshold(),runway1.getDisplacedThreshold());
		assertEquals(r.get(0).getObstacle().getObstacleHeight(),obstacle1.getObstacleHeight());
		assertEquals(r.get(0).getObstacle().getDistanceFromCentreLine(),obstacle1.getDistanceFromCentreLine());
		assertEquals(r.get(0).getObstacle().getDistanceFromThreshold(),obstacle1.getDistanceFromThreshold());



	}   

	@Test
	public void testImportNoObstacle() throws Exception {

		Runway runway1 = new Runway(35, 'R',3902,3900 ,3902,3900,0,50);
		Runway runway2 = new Runway(1, 'L',3902, 3900,3902,3595,306,50);

		ArrayList<Runway> r = new ArrayList<Runway>();
		r.add(runway1);
		r.add(runway2);

		Airport a = new Airport(r);

		ReadAirportXMLFile x = new ReadAirportXMLFile();

		x.write(a);

		a = x.read();

		r = a.getListOfRunways();

		assertEquals(r.get(0).getOrientation(),runway1.getOrientation());
		assertEquals(r.get(0).getLocation(),runway1.getLocation());
		assertEquals(r.get(0).getTora(),runway1.getTora());
		assertEquals(r.get(0).getToda(),runway1.getToda());
		assertEquals(r.get(0).getAsda(),runway1.getAsda());
		assertEquals(r.get(0).getLda(),runway1.getLda());
		assertEquals(r.get(0).getDisplacedThreshold(),runway1.getDisplacedThreshold());

		assertEquals(r.get(1).getOrientation(),runway2.getOrientation());
		assertEquals(r.get(1).getLocation(),runway2.getLocation());
		assertEquals(r.get(1).getTora(),runway2.getTora());
		assertEquals(r.get(1).getToda(),runway2.getToda());
		assertEquals(r.get(1).getAsda(),runway2.getAsda());
		assertEquals(r.get(1).getLda(),runway2.getLda());
		assertEquals(r.get(1).getDisplacedThreshold(),runway2.getDisplacedThreshold());


	}
}