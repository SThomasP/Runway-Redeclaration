package model;

public final class Obstacle {

 	public final int height;
	public final int distanceFromThreshold;
	public final int distanceFromCentreLine;
	
	
	public Obstacle(int height, int distanceFromCentreLine, int distanceFromThreshold) {
		this.height = height;
		this.distanceFromCentreLine = distanceFromCentreLine;
		this.distanceFromThreshold = distanceFromThreshold;
	}

}
