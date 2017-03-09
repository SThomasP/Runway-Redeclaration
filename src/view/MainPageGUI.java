package view;

import controller.Controller;
import model.Obstacle;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;

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
    private JTable recipricalDistances;
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
    private JTextArea todaCalcBrekdown;
    private JTextArea asdaCalcBrekdown;
    private JTextArea ldaCalcBrekdown;
    private JTextArea toraCalcBrekdown;

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
        recipricalDistances = new JTable(new DistanceTableModel());
        recipricalDistances.setFont(displayFont);
        JScrollPane rdScroll = new JScrollPane(recipricalDistances,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel obstacleInfo = new JPanel();
        obstacleInfo.setLayout(new GridLayout(5, 2));

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

        labelAndList.add(new JLabel("view Obstacle From List"));
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
        //BREAKDOWNS
        JPanel breakdownCalc = new JPanel();
        breakdownCalc.setLayout(new GridLayout(5, 1));

        JPanel toraBreakdown = new JPanel();
        toraBreakdown.setLayout(new BorderLayout());
        toraBreakdown.add(new JLabel("TORA Calculation Breakdown"), BorderLayout.NORTH);
        toraCalcBrekdown = new JTextArea();
        toraBreakdown.add(toraCalcBrekdown);
        breakdownCalc.add(toraBreakdown);

        JPanel todaBreakdown = new JPanel();
        todaBreakdown.setLayout(new BorderLayout());
        todaBreakdown.add(new JLabel("TODA Calculation Breakdown"), BorderLayout.NORTH);
        todaCalcBrekdown = new JTextArea();
        todaBreakdown.add(todaCalcBrekdown);
        breakdownCalc.add(todaBreakdown);

        JPanel asdaBreakdown = new JPanel();
        asdaBreakdown.setLayout(new BorderLayout());
        asdaBreakdown.add(new JLabel("ASDA Calculation Breakdown"), BorderLayout.NORTH);
        asdaCalcBrekdown = new JTextArea();
        asdaBreakdown.add(asdaCalcBrekdown);
        breakdownCalc.add(asdaBreakdown);

        JPanel ldaBreakdown = new JPanel();
        ldaBreakdown.setLayout(new BorderLayout());
        ldaBreakdown.add(new JLabel("LDA Calculation Breakdown"), BorderLayout.NORTH);
        ldaCalcBrekdown = new JTextArea();
        ldaBreakdown.add(ldaCalcBrekdown);
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
    
    
    public void setToraWorking(String tora)
    {
    	toraCalcBrekdown.setText(tora);
    }
    public void setTodaWorking(String toda)
    {
    	todaCalcBrekdown.setText(toda);
    }
   
    public void setAsdaWorking(String asda)
    {
    	asdaCalcBrekdown.setText(asda);
    }
    public void setLdaWorking(String lda)
    {
    	ldaCalcBrekdown.setText(lda);
    }
}

