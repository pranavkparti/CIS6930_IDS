package project3;

public class CountMin extends AbstractCountMin{
	
	CountMin(int numHash, int numCounter){
		super(numHash, numCounter);
	}

	@Override
	boolean record(long flowID) {
		//for all k hashes/arrays
		for(int i = 0; i < hashGen.hashList.size(); i++) {
			int index = hashGen.hashList.get(i).hash(flowID);
			//check for overflow
			if(C[i][index] + 1 < 0)
				return false;
			C[i][index]++;
		}
		return true;
	}

	@Override
	int query(long flowID) {
		//check for minimum of all entries
		int minimum = Integer.MAX_VALUE;
		for(int i = 0; i < hashGen.hashList.size();i++) {
			int index = hashGen.hashList.get(i).hash(flowID);
			minimum = Math.min(minimum, C[i][index]);
		}
		return minimum;
	}
	
		

}
