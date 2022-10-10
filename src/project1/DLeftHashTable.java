package project1;

public class DLeftHashTable extends HashTable {
	int segmentSize;
	
	public DLeftHashTable(int tableSize, int numHash) {
		super(tableSize, numHash);
		segmentSize = tableSize / numHash;
		//we generate hash functions with respect to segment size
		hashGen = new HashGenerator(numHash, segmentSize);
		hashGen.generate();
	}
	
	public int query(int flowID) {
		int index, i = 0;
		for(HashFunction h : hashGen.hashList) {
			index = h.hash(flowID) + (segmentSize * i);
			if(table[index] != null && table[index].flowID == flowID)
				return index;
			i++;
		}
		return -1;
	}
	
	public boolean insert(int flowID) {
		int index,i=0;
		index = query(flowID);
		//if flowID already occupies an entry in the table, simply increment the counter 
		if(index != -1) {
			table[index].counter++;
			occupied++;
			return true;
		}
		//if flowID is not already entered into the table
		//search through the segments for an empty entry 
		for(HashFunction h : hashGen.hashList) {
			index = h.hash(flowID) + (segmentSize * i);
			if(table[index] == null) {
				table[index] = new Entry(flowID);
				table[index].counter++;
				occupied++;
				return true;
			}
			i++;
		}
		
		return false;
		
	}

}
