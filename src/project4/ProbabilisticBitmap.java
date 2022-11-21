package project4;

public class ProbabilisticBitmap extends AbstractBitmap{
	//h1 for probabilistic sampling, h2 for recording into bitmap
	HashFunction h1, h2;
	int MAX_HASH = Integer.MAX_VALUE;
	double sProb;
	
	/**Constructor
	 * @param numBits	Length of the probabilistic bitmap
	 * @param sProb		Sampling probability for recording elements
	 * */
	public ProbabilisticBitmap(int numBits, double sProb) {
		super(numBits);
		this.sProb = sProb;
		h1 = new XORHash(numBits);
		h2 = new XORHash(numBits);
	}
	
	boolean record(int element) {
		//receiving an element, use h1 to do probabilistic sampling
		if((h1.getXOR(element)) < (MAX_HASH * sProb)) {
			int hash = h2.hash(element);
			if(bitmap[hash] == 0)
				bitmap[hash] = 1;
			return true;
		}
		//chosen not to record the element
		return false;
	}
	
	double query() {
		//u -> number of zeros in bitmap
		int u = zeros();
		//v -> fraction of zeros in the bitmap
		double v = (double)u / numBits;
		//n'_f = -(m/p) ln V
		double estimate = (-1) * (numBits/sProb) * Math.log(v);
		return estimate;
	}
}
