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
		
		//CounterSketch
		numHash = 3;
		numCounter = 3000;
		inputFile = "project3input.txt";
		String CSFile = "CounterSketch.txt";
		CounterSketch cSketch = new CounterSketch(numHash, numCounter);
		cSketch.demo(inputFile, CSFile);
		
		
		//Active Counter
		int numberSize = 16;
		int exponentSize = 16;
		int numIncrement = 1000000;
		String ACFile = "ActiveCounter.txt"; 
		ActiveCounter ac = new ActiveCounter(numberSize, exponentSize);
		ac.demo(numIncrement, ACFile);
		
//		long x = 4294967295l;
//		System.out.println(Long.toBinaryString(x));
//		
	
	}
	
	
}
