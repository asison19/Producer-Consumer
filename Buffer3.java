
//using atmoics
public class Buffer3 extends Buffer{
	static AtomicData[] data;
	
	public Buffer3(int max, int producerAmount, int produceAmount) {
		super(max, producerAmount, produceAmount, data);
	}
	
	public void read() {
		super.read();
	}
	
	public boolean write() {
		boolean r;
		r = super.write();
		return r;
	}
}
