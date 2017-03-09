package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import controller.Controller;
import model.Obstacle;

/**
 * @author Chloe, started on 21/02/17
 */

public class MainPageGUI extends JFrame {

    private class DistanceTableModel extends AbstractTableModel{

        private String[][] tableData;
        private String[] columnNames;

        public DistanceTableModel(){
            tableData = new String[][]{{"TORA", "", ""}, {"TODA", "", ""}, {"ASDA", "", ""}, {"LDA", "", ""}};
            columnNames = new String[]{"Distance", "Current", "Original"};
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public int getRowCount() {
            return 4;
        }

        @Override
        public int getColumnCount() {
            return 3;
        }



        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return tableData[rowIndex][columnIndex];
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                tableData[rowIndex][columnIndex] = (String) aValue;
                fireTableCellUpdated(rowIndex,columnIndex);
        }

    }

    private JTable distances;
    private JTable reciprocalDistances;
    private TopViewGUI topView;
    private SideViewGUI sideView;
    private JButton submitButton;
    private JTextField oHeight;
    private JTextField oDistanceFromCL;
    private JTextField oDistanceFromT;
    private JComboBox<String> runwayUses;
    private JComboBox<String> runways;
    private JComboBox<String> runwayViewType;
    private JButton importButton;
    private JButton exportButton;
    private JTextArea todaCalcBreakdown;
    private JTextArea asdaCalcBreakdown;
    private JTextArea ldaCalcBreakdown;
    private JTextArea toraCalcBreakdown;
    private JTextField obstacleName; 
    private JTextField obstacleHeight;
    private JComboBox<String> obstacleType;
   
    
    private static Font displayFont = new Font("Arial", Font.PLAIN, 18);

    public void updateRunwayList(String[] runwayNames) {
        runways.removeAllItems();
        for (String runway : runwayNames) {
            runways.addItem(runway);
        }
    }

    public void setAdjustedFigures(int toda, int tora, int lda, int asda) {
        distances.setValueAt(String.valueOf(tora),0,1);
        distances.setValueAt(String.valueOf(toda),1,1);
        distances.setValueAt(String.valueOf(asda),2,1);
        distances.setValueAt(String.valueOf(lda),3,1);
    }
    

    public MainPageGUI() {
        super("Runway Redeclaration");
    }

    public void setOriginalFigures(int toda, int tora, int lda, int asda){
        distances.setValueAt(String.valueOf(tora),0,2);
        distances.setValueAt(String.valueOf(toda),1,2);
        distances.setValueAt(String.valueOf(asda),2,2);
        distances.setValueAt(String.valueOf(lda),3,2);
    }

    public void init(Controller c) {
        Container content = getContentPane();

        JPanel mainFrame = new JPanel();
        mainFrame.setLayout(new GridLayout(1, 2));

        JPanel calculations = new JPanel();
        calculations.setLayout(new GridLayout(3, 1));

        JPanel declaredDistance = new JPanel();
        declaredDistance.setLayout(new BorderLayout());

        distances = new JTable(new DistanceTableModel());
        distances.setFont(displayFont);
        distances.getDefaultRenderer(String.class);
        JScrollPane dScroll = new JScrollPane(distances,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel declaredRecipDistance = new JPanel();
        declaredRecipDistance.setLayout(new BorderLayout());
        reciprocalDistances = new JTable(new DistanceTableModel());
        reciprocalDistances.setFont(displayFont);
        JScrollPane rdScroll = new JScrollPane(reciprocalDistances,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel obstacleInfo = new JPanel();
        obstacleInfo.setLayout(new GridLayout(6, 2));
        
        obstacleInfo.add(new JLabel("Obstacle Name"));
        JComboBox<String> obstacleNames = new JComboBox<String>();
        obstacleNames.setModel(new DefaultComboBoxModel (c.getList().toArray()));
        obstacleInfo.add(obstacleNames);

        obstacleInfo.add(new JLabel("Obstacle Height"));
        oHeight = new JTextField();
        obstacleInfo.add(oHeight);

        obstacleInfo.add(new JLabel("Obstacle Distance From CL"));
        oDistanceFromCL = new JTextField();
        obstacleInfo.add(oDistanceFromCL);

        obstacleInfo.add(new JLabel("Obstacle Distance From Threshold"));
        oDistanceFromT = new JTextField();
        obstacleInfo.add(oDistanceFromT);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(c.getSubmitButtonPress());
        obstacleInfo.add(new JPanel());
        obstacleInfo.add(submitButton);

        declaredDistance.add(new JLabel("Declared Distances For Runway"), BorderLayout.NORTH);
        declaredDistance.add(dScroll, BorderLayout.CENTER);
        calculations.add(declaredDistance);

        declaredRecipDistance.add(new JLabel("Declared Reciprocal Distances For Runway"), BorderLayout.NORTH);
        declaredRecipDistance.add(rdScroll, BorderLayout.CENTER);
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
        JComboBox<String> listOfObstacles = new JComboBox<>();

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

       // JTextField obstacleType = new JTextField();
        String[] typeOfObstacle = {"Aircraft Part", "Vehicle"};
        obstacleType = new JComboBox<String>(typeOfObstacle);
        newObstacle.add(new JLabel("Obstacle Type"));
        newObstacle.add(obstacleType);

        obstacleName = new JTextField();
        newObstacle.add(new JLabel("Obstacle Name"));
        newObstacle.add(obstacleName);

        obstacleHeight = new JTextField();
        newObstacle.add(new JLabel("Obstacle Height"));
        newObstacle.add(obstacleHeight);

        newObstacle.add(new JPanel());
        JButton submitObstacle = new JButton("Submit");
        newObstacle.add(submitObstacle);
        submitObstacle.addActionListener(c.getAddObstacleButtonPress());

        addObstacle.add(newObstacle, BorderLayout.CENTER);
        obstacles.add(addObstacle);


        // *******************************************
        //BREAKDOWNS
        JPanel breakdownCalc = new JPanel();
        breakdownCalc.setLayout(new GridLayout(5, 1));

        JPanel toraBreakdown = new JPanel();
        toraBreakdown.setLayout(new BorderLayout());
        toraBreakdown.add(new JLabel("TORA Calculation Breakdown"), BorderLayout.NORTH);
        toraCalcBreakdown = new JTextArea();
        toraBreakdown.add(toraCalcBreakdown);
        breakdownCalc.add(toraBreakdown);

        JPanel todaBreakdown = new JPanel();
        todaBreakdown.setLayout(new BorderLayout());
        todaBreakdown.add(new JLabel("TODA Calculation Breakdown"), BorderLayout.NORTH);
        todaCalcBreakdown = new JTextArea();
        todaBreakdown.add(todaCalcBreakdown);
        breakdownCalc.add(todaBreakdown);

        JPanel asdaBreakdown = new JPanel();
        asdaBreakdown.setLayout(new BorderLayout());
        asdaBreakdown.add(new JLabel("ASDA Calculation Breakdown"), BorderLayout.NORTH);
        asdaCalcBreakdown = new JTextArea();
        asdaBreakdown.add(asdaCalcBreakdown);
        breakdownCalc.add(asdaBreakdown);

        JPanel ldaBreakdown = new JPanel();
        ldaBreakdown.setLayout(new BorderLayout());
        ldaBreakdown.add(new JLabel("LDA Calculation Breakdown"), BorderLayout.NORTH);
        ldaCalcBreakdown = new JTextArea();
        ldaBreakdown.add(ldaCalcBreakdown);
        breakdownCalc.add(ldaBreakdown);

        JPanel refreshCalc = new JPanel();
        refreshCalc.setLayout(new GridLayout(2, 2));
        refreshCalc.add(new JPanel());
        JButton refresh = new JButton("Refresh");
        refreshCalc.add(new JPanel());
        refreshCalc.add(new JPanel());
        refreshCalc.add(refresh);
        refresh.addActionListener(c.getRefreshButtonPress());
        breakdownCalc.add(refreshCalc);

        //********************************************
        JTabbedPane selectOption = new JTabbedPane();
        selectOption.add("Calculations", calculations);
        selectOption.add("Obstacles", obstacles);
        selectOption.add("Breakdown", breakdownCalc);

        //*******************************************
        JPanel viewRunway = new JPanel();
        viewRunway.setLayout(new BorderLayout());

        runways = new JComboBox<>();
        importButton = new JButton("Import");
        exportButton = new JButton("Export");
        String[] runwayView = {"Top Down view", "Side view"};
        runwayViewType = new JComboBox<String>(runwayView);
        String[] aircraftDirection = {"Take Off", "Landing"};
        runwayUses = new JComboBox<>(aircraftDirection);

        JPanel menuBar = new JPanel();
        menuBar.setLayout(new FlowLayout());
        menuBar.add(runways);
        menuBar.add(importButton);
        menuBar.add(exportButton);
        menuBar.add(runwayViewType);
        menuBar.add(runwayUses);

        TopViewGUI tvg = new TopViewGUI();
        viewRunway.add(menuBar, BorderLayout.NORTH);
        viewRunway.add(tvg, BorderLayout.CENTER);
        mainFrame.add(viewRunway);
        tvg.init();
        mainFrame.add(selectOption);

        content.add(mainFrame);
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        tvg.redrawView();

    }

    public Obstacle getNewObstacle() {
        Obstacle toReturn = new Obstacle(Integer.valueOf(oHeight.getText()),Integer.valueOf(oDistanceFromCL.getText()),Integer.valueOf(oDistanceFromT.getText()));
        return toReturn;
    }
    
    public String getObstacleType() {
    	//System.out.println(obstacleType.getSelectedItem().toString());
    	return obstacleType.getSelectedItem().toString();
    }
    
    public String getObstacleName() {
    //	System.out.println(obstacleName.getText());
    	return obstacleName.getText();
    }
    
    public int getObstacleHeight() {
    //	System.out.println(obstacleHeight.getText());
    	int heightInt = Integer.parseInt(obstacleHeight.getText());
    	return heightInt;
    }
    
    public void setToraWorking(String tora)
    {
    	toraCalcBreakdown.setText(tora);
    }
    public void setTodaWorking(String toda)
    {
    	todaCalcBreakdown.setText(toda);
    }
   
    public void setAsdaWorking(String asda)
    {
    	asdaCalcBreakdown.setText(asda);
    }
    public void setLdaWorking(String lda)
    {
    	ldaCalcBreakdown.setText(lda);
    }
}

