package view;

import java.awt.*;
import java.awt.geom.Line2D;

public class TopViewGUI extends ViewGUI {

    private Rectangle runwayRec;
    private Line2D centreLine;
    private Polygon clearArea;
    private Line2D[] sideLines;
    private final static Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
    private final static Stroke outline = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);

    public void init() {
        setBackground(new Color(51, 204, 51));
        clearArea = new Polygon();
        runwayRec = new Rectangle();
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
        double halfway = height / 2;
        sideLines = new Line2D[20];
        for (int i = 0; i < 10; i++) {
            double ypoint = halfway - 18 + i * 4;
            double xpoint = (width * 0.12);
            sideLines[i] = new Line2D.Double(xpoint, ypoint, x[1], ypoint);
            sideLines[i + 10] = new Line2D.Double(x[4], ypoint, width - xpoint, ypoint);
        }
        runwayRec = new Rectangle((int) (0.05 * width), (int) (0.45 * height), (int) (0.9 * width), (int) (0.10 * height));
        centreLine = new Line2D.Float((float) x[2], (float) (height / 2), (float) x[3], (float) (height / 2));
        repaint();
    }


    public void paint(Graphics g) {
        super.paint(g);
        fillShape(new Color(141, 44, 159), g, clearArea);
        outlineShape(Color.black, g, clearArea, outline);
        fillShape(Color.gray, g, runwayRec);
        outlineShape(Color.black, g, runwayRec, outline);
        outlineShape(Color.white, g, centreLine, dashed);
        for (Line2D line: sideLines){
            outlineShape(Color.white, g, line, outline);
        }
    }
}
