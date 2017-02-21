import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author Chloe, started on 21/02/17
 */

public class MainPageGUI {

	public static void init() {
		JFrame window = new JFrame("Runway Redeclaration");
		Container content = new Container();
		content = window.getContentPane();

		JPanel mainFrame = new JPanel();
		mainFrame.setLayout(new GridLayout(1, 2));

		JPanel calculations = new JPanel();
		calculations.setLayout(new GridLayout(3, 1));
		JPanel declaredDistance = new JPanel();
		JTextArea showDistance = new JTextArea(20, 35);
		
		showDistance.append("Declared Distances" + "\n");
		showDistance.append("TORA : " + "\n");
		showDistance.append("TODA : " + "\n");
		showDistance.append("ASDA : " + "\n");
		showDistance.append("LDA : " + "\n");
		
		JPanel declaredRecipDistance = new JPanel();
		JTextArea showRecipDistance = new JTextArea(20, 35);
		showRecipDistance.append("Declared Distances for Reciprocal" + "\n");
		showRecipDistance.append("TORA : " + "\n");
		showRecipDistance.append("TODA : " + "\n");
		showRecipDistance.append("ASDA : " + "\n");
		showRecipDistance.append("LDA : " + "\n");
		
		JPanel obstacleInfo = new JPanel();
		obstacleInfo.setLayout(new GridLayout(4,2));
		JTextField obstacleDist = new JTextField("Obstacle Distance");
		JTextField obstacleLocation = new JTextField("Obstacle Location");
		JTextField obstacleDistFromCL = new JTextField("Obstacle Distance From CL");
		JTextField obstacleDistFromRunway = new JTextField("Obstacle Distance From Runway");
		JTextArea distance = new JTextArea();
		JTextArea location = new JTextArea();
		JTextArea distanceCL = new JTextArea();
		JTextArea distanceRunway = new JTextArea();
		
		obstacleInfo.add(obstacleDist);
		obstacleInfo.add(distance);
		obstacleInfo.add(obstacleLocation);
		obstacleInfo.add(location);
		obstacleInfo.add(obstacleDistFromCL);
		obstacleInfo.add(distanceCL);
		obstacleInfo.add(obstacleDistFromRunway);
		obstacleInfo.add(distanceRunway);
		
		declaredDistance.add(showDistance);
		calculations.add(declaredDistance);
		
		declaredRecipDistance.add(showRecipDistance);
		calculations.add(declaredRecipDistance);
		
		calculations.add(obstacleInfo);

		JPanel obstacles = new JPanel();
		JPanel breakdownCalc = new JPanel();

		JTabbedPane selectOption = new JTabbedPane();
		selectOption.add("Calculations", calculations);
		selectOption.add("Obstacles", obstacles);
		selectOption.add("Breakdown", breakdownCalc);

		// mainFrame.setLayout(new BorderLayout());

		// JPanel runwayView = new JPanel(); // top down, side
		// gridlayout 2, 1

		// JPanel toolbar = new JPanel(); // select runway, import, export
		// flow layout

		mainFrame.add(new JPanel());
		mainFrame.add(selectOption);

		content.add(mainFrame);
		window.setSize(800, 600);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

	}

	public static void main(String[] args) {
		init();
	}
}
