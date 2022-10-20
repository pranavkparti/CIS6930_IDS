package project3;

public class Counter extends AbstractCounter{
	//the actual count
	private int count;
	
	Counter(){
		count = 0;
	}

	@Override
	int get_count() {
		return count;
	}

	@Override
	boolean increment() {
		count++;
		return true;
	}
	
	
}
