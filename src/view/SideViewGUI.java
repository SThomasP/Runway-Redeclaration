package view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;

public class SideViewGUI extends ViewGUI {


    private Rectangle runwayRec;
    private Rectangle obstacleRec;
    private Polygon  takeOffTri;
    private Polygon landingTri;

    @Override
    public void init() {
        setBackground(Color.cyan);
        runwayRec = new Rectangle();
        takeOffTri = new Polygon();
        landingTri = new Polygon();
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
        takeOffTri.addPoint(width,(int) (0.8*height));
        takeOffTri.addPoint(width, (int)(0.8*height) - rescaleVertical(inverseRescaleHorizontal(width - x4)/50));

        landingTri.reset();
        landingTri.addPoint(x1+ thresholdDistance, (int) (0.8*height));
        landingTri.addPoint(0, (int) (0.8*height));
        landingTri.addPoint(0, (int) (0.8*height) - rescaleVertical(inverseRescaleHorizontal(x1+ thresholdDistance)/50));
        toraLine = new Line2D.Float((float) x4, (float) (height / 1.31), (float) x1, (float) (height / 1.31));
        todaLine = new Line2D.Float((float) width, (float) (height / 1.40), (float) x1, (float) (height / 1.40));
        asdaLine = new Line2D.Float((float) runwayRec.getMaxX(), (float) (height / 1.49), (float) x1, (float) (height / 1.49));
        ldaLine = new Line2D.Float((float) x4, (float) (height / 1.58), (float) x1 + thresholdDistance, (float) (height / 1.58));
        repaint();
    }

    @Override
    protected int rescaleHorizontal(int original) {
        double toReturn = (double) original / runwayLength * getWidth()*(0.87-0.12);
        return (int) (toReturn);
    }

    protected int inverseRescaleHorizontal(int original){
        double toReturn = (double) original / (getWidth()*(0.87-0.12))* runwayLength ;
        return (int) toReturn;
    }

    protected int rescaleVertical(int original) {
        double toReturn = (double) original / 500 * runwayRec.getY();
        return (int) toReturn;
    }

    @Override
    public void removeObstacle() {
        obstacleOnRunway = false;
        int width = getWidth();
        int height = getHeight();
        int x4 = (int) (width * 0.87);
        int x1 = (int) (width * 0.12) + thresholdDistance;
        takeOffTri.reset();
        takeOffTri.addPoint(x4,(int) (0.8*height));
        takeOffTri.addPoint(width,(int) (0.8*height));
        takeOffTri.addPoint(width, (int)(0.8*height) -  rescaleVertical(inverseRescaleHorizontal(width - x4)/50));
        landingTri.reset();
        landingTri.addPoint(x1, (int) (0.8*height));
        landingTri.addPoint(0, (int) (0.8*height));
        landingTri.addPoint(0, (int) (0.8*height) - rescaleVertical(inverseRescaleHorizontal(x1)/50));

    }

    public void redrawDistances(int toda, int tora, int lda, int asda) {
        super.redrawDistances(toda, tora, lda, asda);
        int height = getHeight();
        int width = getWidth();
        int x1 = (int) (width * 0.12);
        int x4 = (int) (width * 0.87);
        if (obstacleOnRunway){
            if(obstacleRec.getCenterX() > width/2) {
                toraLine = new Line2D.Float((float) obstacleRec.getX(), (float) (height / 1.31), (float) x1, (float) (height / 1.31));
                todaLine = new Line2D.Float((float) obstacleRec.getX(), (float) (height / 1.40), (float) x1 , (float) (height / 1.40));
                asdaLine = new Line2D.Float((float) obstacleRec.getX(), (float) (height / 1.49), (float) x1, (float) (height / 1.49));
                ldaLine = new Line2D.Float((float) obstacleRec.getX(), (float) (height / 1.58), (float) x1 + thresholdDistance, (float) (height / 1.58));
            }
            else {
                toraLine = new Line2D.Float((float) x4, (float) (height / 1.31), (float) obstacleRec.getMaxX(), (float) (height / 1.31));
                todaLine = new Line2D.Float((float) width, (float) (height / 1.4), (float) obstacleRec.getMaxX(), (float) (height / 1.4));
                asdaLine = new Line2D.Float((float) runwayRec.getMaxX(), (float) (height / 1.49), (float) obstacleRec.getMaxX(), (float) (height / 1.49));
                if (obstacleRec.getMaxX() > x1 +thresholdDistance) {
                    ldaLine = new Line2D.Float((float) x4, (float) (height / 1.58), (float) obstacleRec.getMaxX(), (float) (height / 1.58));
                }
                else {
                    ldaLine = new Line2D.Float((float) x4, (float) (height / 1.58), (float) x1+thresholdDistance, (float) (height / 1.58));
                }
            }
        }
        else {
            toraLine = new Line2D.Float((float) x4, (float) (height / 1.31), (float) x1, (float) (height / 1.31));
            todaLine = new Line2D.Float((float) width, (float) (height / 1.40), (float) x1, (float) (height / 1.40));
            asdaLine = new Line2D.Float((float) runwayRec.getMaxX(), (float) (height / 1.49), (float) x1, (float) (height / 1.49));
            ldaLine = new Line2D.Float((float) x4, (float) (height / 1.58), (float) x1 + thresholdDistance, (float) (height / 1.58));
        }
    }

    @Override
    public void addObstacle(int width, int length, int height, int dFromT, int dFromCL) {
        obstacleOnRunway = true;
        length = Math.max(10, rescaleHorizontal(length));
        height = Math.max(10, rescaleVertical(height));
        int startPos = (int)(getWidth()*0.12) +thresholdDistance + rescaleHorizontal(dFromT);
        obstacleRec = new Rectangle(startPos,(int) runwayRec.getY() - height,length,height);
        if (obstacleRec.getCenterX() > getWidth() / 2){
            takeOffTri.reset();
            int triCorner = startPos -rescaleHorizontal(50*height);
            takeOffTri.addPoint(getWidth(),(int) (0.8*getHeight()));
            takeOffTri.addPoint(getWidth(),(int)(0.8*getHeight()) -  rescaleVertical(inverseRescaleHorizontal(getWidth() - triCorner)/50) );
            takeOffTri.addPoint(triCorner, (int) (0.8 * getHeight()));

        }
        else{
            landingTri.reset();
            int triCorner = (int) obstacleRec.getMaxX() + rescaleHorizontal(50*height);
            landingTri.addPoint(0, (int) (0.8*getHeight()));
            landingTri.addPoint(0, (int) (0.8*getHeight() - rescaleVertical(inverseRescaleHorizontal(triCorner)/50)));
            landingTri.addPoint(triCorner,(int) (0.8 * getHeight()));
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        outlineShape(Color.black,g, runwayRec,outline);
        if (action == TAKEOFF) {
            fillShape(Color.black,g,takeOffTri);
            outlineShape(Color.black, g, toraLine, outline);
            outlineShape(Color.black, g, todaLine, outline);
            outlineShape(Color.black, g, asdaLine, outline);
            g.drawString(toraString, (int) toraLine.getX2(), (int) toraLine.getY1() + 15);
            g.drawString(todaString, (int) todaLine.getX2(), (int) todaLine.getY1() + 15);
            g.drawString(asdaString, (int) asdaLine.getX2(), (int) asdaLine.getY1() + 15);
        }
        if (action == LANDING) {
            fillShape(Color.black,g,landingTri);
            outlineShape(Color.black, g, ldaLine, outline);
            g.drawString(ldaString, (int) ldaLine.getX2(), (int) ldaLine.getY1() + 15);
        }
        if  (obstacleOnRunway){
            fillShape(Color.RED,g,obstacleRec);
        }
    }
}
