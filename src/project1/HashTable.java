package project1;

import java.io.FileWriter;

public abstract class HashTable {
		//table size -> m
		int tableSize;
		//number of hash functions -> k
		int numHash;
		//the actual hash table 
		Entry[] table;
		//each instance of the table uses a single set of hash functions
		HashGenerator hashGen;
		//number of occupied entries in the table
		int occupied;
		
		//constructor to initialize the (abstract) hash table
		/**Constructor for hash table abstract class 
		 * @param tableSize Size of the hash table m
		 * @param numHash Number of hash functions k*/
		HashTable(int tableSize, int numHash){
			this.tableSize = tableSize; 
			this.numHash = numHash;
			table = new Entry[this.tableSize];
			this.occupied = 0;
		}
		
		/**Abstract function to be implemented by a different class. 
		 * Queries for a specific flow ID in the hash table
		 * @param flowID Flow ID to be queried for in the table*/
		abstract int query(int flowID);
		
		/**Abstract function to be implemented by a different class.
		 * Inserts a flowID into the table, returns success as a boolean
		 * @param flowID Flow ID to be inserted into the table*/
		abstract boolean insert(int flowID);
		
		
		/**Function to print the entries in the table
		 * @param fileName The name of the file to print all table entries in*/
		public void printEntries(String fileName) {
			try {
				FileWriter fw = new FileWriter(fileName);
				fw.write(this.occupied +"\n");
				fw.write("\nTABLE ENTRIES\n");
				for(int i = 0;i < table.length;i++) {
					System.out.print(i + " -> ");
					if(table[i] == null) {
						System.out.println(0);
						fw.write(0 + "\n");
					}
					else
					{
						System.out.println(table[i].flowID);
						fw.write(table[i].flowID +"\n");
					}	
				}
				fw.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/**Function to execute demo of the hash table
		 *@param numFlow Number of flows
		 *@param fileName File to store result in 
		 * */
		public void demo(int numFlow, String fileName) {
			FlowGenerator fg = new FlowGenerator(numFlow);
			fg.generate();
			
			for(int flow : fg.flowList) {
				this.insert(flow);
			}
			
			this.printEntries(fileName);
			
		}
}
