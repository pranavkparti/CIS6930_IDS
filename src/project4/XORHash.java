package project4;

import java.util.Random;

public class XORHash implements HashFunction{
	int saltNum;
	int tableSize;
	
	XORHash(int tableSize){
		Random random = new Random();
		int MAX = Integer.MAX_VALUE;
		int MIN = -Integer.MAX_VALUE;
		//generate +ve or -ve value
//		this.saltNum = (int) ( (long) MIN + Math.random() * ((long)MAX - MIN + 1));
		this.saltNum = random.nextInt(Integer.MAX_VALUE);
		this.tableSize = tableSize;
		}
	
	@Override
	public int hash(int flowID) {
		return Math.abs(flowID ^ saltNum) % tableSize;
	}
	
	@Override
	public int getXOR(int flowID) {
		return (flowID ^ saltNum);
	}

}
