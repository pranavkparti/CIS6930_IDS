package project3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HashGenerator {
	//number of hashes -> k
	int numHash;
	//table size -> m
	int tableSize;
	
	List<HashFunction> hashList;
	

	public HashGenerator(int numHash, int tableSize) {this.numHash = numHash;this.tableSize = tableSize;}
	
	public void generate(){
		hashList = new ArrayList<>();
		Random random = new Random();
		for(int i = 0; i < numHash;  i++) {
			hashList.add(new XORHash(random.nextInt(Integer.MAX_VALUE), tableSize));
		}
		
	}

}
