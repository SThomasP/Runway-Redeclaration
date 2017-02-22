
public class Runway {
	
	int orientation;
	char location;
	int toda;
	int tora;
	int asda;
	int lda;
	int displacedThreshold;
	int stopway;
	int clearway;
	
	
	public int getToda(){
		return toda;
	}
	
	public int getStopway() {
		return stopway;
	}

	public void setStopway(int stopway) {
		this.stopway = stopway;
	}

	public int getClearway() {
		return clearway;
	}

	public void setClearway(int clearway) {
		this.clearway = clearway;
	}

	public void setDisplacedThreshold(int displacedThreshold) {
		this.displacedThreshold = displacedThreshold;
	}

	public int getTora(){
		return tora;
	}
	
	public int getAsda(){
		return asda;
	}
	
	public int getLda(){
		return lda;
	}
	
	public char getLocation(){
		return location;
	}
	
	public void setToda(int toda){
		this.toda = toda;
	}
	
	public void setTora(int tora){
		this.tora = tora;
	}
	
	public void setAsda(int asda){
		this.asda = asda;
	}
	public void setLda(int lda){
		this.lda = lda;
	}
	
	public void setLocation(char location){
		this.location = location;
	}

	public int getDisplacedThreshold() {
		return displacedThreshold;
	}

	public Runway(int orientation, char location, int toda, int tora, int asda, int lda, int displacedThreshold,
			int stopway, int clearway) {
		super();
		this.orientation = orientation;
		this.location = location;
		this.toda = toda;
		this.tora = tora;
		this.asda = asda;
		this.lda = lda;
		this.displacedThreshold = displacedThreshold;
		this.stopway = stopway;
		this.clearway = clearway;
	}



	
	
	
	
}
