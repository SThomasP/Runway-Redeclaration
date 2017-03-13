package model;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.swing.JOptionPane;

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
}
