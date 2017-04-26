package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;

import model.Runway;

public class TopViewGUI extends ViewGUI {


    private double orientation = Math.PI * 2;
    private double zoom = 1;
    private double pointx = -1;
    private double pointy = -1;
    private Polygon runwayRec;
    private Line2D centreLine;
    private Polygon clearArea;
    private Line2D[] sideLines;
    private Polygon obstacleRec;
    private Polygon obstacleRotatedRec;
    private int width;
    private int length;
    private int height;
    private int dFromT;
    private int dFromCL;

    public void init() {
        setBackground(new Color(51, 204, 51));
        clearArea = new Polygon();
        runwayRec = new Polygon();
        toraString = "TORA";
        todaString = "TODA";
        asdaString = "ASDA";
        ldaString = "LDA";
    }


    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }


    public void setPoint(double x, double y) {
        pointx = x;
        pointy = y;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }


    private int rescaleVertical(int original) {
        return (int) ((float) (original) / runwayWidth * runwayRec.getBounds().getHeight());
    }

    protected int rescaleHorizontal(int original) {
        return (int) ((float) (original) / runwayLength * (getWidth() * (0.83 - 0.17)));
    }

    public Polygon getObstacleRec() {
        return obstacleRec;
    }

    public double[] rotateandzoom(double orientation, double x, double y, double zoom) {
        //makes the middle 0
        x = x - getWidth() / 2;
        y = y - getHeight() / 2;
        double newx = ((x * Math.cos(orientation)) - (y * Math.sin(orientation))) + getWidth() / 2;
        double newy = ((x * Math.sin(orientation)) + (y * Math.cos(orientation))) + getHeight() / 2;

        //shift to the point
        newx = newx - pointx + getWidth() / 2;
        newy = newy - pointy + getHeight() / 2;

        //zoom in
        newx = ((newx - getWidth() / 2) * zoom) + getWidth() / 2;
        newy = ((newy - getHeight() / 2) * zoom) + getHeight() / 2;

        double[] rotated = {newx, newy};
        return rotated;
    }

    @Override
    public void redrawDistances(int toda, int tora, int lda, int asda) {

        super.redrawDistances(toda, tora, lda, asda);
        int height = getHeight();
        int width = getWidth();
        int[] x = {0, (int) (width * 0.17), (int) (width * 0.25), (int) (width * 0.75), (int) (width * 0.83), width};
        if (obstacleOnRunway) {
            if (obstacleRec.getBounds().getCenterX() > width / 2) {
                toraLine = new Line2D.Float((float) rotateandzoom(orientation, obstacleRec.getBounds().getX(), (height / 1.75), zoom)[0], (float) rotateandzoom(orientation, obstacleRec.getBounds().getX(), (height / 1.75), zoom)[1], (float) rotateandzoom(orientation, x[1], (height / 1.75), zoom)[0], (float) rotateandzoom(orientation, x[1], (height / 1.75), zoom)[1]);
                //you are here !!
                todaLine = new Line2D.Float((float) rotateandzoom(orientation, obstacleRec.getBounds().getX(), (height / 2.4), zoom)[0], (float) rotateandzoom(orientation, obstacleRec.getBounds().getX(), (height / 2.4), zoom)[1], (float) rotateandzoom(orientation, x[1], (height / 2.4), zoom)[0], (float) rotateandzoom(orientation, x[1], (height / 2.4), zoom)[1]);
                asdaLine = new Line2D.Float((float) rotateandzoom(orientation, obstacleRec.getBounds().getX(), (height / 1.65), zoom)[0], (float) rotateandzoom(orientation, obstacleRec.getBounds().getX(), (height / 1.65), zoom)[1], (float) rotateandzoom(orientation, x[1], (height / 1.65), zoom)[0], (float) rotateandzoom(orientation, x[1], (height / 1.65), zoom)[1]);
                ldaLine = new Line2D.Float((float) rotateandzoom(orientation, obstacleRec.getBounds().getX(), (height / 2.7), zoom)[0], (float) rotateandzoom(orientation, obstacleRec.getBounds().getX(), (height / 2.7), zoom)[1], (float) rotateandzoom(orientation, x[1] + thresholdDistance, (height / 2.7), zoom)[0], (float) rotateandzoom(orientation, x[1] + thresholdDistance, (height / 2.7), zoom)[1]);
            } else {
                toraLine = new Line2D.Float((float) rotateandzoom(orientation, x[4], (height / 1.75), zoom)[0], (float) rotateandzoom(orientation, x[4], (height / 1.75), zoom)[1], (float) rotateandzoom(orientation, obstacleRec.getBounds().getMaxX(), (height / 1.75), zoom)[0], (float) rotateandzoom(orientation, obstacleRec.getBounds().getMaxX(), (height / 1.75), zoom)[1]);
                todaLine = new Line2D.Float((float) rotateandzoom(orientation, width, (height / 2.4), zoom)[0], (float) rotateandzoom(orientation, width, (height / 2.4), zoom)[1], (float) rotateandzoom(orientation, obstacleRec.getBounds().getMaxX(), (height / 2.4), zoom)[0], (float) rotateandzoom(orientation, obstacleRec.getBounds().getMaxX(), (height / 2.4), zoom)[1]);
                asdaLine = new Line2D.Float((float) rotateandzoom(orientation, (width * 0.95), (height / 1.65), zoom)[0], (float) rotateandzoom(orientation, (width * 0.95), (height / 1.65), zoom)[1], (float) rotateandzoom(orientation, obstacleRec.getBounds().getMaxX(), (height / 1.65), zoom)[0], (float) rotateandzoom(orientation, obstacleRec.getBounds().getMaxX(), (height / 1.65), zoom)[1]);
                if (obstacleRec.getBounds().getMaxX() > x[1] + thresholdDistance) {
                    //do this
                    ldaLine = new Line2D.Float((float) rotateandzoom(orientation, x[4], (height / 2.7), zoom)[0], (float) rotateandzoom(orientation, x[4], (height / 2.7), zoom)[1], (float) rotateandzoom(orientation, obstacleRec.getBounds().getMaxX(), (height / 2.7), zoom)[0], (float) rotateandzoom(orientation, obstacleRec.getBounds().getMaxX(), (height / 2.7), zoom)[1]);
                } else {
                    ldaLine = new Line2D.Float((float) rotateandzoom(orientation, x[4], (height / 2.7), zoom)[0], (float) rotateandzoom(orientation, x[4], (height / 2.7), zoom)[1], (float) rotateandzoom(orientation, x[1] + thresholdDistance, (height / 2.7), zoom)[0], (float) rotateandzoom(orientation, x[1] + thresholdDistance, (height / 2.7), zoom)[1]);
                }
            }
        } else {
            toraLine = new Line2D.Float((float) rotateandzoom(orientation, x[4], (height / 1.75), zoom)[0], (float) rotateandzoom(orientation, x[4], (height / 1.75), zoom)[1], (float) rotateandzoom(orientation, x[1], (height / 1.75), zoom)[0], (float) rotateandzoom(orientation, x[1], (height / 1.75), zoom)[1]);
            todaLine = new Line2D.Float((float) rotateandzoom(orientation, width, (height / 2.4), zoom)[0], (float) rotateandzoom(orientation, width, (height / 2.4), zoom)[1], (float) rotateandzoom(orientation, x[1], (height / 2.4), zoom)[0], (float) rotateandzoom(orientation, x[1], (height / 2.4), zoom)[1]);
            asdaLine = new Line2D.Float((float) rotateandzoom(orientation, (width * 0.95), (height / 1.65), zoom)[0], (float) rotateandzoom(orientation, (width * 0.95), (height / 1.65), zoom)[1], (float) rotateandzoom(orientation, x[1], (height / 1.65), zoom)[0], (float) rotateandzoom(orientation, x[1], (height / 1.65), zoom)[1]);
            ldaLine = new Line2D.Float((float) rotateandzoom(orientation, x[4], (height / 2.7), zoom)[0], (float) rotateandzoom(orientation, x[4], (height / 2.7), zoom)[1], (float) rotateandzoom(orientation, x[1] + thresholdDistance, (height / 2.7), zoom)[0], (float) rotateandzoom(orientation, x[1] + thresholdDistance, (height / 2.7), zoom)[1]);
        }

    }

    public void redrawView() {

        clearArea.reset();
        int height = getHeight();
        int[] y = {(int) (height * 0.3), (int) (height * 0.2), (int) (height * 0.7), (int) (height * 0.8)};
        int width = getWidth();
        int[] x = {0, (int) (width * 0.17), (int) (width * 0.25), (int) (width * 0.75), (int) (width * 0.83), width};
        clearArea.addPoint((int) rotateandzoom(orientation, x[0], y[0], zoom)[0], (int) rotateandzoom(orientation, x[0], y[0], zoom)[1]);
        clearArea.addPoint((int) rotateandzoom(orientation, x[1], y[0], zoom)[0], (int) rotateandzoom(orientation, x[1], y[0], zoom)[1]);
        clearArea.addPoint((int) rotateandzoom(orientation, x[2], y[1], zoom)[0], (int) rotateandzoom(orientation, x[2], y[1], zoom)[1]);
        clearArea.addPoint((int) rotateandzoom(orientation, x[3], y[1], zoom)[0], (int) rotateandzoom(orientation, x[3], y[1], zoom)[1]);
        clearArea.addPoint((int) rotateandzoom(orientation, x[4], y[0], zoom)[0], (int) rotateandzoom(orientation, x[4], y[0], zoom)[1]);
        clearArea.addPoint((int) rotateandzoom(orientation, x[5], y[0], zoom)[0], (int) rotateandzoom(orientation, x[5], y[0], zoom)[1]);
        clearArea.addPoint((int) rotateandzoom(orientation, x[5], y[2], zoom)[0], (int) rotateandzoom(orientation, x[5], y[2], zoom)[1]);
        clearArea.addPoint((int) rotateandzoom(orientation, x[4], y[2], zoom)[0], (int) rotateandzoom(orientation, x[4], y[2], zoom)[1]);
        clearArea.addPoint((int) rotateandzoom(orientation, x[3], y[3], zoom)[0], (int) rotateandzoom(orientation, x[3], y[3], zoom)[1]);
        clearArea.addPoint((int) rotateandzoom(orientation, x[2], y[3], zoom)[0], (int) rotateandzoom(orientation, x[2], y[3], zoom)[1]);
        clearArea.addPoint((int) rotateandzoom(orientation, x[1], y[2], zoom)[0], (int) rotateandzoom(orientation, x[1], y[2], zoom)[1]);
        clearArea.addPoint((int) rotateandzoom(orientation, x[0], y[2], zoom)[0], (int) rotateandzoom(orientation, x[0], y[2], zoom)[1]);
        int[] runwayX = {(int) (0.05 * width), (int) (0.05 * width), (int) (0.05 * width + 0.9 * width), (int) (0.05 * width + 0.9 * width)};
        int[] runwayY = {(int) (0.45 * height), (int) (0.45 * height + 0.10 * height), (int) (0.45 * height + 0.10 * height), (int) (0.45 * height)};
        int[] rotatedandzoomedX = new int[4];
        int[] rotatedandzoomedY = new int[4];
        for (int i = 0; i < 4; i++) {
            rotatedandzoomedX[i] = (int) rotateandzoom(orientation, runwayX[i], runwayY[i], zoom)[0];
            rotatedandzoomedY[i] = (int) rotateandzoom(orientation, runwayX[i], runwayY[i], zoom)[1];


        }
        runwayRec = new Polygon(rotatedandzoomedX, rotatedandzoomedY, 4);
        centreLine = new Line2D.Float((float) rotateandzoom(orientation, x[2], (height / 2), zoom)[0], (float) rotateandzoom(orientation, x[2], (height / 2), zoom)[1], (float) rotateandzoom(orientation, x[3], (height / 2), zoom)[0], (float) rotateandzoom(orientation, x[3], (height / 2), zoom)[1]);
        redrawDistances(toda,tora,asda,lda);
        sideLines = new Line2D[20];
        double halfway = height / 2;
        for (int i = 0; i < 10; i++) {
            double ypoint = halfway - 18 + i * 4;
            double xpoint = (width * 0.12);
            sideLines[i] = new Line2D.Double(rotateandzoom(orientation, xpoint, ypoint, zoom)[0], rotateandzoom(orientation, xpoint, ypoint, zoom)[1], rotateandzoom(orientation, x[1], ypoint, zoom)[0], rotateandzoom(orientation, x[1], ypoint, zoom)[1]);
            sideLines[i + 10] = new Line2D.Double(rotateandzoom(orientation, x[4], ypoint, zoom)[0], rotateandzoom(orientation, x[4], ypoint, zoom)[1], rotateandzoom(orientation, width - xpoint, ypoint, zoom)[0], rotateandzoom(orientation, width - xpoint, ypoint, zoom)[1]);
        }
    }


    @Override
    public void removeObstacle() {
        obstacleOnRunway = false;
    }

    @Override
    public void addObstacle(int width, int length, int height, int dFromT, int dFromCL) {

        obstacleOnRunway = true;
        this.dFromT = (int) (getWidth() * 0.17) + thresholdDistance + rescaleHorizontal(dFromT);
        this.width = Math.max(rescaleVertical(width), 10);
        this.length = Math.max(rescaleHorizontal(length), 10);
        this.dFromCL = getHeight() / 2 + rescaleVertical(-dFromCL);
        obstacleDraw();
    }

    public void obstacleDraw() {

        obstacleRec = new Polygon(new int[]{dFromT, dFromT, dFromT + length, dFromT + length}, new int[]{dFromCL, dFromCL + width, dFromCL + width, dFromCL}, 4);
        obstacleRotatedRec = new Polygon(new int[]{(int) rotateandzoom(orientation, dFromT, dFromCL, zoom)[0], (int) rotateandzoom(orientation, dFromT, dFromCL + width, zoom)[0], (int) rotateandzoom(orientation, dFromT + length, dFromCL + width, zoom)[0], (int) rotateandzoom(orientation, dFromT + length, dFromCL, zoom)[0]}, new int[]{(int) rotateandzoom(orientation, dFromT, dFromCL, zoom)[1], (int) rotateandzoom(orientation, dFromT, dFromCL + width, zoom)[1], (int) rotateandzoom(orientation, dFromT + length, dFromCL + width, zoom)[1], (int) rotateandzoom(orientation, dFromT + length, dFromCL, zoom)[1]}, 4);
        repaint();
    }


    private void writeDistances(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.rotate(orientation);
        if (action == TAKEOFF) {
            int newX = (int) (toraLine.getX2() * Math.cos(orientation) + (toraLine.getY2() + 15) * Math.sin(orientation));
            int newY = (int) (-toraLine.getX2() * Math.sin(orientation) + (toraLine.getY2() + 15) * Math.cos(orientation));
            g2d.drawString(toraString, newX, newY);

            newX = (int) (todaLine.getX2() * Math.cos(orientation) + (todaLine.getY2() + 15) * Math.sin(orientation));
            newY = (int) (-todaLine.getX2() * Math.sin(orientation) + (todaLine.getY2() + 15) * Math.cos(orientation));
            g2d.drawString(todaString, newX, newY);

            newX = (int) (asdaLine.getX2() * Math.cos(orientation) + (asdaLine.getY2() + 15) * Math.sin(orientation));
            newY = (int) (-asdaLine.getX2() * Math.sin(orientation) + (asdaLine.getY2() + 15) * Math.cos(orientation));
            g2d.drawString(asdaString, newX, newY);
        }
        else if (action == LANDING){
            int newX = (int) (ldaLine.getX2() * Math.cos(orientation) + (ldaLine.getY2() + 15) * Math.sin(orientation));
            int newY = (int) (-ldaLine.getX2() * Math.sin(orientation) + (ldaLine.getY2() + 15) * Math.cos(orientation));
            g2d.drawString(ldaString, newX, newY);

        }
        
        
    }


    public void paint(Graphics g) {
        super.paint(g);
        if (pointx == -1) {
            pointx = getWidth() / 2;
        }
        if (pointy == -1) {
            pointy = getHeight() / 2;
        }
        redrawView();
        fillShape(new Color(141, 44, 159), g, clearArea);
        outlineShape(Color.black, g, clearArea, outline);
        fillShape(Color.gray, g, runwayRec);
        outlineShape(Color.black, g, runwayRec, outline);
        outlineShape(Color.white, g, centreLine, dashed);
        if (action == TAKEOFF) {
            outlineShape(Color.blue, g, toraLine, outline);
            outlineShape(Color.blue, g, todaLine, outline);
            outlineShape(Color.orange, g, asdaLine, outline);
        }
        if (action == LANDING) {
            outlineShape(Color.orange, g, ldaLine, outline);
        }
        writeDistances(g);
        for (Line2D line : sideLines) {
            outlineShape(Color.white, g, line, outline);
        }
        if (obstacleOnRunway) {
            fillShape(Color.black, g, obstacleRotatedRec);

        }

        drawRunwayName(name[0], orientation + Math.toRadians(90), (int) rotateandzoom(orientation, getWidth() / 5+ 18, getHeight() / 2 - 9, zoom)[0], (int) rotateandzoom(orientation, getWidth() / 5 + 18, getHeight() / 2 - 9, zoom)[1], g, Color.white);
        drawRunwayName(name[1], orientation + Math.toRadians(90), (int) rotateandzoom(orientation, getWidth() / 5+ 7, getHeight() / 2 - 3, zoom)[0], (int) rotateandzoom(orientation, getWidth() / 5 + 7, getHeight() / 2 - 3, zoom)[1], g, Color.white);
        drawRunwayName(inverseName[0], orientation + Math.toRadians(-90), (int) rotateandzoom(orientation, 4 *getWidth() / 5 - 18, getHeight() / 2 + 9, zoom)[0], (int) rotateandzoom(orientation, 4 * getWidth() / 5 - 18, getHeight() / 2 + 9, zoom)[1], g, Color.white);
        drawRunwayName(inverseName[1], orientation + Math.toRadians(-90), (int) rotateandzoom(orientation, 4 *getWidth() / 5 - 7, getHeight() / 2 + 3, zoom)[0], (int) rotateandzoom(orientation, 4 * getWidth() / 5 - 7, getHeight() / 2 + 3, zoom)[1], g, Color.white);
    }
}
