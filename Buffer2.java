//using isolated sections
public class Buffer2 extends Buffer{
	
	static Data[] data;
	
	public Buffer2(int max, int producerAmount, int produceAmount) {
		super(max, producerAmount, produceAmount, data);
	}
	
	public synchronized void read() {
		super.read();
	}
	
	public synchronized boolean write() {
		boolean r;
		r = super.write();
		return r;
	}
}