package model;
import static org.junit.Assert.assertEquals;
import controller.Controller;
import view.MainPageGUI;

public class GUITest {

	/**
	 * @author Chloe
	 * created on 13/03/17
	 * JUnit Testing for the GUI
	 */
	@org.junit.Test
	public void testLoadGUI() {
		MainPageGUI gui = new MainPageGUI();
		Controller c = new Controller();
		gui.init(c);
	}

	@org.junit.Test
	public void checkStartObstacleNameField() {
		MainPageGUI gui = new MainPageGUI();
		Controller c = new Controller();
		gui.init(c);
		assertEquals(gui.getObstacleName(), "");
	}
	
	@org.junit.Test
	public void checkStartObstacleTypeField() {
		MainPageGUI gui = new MainPageGUI();
		Controller c = new Controller();
		gui.init(c);
		assertEquals(gui.getObstacleType(), "Aircraft Part");
	}
	
	@org.junit.Test
	public void checkStartObstacleHeightField() {
		MainPageGUI gui = new MainPageGUI();
		Controller c = new Controller();
		gui.init(c);
		int test = gui.getObstacleHeight();
		assertEquals(gui.getObstacleHeight(), 0);
	}
	
	@org.junit.Test
	public void checkStartObstacleWidthField() {
		MainPageGUI gui = new MainPageGUI();
		Controller c = new Controller();
		gui.init(c);
		int test = gui.getObstacleWidth();
		assertEquals(gui.getObstacleWidth(), 0);
	}
	
	@org.junit.Test
	public void checkStartObstacleLengthField() {
		MainPageGUI gui = new MainPageGUI();
		Controller c = new Controller();
		gui.init(c);
		int test = gui.getObstacleLength();
		assertEquals(gui.getObstacleLength(), 0);
	}
	
//	@org.junit.Test
//	public void checkCalcObstacleBox() {
//		MainPageGUI gui = new MainPageGUI();
//		Controller c = new Controller();
//		gui.init(c);
//		String test = gui.getObstacleBox().getSelectedItem().toString();
//	}
}
