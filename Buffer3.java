import java.util.concurrent.atomic.AtomicInteger;

//using atomics
public class Buffer3 extends Buffer{
	static AtomicData[] data;
	AtomicInteger value = new AtomicInteger(0);
	public Buffer3(int max, int producerAmount, int produceAmount) {
		super(max, producerAmount, produceAmount, data);
		data = new AtomicData[max];
	}
	
	public void read() {
		//if the element is empty, tick the next one for checking, return then check again
		if(super.data[indexR % max] == null) { 
			indexR++;
			return;
		}
		System.out.print(super.data[indexR % max].value + " ");
		super.data[indexR % max] = null; //empty the element
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
		if(super.data[indexW % max]!= null) {
			indexW++;
			return false;
		}
		super.data[indexW % max] = new AtomicData(value.incrementAndGet()); 
		indexW++;
		return true;
		
	}
}
/*
 * TODO:
 * some thread doesn't die still; consumers?
 * still some duplicates despite using atomic value 
 * Example: [..] 486 487 488 489 490 491 491 493 493 495 495 497 497 499 499
 * numbers in between is skipped, 492, 494, 496, 498, 500.
 */

