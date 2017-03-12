package model;

import java.io.Serializable;

public final class Obstacle implements Serializable {

	/*
	Final class representation of an obstacle just used for data storage
	 */
	private static final long serialVersionUID = 1L;
	private int width;
	private int length;
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
	
	public Obstacle(String obstacleName, int obstacleHeight, int obstacleWidth, int obstacleLength) {
		this.name = obstacleName;
		this.height = obstacleHeight;
		this.width = obstacleWidth;
		this.length = obstacleLength;
	}

	public int getObstacleHeight() {
		return height;
	}
	
	public int getObstacleWidth() {
		return width;
	}
	
	public int getObstacleLength() {
		return length;
	}
	
	public String getName() {
		return name;
	}


}
