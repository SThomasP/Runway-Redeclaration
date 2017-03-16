package model;

import java.util.ArrayList;

public class Airport {
	/*
	 An airport, containing a list of runways and the currently active runway
	 */

	private ArrayList <Runway> listOfRunways;
	private Runway currentRunway;
	
	public Airport(ArrayList<Runway> runways){
		
		currentRunway = runways.get(0);
		listOfRunways = runways;
	}

	public Runway getCurrentRunway(){
		return currentRunway;
	}

	public ArrayList<Runway> getListOfRunways() {
		return listOfRunways;
	}

	public void setCurrentRunway(Runway currentRunway) {
		this.currentRunway = currentRunway;
	}

	

}
