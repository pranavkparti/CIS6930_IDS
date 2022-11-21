package project4;

import java.util.Random;

//class to generate random flows
public class SpreadGenerator {
	int numElements;
	int[] elementList;
	SpreadGenerator(int numElements){
		this.numElements = numElements;
		generate();
	}
	
	public void generate() {
		elementList = new int[numElements];
		//Generated elements are +ve
		Random random = new Random();
		for(int i = 0; i < elementList.length; i++)
			elementList[i] = random.nextInt(Integer.MAX_VALUE);
	}
}
