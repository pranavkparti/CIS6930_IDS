package project2;

import java.io.FileWriter;

public class CountingBloomFilter extends Filter{
	
	public CountingBloomFilter(int filterSize, int numHash) {
		super(filterSize, numHash);
		
	}
	
	public boolean query(int flowID) {
		boolean flag = true;
		for(HashFunction h : hashGen.hashList)
			flag &= (filter[h.hash(flowID)] > 0) ? true : false;
		return flag;
	}
	
	public boolean encode(int flowID) {
		for(HashFunction h : hashGen.hashList) 
			filter[h.hash(flowID)] ++;
		return true;
	}
	
	public boolean remove(int flowID) {
		if(!query(flowID))
			return false;
		for(HashFunction h: hashGen.hashList)
			filter[h.hash(flowID)] --;
		return true;
		
	}
	
	public void demo(int numFlows, int numRemoved, int numAdded, String fileName) {
		try {
			FileWriter fw = new FileWriter(fileName);
			
			//Initially added flows : Set A
			FlowGenerator A = new FlowGenerator(numFlows);
			A.generate();
			
			for(int flow: A.flowList)
				encode(flow);
			
			//remove first numRemoved number of elements from filter
			for(int i = 0; i < numRemoved; i++)
				remove(A.flowList[i]);
			//add numAdded number of elements to the filter
			FlowGenerator B = new FlowGenerator(numAdded);
			B.generate();
			
			for(int flow : B.flowList)
				encode(flow);
			
			//lookup all original A elements
			int lookupA = 0;
			for(int flow : A.flowList)
				if(query(flow)) lookupA++;
			
			System.out.println(lookupA);
			fw.write(lookupA + "\n");
			fw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
