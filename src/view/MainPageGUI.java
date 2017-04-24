package view;

import controller.Controller;
import model.Obstacle;
import model.Runway;

import java.awt.*;
import java.util.ArrayList;

import javax.smartcardio.Card;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import controller.Controller;
import model.Obstacle;

/**
 * @author Chloe, started on 21/02/17
 */

public class MainPageGUI extends JFrame {

    public static Font displayFont = new Font("Arial", Font.PLAIN, 18);
    public static final String[] runwayViews = {"Top Down View", "Side View"};
    private  static final String[] runwayUsesArray = {"Take Off", "Landing"};
    private JTable distances;
    private JTable reciprocalDistances;
    private JPanel viewRunway;
    private TopViewGUI topView;
    private SideViewGUI sideView;
    private JButton submitButton;
    private JTextField oHeight;
    private JTextField oWidth;
    private JTextField oLength;
    private JTextField oDistanceFromCL;
    private JTextField oDistanceFromT;
    private JComboBox<String> runwayUses;
    private JComboBox<Runway> runways;
    private JComboBox<String> runwayViewType;
    private JButton importButton;
    private JButton exportButton;
    private JButton refreshMain;
    private JButton resetView;
    private JButton rotateView;
    private JButton zoomInView;
    private JButton zoomOutView;
    private JButton printView;
    private JPanel differentViews;
    private JTextArea todaCalcBreakdown;
    private JTextArea asdaCalcBreakdown;
    private JTextArea ldaCalcBreakdown;
    private JTextArea toraCalcBreakdown;
    private JTextField obstacleName;
    private JTextField obstacleHeight;
    private JTextField obstacleWidth;
    private JTextField obstacleLength;
    private JTextArea displayObstacle;
    private JComboBox<String> obstacleType;
    private JComboBox<String> obstacleNames;
    private JComboBox<String> listToViewObstacles;
    public MainPageGUI() {
        super("Runway Redeclaration");
    }

    public void addObstacleToViews() {
        topView.removeObstacle();
        sideView.removeObstacle();
        topView.addObstacle(getCurrentObstacleWidth(), getCurrentObstacleLength(), getCurrentObstacleHeight(), getDistanceFromT(), getDistanceFromCL());
        sideView.addObstacle(getCurrentObstacleWidth(), getCurrentObstacleLength(), getCurrentObstacleHeight(), getDistanceFromT(), getDistanceFromCL());
        topView.repaint();
        sideView.repaint();
    }

    public void updateGraphicRunway() {
        Runway r = getSelectedRunway();
        topView.changeRunway(r.toString(), r.inverse(), r.getToraOriginal(), r.getWidth(), r.getDisplacedThreshold());
        sideView.changeRunway(r.toString(), r.inverse(), r.getToraOriginal(), r.getWidth(), r.getDisplacedThreshold());
        topView.redrawView();
        sideView.redrawView();
        topView.redrawDistances(r.getTodaOriginal(), r.getToraOriginal(), r.getLdaOriginal(), r.getAsdaOriginal());
        sideView.redrawDistances(r.getTodaOriginal(), r.getToraOriginal(), r.getLdaOriginal(), r.getAsdaOriginal());
        topView.removeObstacle();
        sideView.removeObstacle();
        if (r.isObstaclePresent()) {
            Obstacle o = r.getObstacle();
            topView.addObstacle(o.getObstacleWidth(), o.getObstacleLength(), o.getObstacleHeight(), o.getDistanceFromThreshold(), o.getDistanceFromCentreLine());
            sideView.addObstacle(o.getObstacleWidth(), o.getObstacleLength(), o.getObstacleHeight(), o.getDistanceFromThreshold(), o.getDistanceFromCentreLine());
        }
        topView.setOrientation(Math.toRadians(r.getOrientation()*10));
        topView.repaint();
        sideView.repaint();
    }

    public void refreshViews() {
        String view = (String) runwayViewType.getSelectedItem();
        CardLayout cards = (CardLayout) differentViews.getLayout();
        cards.show(differentViews,view);
    }

    public TopViewGUI getTopView() {
        return topView;
    }

    public SideViewGUI getSideView() {
        return sideView;
    }

    public JTextArea getTodaCalc() {
        return todaCalcBreakdown;
    }

    public JPanel getPanel() {
        return viewRunway;
    }

    public JTable getDistanceTable() {
        return distances;
    }

    public JComboBox getRunwayViews() {
        return runwayViewType;
    }

    public void updateRunwayList(ArrayList<Runway> runwayNames) {
        runways.removeAllItems();
        for (Runway runway : runwayNames) {
            runways.addItem(runway);
        }
    }

    public Runway getSelectedRunway() {
        return (Runway) runways.getSelectedItem();
    }

    public void setAdjustedFigures(int toda, int tora, int lda, int asda) {
        distances.setValueAt(String.valueOf(tora), 0, 1);
        distances.setValueAt(String.valueOf(toda), 1, 1);
        distances.setValueAt(String.valueOf(asda), 2, 1);
        distances.setValueAt(String.valueOf(lda), 3, 1);
        topView.redrawDistances(toda, tora, lda, asda);
        sideView.redrawDistances(toda, tora, lda, asda);
    }

    public JComboBox<Runway> getRunways() {
        return runways;
    }
    
    
    public void setOriginalFigures(int toda, int tora, int lda, int asda) {
        distances.setValueAt(String.valueOf(tora), 0, 2);
        distances.setValueAt(String.valueOf(toda), 1, 2);
        distances.setValueAt(String.valueOf(asda), 2, 2);
        distances.setValueAt(String.valueOf(lda), 3, 2);
    }

    public void setRecipAdjustedFigures(int toda, int tora, int lda, int asda) {
    	reciprocalDistances.setValueAt(String.valueOf(tora), 0, 1);
    	reciprocalDistances.setValueAt(String.valueOf(toda), 1, 1);
    	reciprocalDistances.setValueAt(String.valueOf(asda), 2, 1);
    	reciprocalDistances.setValueAt(String.valueOf(lda), 3, 1);
        
    }

    public void setRecipOriginalFigures(int toda, int tora, int lda, int asda) {
    	reciprocalDistances.setValueAt(String.valueOf(tora), 0, 2);
    	reciprocalDistances.setValueAt(String.valueOf(toda), 1, 2);
    	reciprocalDistances.setValueAt(String.valueOf(asda), 2, 2);
    	reciprocalDistances.setValueAt(String.valueOf(lda), 3, 2);
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
        JScrollPane dScroll = new JScrollPane(distances, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel recipAndFunc = new JPanel();
        recipAndFunc.setLayout(new GridLayout(2,1));
        
        JPanel declaredRecipDistance = new JPanel();
        declaredRecipDistance.setLayout(new BorderLayout());
        reciprocalDistances = new JTable(new DistanceTableModel());
        reciprocalDistances.setFont(displayFont);
        JScrollPane rdScroll = new JScrollPane(reciprocalDistances, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        JPanel functionButtonPanel = new JPanel();
        functionButtonPanel.setLayout(new GridLayout(5,3));
        
        resetView = new JButton("Reset");
        rotateView = new JButton("Rotate");
        printView = new JButton("Print");
        zoomInView = new JButton("Zoom In");
        zoomOutView = new JButton("Zoom Out");

        functionButtonPanel.add(new JPanel());
        functionButtonPanel.add(resetView);
        functionButtonPanel.add(new JPanel());
        
        functionButtonPanel.add(new JPanel());
        functionButtonPanel.add(rotateView);
        functionButtonPanel.add(new JPanel());
        
        functionButtonPanel.add(new JPanel());
        functionButtonPanel.add(printView);
        functionButtonPanel.add(new JPanel());
        
        functionButtonPanel.add(new JPanel());
        functionButtonPanel.add(zoomInView);
        functionButtonPanel.add(new JPanel());
        
        functionButtonPanel.add(new JPanel());
        functionButtonPanel.add(zoomOutView);
        functionButtonPanel.add(new JPanel());

        JPanel obstacleInfo = new JPanel();
        obstacleInfo.setLayout(new GridLayout(7, 2));

        obstacleInfo.add(new JLabel("Obstacle Name"));
        obstacleNames = new JComboBox<String>();
        obstacleNames.addActionListener(c.getRefreshMainButtonPress());

        obstacleInfo.add(obstacleNames);

        obstacleInfo.add(new JLabel("Obstacle Height"));
        oHeight = new JTextField();
        obstacleInfo.add(oHeight);

        obstacleInfo.add(new JLabel("Obstacle Width"));
        oWidth = new JTextField();
        obstacleInfo.add(oWidth);

        obstacleInfo.add(new JLabel("Obstacle Length"));
        oLength = new JTextField();
        obstacleInfo.add(oLength);

        obstacleInfo.add(new JLabel("Obstacle Distance From CL"));
        oDistanceFromCL = new JTextField();
        obstacleInfo.add(oDistanceFromCL);

        obstacleInfo.add(new JLabel("Obstacle Distance From Threshold"));
        oDistanceFromT = new JTextField();
        obstacleInfo.add(oDistanceFromT);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(c.getSubmitButtonPress());

        JLabel refreshMain = new JLabel("");
        obstacleInfo.add(refreshMain);
        obstacleInfo.add(submitButton);

        declaredDistance.add(new JLabel("Declared Distances For Runway"), BorderLayout.NORTH);
        declaredDistance.add(dScroll, BorderLayout.CENTER);
        
        
        recipAndFunc.add(functionButtonPanel);
        recipAndFunc.add(declaredDistance);
        calculations.add(recipAndFunc);

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

        // String[] obstacleNames = { "Runway 1", "Runway 2", "Runway 3" };
        listToViewObstacles = new JComboBox<>();

        labelAndList.add(new JLabel("View Obstacle From List"));
        labelAndList.add(listToViewObstacles);

        JPanel displayObstacleInfo = new JPanel();
        displayObstacle = new JTextArea(20, 50);
        JScrollPane scroll = new JScrollPane(displayObstacle);
        scroll.setVisible(true);
        viewObstacleList.add(labelAndList, BorderLayout.NORTH);
        displayObstacleInfo.add(scroll);
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
        newObstacle.setLayout(new GridLayout(6, 2));

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

        obstacleWidth = new JTextField();
        newObstacle.add(new JLabel("Obstacle Width"));
        newObstacle.add(obstacleWidth);

        obstacleLength = new JTextField();
        newObstacle.add(new JLabel("Obstacle Length"));
        newObstacle.add(obstacleLength);

        newObstacle.add(new JPanel());
        JButton submitObstacle = new JButton("Submit");
        newObstacle.add(submitObstacle);
        submitObstacle.addActionListener(c.getAddObstacleButtonPress());

        addObstacle.add(newObstacle, BorderLayout.CENTER);
        obstacles.add(addObstacle);

        // *******************************************
        // BREAKDOWNS
        JPanel breakdownCalc = new JPanel();
        breakdownCalc.setLayout(new GridLayout(5, 1));

        JPanel toraBreakdown = new JPanel();
        toraBreakdown.setLayout(new BorderLayout());
        toraBreakdown.add(new JLabel("TORA Calculation Breakdown"), BorderLayout.NORTH);
        toraCalcBreakdown = new JTextArea();
        JScrollPane toraScrollPane = new JScrollPane(toraCalcBreakdown);
        toraCalcBreakdown.setEditable(false);
        toraScrollPane.setVisible(true);
        toraBreakdown.add(toraScrollPane);
        breakdownCalc.add(toraBreakdown);

        JPanel todaBreakdown = new JPanel();
        todaBreakdown.setLayout(new BorderLayout());
        todaBreakdown.add(new JLabel("TODA Calculation Breakdown"), BorderLayout.NORTH);
        todaCalcBreakdown = new JTextArea();
        JScrollPane todaScrollPane = new JScrollPane(todaCalcBreakdown);
        todaCalcBreakdown.setEditable(false);
        todaScrollPane.setVisible(true);
        todaBreakdown.add(todaScrollPane);
        breakdownCalc.add(todaBreakdown);

        JPanel asdaBreakdown = new JPanel();
        asdaBreakdown.setLayout(new BorderLayout());
        asdaBreakdown.add(new JLabel("ASDA Calculation Breakdown"), BorderLayout.NORTH);
        asdaCalcBreakdown = new JTextArea();
        JScrollPane asdaScrollPane = new JScrollPane(asdaCalcBreakdown);
        asdaCalcBreakdown.setEditable(false);
        asdaScrollPane.setVisible(true);
        asdaBreakdown.add(asdaScrollPane);
        breakdownCalc.add(asdaBreakdown);

        JPanel ldaBreakdown = new JPanel();
        ldaBreakdown.setLayout(new BorderLayout());
        ldaBreakdown.add(new JLabel("LDA Calculation Breakdown"), BorderLayout.NORTH);
        ldaCalcBreakdown = new JTextArea();
        JScrollPane ldaScrollPane = new JScrollPane(ldaCalcBreakdown);
        ldaCalcBreakdown.setEditable(false);
        ldaScrollPane.setVisible(true);
        ldaBreakdown.add(ldaScrollPane);
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

        // ********************************************
        JTabbedPane selectOption = new JTabbedPane();
        selectOption.add("Calculations", calculations);
        selectOption.add("Obstacles", obstacles);
        selectOption.add("Breakdown", breakdownCalc);

        // *******************************************
        viewRunway = new JPanel();
        viewRunway.setLayout(new BorderLayout());

        runways = new JComboBox<>();
        runways.addActionListener(c.getChooseCurrentRunway());
        importButton = new JButton("Import");
        importButton.addActionListener(c.getImportAirport());
        exportButton = new JButton("Export");
        exportButton.addActionListener(c.getExportAirport());
        runwayViewType = new JComboBox<String>(runwayViews);
        runwayUses = new JComboBox<>(runwayUsesArray);
        runwayUses.addActionListener(c.getChangeRunwayUse());
        JPanel menuBar = new JPanel();
        menuBar.setLayout(new FlowLayout());
        menuBar.add(runways);
        menuBar.add(importButton);
        menuBar.add(exportButton);
        menuBar.add(runwayViewType);
        menuBar.add(runwayUses);

        topView = new TopViewGUI();
        sideView = new SideViewGUI();
        differentViews = new JPanel(new CardLayout());
        differentViews.add(topView,runwayViews[0]);
        differentViews.add(sideView, runwayViews[1]);
        viewRunway.add(menuBar, BorderLayout.NORTH);
        viewRunway.add(differentViews, BorderLayout.CENTER);
        mainFrame.add(viewRunway);
        topView.init();
        sideView.init();
        mainFrame.add(selectOption);

        content.add(mainFrame);
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        topView.changeRunway("D", "DD", 1, 1, 1);
        sideView.changeRunway("D", "DD", 1, 1, 1);
        topView.redrawView();
        sideView.redrawView();


    }

    public JComboBox<String> getObstacleNames() {
        return obstacleNames;
    }

    public Obstacle getNewObstacle() {
        Obstacle toReturn = new Obstacle(Integer.valueOf(oHeight.getText()), Integer.valueOf(oDistanceFromCL.getText()),
                Integer.valueOf(oDistanceFromT.getText()));
        return toReturn;
    }

    public JComboBox getObstacleBox() {
        return obstacleNames;
    }

    public JComboBox getViewObstaclesList() {
        return listToViewObstacles;
    }

    public JTextArea getDisplayObstacle() {
        return displayObstacle;
    }

    public JTextField getHeightBox() {
        return oHeight;
    }

    public JTextField getWidthBox() {
        return oWidth;
    }

    public JTextField getLengthBox() {
        return oLength;
    }

    public JTextField getDistanceCLBox() {
        return oDistanceFromCL;
    }

    public JTextField getDistanceFromTBox() {
        return oDistanceFromT;
    }

    public String getObstacleType() {
        return obstacleType.getSelectedItem().toString();
    }

    public String getObstacleName() {
        return obstacleName.getText();
    }

    public int getDistanceFromCL() {
        int heightInt;
        if (oDistanceFromCL.getText().equals("")) {
            heightInt = 0;
        } else {
            heightInt = Integer.parseInt(oDistanceFromCL.getText());
        }
        return heightInt;
    }

    public int getDistanceFromT() {
        int heightInt;
        if (oDistanceFromT.getText().equals("")) {
            heightInt = 0;
        } else {
            heightInt = Integer.parseInt(oDistanceFromT.getText());
        }
        return heightInt;
    }

    public int getCurrentObstacleHeight() {
        int heightInt;
        if (oHeight.getText().equals("")) {
            heightInt = 0;
        } else {
            heightInt = Integer.parseInt(oHeight.getText());
        }
        return heightInt;
    }

    public int getCurrentObstacleWidth() {
        int widthInt;
        if (oWidth.getText().equals("")) {
            widthInt = 0;
        } else {
            widthInt = Integer.parseInt(oWidth.getText());
        }
        return widthInt;
    }

    public int getCurrentObstacleLength() {
        int lengthInt;
        if (oLength.getText().equals("")) {
            lengthInt = 0;
        } else {
            lengthInt = Integer.parseInt(oLength.getText());
        }
        return lengthInt;
    }

    public int getObstacleHeight() {
        int heightInt;
        if (obstacleHeight.getText().equals("")) {
            heightInt = 0;
        } else {
            heightInt = Integer.parseInt(obstacleHeight.getText());
        }
        return heightInt;
    }

    public int getObstacleWidth() {
        int widthInt;
        if (obstacleWidth.getText().equals("")) {
            widthInt = 0;
        } else {
            widthInt = Integer.parseInt(obstacleWidth.getText());
        }
        return widthInt;
    }

    public int getObstacleLength() {
        int lengthInt;
        if (obstacleLength.getText().equals("")) {
            lengthInt = 0;
        } else {
            lengthInt = Integer.parseInt(obstacleLength.getText());
        }
        return lengthInt;
    }

    public void setToraWorking(String tora) {
        toraCalcBreakdown.setText(tora);
    }

    public void setTodaWorking(String toda) {
        todaCalcBreakdown.setText(toda);
    }

    public void setAsdaWorking(String asda) {
        asdaCalcBreakdown.setText(asda);
    }

    public void setLdaWorking(String lda) {
        ldaCalcBreakdown.setText(lda);
    }

    public void changeRunwayUse(String runwayUse) {
        if (runwayUse == runwayUsesArray[ViewGUI.TAKEOFF]){
            topView.setAction(ViewGUI.TAKEOFF);
            sideView.setAction(ViewGUI.TAKEOFF);
        }
        else if (runwayUse == runwayUsesArray[ViewGUI.LANDING]){
            topView.setAction(ViewGUI.LANDING);
            sideView.setAction(ViewGUI.LANDING);
        }
        topView.repaint();
        sideView.repaint();
    }

    private class DistanceTableModel extends AbstractTableModel {

        private String[][] tableData;
        private String[] columnNames;

        public DistanceTableModel() {
            tableData = new String[][]{{"TORA", "", ""}, {"TODA", "", ""}, {"ASDA", "", ""},
                    {"LDA", "", ""}};
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
            fireTableCellUpdated(rowIndex, columnIndex);
        }

    }
}
