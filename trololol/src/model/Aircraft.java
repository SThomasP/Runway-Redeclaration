package model;

public class Aircraft {
	/*
	Simple representation of the aircraft, containing it's blast distance.
	 */
	private int blastDistance;

	public int getBlastDistance(){
		return blastDistance;
	}
	
	public void setBlastDistance(int blastDistance){
		this.blastDistance = blastDistance;
	}

	public Aircraft(int blastDistance) {
		super();
		this.blastDistance = blastDistance;
	}
	
}
