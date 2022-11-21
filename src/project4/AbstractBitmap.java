package project4;

import java.io.FileWriter;
import java.util.Arrays;

public abstract class AbstractBitmap {
	int numBits;
	int[] bitmap;
	
	//Constructor
	AbstractBitmap(int numBits){
		this.numBits = numBits;
		bitmap = new int[this.numBits];
	}
	
	abstract boolean record(int element);
	
	abstract double query();
	
	/**Function to return the number of zeros in the bitmap
	 * @return number of zeros in bitmap
	 *  */
	int zeros() {
		int count = 0;
		for(int i = 0; i < bitmap.length; i++)
			if(bitmap[i] == 0) count++;
		return count;
	}
	
	void resetBitmap() {
		Arrays.fill(bitmap, 0);
	}
	
	void demo(String outputFile, int low, int high) {
		try {
			
			FileWriter fw = new FileWriter(outputFile);
			
			//since flow spreads differ by order of magnitude 10
			for(int i = low; i <= high; i *= 10) {
				//for each flow, generate unique spread
				SpreadGenerator sg = new SpreadGenerator(i);
			
				//record each element of spread into the bitmap
				for(int element : sg.elementList)
					record(element);
				//get the estimate of the spread
				double estimate = query();
				//write the result into file
//				fw.write(i + "\t" + String.format("%.2f",estimate) + "\n");
				//round to two decimal places
				System.out.println(i +"\t" + String.format("%.2f",estimate));
				fw.write(i + "\t\t" + String.format("%.2f",estimate) + "\n");
				//reset the bitmap for new flow spread
				resetBitmap();
			}
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
