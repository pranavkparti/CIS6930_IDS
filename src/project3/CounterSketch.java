package project3;

import java.util.Arrays;

public class CounterSketch extends AbstractCountMin{

	CounterSketch(int numHash, int numCounter){
		super(numHash, numCounter);
	}
	
	public boolean record(long flowID) {
		//some flows recorded positively, others negatively
		for(int i = 0; i < hashGen.hashList.size(); i++) {
			//flowID ^ salt, can be +ve or -ve
			int xor = hashGen.hashList.get(i).getXOR(flowID);
			//index >= 0
			int index = Math.abs(xor % numCounter);
			//if MSB of xor is 1, record +vely, else -vely
			C[i][index] += ((xor >> 31 & 1) == 1) ? 1 : -1;
		}
		return true;
	}
	
	public int query(long flowID) {
		//array to store all queried values
		int[] queries = new int[hashGen.hashList.size()];
		for(int i = 0; i < hashGen.hashList.size();i++) {
			//flowID ^ salt, may be +ve or -ve
			int xor = hashGen.hashList.get(i).getXOR(flowID);
			int index = Math.abs(xor % numCounter);
			//estimate is always +ve
			queries[i] = Math.abs(C[i][index]);
		}
		//calculate median of queries
		Arrays.sort(queries);
		//if array length is odd, return mid element, else avergage of 2 middle elements
		int median = (numHash % 2 == 1) ? queries[numHash/2] : (queries[numHash/2] + queries[(numHash/2)+1])/2;
		return median;
	}
}
