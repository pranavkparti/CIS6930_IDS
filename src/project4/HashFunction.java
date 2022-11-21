package project4;

/**Interface representing a hash function*/
public interface HashFunction {
	public int hash(int flowID);
	
	public int getXOR(int flowID);
}
