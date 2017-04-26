package view;


import javax.swing.*;
import java.awt.*;

import static junit.framework.TestCase.assertEquals;

public class TopViewGUITest {

    public TopViewGUI createGUI(){
        JFrame frame = new JFrame();
        TopViewGUI tvg = new TopViewGUI();
        frame.add(tvg);
        tvg.init();
        frame.setVisible(true);
        tvg.changeRunway(new String[] {"09","L"}, new String[] {"27","R"},3902,50,306);
        tvg.redrawView();
        return tvg;
    }
/*
    @org.junit.Test
    public void smallObstacle(){
        TopViewGUI tvg = createGUI();
        tvg.addObstacle(6,72,8,20,5);
        Polygon x =  tvg.getObstacleRec();
        assertEquals(10.0,x.getWidth());
        assertEquals(10.0,x.getHeight());

    }

    @org.junit.Test
    public void largeObstacle(){
        TopViewGUI tvg = createGUI();
        tvg.addObstacle(6,72,8,20,5);
        Rectangle x =  tvg.getObstacleRec();
        assertEquals(10.0,x.getWidth());
        assertEquals(10.0,x.getHeight());

    }
    */
}
