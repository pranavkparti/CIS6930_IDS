package project2;

public abstract class Filter {
	
	int filterSize;
	int numHash;
	HashGenerator hashGen;
	int[] filter;
	
	Filter(int filterSize, int numHash){
		this.filterSize = filterSize;
		this.numHash = numHash;
		filter = new int[this.filterSize];
		for(int i = 0; i < filter.length; i++)
			filter[i] = 0;
		hashGen = new HashGenerator(numHash, filterSize);
		hashGen.generate();
	}
	
	abstract boolean query(int flowID);
	
	abstract boolean encode(int flowID);
	
	
	

}
