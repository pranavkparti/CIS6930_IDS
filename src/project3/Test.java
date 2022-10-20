package project3;

public class Test {
	public static void main(String[] args) {
		//CountMin
		int numHash = 3;
		int numCounter = 3000;
		String inputFile = "project3input.txt";
		String CMFile = "CountMin.txt";
		CountMin cMin = new CountMin(numHash, numCounter);
		cMin.demo(inputFile, CMFile);
		
		
		
		
		
		//Active Counter
		int numberSize = 16;
		int exponentSize = 16;
		int numIncrement = 1000000;
		String ACFile = "ActiveCounter.txt"; 
		ActiveCounter ac = new ActiveCounter(numberSize, exponentSize);
		ac.demo(numIncrement, ACFile);
		
	
	}
	
	
}
