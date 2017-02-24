import java.awt.*;
import java.awt.geom.Line2D;

public class TopViewGUI extends ViewGUI {

    private Rectangle runwayRec;
    private Line2D centreLine;
    private Polygon clearArea;

    public void init(){
        setBackground(new Color(51,204,51));
        clearArea = new Polygon();
        runwayRec = new Rectangle();
    }

    public void redrawView(){
        clearArea.reset();
        int height = getHeight();
        int[] y = {(int) (height*0.3),(int) (height*0.2), (int) (height*0.7),(int) (height*0.8)};
        int width = getWidth();
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
        runwayRec = new Rectangle((int) (0.1*width),(int)(0.45*height),(int) (0.8*width),(int) (0.11*height));
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(141, 44, 159));
        g2d.fillPolygon(clearArea);
        g2d.setColor(Color.gray);
        g2d.fill(runwayRec);
    }
}
