import javax.swing.*;

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


}

