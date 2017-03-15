package view;

import java.awt.*;

public class SideViewGUI extends ViewGUI {


    private Rectangle runwayRec;
    private Rectangle obstacleRec;
    private Polygon  takeOffTri;

    @Override
    public void init() {
        setBackground(Color.cyan);
        runwayRec = new Rectangle();
        takeOffTri = new Polygon();
        toraString = "TORA";
        todaString = "TODA";
        asdaString = "ASDA";
        ldaString = "LDA";
    }

    
    @Override
    public void redrawView() {
        int width = getWidth();
        int height = getHeight();
        int x4 = (int) (width * 0.87);
        int x1 = (int) (width * 0.12);
        runwayRec = new Rectangle((int) (0.05*width), (int) (0.8*height), (int) (0.9*width),(int) (0.05*height));
        takeOffTri.reset();
        takeOffTri.addPoint(x4,(int) (0.8*height));
        takeOffTri.addPoint(x4, (int) (0.8*height) - rescaleVertical(50));
        takeOffTri.addPoint(x4 - rescaleHorizontal(50*50), (int) (0.8 * height));
        repaint();
    }

    @Override
    protected int rescaleHorizontal(int original) {
        double toReturn = (double) original / runwayLength * getWidth()*(0.87-0.12);
        return (int) (toReturn);
    }

    protected int rescaleVertical(int original) {
        double toReturn = (double) original / 500 * runwayRec.getY();
        return (int) toReturn;
    }

    @Override
    public void removeObstacle() {

    }

    @Override
    public void addObstacle(int width, int length, int height, int dFromT, int dFromCL) {
        obstacleOnRunway = true;
        obstacleRec = new Rectangle((int)(getWidth()*0.17) +thresholdDistance + rescaleHorizontal(dFromT),(int) runwayRec.getY() - rescaleVertical(height),rescaleHorizontal(length),rescaleVertical(height));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        outlineShape(Color.black,g, runwayRec, new BasicStroke(2,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL));
        fillShape(Color.black,g,takeOffTri);
        if  (obstacleOnRunway){
            fillShape(Color.RED,g,obstacleRec);
        }
    }
}
