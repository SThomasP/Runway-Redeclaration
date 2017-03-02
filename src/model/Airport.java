package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Airport {
	private ArrayList <Runway> listOfRunways;
	private HashMap<String,Integer> commonObstacles;
	private Runway currentRunway;
	
	public Airport(Runway currentRunway){
		this.currentRunway = currentRunway;
	}

	public Runway getCurrentRunway(){
		return currentRunway;
	}

}
