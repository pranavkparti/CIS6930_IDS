package project3;

public class Flow {
	String flowID;
	int numPackets;
	int estimate;
	
	Flow(String flowID, int numPackets){
		this.flowID = flowID;
		this.numPackets = numPackets;
		estimate = 0;
	}
}
