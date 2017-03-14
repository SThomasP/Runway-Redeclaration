package model;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Event;
import java.awt.Robot;
import java.util.ArrayList;

import controller.Controller;
import view.MainPageGUI;

public class GUITest {

	/**
	 * @author Chloe
	 * created on 13/03/17
	 * JUnit Testing for the GUI
	 */
	
	@org.junit.Test
	public void testLoadGUI() throws Exception {
		MainPageGUI gui = new MainPageGUI();
		Controller c = new Controller();
		gui.init(c);
	}

	@org.junit.Test
	public void checkStartObstacleNameField() throws Exception {
		MainPageGUI gui = new MainPageGUI();
		Controller c = new Controller();
		gui.init(c);
		assertEquals(gui.getObstacleName(), "");
	}
	
	@org.junit.Test
	public void checkStartObstacleTypeField() throws Exception {
		MainPageGUI gui = new MainPageGUI();
		Controller c = new Controller();
		gui.init(c);
		assertEquals(gui.getObstacleType(), "Aircraft Part");
	}
	
	@org.junit.Test
	public void checkStartObstacleHeightField() throws Exception {
		MainPageGUI gui = new MainPageGUI();
		Controller c = new Controller();
		gui.init(c);
		int test = gui.getObstacleHeight();
		assertEquals(test, 0);
	}
	
	@org.junit.Test
	public void checkStartObstacleWidthField() throws Exception {
		MainPageGUI gui = new MainPageGUI();
		Controller c = new Controller();
		gui.init(c);
		int test = gui.getObstacleWidth();
		assertEquals(test, 0);
	}
	
	@org.junit.Test
	public void checkStartObstacleLengthField() throws Exception {
		MainPageGUI gui = new MainPageGUI();
		Controller c = new Controller();
		gui.init(c);
		int test = gui.getObstacleLength();
		assertEquals(test, 0);
	}
	
	@org.junit.Test
	public void checkCalcObstacleBox() {
		MainPageGUI gui = new MainPageGUI();
		Controller c = new Controller();
		gui.init(c);
		Object test = gui.getObstacleBox().getSelectedItem();
		assertTrue(test == null);
	}
	
	@org.junit.Test
	public void checkCalcObstacleHeightBox() {
		MainPageGUI gui = new MainPageGUI();
		Controller c = new Controller();
		gui.init(c);
		assertEquals(gui.getHeightBox().getText(), "");
	}
	
	@org.junit.Test
	public void checkCalcObstacleWidthBox() {
		MainPageGUI gui = new MainPageGUI();
		Controller c = new Controller();
	gui.init(c);
		assertEquals(gui.getWidthBox().getText(), "");
	}
	
	@org.junit.Test
	public void checkCalcObstacleLengthBox() {
		MainPageGUI gui = new MainPageGUI();
		Controller c = new Controller();
		gui.init(c);
		assertEquals(gui.getLengthBox().getText(), "");
	}
	
	@org.junit.Test
	public void checkCalcObstacleDistanceCLBox() {
		MainPageGUI gui = new MainPageGUI();
		Controller c = new Controller();
		gui.init(c);
		assertEquals(gui.getDistanceCLBox().getText(), "");
	}
	
	@org.junit.Test
	public void checkCalcObstacleDistanceTBox() {
		MainPageGUI gui = new MainPageGUI();
		Controller c = new Controller();
		gui.init(c);
		assertEquals(gui.getDistanceFromTBox().getText(), "");
	}
	
	@org.junit.Test 
	public void resizableGUI() {
		MainPageGUI gui = new MainPageGUI();
		Controller c = new Controller();
		assertTrue(gui.isResizable());
	}
	
	@org.junit.Test 
	public void checkRefreshMainButtonClick() {
		MainPageGUI gui = new MainPageGUI();
		Controller c = new Controller();
		assertTrue(c.getRefreshMainButtonPress() != null);
	}
	
	@org.junit.Test
	public void checkDataInTable() {
		MainPageGUI gui = new MainPageGUI();
		Controller c = new Controller();
		gui.init(c);
		ArrayList<Runway> listOfRunways = new ArrayList<Runway>();
		listOfRunways.add(new Runway(9, 'L', 3902, 3900, 3902, 3595, 306,50));
		Airport airport = new Airport(listOfRunways);
		Runway currentRunway = airport.getCurrentRunway();
		gui.setOriginalFigures(currentRunway.getToda(), currentRunway.getTora(), currentRunway.getLda(),
				currentRunway.getAsda());
		assertEquals(gui.getDistanceTable().getValueAt(0,2), Integer.toString(3900));
		assertEquals(gui.getDistanceTable().getValueAt(1,2), Integer.toString(3902));
		assertEquals(gui.getDistanceTable().getValueAt(2,2), Integer.toString(3902));
		assertEquals(gui.getDistanceTable().getValueAt(3,2), Integer.toString(3595));
		}
}

