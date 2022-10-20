package project3;

import java.util.Arrays;

public abstract class AbstractCountMin {
	//the array of array of counters
	int[][] C;
	//number of hashes/arrays k
	private int numHash;
	//number of counters per array w
	private int numCounter;
	//hash generator for list of hashes
	HashGenerator hashGen;
	
	AbstractCountMin(int numHash, int numCounter){
		this.numHash = numHash;
		this.numCounter = numCounter;
		C = new int[this.numHash][this.numCounter];
		//fill C with all zeros
		for(int[] row : C)
			Arrays.fill(row, 0);
		//generate list of hashes
		hashGen = new HashGenerator(numHash, numCounter);
		hashGen.generate();
	}
	
	abstract boolean record(long flowID);
	
	abstract int query(long flowID); 
	
	public long ipToInt(String ip) {
		return Long.valueOf(ip.replaceAll("[.]", ""));
	}
	
	
}
