import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

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
		showRecipDistance.append("TORA : " + "\n");
		showRecipDistance.append("TODA : " + "\n");
		showRecipDistance.append("ASDA : " + "\n");
		showRecipDistance.append("LDA : " + "\n");

		JPanel obstacleInfo = new JPanel();
		obstacleInfo.setLayout(new GridLayout(5,2));

		obstacleInfo.add(new JLabel("Obstacle Height"));
		JTextField distanceText = new JTextField();
		obstacleInfo.add(distanceText);

		obstacleInfo.add(new JLabel("Close To Threshold"));
		JToggleButton toggle = new JToggleButton("Close To Threshold");
		obstacleInfo.add(toggle);

		obstacleInfo.add(new JLabel("Obstacle Distance From CL"));
		JTextField distCLText = new JTextField();
		obstacleInfo.add(distCLText);

		obstacleInfo.add(new JLabel("Obstacle Distance From Threshold"));
		JTextField distRunwayText = new JTextField();
		obstacleInfo.add(distRunwayText);

		JButton submitObstacleInfo = new JButton("Submit");
		obstacleInfo.add(new JPanel());
		obstacleInfo.add(submitObstacleInfo);
		
		declaredDistance.add(new JLabel("Declared Distances For Runway"), BorderLayout.NORTH);
		declaredDistance.add(showDistance, BorderLayout.CENTER);
		calculations.add(declaredDistance);
		
		declaredRecipDistance.add(new JLabel("Declared Reciprocal Distances For Runway"), BorderLayout.NORTH);
		declaredRecipDistance.add(showRecipDistance, BorderLayout.CENTER);
		
		
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

		//String[] obstacleNames = { "Runway 1", "Runway 2", "Runway 3" };
		JComboBox<Runway> listOfObstacles = new JComboBox<Runway>();
		
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
		newObstacle.setLayout(new GridLayout(5, 2));

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
		
        Runway[] runwayList= {new Runway(9,'L',3902,3900,3902,3595,306),new Runway(27,'R',3884,3962,3884,3595,0)};
        JComboBox<Runway> selectRunway = new JComboBox<Runway>(runwayList);
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
        
        SideViewGUI tvg = new SideViewGUI();
        viewRunway.add(menuBar, BorderLayout.NORTH);
		viewRunway.add(tvg, BorderLayout.CENTER);
		mainFrame.add(viewRunway);
		tvg.init();
		mainFrame.add(selectOption);
		
		content.add(mainFrame);
		window.setSize(1200, 700);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setVisible(true);
		tvg.redrawView();
		
		submitObstacleInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				//TODO get runway from the input not this
				Runway inputRunway = (Runway) selectRunway.getSelectedItem();
				Obstacle o = new Obstacle(Integer.parseInt(distanceText.getText()));
				ObstacleOnRunway or = new ObstacleOnRunway(o,Integer.parseInt(distRunwayText.getText()),Integer.parseInt(distCLText.getText()),inputRunway,toggle.isSelected());
				ArrayList<Integer >recalcuatedDistances = Calculator.calculate(or);
				showDistance.setText("TORA : " + inputRunway.tora+ " NewTORA :" + recalcuatedDistances.get(0) + "\n");
				showDistance.append("TODA : " + inputRunway.toda+ " NewTODA :" + recalcuatedDistances.get(1) + "\n");
				showDistance.append("ASDA : " + inputRunway.asda+ " NewASDA :" + recalcuatedDistances.get(2) + "\n");
				showDistance.append("LDAA : " + inputRunway.lda+ " NewLDA :" + recalcuatedDistances.get(3) + "\n");
				
				
			}
        });

	}
	
	public static void main(String[] args) {
		init();
	}
}

