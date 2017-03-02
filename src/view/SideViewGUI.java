package view;

import java.awt.*;

public class SideViewGUI extends ViewGUI {


    private Rectangle runwayRec;

    @Override
    public void init() {
        setBackground(Color.cyan);
    }

    @Override
    public void redrawView() {
        int width = getWidth();
        int height = getHeight();
        runwayRec = new Rectangle((int) (0.05*width), (int) (0.8*height), (int) (0.9*width),(int) (0.05*height));
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        outlineShape(Color.black,g, runwayRec, new BasicStroke(2,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL));
    }
}
