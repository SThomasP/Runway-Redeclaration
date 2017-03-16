package view;

import java.awt.*;
import java.awt.geom.Line2D;

public class TopViewGUI extends ViewGUI {

    private Rectangle runwayRec;
    private Line2D centreLine;
    private Polygon clearArea;
    private Line2D[] sideLines;
    private Rectangle obstacleRec;
    
    public void init() {
        setBackground(new Color(51, 204, 51));
        clearArea = new Polygon();
        runwayRec = new Rectangle();
        toraString = "TORA";
        todaString = "TODA";
        asdaString = "ASDA";
        ldaString = "LDA";
    }


    private int rescaleVertical(int original){
        return (int) ((float) (original)/runwayWidth*runwayRec.getHeight());
    }

    protected int rescaleHorizontal(int original){
        return  (int) ((float) (original)/runwayLength*(getWidth()*(0.83 - 0.17)));
    }

    public Rectangle getObstacleRec(){
        return obstacleRec;
    }


    @Override
    public void redrawDistances(int toda, int tora, int lda, int asda) {
        super.redrawDistances(toda, tora, lda, asda);
        int height = getHeight();
        int width = getWidth();
        int[] x = {0, (int) (width * 0.17), (int) (width * 0.25), (int) (width * 0.75), (int) (width * 0.83), width};
        if (obstacleOnRunway){
            if(obstacleRec.getCenterX() > width/2) {
                toraLine = new Line2D.Float((float) obstacleRec.getX(), (float) (height / 1.75), (float) x[1], (float) (height / 1.75));
                todaLine = new Line2D.Float((float) obstacleRec.getX(), (float) (height / 2.4), (float) x[1] , (float) (height / 2.4));
                asdaLine = new Line2D.Float((float) obstacleRec.getX(), (float) (height / 1.65), (float) x[1], (float) (height / 1.65));
                ldaLine = new Line2D.Float((float) obstacleRec.getX(), (float) (height / 2.7), (float) x[1] + thresholdDistance, (float) (height / 2.7));
            }
            else {
                toraLine = new Line2D.Float((float) x[4], (float) (height / 1.75), (float) obstacleRec.getMaxX(), (float) (height / 1.75));
                todaLine = new Line2D.Float((float) width, (float) (height / 2.4), (float) obstacleRec.getMaxX(), (float) (height / 2.4));
                asdaLine = new Line2D.Float((float) (width*0.95), (float) (height / 1.65), (float) obstacleRec.getMaxX(), (float) (height / 1.65));
                if (obstacleRec.getMaxX() > x[1] +thresholdDistance) {
                    ldaLine = new Line2D.Float((float) x[4], (float) (height / 2.7), (float) obstacleRec.getMaxX(), (float) (height / 2.7));
                }
                else {
                    ldaLine = new Line2D.Float((float) x[4], (float) (height / 2.7), (float) x[1]+thresholdDistance, (float) (height / 2.7));
                }
            }
        }
        else {
            toraLine = new Line2D.Float((float) x[4], (float) (height / 1.75), (float) x[1], (float) (height / 1.75));
            todaLine = new Line2D.Float((float) width, (float) (height / 2.4), (float) x[1], (float) (height / 2.4));
            asdaLine = new Line2D.Float((float) (width*0.95), (float) (height / 1.65), (float) x[1], (float) (height / 1.65));
            ldaLine = new Line2D.Float((float) x[4], (float) (height / 2.7), (float) x[1] + thresholdDistance, (float) (height / 2.7));
        }
    }

    public void redrawView() {
        clearArea.reset();
        int height = getHeight();
        int[] y = {(int) (height * 0.3), (int) (height * 0.2), (int) (height * 0.7), (int) (height * 0.8)};
        int width = getWidth();
        int[] x = {0, (int) (width * 0.17), (int) (width * 0.25), (int) (width * 0.75), (int) (width * 0.83), width};
        clearArea.addPoint(x[0], y[0]);
        clearArea.addPoint(x[1], y[0]);
        clearArea.addPoint(x[2], y[1]);
        clearArea.addPoint(x[3], y[1]);
        clearArea.addPoint(x[4], y[0]);
        clearArea.addPoint(x[5], y[0]);
        clearArea.addPoint(x[5], y[2]);
        clearArea.addPoint(x[4], y[2]);
        clearArea.addPoint(x[3], y[3]);
        clearArea.addPoint(x[2], y[3]);
        clearArea.addPoint(x[1], y[2]);
        clearArea.addPoint(x[0], y[2]);
        runwayRec = new Rectangle((int) (0.05 * width), (int) (0.45 * height), (int) (0.9 * width), (int) (0.10 * height));
        centreLine = new Line2D.Float((float) x[2], (float) (height / 2), (float) x[3], (float) (height / 2));
        toraLine = new Line2D.Float((float) x[4], (float) (height/1.75), (float) x[1], (float) (height/1.75));
        todaLine = new Line2D.Float((float) width, (float) (height/2.4), (float) x[1], (float) (height/2.4));
        asdaLine = new Line2D.Float((float) 0.95*width, (float) (height/1.65), (float) x[1], (float) (height/1.65));
        ldaLine = new Line2D.Float((float) x[4], (float) (height/2.7), (float) x[1], (float) (height/2.7));
        sideLines = new Line2D[20];
        double halfway = height / 2;
        for (int i = 0; i < 10; i++) {
            double ypoint = halfway - 18 + i * 4;
            double xpoint = (width * 0.12);
            sideLines[i] = new Line2D.Double(xpoint, ypoint, x[1], ypoint);
            sideLines[i + 10] = new Line2D.Double(x[4], ypoint, width - xpoint, ypoint);
        }
    }


    @Override
    public void removeObstacle() {
        obstacleOnRunway = false;
    }

    @Override
    public void addObstacle(int width, int length, int height, int dFromT, int dFromCL) {
        obstacleOnRunway = true;
        dFromT = (int) (getWidth()*0.17) + thresholdDistance + rescaleHorizontal(dFromT);
        width = Math.max(rescaleVertical(width), 10);
        length = Math.max(rescaleHorizontal(length), 10);
        dFromCL = getHeight()/2 + rescaleVertical(dFromCL);
        obstacleRec = new Rectangle(dFromT,dFromCL,length, width);
        repaint();
    }

    private static void drawRunwayName(String name, double rotation, int x, int y, Graphics g){
        rotation = Math.toRadians(rotation);
        Graphics2D g2d =  (Graphics2D) g.create();
        g2d.rotate(rotation);
        g2d.setColor(Color.white);
        g2d.setFont(MainPageGUI.displayFont);
        int newX = (int) (x*Math.cos(rotation) + y*Math.sin(rotation));
        int  newY = (int) (-x*Math.sin(rotation) + y*Math.cos(rotation));
        g2d.drawString(name,newX,newY);
        g2d.dispose();
    }

    public void paint(Graphics g) {
        super.paint(g);
        fillShape(new Color(141, 44, 159), g, clearArea);
        outlineShape(Color.black, g, clearArea, outline);
        fillShape(Color.gray, g, runwayRec);
        outlineShape(Color.black, g, runwayRec, outline);
        outlineShape(Color.white, g, centreLine, dashed);
        outlineShape(Color.blue, g, toraLine, outline);
        outlineShape(Color.blue, g, todaLine, outline);
        outlineShape(Color.orange, g, asdaLine, outline);
        outlineShape(Color.orange, g, ldaLine, outline);
        g.drawString(toraString, (int) toraLine.getX2(), (int) toraLine.getY1() + 15);
        g.drawString(todaString, (int) todaLine.getX2(), (int) todaLine.getY1() + 15);
        g.drawString(asdaString, (int) asdaLine.getX2(), (int) asdaLine.getY1() + 15);
        g.drawString(ldaString, (int) ldaLine.getX2(), (int) ldaLine.getY1() + 15);
        for (Line2D line: sideLines){
            outlineShape(Color.white, g, line, outline);
        }
        if (obstacleOnRunway){
            fillShape(Color.black,g,obstacleRec);
            
        }

        drawRunwayName(name,90,getWidth()/5, getHeight()/2 - 18, g);
        drawRunwayName(inverseName, -90, 4 * getWidth()/5, getHeight()/2 + 18, g);
    }
}
