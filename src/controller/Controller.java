package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Airport;
import model.Obstacle;
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
	private ActionListener addObstacleButtonPress;
	private ActionListener chooseCurrentRunway;
	private ActionListener refreshMainButtonPress;
	private File obstacleList;
	private ArrayList<Obstacle> listOfObstacles = new ArrayList<Obstacle>();

	public ActionListener getRefreshMainButtonPress() {
		return refreshMainButtonPress;
	}

	public ActionListener getSubmitButtonPress() {
		return submitButtonPress;
	}

	public ActionListener getRefreshButtonPress() {
		return refreshButtonPress;
	}


    public ActionListener getChooseCurrentRunway() {
		return chooseCurrentRunway;
	}


	public ActionListener getSubmitButtonPress() {
        return submitButtonPress;
    }
    
	public ActionListener getAddObstacleButtonPress() {
		return addObstacleButtonPress;
	}

	public ArrayList<Obstacle> getList() {
		System.out.println(listOfObstacles);
		return listOfObstacles;
	}

	public boolean checkFileExists() {
		if (!obstacleList.exists()) {
			try {
				obstacleList.createNewFile();
				return false;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public void outputFromList() throws ClassNotFoundException {
		FileInputStream fis;
		// if (checkFileExists())
		try {
			fis = new FileInputStream("obstacleList.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			listOfObstacles = (ArrayList<Obstacle>) ois.readObject();
			System.out.println("Read file");
			for (Obstacle e : listOfObstacles) {
				System.out.println(e.getName());
			}
			ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void putObstacle() {
		gui.getObstacleBox().removeAllItems();
		for (Obstacle o : listOfObstacles) {
			gui.getObstacleBox().addItem(o.getName());
		}
	}

	
	public void writeObstacleList() {
		File obstacleFile = new File("obstacleList.txt");
		try {

			String name = gui.getObstacleName();
			int height = gui.getObstacleHeight();

			FileOutputStream fos = new FileOutputStream(obstacleFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			Obstacle o = new Obstacle(name, height);
			listOfObstacles.add(o);
			System.out.println("Added " + name);
			oos.writeObject(listOfObstacles);
			oos.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		
		addObstacleButtonPress = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int yesNoButton = JOptionPane.YES_NO_OPTION;
				JOptionPane.showConfirmDialog(null, "Would you like to add this obstacle?", "Confirm", yesNoButton);
				if (yesNoButton == JOptionPane.YES_OPTION) {
					writeObstacleList();
					putObstacle();
					JOptionPane.showMessageDialog(gui, "Obstacle Added");
				}

			}
		};

		refreshMainButtonPress = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					outputFromList();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			//	putObstacle();
				System.out.println(gui.getObstacleBox().getSelectedItem().toString());
				for (Obstacle o : listOfObstacles) {
					
					if (gui.getObstacleBox().getSelectedItem().toString().equals(o.getName())) {
						gui.getHeightBox().setText(Integer.toString(o.getObstacleHeight()));
					}
					System.out.println(listOfObstacles.size());
				}
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
