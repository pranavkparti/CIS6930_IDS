package project3;

public abstract class AbstractCounter {
	
	abstract int get_count();
	
	abstract boolean increment();
	
	/**Helper function to convert an integer into binary, represented as a String
	 * @param n Integer number to convert to binary
	 * @return String representation of the binary
	 * */
	public String intToBinary(int n) {
		String res = "";
		while(n > 0) {
			res += (n % 2 == 0) ? "0" : "1";
			n /= 2;
		}
		return res;
	}
	
}
