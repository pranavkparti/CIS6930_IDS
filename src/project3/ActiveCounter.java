package project3;

import java.io.FileWriter;
import java.util.Random;

public class ActiveCounter extends AbstractCounter{
	//number of bits for number part of counter
	private int numberSize;
	//number of bits for exponent part of counter
	private int exponentSize;
	//number part of counter
	private int number;
	//exponent part of counter
	private int exponent;
	
	ActiveCounter(int numberSize, int exponentSize){
		this.numberSize = numberSize;
		this.exponentSize = exponentSize;
		number = exponent = 0;
	}
	
	
	/**Function to calculate and return the value of the active counter*/
	@Override
	int get_count() {
		//c.n * 2^c.e
		return (int)(number * Math.pow(2, exponent));
	}
	
	/**Function to increment the active counter by 1*/
	@Override
	boolean increment() {
		//increments with a probability 1/2^exponent
		Random random = new Random();
		//simulate probability using nextInt() and bound 2^c.e
		if(random.nextInt((int) Math.pow(2, exponent)) == 0)
			number++;
		//if overflow occurs
		if(intToBinary(number).length() > numberSize) {
			//check if exponent will not get overflowed
			if(intToBinary(exponent + 1).length() > exponentSize)
				return false;
			//right shift number part
			number = number >> 1;
			//increment exponent part
			exponent++;
		}
		return true;
	}
	
	/**Demo function that performs <code>numIncrement</code> number of increments
	 * and outputs the result in the console and also writes to the file <code>fileName</code>*/
	public void demo(int numIncrement, String fileName) {
		for(int i =0; i < numIncrement; i++)
			this.increment();
		
		int finalCount = this.get_count();
		System.out.println(finalCount);
		
		try {
			FileWriter fw = new FileWriter(fileName);
			fw.write(finalCount + "\n");
			fw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}