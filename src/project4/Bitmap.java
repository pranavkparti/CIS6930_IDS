package project4;

import java.io.FileWriter;

public class Bitmap extends AbstractBitmap{
	HashFunction h;
	
	//Constructor
	Bitmap(int numBits){
		super(numBits);
		h = new XORHash(numBits);
	}
	
	boolean record(int element) {
		int hash = h.hash(element);
		if(bitmap[hash] == 0)
			bitmap[hash] = 1;
		return true;
	}
	
	double query() {
		//u -> number of zeros in bitmap
		int u = zeros();
		//v -> fraction of zeros in the bitmap
		double v = (double)u / numBits;
		//n'_f = -m ln V
		double estimate = (-1) * numBits * Math.log(v);
		//round estimate to 2 decimal places for convenience
		//return Math.round(estimate * 100.0) /100.0;
		return estimate;
	}
	

}
