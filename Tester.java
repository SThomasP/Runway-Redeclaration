
public class Tester {

	public static void main(String[] args) {
		Runway r27 = new Runway(27,'R',3884,3962,3884,3595,0);
		Runway r9 = new Runway(9,'L',3902,3900,3902,3595,306);
		Obstacle truck = new Obstacle(12);
		Aircraft a1 = new Aircraft(300);
		ObstacleOnRunway or1 = new ObstacleOnRunway(truck,50,0,r9,true);
		System.out.println(" Example calculations output ");
		System.out.println(" ");
		System.out.println("Test 1: (obstacle close to threshold)");
		System.out.println("Results: ");
		System.out.println(Calculator.calculate(or1, a1));
		System.out.println(" ");
		System.out.println(Calculator.working);
		/*
		if (Calculator.checkSub == 0){
			System.err.println("Calculations for substraction went wrong");	
		} 
		if (or1.closeToThreshold){
			if(Calculator.checkSub == a1.blastDistance){
				System.out.println("blast dictance is used in calculations");
			} else if (Calculator.checkSub == 300){
				System.out.println("RESA and strip end are used in calculations");
			} else {	System.err.println("calculations went wrong");
						System.err.println(Calculator.checkSub);
			}
		} else {
			int heightCheck = truck.height*50;
			if(Calculator.checkSub == 240){
				System.out.println("RESA is used for the calculations");
			} else if (Calculator.checkSub == heightCheck){
				System.out.println("");
			}
		}
		*/
		System.out.println(" ");
		System.out.println("Test 2: (obstacle is not close to threshold)");
		System.out.println("Results: ");
		ObstacleOnRunway or2 = new ObstacleOnRunway(truck,3646,0,r27,false);
		System.out.println(Calculator.calculate(or2, a1));
		System.out.println(" ");
		System.out.println(Calculator.working);
		
		MainPageGUI mpg = new MainPageGUI();
		mpg.init();
		

		
	}

}
