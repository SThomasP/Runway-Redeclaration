import javax.swing.*;
import java.awt.*;

/**
 * Created by steff on 21/02/2017.
 */
public abstract class ViewGUI extends JPanel {

    public Runway getRunway() {
        return runway;
    }

    public void setRunway(Runway runway) {
        this.runway = runway;
    }

    protected Runway runway;

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

