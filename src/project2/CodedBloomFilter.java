package project2;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class CodedBloomFilter {
	
	int numFilters;
	int filterSize;
	int numSets;
	int numHash;
	List<BloomFilter> filterList;
	
	CodedBloomFilter(int numSets, int filterSize, int numHash){
		this.numSets = numSets;
		numFilters = (int) (Math.log(this.numSets + 1) / Math.log(2));
		//initialize numFilters number of BloomFilters
		filterList = new ArrayList<>();
		for(int i = 0; i < numFilters; i++)
			filterList.add(new BloomFilter(filterSize, numHash));
	}
	
	public boolean query(int setNumber, int flowID) {
		//query the filters whose indices are 1 bits
		int index = 0;
		boolean flag = true;
		while(setNumber > 0) {
			//if the current bit is 1
			if((setNumber & 1) != 0) {
				flag &= filterList.get(index).query(flowID);
			}
			//move to next filter
			index++;
			setNumber = setNumber >> 1;
		}
		return flag;
	}
	
	public boolean encode(int setNumber, int flowID) {
		int index = 0;
		while(setNumber > 0) {
			//if the bit is 1
			if((setNumber & 1) != 0) {
				filterList.get(index).encode(flowID);
			}
			index++;
			setNumber = setNumber >> 1;
		}
		return true;
	}
	
	
	public void demo(int numFlows, String fileName) {
		//numFlows -> number of flows per set
		List<FlowGenerator> fgList = new ArrayList<>();
		//generate 7000 flows
		for(int i = 0; i < numSets; i++) {
			fgList.add(new FlowGenerator(numFlows));
			fgList.get(i).generate();
		}
		//encode all the elements into the filters
		for(int i = 0; i < fgList.size(); i++) {
			for(int flow : fgList.get(i).flowList)
				encode(i+1,flow);
		}
		//keep track of successful lookups
		int lookup = 0;
		//query all the elements
		for(int i = 0; i < fgList.size(); i++) {
			for(int flow : fgList.get(i).flowList) {
				if(query(i+1,flow)) lookup++;
			}
		}
		
		try {
			FileWriter fw = new FileWriter(fileName);
			fw.write(lookup + "\n");
			System.out.println(lookup);
			fw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
}
