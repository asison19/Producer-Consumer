import java.util.concurrent.locks.ReentrantLock;

//using locks
public class Buffer1 extends Buffer{
	
	ReentrantLock lock = new ReentrantLock();
	static Data[] data;
	
	public Buffer1(int max, int producerAmount, int produceAmount) {
		super(max, producerAmount, produceAmount, data);
	}
	
	public void read() {
		lock.lock();
		super.read();
		lock.unlock();
	}
	
	public boolean write() {
		boolean r;
		lock.lock();
		r = super.write();
		lock.unlock();
		return r;
	}
}