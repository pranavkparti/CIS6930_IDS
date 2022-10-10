package project1;

import java.util.ArrayList;
import java.util.List;

//Multi-hashing Table implementation
public class MultiHashTable extends HashTable {
	
	/**Constructor to initialize multihash table
	 *@param tableSize Size of the hash table m
	 *@param numHash Number of hashes k */
	MultiHashTable(int tableSize, int numHash){
		super(tableSize, numHash);
		hashGen = new HashGenerator(this.numHash, this.tableSize);
		hashGen.generate();
	}
	
	/**Function to query if a flow is present in the table or not
	 * @param flowID The flow ID to be queried
	 * @return index of flowID in table if it exists, else -1*/
	public int query(int flowID) {
		//get the list of k indices that flowID hashes to
		List<Integer> indices = hashAll(flowID); 
		//if the flowID is present in any one of the k entries, return index of flowID
		for(Integer index : indices) {
			if(table[index] != null && table[index].flowID == flowID)
				return index;
		}
		return -1;
	}
	
	/**Function to insert a flow into the table
	 * @param flowID The flow ID to be inserted
	 * @return true if flow is inserted, false otherwise*/
	public boolean insert(int flowID) {
		//list of the k indices flowID hashes to
		List<Integer> indices = hashAll(flowID);
		
		//if flowID is already present in the hash table, increment the counter
		for(Integer index : indices) {
			
			
			if(table[index] != null && table[index].flowID == flowID) {
				table[index].counter++;
				return true;
			}
		}
		//flowID is not present in the hash table, so we
		//check if there is space for the flowID to be entered
		for(Integer index : indices) {
			if(table[index] == null) {
				table[index] = new Entry(flowID);
				table[index].flowID = flowID;
				table[index].counter = 1;
				occupied++;
				return true;
			}
		}
		//the flowID is not already present or is unable to be entered into the table
		return false;
	}

	/**Function to get the list of k indices a flowID hashes to
	 * @param flowID The flow ID to be hashed into the table
	 * @return The list of indices in the hash table the flow ID hashed to*/
	public List<Integer> hashAll(int flowID){
		List<Integer> indices = new ArrayList<>();
		//for each hash function generated by the HashGenerator instance
		for(HashFunction h : hashGen.hashList) {
			indices.add(h.hash(flowID));
		}
		return indices;
	}
	
	
	
}
