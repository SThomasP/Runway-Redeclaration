package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.View;

import model.Airport;
import model.Obstacle;
import model.Runway;
import view.MainPageGUI;
import view.SideViewGUI;
import view.TopViewGUI;
import view.ViewGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by steff on 01/03/2017.
 */
public class Controller {

	private ReadObstacleXMLFile readnwrite = new ReadObstacleXMLFile();
	private ReadAirportXMLFile airportXML = new ReadAirportXMLFile();

	private Airport airport;
	private MainPageGUI gui;
	private ActionListener importAirport;
	private ActionListener exportAirport;
	private ActionListener submitButtonPress;
	private ActionListener refreshButtonPress;
	private ActionListener addObstacleButtonPress;
	private ActionListener chooseCurrentRunway;
	private ActionListener refreshMainButtonPress;
	private ActionListener changeRunwayUse;
	private File obstacleList;
	public ArrayList<Obstacle> listOfObstacles = new ArrayList<Obstacle>();

	public ActionListener getImportAirport() {
		return importAirport;
	}

	public ActionListener getExportAirport() {
		return exportAirport;
	}

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

	public ActionListener getAddObstacleButtonPress() {
		return addObstacleButtonPress;
	}

	public MainPageGUI getGui(){
		return gui;
	}

	public ArrayList<Obstacle> getList() {
		System.out.println(listOfObstacles);
		return listOfObstacles;
	}

	public ActionListener getChangeRunwayUse() {
		return changeRunwayUse;
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
		try {
			listOfObstacles = readnwrite.read();
			System.out.println(listOfObstacles.size());
			for (Obstacle e : listOfObstacles) {
				System.out.println(e.getName());

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void putObstacle() {
		try {
			outputFromList();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		gui.getObstacleBox().removeAllItems();
		gui.getViewObstaclesList().removeAllItems();
		for (Obstacle o : listOfObstacles) {
			gui.getObstacleBox().addItem(o.getName());
			gui.getViewObstaclesList().addItem(o.getName());
		}
	}

	public void writeObstacleList() {
		try {
			String name = gui.getObstacleName();
			int height = gui.getObstacleHeight();
			int width = gui.getObstacleWidth();
			int length = gui.getObstacleLength();
			Obstacle o = new Obstacle(name, height, width, length);
			listOfObstacles.add(o);
			System.out.println(listOfObstacles);
			readnwrite.write(listOfObstacles);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Controller() {
		// create an action listener for when the export button is pressed.
		exportAirport = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				airportXML.write(airport);
			}

		};

		importAirport = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				airport = airportXML.read();
				// needs to remove choose current runway or it will output a
				// null pointer exception
				// this is because a time the list will be empty
				gui.getRunways().removeActionListener(chooseCurrentRunway);
				gui.updateRunwayList(airport.getListOfRunways());
				gui.getRunways().addActionListener(chooseCurrentRunway);
				gui.updateGraphicRunway();
				Runway currentRunway = airport.getCurrentRunway();
				// add the figures to the gui
				gui.setOriginalFigures(currentRunway.getToda(), currentRunway.getTora(), currentRunway.getLda(),
						currentRunway.getAsda());
				gui.setAdjustedFigures(currentRunway.getToda(), currentRunway.getTora(), currentRunway.getLda(),
						currentRunway.getAsda());

			}

		};

		// create an action listener for when the submit button is pressed.
		submitButtonPress = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gui.addObstacleToViews();
				Runway inputRunway = airport.getCurrentRunway();
				inputRunway.addObstacle(gui.getNewObstacle());
				gui.setAdjustedFigures(inputRunway.getToda(), inputRunway.getTora(), inputRunway.getLda(),
						inputRunway.getAsda());

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

		changeRunwayUse = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox box = (JComboBox) e.getSource();
				gui.changeRunwayUse((String) box.getSelectedItem());
			}
		};

		refreshMainButtonPress = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				// STOPS IT FROM TRYING TO LOOK AT A EMPTY LIST
				if (gui.getObstacleNames().getItemCount() != 0) {
					for (Obstacle o : listOfObstacles) {
						if (gui.getObstacleBox().getSelectedItem().toString().equals(o.getName())) {
							gui.getHeightBox().setText(Integer.toString(o.getObstacleHeight()));
							gui.getWidthBox().setText(Integer.toString(o.getObstacleWidth()));
							gui.getLengthBox().setText(Integer.toString(o.getObstacleLength()));
						}
					}
				}
			}
		};

		// changes current runway and shows its values
		chooseCurrentRunway = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				airport.setCurrentRunway(gui.getSelectedRunway());
				gui.setOriginalFigures(airport.getCurrentRunway().getTodaOriginal(),
						airport.getCurrentRunway().getToraOriginal(), airport.getCurrentRunway().getLdaOriginal(),
						airport.getCurrentRunway().getAsdaOriginal());
				gui.setAdjustedFigures(airport.getCurrentRunway().getToda(), airport.getCurrentRunway().getTora(),
						airport.getCurrentRunway().getLda(), airport.getCurrentRunway().getAsda());
				gui.updateGraphicRunway();
			}
		};

		// create and init the GUI
		gui = new MainPageGUI();
		gui.init(this);
		putObstacle();

		gui.getRunwayViews().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gui.refreshViews();
			}


		});

		gui.getViewObstaclesList().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// stops null pointer execption
				if (gui.getViewObstaclesList().getItemCount() != 0) {
					gui.getDisplayObstacle().setText("");
					for (Obstacle o : listOfObstacles) {
						if (gui.getViewObstaclesList().getSelectedItem().toString().equals(o.getName())) {
							gui.getDisplayObstacle()
									.append("Name   " + o.getName() + "\t" + "Height:   " + o.getObstacleHeight() + "\t"
											+ "Width:   " + o.getObstacleWidth() + "\t" + "Length:   "
											+ o.getObstacleLength() + "\n");
						}
					}
				}
			}

		});


		// create the model with a single runway
		ArrayList<Runway> listOfRunways = new ArrayList<Runway>();
		listOfRunways.add(new Runway(9, 'L', 3902, 3900, 3902, 3595, 306, 50));
		listOfRunways.add(new Runway(27, 'R', 3902, 3900, 3902, 3902, 0, 50));

		airport = new Airport(listOfRunways);
		gui.updateRunwayList(airport.getListOfRunways());
		gui.updateGraphicRunway();
		Runway currentRunway = airport.getCurrentRunway();
		// add the figures to the gui
		gui.setOriginalFigures(currentRunway.getToda(), currentRunway.getTora(), currentRunway.getLda(),
				currentRunway.getAsda());
	}

	public static void main(String[] args) {
		Controller c = new Controller();
	}
}
