package project4;

public class Test {
	public static void main(String[] args) {
		//CountMin
//		int numHash = 3;
//		int numCounter = 3000;
//		String inputFile = "project3input.txt";
//		String CMFile = "CountMin.txt";
//		CountMin cMin = new CountMin(numHash, numCounter);
//		cMin.demo(inputFile, CMFile);
//		
//		//CounterSketch
//		numHash = 3;
//		numCounter = 3000;
//		inputFile = "project3input.txt";
//		String CSFile = "CounterSketch.txt";
//		CounterSketch cSketch = new CounterSketch(numHash, numCounter);
//		cSketch.demo(inputFile, CSFile);
//		
//		
//		//Active Counter
//		int numberSize = 16;
//		int exponentSize = 16;
//		int numIncrement = 1000000;
//		String ACFile = "ActiveCounter.txt"; 
//		ActiveCounter ac = new ActiveCounter(numberSize, exponentSize);
//		ac.demo(numIncrement, ACFile);
		
		//Bitmap
		Bitmap bmp = new Bitmap(10000);
		String BMFile = "Bitmap.txt";
		int low = 100;
		int high = 1000000;
		bmp.demo(BMFile, low, high);
		
		//Probabilistic Bitmap
		ProbabilisticBitmap pbmp = new ProbabilisticBitmap(10000, 0.1);
		String PBMFile = "ProbabilisticBitmap.txt";
		pbmp.demo(PBMFile, low, high);
		
		//HyperLogLog Sketch
		int numRegisters = 256;
		low = 1000;
		HyperLogLog hll = new HyperLogLog(numRegisters);
		String HLLFile = "HyperLogLog.txt";
		hll.demo(HLLFile, low, high);
	
	}
	
	
}
