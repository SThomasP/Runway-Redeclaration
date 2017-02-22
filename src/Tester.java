
public class Tester {

	public static void main(String[] args) {
		Runway r27 = new Runway(27,'R',3884,3962,3884,3595,0,0,0);
		Runway r9 = new Runway(9,'L',3902,3902,3902,3595,306,0,0);
		Obstacle truck = new Obstacle(12);
		Aircraft a1 = new Aircraft(300);
		ObstacleOnRunway or1 = new ObstacleOnRunway(truck,-50,0,r9,true);
		System.out.println(Calculator.calculate(or1, a1));
		ObstacleOnRunway or2 = new ObstacleOnRunway(truck,3646,0,r27,false);
		System.out.println(Calculator.calculate(or2, a1));

		
	}

}
