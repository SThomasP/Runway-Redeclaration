package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import model.Airport;
import model.Obstacle;
import model.Runway;
import view.MainPageGUI;

/**
 * Created by steff on 01/03/2017.
 */
public class Controller {

	private Airport airport;
	private MainPageGUI gui;
	private ActionListener submitButtonPress;
	private ActionListener refreshButtonPress;
	private ActionListener addObstacleButtonPress;
	private File obstacleList;
	private ArrayList<Obstacle> listOfObstacles = new ArrayList<Obstacle>();

	public ActionListener getSubmitButtonPress() {
		return submitButtonPress;
	}

	public ActionListener getRefreshButtonPress() {
		return refreshButtonPress;
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

	public void outputFromList() {
		InputStream fis;
		String line;
		try {
			fis = new FileInputStream("obstacleList.txt");
			InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
			BufferedReader br = new BufferedReader(isr);
			while ((line = br.readLine()) != null) {
		    String t = line.split("\\s+")[0];
			String s =	line.split("\\s+")[1];
			System.out.println(t+ " " + s);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void writeObstacleList() {
		obstacleList = new File("obstacleList.txt");
		try {
			String name = gui.getObstacleName();
			int height = gui.getObstacleHeight();
			String type = gui.getObstacleType();
			BufferedWriter output = new BufferedWriter(new FileWriter(obstacleList, true));
			output.write(name + " " + height + " " + type);
			output.newLine();
			output.close();
			Obstacle o = new Obstacle(gui.getObstacleName(), gui.getObstacleHeight());
			listOfObstacles.add(o);
			 System.out.println(listOfObstacles);
			outputFromList();
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
				writeObstacleList();
			}

		};
		// create and init the GUI
		gui = new MainPageGUI();
		gui.init(this);
		String[] runways = { "09L", "27R", "09R", "27L" };
		gui.updateRunwayList(runways);
		// create the model with a single runway
		airport = new Airport(new Runway(9, 'L', 3902, 3900, 3902, 3595, 306));
		Runway currentRunway = airport.getCurrentRunway();
		// add the figures to the gui
		gui.setOriginalFigures(currentRunway.getToda(), currentRunway.getTora(), currentRunway.getLda(),
				currentRunway.getAsda());
	}

	public static void main(String[] args) {
		Controller c = new Controller();
	}
}
