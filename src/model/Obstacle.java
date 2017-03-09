package model;

import java.util.ArrayList;

public final class Obstacle {

	/*
	Final class representation of an obstacle just used for data storage
	 */

 	public int height;
	public int distanceFromThreshold;
	public int distanceFromCentreLine;
	private String name;
	private String type;
	//ArrayList<Obstacle> listOfObstacles = new ArrayList<Obstacle>();
	
	public Obstacle(int height, int distanceFromCentreLine, int distanceFromThreshold) {
		this.height = height;
		this.distanceFromCentreLine = distanceFromCentreLine;
		this.distanceFromThreshold = distanceFromThreshold;
		
	}
	
	public Obstacle(String obstacleName, int obstacleHeight) {
		this.name = obstacleName;
		this.height = obstacleHeight;
	}



}
