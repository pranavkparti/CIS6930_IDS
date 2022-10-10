package project1;

public class CuckooHashTable extends HashTable{
	//number of cuckoo steps s
	int cuckooSteps;
	//counter to track number of move steps performed
	int moveCount;
	
	public CuckooHashTable(int tableSize, int numHash, int cuckooSteps) {
		super(tableSize, numHash);
		hashGen = new HashGenerator(this.numHash, this.tableSize);
		hashGen.generate();
		this.cuckooSteps = cuckooSteps;
		this.moveCount = 0;
	}
	
	public int query(int flowID) {
		int index;
		for(HashFunction h : hashGen.hashList) {
			index = h.hash(flowID); 
			if(table[index] != null && table[index].flowID == flowID)
				return index;
		}
		return -1;
	}
	
	public boolean insert(int flowID) {
		int index = query(flowID);
		//if flowID is already hashed to table, simply increment it
		if(index != -1) {
			table[index].counter++;
			return true;
		}
		//if flowID is not hashed to the table
		//if a null entry is available, insert flowID into that entry
		//and make counter = 1
		for(HashFunction h: hashGen.hashList) {
			index = h.hash(flowID);
			if(table[index] == null) {
				table[index] = new Entry(flowID);
				table[index].counter++;
				occupied++;
				return true;
			}
		}
		//if flowID is not in table, and no null entry is available
		//do move step (as needed) on the k occupied entries that flowID hashed to 
		for(HashFunction h : hashGen.hashList) {
			index = h.hash(flowID);
			if(move(index, cuckooSteps)) {
				//if move is successful, the entry becomes null, so initialize Entry object
				table[index] = new Entry(flowID);
				table[index].counter++;
				occupied++;
				return true;
			}
		}
		//if flowID is not in table, no empty entry is available
		//and if moving occupied entries is also not possible, we ignore the flow
		return false;
	}
	
	/**Recursive function to move an occupied entry to an empty entry. Performed for {@code cuckooStep} number of steps.
	 * @param index Index of occupied entry to be moved
	 * @param cuckooSteps Number of cuckoo steps to perform*/
	public boolean move(int index, int cuckooSteps) {
		//increment moveCount each time a move operation is performed
		moveCount++;
		//flowID to be moved
		int flowID = table[index].flowID;
		int hindex;
		//if an empty entry is found where flowID can hash to, we move flowID to that entry
		for(HashFunction h : hashGen.hashList) {
			hindex = h.hash(flowID);
			if(hindex != index && table[hindex] == null) {
				//move entry at index to hindex
				table[hindex] = new Entry(flowID);
				table[hindex].counter = table[index].counter;
				//empty original entry after moving
				table[index] = null;
				return true;
			}
		}
		//if no other empty entries for flowID are found, we need to perform move again
		//recursively as many times as needed (or until cuckooSteps = 1)
		if(cuckooSteps > 1) {
			//we perform move for (at most) each and every occupied entry to which flowID hashes to
			for(HashFunction h : hashGen.hashList) {
				hindex = h.hash(flowID);
				if(hindex != index && move(hindex, cuckooSteps - 1)) {
					table[hindex] = new Entry(flowID);
					table[hindex].counter = table[index].counter;
					table[index] = null;
					return true;
				}
			}
		}
		//if move is simply not possible
		return false;
		
	}

}
