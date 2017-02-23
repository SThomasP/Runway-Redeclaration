import java.awt.*;
import java.awt.geom.*;

public class TopViewGUI extends ViewGUI {

    private Rectangle2D runwayRec;
    private Polygon clearArea;

    public void init(){
        setBackground(Color.GREEN);
        int[] xPoints = {0,getWidth()/ 5,3*getWidth()/ 10,7*getWidth()/10,4*getWidth()/5,getWidth(),getWidth(),4*getWidth()/5,3*getWidth()/10,7*getWidth()/10,getWidth()/5,0};
        int[] yPoints = {getHeight()/3,getHeight()/3,getHeight()/6,getHeight()/6,getHeight()/3,getHeight()/3,2*getHeight()/3,2*getHeight()/3,5*getHeight()/6,5*getHeight()/6,2*getHeight()/3,2*getHeight()/3};
        clearArea = new Polygon(xPoints, yPoints,12);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(141, 44, 159));
        g2d.fillPolygon(clearArea);
    }
}
