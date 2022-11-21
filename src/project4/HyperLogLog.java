package project4;

import java.io.FileWriter;
import java.util.Arrays;

public class HyperLogLog {
	//number of registers
	int m;
	//the HLL array of registers, assuming each register is 5-bits (range 0 - 31)
	int[] B;
	//constant alpha
	double alpha;
	//hash function to hash to get register and also to get geometric hash
	HashFunction h;
	
	//Constructor
	HyperLogLog(int m){
		this.m = m;
		B = new int[m];
		alpha = (0.7213)/(1 + 1.079 / m);
		h = new XORHash(m);
	}
	
	boolean record(int element) {
		//first hash to a register
		int index = h.hash(element);
		//for the register B[H(e)]
		//geometric hash of e
		int gHash = geoHash(element) + 1;
		//update B[H(e)]
		B[index] = Math.max(B[index], gHash);
		return true;
	}
	
	double query() {
		//harmonic averaging of all register values
		double hSum = 0;
		for(int i = 0; i < B.length; i++)
			hSum += 1 / Math.pow(2, B[i]);
		double estimate = alpha * m * m * (1 / hSum);
		return estimate;
	}
	
	int geoHash(int element) {
		//do uniform hash
		//elements are +ve
		int uniformHash = Math.abs(h.getXOR(element));
		//get the number of leading zeros
		int numLZ  = Integer.numberOfLeadingZeros(uniformHash) -1 ;
		return numLZ;
	}
	
	void demo(String outputFile, int low, int high) {
		try {
			
			FileWriter fw = new FileWriter(outputFile);
			
			//since flow spreads differ by order of magnitude 10
			for(int i = low; i <= high; i *= 10) {
				//for each flow, generate unique spread
				SpreadGenerator sg = new SpreadGenerator(i);
			
				//record each element of spread into the HLL sketch
				for(int element : sg.elementList)
					record(element);
				//get the estimate of the spread
				double estimate = query();
				//write the result into file
//				fw.write(i + "\t" + String.format("%.2f",estimate) + "\n");
				//round to two decimal places
				System.out.println(i +"\t" + String.format("%.2f",estimate));
				fw.write(i + "\t\t" + String.format("%.2f",estimate) + "\n");
				//reset the HLL for new flow spread
				Arrays.fill(B, 0);
			}
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
