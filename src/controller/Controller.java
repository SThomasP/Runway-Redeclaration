package controller;

import model.Airport;
import model.Runway;
import view.MainPageGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by steff on 01/03/2017.
 */
public class Controller {


    private Airport airport;
    private MainPageGUI gui;
    private ActionListener submitButtonPress;




    public  ActionListener getSubmitButtonPress(){
        return submitButtonPress;
    }

    public Controller() {
        submitButtonPress = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runway inputRunway = airport.getCurrentRunway();
                inputRunway.addObstacle(gui.getNewObstacle());
                gui.setAdjustedFigures(inputRunway.getToda(),inputRunway.getTora(),inputRunway.getLda(),inputRunway.getAsda());
            }

        };
        gui = new MainPageGUI();
        gui.init(this);
        String[] runways = {"09L", "27R", "09R", "27L"};
        gui.updateRunwayList(runways);
        airport = new Airport(new Runway(9,'L',3902,3900,3902,3595,306));
        Runway currentRunway = airport.getCurrentRunway();
        gui.setOriginalFigures(currentRunway.getToda(),currentRunway.getTora(),currentRunway.getLda(),currentRunway.getAsda());
    }

    public static void main(String[] args){
        Controller  c = new Controller();
    }
}
