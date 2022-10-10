package project2;

import java.io.FileWriter;

public class BloomFilter extends Filter{
	
	BloomFilter(int filterSize, int numHash){
		super(filterSize, numHash);
	}
	
	public boolean query(int flowID) {
		boolean flag = true;
		for(HashFunction h : hashGen.hashList) {
			flag &= (filter[h.hash(flowID)] == 1) ? true : false;
		}
		return flag;
	}
	
	public boolean encode(int flowID) {
		if(!query(flowID)) {
			for(HashFunction h : hashGen.hashList) {
				filter[h.hash(flowID)] = 1;
			}
		}
		return true;
	}
	
	
	public void demo(int numFlows, String fileName) {
		try {
			FileWriter f = new FileWriter(fileName);
			//Set A of flows
			FlowGenerator A = new FlowGenerator(numFlows);
			A.generate();
			
			//keep track of successful lookups
			int lookupA = 0;
			//encode the flows into the filter
			for(int flow : A.flowList)
				encode(flow);
			
			//lookup the flows and record successes
			for(int flow : A.flowList)
				if(query(flow)) lookupA++;
			
			System.out.println(lookupA);
			f.write(lookupA + "\n");
			
			//Set B of flows
			FlowGenerator B = new FlowGenerator(numFlows);
			B.generate();
			int lookupB = 0;
			//lookup the flows and record successes
			for(int flow : B.flowList)
				if(query(flow)) lookupB++;
			
			System.out.println(lookupB);
			f.write(lookupB + "\n");
			f.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
