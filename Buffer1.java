import java.util.concurrent.locks.ReentrantLock;

//using locks
public class Buffer1 extends Buffer{
	
	ReentrantLock lock = new ReentrantLock();
	
	public Buffer1(int max, int producerAmount, int produceAmount) {
		super(max, producerAmount, produceAmount);
	}
	
	public void read() {
		lock.lock();
		super.read();
		lock.unlock();
	}
	
	public void write() {
		lock.lock();
		super.write();
		lock.unlock();
	}
}
