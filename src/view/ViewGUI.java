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
    protected int thresholdDistance, runwayLength, runwayWidth;
    protected String name, inverseName;
    protected boolean obstacleOnRunway = false;
    protected int action = 0;


    public final static int TAKEOFF  = 0;
    public final static int LANDING = 1;

    protected final static Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
    protected final static Stroke outline = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);

    public  void redrawDistances(int toda, int tora, int lda, int asda){
        this.todaString = "TODA: "+toda+"m";
        this.toraString = "TORA: "+tora+"m";
        this.asdaString = "ASDA: "+asda+"m";
        this.ldaString = "LDA: "+lda+"m";
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action){
        this.action = action;
    }

    public abstract void init();

    public abstract void redrawView();

    protected abstract int rescaleHorizontal(int original);

    public void changeRunway(String name, String inverseName, int tora, int width, int thresholdDistance){
        this.name = name;
        this.inverseName = inverseName;
        this.runwayLength = tora;
        this.runwayWidth = width;
        this.thresholdDistance = rescaleHorizontal(thresholdDistance);
    }

    protected static void outlineShape(Color c, Graphics g, Shape shape, Stroke stroke){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(c);
        g2d.setStroke(stroke);
        g2d.draw(shape);
        g2d.dispose();
    }

    public abstract void removeObstacle();

    public abstract void addObstacle(int width, int length, int height, int dFromT, int dFromCL);

    public Line2D getTodaLine() {
        return todaLine;
    }

    public Line2D getToraLine() {
        return toraLine;
    }

    public Line2D getLdaLine() {
        return ldaLine;
    }

    public Line2D getAsdaLine() {
        return asdaLine;
    }

    protected static void fillShape(Color c, Graphics g, Shape shape){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(c);
        g2d.fill(shape);
        g2d.dispose();
    }

}

