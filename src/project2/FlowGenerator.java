package project2;

import java.util.Random;

//class to generate random flows
public class FlowGenerator {
	int numFlows;
	int[] flowList;
	
	FlowGenerator(int numFlows){this.numFlows = numFlows;}
	
	public void generate() {
		flowList = new int[numFlows];
		Random random = new Random();
		for(int i = 0; i < flowList.length; i++)
			flowList[i] = random.nextInt(Integer.MAX_VALUE);
	}
}
