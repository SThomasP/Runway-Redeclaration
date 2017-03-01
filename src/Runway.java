
public class Runway {
	
	@Override
	public String toString() {
		return String.valueOf(orientation) + location;
	}

	int orientation;
	char location;
	int toda;
	int tora;
	int asda;
	int lda;
	int displacedThreshold;
	
	public int getToda(){
		return toda;
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

	public Runway(int orientation, char location, int toda, int tora, int asda, int lda, int displacedThreshold) {
		super();
		this.orientation = orientation;
		this.location = location;
		this.toda = toda;
		this.tora = tora;
		this.asda = asda;
		this.lda = lda;
		this.displacedThreshold = displacedThreshold;
	}



	
	
	
	
}
