package project3;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;

public abstract class AbstractCountMin {
	//the array of array of counters
	int[][] C;
	//number of hashes/arrays k
	int numHash;
	//number of counters per array w
	int numCounter;
	//hash generator for list of hashes
	HashGenerator hashGen;
	
	AbstractCountMin(int numHash, int numCounter){
		this.numHash = numHash;
		this.numCounter = numCounter;
		C = new int[this.numHash][this.numCounter];
		//fill C with all zeros
		for(int[] row : C)
			Arrays.fill(row, 0);
		//generate list of hashes
		hashGen = new HashGenerator(numHash, numCounter);
		hashGen.generate();
	}
	
	abstract boolean record(long flowID);
	
	abstract int query(long flowID); 
	
	public long ipToInt(String ip) {
		return Long.valueOf(ip.replaceAll("[.]", ""));
	}
	
	
	public void demo(String inputFile, String fileName) {
		try {
			//Scanner to read through the given input file
			Scanner inputReader = new Scanner(new File(inputFile));
			//FileWriter to write to output file
			FileWriter fw = new FileWriter(fileName);
			//make sure input file is valid
			if(!inputReader.hasNextLine()) {
				System.out.println("Input file is empty");
				System.exit(-1);
			}
			//first line of input file is number of flows
			int numFlows = Integer.valueOf(inputReader.nextLine());
			//we use an array to store the  flows
			Flow[] flowList = new Flow[numFlows];
			//the rest of the lines are flows and number of packets
			for(int i = 0; i < numFlows; i++) {
				//split input line into IP address and number of packets
				String[] tokens = inputReader.nextLine().split("\\s+");
				//store IP and number of packets into array
				flowList[i] = new Flow(tokens[0], Integer.valueOf(tokens[1]));
			}
			//keep track of average error
			int avgError = 0;
			//record the flows into countmin
			for(Flow f : flowList) {
				//ipToInt() converts String IP address (flowID) into long 
				long ip = ipToInt(f.flowID);
				//record the flow numPackets number of times
				for(int i = 0; i < f.numPackets; i++)
					record(ip);
			}
			//query and calculate error for the flows
			for(Flow f : flowList) {
				//query the flowID to get estimate
				f.estimate = query(ipToInt(f.flowID));
				//calculate error
				avgError += Math.abs(f.estimate - f.numPackets);
			}
			
			avgError /= numFlows;
			
			//sort the flows according to estimate
			Arrays.sort(flowList, (a,b) -> Integer.compare(-a.estimate, -b.estimate));
			
			//write avgError as first line of output file
			fw.write(avgError + "\n");
			System.out.println(avgError);
			//top 100 flows
			for(int i = 0; i < 100; i++)
				fw.write(flowList[i].flowID + "\t" + 
						 flowList[i].estimate + "\t" + 
						flowList[i].numPackets + "\n");	
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
}
