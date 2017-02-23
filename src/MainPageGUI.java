import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

		obstacleInfo.add(new JLabel("Obstacle Distance"));
		JTextField distanceText = new JTextField();
		obstacleInfo.add(distanceText);

		obstacleInfo.add(new JLabel("Obstacle Location"));
		JTextField locationText = new JTextField();
		obstacleInfo.add(locationText);

		obstacleInfo.add(new JLabel("Obstacle Distance From CL"));
		JTextField distCLText = new JTextField();
		obstacleInfo.add(distCLText);

		obstacleInfo.add(new JLabel("Obstacle Distance From Runway"));
		JTextField distRunwayText = new JTextField();
		obstacleInfo.add(distRunwayText);

		declaredDistance.add(showDistance);
		calculations.add(declaredDistance);
		declaredRecipDistance.add(showRecipDistance);
		calculations.add(declaredRecipDistance);
		calculations.add(obstacleInfo);

		// *******************************************

		JPanel obstacles = new JPanel();

		obstacles.setLayout(new GridLayout(2, 1));

		JPanel viewObstacleList = new JPanel();
		viewObstacleList.setLayout(new BorderLayout());

		JPanel labelAndList = new JPanel();
		labelAndList.setLayout(new GridLayout(1, 2));

		String[] obstacleNames = { "Runway 1", "Runway 2", "Runway 3" };
		JComboBox<String> listOfObstacles = new JComboBox<String>(obstacleNames);
		
		labelAndList.add(new JLabel("View Obstacle From List"));
		labelAndList.add(listOfObstacles);

		JPanel displayObstacleInfo = new JPanel();
		JTextArea displayObstacle = new JTextArea(20, 50);
		viewObstacleList.add(labelAndList, BorderLayout.NORTH);
		displayObstacleInfo.add(displayObstacle);
		viewObstacleList.add(displayObstacleInfo, BorderLayout.CENTER);

		obstacles.add(viewObstacleList);

		JPanel addObstacle = new JPanel();
		addObstacle.setLayout(new BorderLayout());

		JPanel addObstacleTitle = new JPanel();
		addObstacleTitle.setLayout(new GridLayout(1, 2));

		addObstacleTitle.add(new JLabel("Add Obstacle"));
		addObstacleTitle.add(new JLabel());
		addObstacle.add(addObstacleTitle, BorderLayout.NORTH);

		JPanel newObstacle = new JPanel();
		newObstacle.setLayout(new GridLayout(4, 2));

		JTextField obstacleType = new JTextField();
		newObstacle.add(new JLabel("Obstacle Type"));
		newObstacle.add(obstacleType);
		
		JTextField obstacleName = new JTextField();
		newObstacle.add(new JLabel("Obstacle Name"));
		newObstacle.add(obstacleName);

		JTextField obstacleHeight = new JTextField();
		newObstacle.add(new JLabel("Obstacle Height"));
		newObstacle.add(obstacleHeight);

		newObstacle.add(new JPanel());
		JButton submitObstacle = new JButton("Submit");
		newObstacle.add(submitObstacle);

		addObstacle.add(newObstacle, BorderLayout.CENTER);
		obstacles.add(addObstacle);

		// *******************************************
		JPanel breakdownCalc = new JPanel();

		
		
		
		//********************************************
		JTabbedPane selectOption = new JTabbedPane();
		selectOption.add("Calculations", calculations);
		selectOption.add("Obstacles", obstacles);
		selectOption.add("Breakdown", breakdownCalc);

		// JPanel runwayView = new JPanel(); // top down, side
		// gridlayout 2, 1

		// JPanel toolbar = new JPanel(); // select runway, import, export
		// flow layout
		TopViewGUI tvg = new TopViewGUI();
		tvg.init();
		mainFrame.add(tvg);
		tvg.init();
		mainFrame.add(selectOption);
		content.add(mainFrame);
		window.setSize(1200, 700);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);
	}

	public static void main(String[] args) {
		init();
	}
}
