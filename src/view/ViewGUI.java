package view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

/**
 * Created by steff on 21/02/2017.
 */
public abstract class ViewGUI extends JPanel {

    protected Line2D todaLine, toraLine, ldaLine, asdaLine;
    protected String todaString, toraString, ldaString, asdaString;
    protected boolean landing;


    public void redrawDistances(int toda, int tora, int lda, int asda){

    }

    public abstract boolean checkLanding();
    
    public abstract void init();

    public abstract void redrawView();

    protected static void outlineShape(Color c, Graphics g, Shape shape, Stroke stroke){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(c);
        g2d.setStroke(stroke);
        g2d.draw(shape);
        g2d.dispose();
    }

    protected static void fillShape(Color c, Graphics g, Shape shape){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(c);
        g2d.fill(shape);
        g2d.dispose();
    }

}

