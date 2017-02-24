import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
		declaredDistance.setLayout(new BorderLayout());
		JTextArea showDistance = new JTextArea(15, 40);

		showDistance.append("TORA : " + "\n");
		showDistance.append("TODA : " + "\n");
		showDistance.append("ASDA : " + "\n");
		showDistance.append("LDA : " + "\n");

		JPanel declaredRecipDistance = new JPanel();
		declaredRecipDistance.setLayout(new BorderLayout());
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

		declaredDistance.add(new JLabel("Declared Distances For Runway"), BorderLayout.NORTH);
		declaredDistance.add(showDistance, BorderLayout.CENTER);
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
		breakdownCalc.setLayout(new GridLayout(5,1));
		
		JPanel toraBreakdown = new JPanel();
		toraBreakdown.setLayout(new BorderLayout());
		toraBreakdown.add(new JLabel("TORA Calculation Breakdown"), BorderLayout.NORTH);
		JTextField toraCalcBrekdown = new JTextField();
		toraBreakdown.add(toraCalcBrekdown);
		breakdownCalc.add(toraBreakdown);
		
		JPanel todaBreakdown = new JPanel();
		todaBreakdown.setLayout(new BorderLayout());
		todaBreakdown.add(new JLabel("TODA Calculation Breakdown"), BorderLayout.NORTH);
		JTextField todaCalcBrekdown = new JTextField();
		todaBreakdown.add(todaCalcBrekdown);
		breakdownCalc.add(todaBreakdown);
		
		JPanel asdaBreakdown = new JPanel();
		asdaBreakdown.setLayout(new BorderLayout());
		asdaBreakdown.add(new JLabel("ASDA Calculation Breakdown"), BorderLayout.NORTH);
		JTextField asdaCalcBrekdown = new JTextField();
		asdaBreakdown.add(asdaCalcBrekdown);
		breakdownCalc.add(asdaBreakdown);
		
		JPanel ldaBreakdown = new JPanel();
		ldaBreakdown.setLayout(new BorderLayout());
		ldaBreakdown.add(new JLabel("LDA Calculation Breakdown"), BorderLayout.NORTH);
		JTextField ldaCalcBrekdown = new JTextField();
		ldaBreakdown.add(ldaCalcBrekdown);
		breakdownCalc.add(ldaBreakdown);
		
		JPanel refreshCalc = new JPanel();
		refreshCalc.setLayout(new GridLayout(2,2));
		refreshCalc.add(new JPanel());
		JButton refresh = new JButton("Refresh");
		refreshCalc.add(new JPanel());
		refreshCalc.add(new JPanel());
		refreshCalc.add(refresh);
		breakdownCalc.add(refreshCalc);
		
		//********************************************
		JTabbedPane selectOption = new JTabbedPane();
		selectOption.add("Calculations", calculations);
		selectOption.add("Obstacles", obstacles);
		selectOption.add("Breakdown", breakdownCalc);
		
		//*******************************************
		JPanel viewRunway = new JPanel();
		viewRunway.setLayout(new BorderLayout());
		
        String[] runwayList= {"Select Runway", "Runway 1 09L", "Runway 2 27R", "Runway 3 09L"};
        JComboBox<String> selectRunway = new JComboBox<String>(runwayList);
        JButton importXML = new JButton("Import");
        JButton exportXML = new JButton("Export");
        JButton exitApp = new JButton("Exit");
        String[] runwayView = {"Top Down View", "Side View"};
        JComboBox<String> runwayViewType = new JComboBox<String>(runwayView);
        String[] aircraftDirection = {"Take Off", "Landing"};
        JComboBox<String> runwayDirection = new JComboBox<String>(aircraftDirection);
        
        exitApp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int exit = JOptionPane.showConfirmDialog(null, "Do you want to exit the program?" , "Exit Runway Redeclaration", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if (exit == JOptionPane.YES_OPTION)
				{
				 window.dispose();
				}
			}
        });
        
        JPanel menuBar = new JPanel();
        menuBar.setLayout(new FlowLayout());
        menuBar.add(selectRunway);
        menuBar.add(importXML);
        menuBar.add(exportXML);
        menuBar.add(exitApp);
        menuBar.add(runwayViewType);
        menuBar.add(runwayDirection);
        
        TopViewGUI tvg = new TopViewGUI();
        viewRunway.add(menuBar, BorderLayout.NORTH);
		viewRunway.add(tvg, BorderLayout.CENTER);
		
		tvg.init(); 
		mainFrame.add(viewRunway);
		tvg.init();
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
