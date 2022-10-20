package project2;

public class Test {
	public static void main(String[] args) {
		
		BloomFilter bf = new BloomFilter(10000, 7);
		String bfFile = "output/BloomFilter.txt";
		bf.demo(1000,bfFile);
		
		CountingBloomFilter cbf = new CountingBloomFilter(10000, 7);
		String cbfFile = "output/CountingBloomFilter.txt";
		cbf.demo(1000, 500, 500, cbfFile);
		
		CodedBloomFilter cobf = new CodedBloomFilter(7, 30000,7);
		String cobfFile = "output/CodedBloomFilter.txt";
		cobf.demo(1000, cobfFile);
	}
}
