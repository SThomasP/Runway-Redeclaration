package controller;

import model.Airport;
import model.Runway;
import view.MainPageGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by steff on 01/03/2017.
 */
public class Controller {


    private Airport airport;
    private MainPageGUI gui;
    private ActionListener submitButtonPress;
    private ActionListener refreshButtonPress;
    private ActionListener chooseCurrentRunway;



    public ActionListener getChooseCurrentRunway() {
		return chooseCurrentRunway;
	}


	public ActionListener getSubmitButtonPress() {
        return submitButtonPress;
    }
    

    public ActionListener getRefreshButtonPress() {
		return refreshButtonPress;
	}


	public Controller() {
        // create an action listener for when the submit button is pressed.
        submitButtonPress = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runway inputRunway = airport.getCurrentRunway();
                inputRunway.addObstacle(gui.getNewObstacle());
                gui.setAdjustedFigures(inputRunway.getToda(), inputRunway.getTora(), inputRunway.getLda(), inputRunway.getAsda());
                
            }

        };
        refreshButtonPress = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.setTodaWorking(Runway.todaworking);
                gui.setToraWorking(Runway.toraworking);
                gui.setAsdaWorking(Runway.asdaworking);
                gui.setLdaWorking(Runway.ldaworking);
            }

        };
        //changes current runway and shows its values
        chooseCurrentRunway = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                airport.setCurrentRunway(gui.getSelectedRunway());
                gui.setOriginalFigures(airport.getCurrentRunway().getTodaOriginal(), airport.getCurrentRunway().getToraOriginal(), airport.getCurrentRunway().getLdaOriginal(), airport.getCurrentRunway().getAsdaOriginal());
                gui.setAdjustedFigures(airport.getCurrentRunway().getToda(), airport.getCurrentRunway().getTora(), airport.getCurrentRunway().getLda(), airport.getCurrentRunway().getAsda());            }

        };
        //create and init the GUI
        gui = new MainPageGUI();
        gui.init(this);
        // create the model with a single runway
        ArrayList<Runway> listOfRunways = new ArrayList<Runway>();
        listOfRunways.add(new Runway(9, 'L', 3902, 3900, 3902, 3595, 306));
        listOfRunways.add(new Runway(27, 'R', 3902, 3900, 3902, 3902, 0));
        airport = new Airport(listOfRunways);
        gui.updateRunwayList(airport.getListOfRunways());
        
        
        Runway currentRunway = airport.getCurrentRunway();
        //add the figures to the gui
        gui.setOriginalFigures(currentRunway.getToda(), currentRunway.getTora(), currentRunway.getLda(), currentRunway.getAsda());
    }

    public static void main(String[] args) {
        Controller c = new Controller();
    }
}
