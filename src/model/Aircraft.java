package model;

public class Aircraft {
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
