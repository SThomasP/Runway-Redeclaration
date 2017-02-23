import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * @author Chloe, started on 21/02/17
 */

public class MainPageGUI {

	public static void init() {
		JFrame window = new JFrame("Runway Redeclaration");
		Container content = window.getContentPane();

		JPanel mainFrame = new JPanel();
		mainFrame.setLayout(new GridLayout(1, 2));

		JPanel calculations = new JPanel();
		calculations.setLayout(new GridLayout(3, 1));
		JPanel declaredDistance = new JPanel();
		JTextArea showDistance = new JTextArea(20, 50);
		
		showDistance.append("Declared Distances" + "\n");
		showDistance.append("TORA : " + "\n");
		showDistance.append("TODA : " + "\n");
		showDistance.append("ASDA : " + "\n");
		showDistance.append("LDA : " + "\n");
		
		JPanel declaredRecipDistance = new JPanel();
		JTextArea showRecipDistance = new JTextArea(20, 50);
		showRecipDistance.append("Declared Distances for Reciprocal" + "\n");
		showRecipDistance.append("TORA : " + "\n");
		showRecipDistance.append("TODA : " + "\n");
		showRecipDistance.append("ASDA : " + "\n");
		showRecipDistance.append("LDA : " + "\n");
		
		JPanel obstacleInfo = new JPanel();
		obstacleInfo.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel obstacleDist = new JLabel("Obstacle Distance");
		obstacleInfo.add(obstacleDist);
		
		JTextField distanceText = new JTextField(5);
		obstacleInfo.add(distanceText);
		JLabel obstacleLocation = new JLabel("Obstacle Location");
		obstacleInfo.add(obstacleLocation);
		JTextField locationText = new JTextField();
		obstacleInfo.add(locationText);
		JLabel obstacleDistFromCL = new JLabel("Obstacle Distance From CL");
		obstacleInfo.add(obstacleDistFromCL);
		JTextField distCLText = new JTextField();
		obstacleInfo.add(distCLText);
		JLabel obstacleDistFromRunway = new JLabel("Obstacle Distance From Runway");
		obstacleInfo.add(obstacleDistFromRunway);
		JTextField distRunwayText = new JTextField();
		obstacleInfo.add(distRunwayText);
		
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
		TopViewGUI tvg = new TopViewGUI();
		tvg.init();
		mainFrame.add(tvg);

		mainFrame.add(selectOption);

		content.add(mainFrame);
		window.setSize(1200, 700);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);
		tvg.redrawView();

	}

	public static void main(String[] args) {
		init();
	}
}
