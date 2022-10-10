package project1;

public class Test {
	public static void main(String[] args) {
		//initialize multi-hashing table and run demo
		MultiHashTable mht = new MultiHashTable(1000, 3);
		String mht_file = "mht.txt";
		mht.demo(1000, mht_file);
		//initialize cuckoo hashing table and run demo
		CuckooHashTable cht = new CuckooHashTable(1000, 3, 2);
		String cht_file = "cht.txt";
		cht.demo(1000, cht_file);
		System.out.println(cht.moveCount);
		//initialize D-Left hashing table and run demo
		DLeftHashTable dlht = new DLeftHashTable(1000, 4);
		String dlht_file = "dlht.txt";
		dlht.demo(1000, dlht_file);
	}
}
