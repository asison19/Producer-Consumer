import java.util.concurrent.atomic.AtomicInteger;

//using atomics
public class Buffer3 extends Buffer{
	static AtomicData[] data;
	AtomicInteger value = new AtomicInteger(1);
	public Buffer3(int max, int producerAmount, int produceAmount) {
		super(max, producerAmount, produceAmount, data);
		data = new AtomicData[max];
	}
	
	public void read() {
		if(data[indexR % max] == null) { 
			indexR++;
			return;
		}
		System.out.print(data[indexR % max].value + " ");
		data[indexR % max] = null; //empty the element
		indexR++;
		amountRead++;
		try {
			Thread.sleep(sleepAmount); //sleep 1 second
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(amountRead == totalValues) {
			isDone = true;
		}
	}
	
	public boolean write() {
		if(data[indexW % max]!= null) {
			indexW++;
			return false;
		}

		data[indexW % max] = new AtomicData(value.get());
		indexW++;
		value.incrementAndGet();
		return true; //TODO gets stuck here?
	}
}

