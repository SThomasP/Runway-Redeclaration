
public class ObstacleOnRunway {

	Obstacle obstacle;
	int distanceFromThreshold;
	int distanceFromCentreLine;
	Runway runway;
	boolean closeToThreshold;
	
	public Obstacle getObstacle(){
		return obstacle;
	}
	
	public int getDistanceFromThreshold(){
		return distanceFromThreshold;
	}
	
	public int getDistanceFromCentreLine(){
		return distanceFromCentreLine;
	}
	
	public void setObstacle(Obstacle obstacle){
		this.obstacle = obstacle;
	}
	
	public void setDistanceFromThreshold(int distanceFromThreshold ){
		this.distanceFromThreshold = distanceFromThreshold;
	}
	
	public void setSistanceFromCentreLine(int distanceFromCentreLine){
		this.distanceFromCentreLine = distanceFromCentreLine;
	}

	public Runway getRunway() {
		return runway;
	}

	public void setRunway(Runway runway) {
		this.runway = runway;
	}
	
	
}
