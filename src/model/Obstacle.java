package model;

import java.io.Serializable;

public final class Obstacle implements Serializable {

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

	public int getObstacleHeight() {
		return height;
	}
	
	public String getName() {
		return name;
	}


}
