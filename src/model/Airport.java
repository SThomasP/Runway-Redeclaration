package model;

import java.util.ArrayList;

public class Airport {
	/*
	 An airport, containing a list of runways and the currently active runway
	 */

	private ArrayList <Runway> listOfRunways;
	private Runway currentRunway;
	
	public Airport(Runway currentRunway){
		this.currentRunway = currentRunway;
	}

	public Runway getCurrentRunway(){
		return currentRunway;
	}

}
