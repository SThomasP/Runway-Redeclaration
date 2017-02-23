import java.awt.*;
import java.awt.geom.*;

public class TopViewGUI extends ViewGUI {

    private Rectangle2D runwayRec;
    private Polygon clearArea;

    public void init(){
        clearArea = new Polygon();
    }

    public void redrawView(){
        clearArea.reset();
        int height = getHeight();
        System.out.println(height);
        int[] y = {(int) (height*0.3),(int) (height*0.2), (int) (height*0.7),(int) (height*0.8)};
        int width = getWidth();
        System.out.println(width);
        int[] x = {0,(int) (width*0.2),(int) (width*0.25),(int) (width*0.75), (int) (width * 0.8) ,width};
        clearArea.addPoint(x[0],y[0]);
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
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(141, 44, 159));
        g2d.fillPolygon(clearArea);
    }
}
