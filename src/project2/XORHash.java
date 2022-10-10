package project2;

public class XORHash implements HashFunction{
	int saltNum;
	int tableSize;
	
	XORHash(int saltNum, int tableSize){this.saltNum = saltNum;this.tableSize = tableSize;}
	
	@Override
	public int hash(int flowID) {
		return (flowID ^ saltNum) % tableSize;
	}

}
