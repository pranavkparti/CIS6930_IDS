package project3;

/**Interface representing a hash function*/
public interface HashFunction {
	public int hash(int flowID);
	
	public int hash(long flowID);
}
